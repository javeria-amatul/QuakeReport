package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.Time;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.R.attr.resource;
import static com.example.android.quakereport.R.id.date;
import static com.example.android.quakereport.R.id.location_offset;

/**
 * Created by Javeria on 8/2/2017.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {


    private static final String LOCATION_SEPARATOR=" of ";
    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }


    public EarthquakeAdapter(Context context, List<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    /**
     * Return the formatted magnitude string showing 1 decimal place (i.e. "3.2")
     * from a decimal magnitude value.
     */
    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }

    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {

        View listView=convertView;

        if(listView==null){
            listView=LayoutInflater.from(getContext()).inflate(R.layout.earthquake_list_item, parent, false);
        }



        Earthquake currentQuake=getItem(position);

        TextView magnitude=(TextView) listView.findViewById(R.id.magnitude);
        // Format the magnitude to show 1 decimal place
        String formattedMagnitude = formatMagnitude(currentQuake.getmMagnitude());
        magnitude.setText(formattedMagnitude);

        String offsetLocation;
        String primaryLocation;

        String originalLocation= currentQuake.getmLocation();
        if(originalLocation.contains(LOCATION_SEPARATOR)){
            String[] parts= originalLocation.split(LOCATION_SEPARATOR);
            offsetLocation=parts[0]+LOCATION_SEPARATOR;
            primaryLocation=parts[1];

        }
        else{
            offsetLocation=getContext().getString(R.string.near_the);
            primaryLocation=originalLocation;
        }

        TextView locationOffset= (TextView) listView.findViewById(R.id.location_offset);
        locationOffset.setText(offsetLocation);

        TextView primaryLoc= (TextView) listView.findViewById(R.id.primary_location);
        primaryLoc.setText(primaryLocation);


        // Create a new Date object from the time in milliseconds of the earthquake
        Date dateObject=new Date(currentQuake.getmDate());
        // Find the TextView with view ID date
        TextView dateView=(TextView) listView.findViewById(R.id.date);
        // Format the date string (i.e. "Mar 3, 1984")
        String formattedDate=formatDate(dateObject);
        // Display the date of the current earthquake in that TextView
        dateView.setText(formattedDate);

        // Find the TextView with view ID date
        TextView timeView=(TextView) listView.findViewById(R.id.time);
        // Format the time string (i.e. "4:30PM")
        String formattedTime=formatTime(dateObject);
        // Display the time of the current earthquake in that TextView
        timeView.setText(formattedTime);

        return listView;
    }
}
