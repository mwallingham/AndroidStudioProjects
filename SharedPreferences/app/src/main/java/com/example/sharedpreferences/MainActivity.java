package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.sharedpreferences", Context.MODE_PRIVATE);

        /*ArrayList<String> friends = new ArrayList<String>();

        friends.add("Tanmay");
        friends.add("Harrison");
        friends.add("Harry");

        try {
           sharedPreferences.edit().putString("Friends", ObjectSerializer.serialize(friends)).apply();
           Log.i("Friends", ObjectSerializer.serialize(friends));

        } catch (Exception e) {
            e.printStackTrace();
        }
*/
        ArrayList<String> newFriends = new ArrayList<String>();

        try {
            newFriends = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("Friends", ObjectSerializer.serialize(new ArrayList<String>())));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.i("Retrieved", newFriends.toString());

        //sharedPreferences.edit().p

        //sharedPreferences.edit().putString("username",  "Mark").apply();

        //String userName = sharedPreferences.getString("username", "No name");

        //Log.i("This is the username: ", userName);

    }
}
