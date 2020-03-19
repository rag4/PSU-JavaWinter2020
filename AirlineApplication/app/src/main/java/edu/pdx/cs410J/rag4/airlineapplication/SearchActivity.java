package edu.pdx.cs410J.rag4.airlineapplication;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;

public class SearchActivity extends AppCompatActivity {

    private EditText airlineName;
    private Airline airline;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        airlineName = findViewById(R.id.airlineName);

        airlineClick();
        printClick();
        prettyClick();
        xmlClick();
    }

    private void printClick() {
        Button printAirline = findViewById(R.id.printButton);
        printAirline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String error = "";
                String name = airlineName.getText().toString();
                try {
                    if (airlineName.getText().length() == 0) {
                        error = "SOURCE: NOTHING INPUTTED";
                        throw new IllegalArgumentException();
                    } else {
                        loadAirlineFile(name);
                        setContentView(R.layout.list_print);
                        ListView listView = findViewById(R.id.list_view);
                        ArrayAdapter<Flight> adapter = new FlightAdapter(SearchActivity.this);
                        for (Flight f : airline.getFlights()) {
                            adapter.add(f);
                        }
                        listView.setAdapter(adapter);
                    }
                } catch (IllegalArgumentException | IOException | ParseException e) {
                    Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private void prettyClick() {
        Button prettyAirline = findViewById(R.id.prettyButton);
        prettyAirline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String error = "";
                String name = airlineName.getText().toString();
                try {
                    if (airlineName.getText().length() == 0) {
                        error = "SOURCE: NOTHING INPUTTED";
                        throw new IllegalArgumentException();
                    } else {
                        loadAirlineFile(name);
                        setContentView(R.layout.list_print);
                        ListView listView = findViewById(R.id.list_view);
                        ArrayAdapter<Flight> adapter = new PrettyFlightAdapter(SearchActivity.this);
                        for (Flight f : airline.getFlights()) {
                            adapter.add(f);
                        }
                        listView.setAdapter(adapter);
                    }
                } catch (IllegalArgumentException | IOException | ParseException e) {
                    Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void xmlClick() {
        Button xmlAirline = findViewById(R.id.xmlButton);
        xmlAirline.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                String error = "";
                String name = airlineName.getText().toString();
                try {
                    if (airlineName.getText().length() == 0) {
                        error = "SOURCE: NOTHING INPUTTED";
                        throw new IllegalArgumentException();
                    } else {
                        loadAirlineFile(name);
                        setContentView(R.layout.list_print);
                        TextView textView = findViewById(R.id.xmlText);

                        String result = "";
                        XmlDumper xmlDumper = new XmlDumper(result);
                        xmlDumper.dump(airline);


                        textView.setText(xmlDumper.result);
                    }
                } catch (IllegalArgumentException | IOException | ParseException e) {
                    Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    private void airlineClick() {
        Button createAirline = (Button) findViewById(R.id.airlineButton);
        createAirline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String error = "";
                try {
                    String name = airlineName.getText().toString();
                    if (airlineName.getText().length() == 0) {
                        error = "SOURCE: NOTHING INPUTTED";
                        throw new IllegalArgumentException();
                    }

                    //CREATION
                    if (!getAirlineFile(name).exists()) {
                        Toast.makeText(getApplicationContext(), "AIRLINE DOES NOT EXISTS", Toast.LENGTH_LONG).show();
                    } else {
                        loadAirlineFile(name);
                        //SUCCESS
                        Toast.makeText(getApplicationContext(), "AIRLINE FOUND", Toast.LENGTH_LONG).show();
                        LinearLayout linear = findViewById(R.id.printLinear);
                        linear.setVisibility(View.VISIBLE);
                    }
                } catch (IllegalArgumentException | IOException | ParseException e) {
                    Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    class FlightAdapter extends ArrayAdapter<Flight> {

        FlightAdapter(@NonNull Context context) {
            super(context, R.layout.flight_view);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View flightView, @NonNull ViewGroup parent) {
            if (flightView == null) {
                flightView = getLayoutInflater().inflate(R.layout.flight_view, parent, false);
            }

            Flight flight = getItem(position);

            TextView number = flightView.findViewById(R.id.flightNumber);
            number.setText(flight.getNumber() + " " + flight.getSource() + " " + flight.getDepartureString() + " " + flight.getDestination() + " " + flight.getArrivalString());

            return flightView;
        }

    }
    class PrettyFlightAdapter extends ArrayAdapter<Flight> {

        PrettyFlightAdapter(@NonNull Context context) {
            super(context, R.layout.flight_view);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View flightView, @NonNull ViewGroup parent) {
            if (flightView == null) {
                flightView = getLayoutInflater().inflate(R.layout.flight_view, parent, false);
            }

            Flight flight = getItem(position);

            TextView number = flightView.findViewById(R.id.flightNumber);
            number.setText("Flight Number: " + flight.getNumber());

            TextView departs = flightView.findViewById(R.id.departs);
            departs.setText("You are departing from airport  " + flight.getSource() + " in " + flight.getSRCName() + " and your flight departs at: " + flight.getDepartureString());

            TextView arrives = flightView.findViewById(R.id.arrives);
            arrives.setText("You will arrive at airport  " + flight.getDestination() + " in " + flight.getDESTName() + " and your flight departs at: " + flight.getArrivalString());

            TextView duration = flightView.findViewById(R.id.duration);
            duration.setText("Your flight duration will take  " + flight.getDifference() + " minutes.");

            return flightView;
        }

    }
    private File getAirlineFile(String airlineName) {
        File dir = getFilesDir();
        return new File(dir, airlineName + ".txt");
    }

    private void loadAirlineFile(String airlineName) throws IOException, ParseException {
        String[] newCommandArgs; // parse words into text file, and classify them into this String array
        BufferedReader reader = new BufferedReader(new FileReader(getAirlineFile(airlineName)));
        String line = reader.readLine();
        String name = line;
        this.airline = new Airline(name);
        this.airlineName.setText(name);

        while ((line = reader.readLine()) != null) {
            newCommandArgs = line.split(" ");

            String flightNumberCheck = newCommandArgs[0]; //easy string variable to get the flight number
            if (!flightNumberCheck.matches("[0-9]+")) {
                throw new IllegalArgumentException();
            }

            if (newCommandArgs[2].charAt(1) == ('/')) {
                newCommandArgs[2] = "0" + newCommandArgs[2];
            }
            if (newCommandArgs[2].charAt(4) == ('/')) {
                newCommandArgs[2] = newCommandArgs[2].substring(0, 3) + "0" + (newCommandArgs[2].substring(3));
            }
            if (newCommandArgs[3].indexOf(':') == 1) {
                newCommandArgs[3] = "0" + newCommandArgs[3];
            }

            if (newCommandArgs[6].charAt(1) == ('/')) {
                newCommandArgs[6] = "0" + newCommandArgs[6];
            }
            if (newCommandArgs[6].charAt(4) == ('/')) {
                newCommandArgs[6] = newCommandArgs[6].substring(0, 3) + "0" + (newCommandArgs[6].substring(3));
            }
            if (newCommandArgs[7].indexOf(':') == 1) {
                newCommandArgs[7] = "0" + newCommandArgs[7];
            }
            Flight flight = new Flight(Integer.parseInt(newCommandArgs[0]), newCommandArgs[1], newCommandArgs[2] + " " + newCommandArgs[3] + " " + newCommandArgs[4],
                    newCommandArgs[5], newCommandArgs[6] + " " + newCommandArgs[7] + " " + newCommandArgs[8]);
            this.airline.addFlight(flight); // add this newly created flight with initialized values into airline
        }
    }
}
