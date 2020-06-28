package com.example.listviewdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.ArrayList;

import static java.util.Arrays.asList;

public class MainActivity extends AppCompatActivity {

    ListView timesTablesList;

    public void generateTimesTable(int timesTableNumber){

        ArrayList<String> timesTables = new ArrayList<String>();

        for (int i=0; i <= 10 ; i++) {

            timesTables.add(i, Integer.toString((i+1) * timesTableNumber));

        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, timesTables);

        timesTablesList.setAdapter(arrayAdapter);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        final SeekBar timesTable = (SeekBar) findViewById(R.id.tableSeekBar);
        timesTablesList = (ListView) findViewById(R.id.timesTableListView);

        timesTable.setMax(20);
        timesTable.incrementProgressBy(1);
        timesTable.setProgress(1);

        generateTimesTable(1);

        timesTable.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                int min = 1;
                int timesTableNumber;

                if (progress < min) {

                    timesTableNumber = min;
                    timesTable.setProgress(min);

                } else {

                    timesTableNumber = progress;

                }

                generateTimesTable(timesTableNumber);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
