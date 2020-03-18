package edu.pdx.cs410J.rag4.androidcalculator;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class ListViewExample extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_example);

        ListView listView = findViewById(R.id.list_view);

        ArrayAdapter<Flight> adapter = new FlightAdapter(this);
        adapter.add(new Flight(1, "PDX",  "1/1/2020 1:00 PM", "LAX","1/1/2020 4:00 PM"));
        adapter.add(new Flight(2, "PDX",  "1/1/2020 2:00 PM", "BOX","1/1/2020 10:00 PM"));
        listView.setAdapter(adapter);

    }

     class FlightAdapter extends ArrayAdapter<Flight> {

        public FlightAdapter(@NonNull Context context) {
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
            number.setText(getString(R.string.flight_number, flight.getNumber()));

            TextView departs = flightView.findViewById(R.id.departs);
            departs.setText("Departs " + flight.getSource() + " at " + flight.getDepartureString());

            return flightView;
        }
    }

}
