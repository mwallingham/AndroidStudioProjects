package com.example.locationsaver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    static ArrayList<String> locations = new ArrayList<String>();
    static ArrayList<LatLng> positions = new ArrayList<LatLng>();
    static ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.locationsaver", Context.MODE_PRIVATE);

        ArrayList<String> latitudes = new ArrayList<String>();
        ArrayList<String> longitudes = new ArrayList<String>();

        locations.clear();
        latitudes.clear();
        longitudes.clear();
        positions.clear();

        try {

            locations = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("places", ObjectSerializer.serialize(new ArrayList<String>())));
            latitudes = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("latitudes", ObjectSerializer.serialize(new ArrayList<String>())));
            longitudes = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("longitudes", ObjectSerializer.serialize(new ArrayList<String>())));

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (locations.size() > 0 && latitudes.size() > 0 && longitudes.size() > 0) {
            if (locations.size() == latitudes.size() && locations.size() == longitudes.size()){
                for (int i=0; i < locations.size(); i++) {
                    positions.add(new LatLng(Double.parseDouble(latitudes.get(i)), Double.parseDouble(longitudes.get(i))));
                }
            }
        } else {
            locations.add("Add a new place...");
            positions.add(new LatLng(0,0));
        }

        ListView locationListView = findViewById(R.id.locationListView);



        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, locations);

        locationListView.setAdapter(arrayAdapter);

        locationListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            Intent intent = new Intent (getApplicationContext(), MapActivity.class);
            intent.putExtra("Position", position);

            startActivity(intent);

            }
        });
    }
}

