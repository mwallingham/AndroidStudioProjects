package com.example.interactivitydemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void switchImage(View view) {
        Log.i("Login Good", "Switching");
        ImageView image = (ImageView) findViewById(R.id.ladsvision);
        image.setImageResource(R.drawable.the_bois);
    }

    public void login(View view) {

        EditText nameEditText = findViewById(R.id.nameEditText);
        EditText passwordEditText = findViewById(R.id.passwordEditText);

        Log.i("Info", "Pressed");
        Log.i("Username", nameEditText.getText().toString());
        Log.i("Password", passwordEditText.getText().toString());

        String login_string = "Login Successful: Welcome " + nameEditText.getText().toString();

        Toast.makeText(this, login_string , Toast.LENGTH_SHORT).show();

        switchImage(view);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
