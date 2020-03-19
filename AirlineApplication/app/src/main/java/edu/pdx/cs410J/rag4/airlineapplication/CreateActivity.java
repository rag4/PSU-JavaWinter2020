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
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import edu.pdx.cs410J.AirportNames;

public class CreateActivity extends AppCompatActivity {

    private EditText airlineName;

    private EditText flightNumber;
    private EditText source;
    private EditText departure;
    private EditText destination;
    private EditText arrival;

    private Airline airline;
    private Flight flight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        airlineName = findViewById(R.id.airlineName);

        flightNumber = findViewById(R.id.flightNumber);
        source = findViewById(R.id.source);
        departure = findViewById(R.id.depart);
        destination = findViewById(R.id.dest);
        arrival = findViewById(R.id.arrive);

        airlineClick();
        flightClick();
        printClick();
        prettyClick();
        xmlClick();
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
                        ArrayAdapter<Flight> adapter = new PrettyFlightAdapter(CreateActivity.this);
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
                        ArrayAdapter<Flight> adapter = new FlightAdapter(CreateActivity.this);
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

    private void flightClick() {
        Button createFlight = findViewById(R.id.flightButton);
        createFlight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String error = "";

                try {
                    if (airlineName.getText().length() == 0) {
                        error = "AIRLINE: NOTHING INPUTTED";
                        throw new IllegalArgumentException();
                    }
                    //FLIGHT NUMBER
                    int number = Integer.parseInt(flightNumber.getText().toString());
                    if (flightNumber.getText().length() == 0) {
                        error = "FLIGHT NUMBER: NOTHING INPUTTED";
                        throw new IllegalArgumentException();
                    }

                    //SOURCE
                    String src = source.getText().toString().toUpperCase();
                    if (source.getText().length() == 0) {
                        error = "SOURCE: NOTHING INPUTTED";
                        throw new IllegalArgumentException();
                    }
                    if (src.length() < 3) { // if src has no or too few letters
                        error = "SOURCE: THE LENGTH IS TOO SMALL.";
                        throw new IllegalArgumentException();
                    }
                    if (src.length() > 3) { // if src has too many letters
                        error = "SOURCE: THE LENGTH IS TOO BIG.";
                        throw new IllegalArgumentException();
                    }
                    if (Pattern.compile("[^a-zA-Z]").matcher(src).find()) { // if src contains something other than a letter
                        error = "SOURCE: IT  CONTAINS AN ILLEGAL CHARACTER.";
                        throw new IllegalArgumentException();
                    }
                    if (AirportNames.getName(src.toUpperCase()) == null) {
                        error = "SOURCE: DOES NOT CORRESPOND TO AN EXISTING AIRPORT. ";
                        throw new IllegalArgumentException();
                    }

                    //DEPART
                    String depart = departure.getText().toString();
                    if (departure.getText().length() == 0) {
                        error = "DEPART: NOTHING INPUTTED (MM/DD/YYYY HH:MM aa)";
                        throw new IllegalArgumentException();
                    }
                    if (depart.charAt(1) == ('/')) {
                        depart = "0" + depart;
                    }
                    if (depart.charAt(4) == ('/')) {
                        depart = depart.substring(0, 3) + "0" + (depart.substring(3));
                    }
                    // insert a 0 if time format: h:mm
                    if (depart.charAt(12) == ':') {
                        depart = depart.substring(0, 11) + "0" + (depart.substring(11));
                    }
                    if (Integer.parseInt(depart.substring(11, 13)) > 12) {
                        error = "DEPART: PLEASE DO NOT PUT 24-HOUR TIME (MM/DD/YYYY HH:MM aa)";
                        throw new IllegalArgumentException();
                    }
                    if (depart.length() < 19) { // if depart is smaller than expected
                        error = "DEPART: THE LENGTH IS TOO SMALL (MM/DD/YYYY HH:MM aa)";
                        throw new IllegalArgumentException();
                    }
                    if (depart.length() > 19) { // if depart is bigger than expected
                        error = "DEPART: THE LENGTH IS TOO BIG (MM/DD/YYYY HH:MM aa)";
                        throw new IllegalArgumentException();
                    }
                    if (depart.substring(0, 15).contains("[a-zA-Z]+")) { // if depart contains letters
                        error = "DEPART: IT SHOULDN'T CONTAIN LETTERS.";
                        throw new IllegalArgumentException();
                    }
                    for (int i = 0; i <= 18; i++) { // check validity of certain strings
                        if (i == 2 || i == 5) { // check for proper backslash
                            if (depart.charAt(i) != '/') {
                                error = "DEPART: FORMAT IS WRONG. CANNOT FIND BACKSLASH IN CORRECT POSITION (MM/DD/YYYY HH:MM aa)";
                                throw new IllegalArgumentException();
                            } else {
                                i++;
                            }
                        }
                        if (i == 10 || i == 16) { // check for proper whitespace
                            if (!Character.isWhitespace(depart.charAt(i))) {
                                error = "DEPART: FORMAT IS WRONG. CANNOT FIND WHITESPACE IN CORRECT POSITION (MM/DD/YYYY HH:MM aa)";
                                throw new IllegalArgumentException();
                            } else {
                                i++;
                            }
                        }
                        if (i == 13) { // check for proper colon
                            if (depart.charAt(i) != ':') {
                                error = "DEPART: FORMAT IS WRONG. CANNOT FIND COLON IN CORRECT POSITION (MM/DD/YYYY HH:MM aa)";
                                throw new IllegalArgumentException();
                            } else {
                                i++;
                            }
                        }
                        if (i == 17) {
                            if (depart.toUpperCase().charAt(i) != 'A' && depart.toUpperCase().charAt(i) != 'P') {
                                error = "DEPART: FORMAT IS WRONG. YOUR AM/PM ARE MALFORMATTED (MM/DD/YYYY HH:MM aa)";
                                throw new IllegalArgumentException();
                            } else {
                                i++;
                            }
                        }
                        if (i == 18) {
                            if (depart.toUpperCase().charAt(i) != 'M') {
                                error = "DEPART: FORMAT IS WRONG. YOUR AM/PM ARE MALFORMATTED (MM/DD/YYYY HH:MM aa)";
                                throw new IllegalArgumentException();
                            } else {
                                break;
                            }
                        }
                        if (!Character.isDigit(depart.charAt(i))) { // check if digit
                            error = "DEPART: ILLEGAL CHARACTER, IT MUST BE A DIGIT.";
                            throw new IllegalArgumentException();
                        }
                    }
                    DateFormat departFormat = new SimpleDateFormat("MM/dd/yyyy h:mm a");
                    Date departSample = departFormat.parse(depart);

                    //DEST
                    String dest = destination.getText().toString().toUpperCase();
                    if (destination.getText().length() == 0) {
                        error = "DEST: NOTHING INPUTTED ";
                        throw new IllegalArgumentException();
                    }
                    if (dest.length() < 3) { // if dest has no or too few letters
                        error = "DEST: THE LENGTH IS TOO SMALL.";
                        throw new IllegalArgumentException();
                    }
                    if (dest.length() > 3) { // if dest has too many letters
                        error = "DEST: THE LENGTH IS TOO BIG.";
                        throw new IllegalArgumentException();
                    }
                    if (Pattern.compile("[^a-zA-Z]").matcher(dest).find()) { // if dest contains something other than a letter
                        error = "DEST: IT  CONTAINS AN ILLEGAL CHARACTER.";
                        throw new IllegalArgumentException();
                    }
                    if (AirportNames.getName(dest.toUpperCase()) == null) {
                        error = "DEST: DOES NOT CORRESPOND TO AN EXISTING AIRPORT. ";
                        throw new IllegalArgumentException();
                    }

                    //ARRIVE
                    String arrive = arrival.getText().toString();
                    if (arrival.getText().length() == 0) {
                        error = "ARRIVE: NOTHING INPUTTED (MM/DD/YYYY HH:MM aa)";
                        throw new IllegalArgumentException();
                    }
                    // insert a 0 if date format: m/dd/yyyy
                    if (arrive.charAt(1) == ('/')) {
                        arrive = "0" + arrive;
                    }
                    // insert a 0 if date format: mm/d/yyyy OR m/d/yyyy --> mm/d/yyyy
                    if (arrive.charAt(4) == ('/')) {
                        arrive = arrive.substring(0, 3) + "0" + (arrive.substring(3));
                    }
                    // insert a 0 if time format: h:mm
                    if (arrive.charAt(12) == ':') {
                        arrive = arrive.substring(0, 11) + "0" + (arrive.substring(11));
                    }

                    if (Integer.parseInt(arrive.substring(11, 13)) > 12) {
                        error = "ARRIVAL: PLEASE DO NOT PUT 24-HOUR TIME (MM/DD/YYYY HH:MM aa)";
                        throw new IllegalArgumentException();
                    }
                    if (arrive.length() < 19) { // if depart is smaller than expected
                        error = "ARRIVAL: THE LENGTH IS TOO SMALL (MM/DD/YYYY HH:MM aa)";
                        throw new IllegalArgumentException();

                    }
                    if (arrive.length() > 19) { // if depart is bigger than expected
                        error = "ARRIVAL: THE LENGTH IS TOO BIG (MM/DD/YYYY HH:MM aa)";
                        throw new IllegalArgumentException();
                    }
                    if (arrive.substring(0, 15).contains("[a-zA-Z]+")) { // if depart contains letters
                        error = "ARRIVAL: IT SHOULDN'T CONTAIN LETTERS.";
                        throw new IllegalArgumentException();
                    }
                    for (int i = 0; i <= 18; i++) { // check validity of certain strings
                        if (i == 2 || i == 5) { // check for proper backslash
                            if (arrive.charAt(i) != '/') {
                                error = "ARRIVAL: FORMAT IS WRONG. CANNOT FIND BACKSLASH IN CORRECT POSITION (MM/DD/YYYY HH:MM aa)";
                                throw new IllegalArgumentException();
                            } else {
                                i++;
                            }
                        }

                        if (i == 10 || i == 16) { // check for proper whitespace
                            if (!Character.isWhitespace(arrive.charAt(i))) {
                                error = "ARRIVAL: FORMAT IS WRONG. CANNOT FIND WHITESPACE IN CORRECT POSITION (MM/DD/YYYY HH:MM aa)";
                                throw new IllegalArgumentException();
                            } else {
                                i++;
                            }
                        }
                        if (i == 13) { // check for proper colon
                            if (arrive.charAt(i) != ':') {
                                error = "ARRIVAL: FORMAT IS WRONG. CANNOT FIND COLON IN CORRECT POSITION (MM/DD/YYYY HH:MM aa)";
                                throw new IllegalArgumentException();
                            } else {
                                i++;
                            }
                        }
                        if (i == 17) {
                            if (arrive.toUpperCase().charAt(i) != 'A' && arrive.toUpperCase().charAt(i) != 'P') {
                                error = "ARRIVAL: FORMAT IS WRONG. YOUR AM/PM ARE MALFORMATTED (MM/DD/YYYY HH:MM aa)";
                                throw new IllegalArgumentException();
                            } else {
                                i++;
                            }
                        }
                        if (i == 18) {
                            if (arrive.toUpperCase().charAt(i) != 'M') {
                                error = "ARRIVAL: FORMAT IS WRONG. YOUR AM/PM ARE MALFORMATTED (MM/DD/YYYY HH:MM aa)";
                                throw new IllegalArgumentException();
                            } else {
                                break;
                            }
                        }
                        if (!Character.isDigit(arrive.charAt(i))) { // check if digit
                            error = "ARRIVAL: ILLEGAL CHARACTER, IT MUST BE A DIGIT ";
                            throw new IllegalArgumentException();
                        }
                    }
                    DateFormat arriveFormat = new SimpleDateFormat("MM/dd/yyyy h:mm a");
                    Date arriveSample = arriveFormat.parse(arrive);
                    assert arriveSample != null;
                    if (!arriveSample.after(departSample)) {
                        error = "ARRIVAL: ARRIVAL DATE AND TIME CANNOT BE BEFORE DEPART DATE AND TIME.";
                        throw new IllegalArgumentException();
                    }

                    //CREATE FLIGHT
                    String name = airlineName.getText().toString();
                    if (!getAirlineFile(name).exists()) {
                        Toast.makeText(getApplicationContext(), "FAILURE: You are adding a Flight to a nonexistent airline", Toast.LENGTH_LONG).show();
                    } else {
                        loadAirlineFile(name);
                        flight = new Flight(number, src, depart, dest, arrive);
                        airline.addFlight(flight);
                        addFlightToAirlineFile(name, airline);
                        //SUCCESS
                        Toast.makeText(getApplicationContext(), "SUCCESS: Flight has been added", Toast.LENGTH_LONG).show();

                        //CLEAR
                        flightNumber.getText().clear();
                        source.getText().clear();
                        departure.getText().clear();
                        destination.getText().clear();
                        arrival.getText().clear();
                    }


                } catch (IllegalArgumentException | ParseException | IOException e) {
                    //FAILURE
                    Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private void airlineClick() {
        Button createAirline = findViewById(R.id.airlineButton);
        createAirline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String error = "";
                try {
                    String name = airlineName.getText().toString();
                    if (airlineName.getText().length() == 0) {
                        error = "AIRLINE: NOTHING INPUTTED";
                        throw new IllegalArgumentException();
                    }

                    //CREATION
                    if (!getAirlineFile(name).exists()) {
                        airline = new Airline(name);
                        saveToAirlineFile(name);
                        //SUCCESS
                        Toast.makeText(getApplicationContext(), "SUCCESS: Airline has been created, try adding a flight", Toast.LENGTH_LONG).show();
                        LinearLayout linear = findViewById(R.id.printLinear);
                        linear.setVisibility(View.VISIBLE);
                    } else {
                        loadAirlineFile(name);
                        //SUCCESS
                        Toast.makeText(getApplicationContext(), "NOTICE: Airline already exists, try adding to this flight", Toast.LENGTH_LONG).show();
                        LinearLayout linear = findViewById(R.id.printLinear);
                        linear.setVisibility(View.VISIBLE);
                    }
                } catch (IllegalArgumentException | IOException | ParseException e) {
                    Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private File getAirlineFile(String airlineName) {
        File dir = getFilesDir();
        return new File(dir, airlineName + ".txt");
    }

    private void saveToAirlineFile(String airlineName) throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter(getAirlineFile(airlineName)));
        writer.println(airlineName);
        writer.flush();
        writer.close();
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


    private void addFlightToAirlineFile(String airlineName, Airline airline) throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter(getAirlineFile(airlineName)));
        writer.println(airlineName);
        for (Flight f : airline.flights) {
            writer.println(f.getNumber() + " " + f.getSource() + " " + f.getDepartureString() + " " + f.getDestination() + " " + f.getArrivalString());
        }
        writer.flush();
        writer.close();
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
}


