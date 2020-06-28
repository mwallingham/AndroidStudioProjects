package com.example.higherorlower;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int trueVal;

    public void generateNumber() {

        Random rand = new Random();
        trueVal = rand.nextInt(20) + 1;
    }


    public void guess(View view) {

        EditText amountEditText = findViewById(R.id.editText);

        int guess = Integer.parseInt(amountEditText.getText().toString());

        if (guess > trueVal) {

            Toast.makeText(this, "Too High!" , Toast.LENGTH_LONG).show();

        } else if (guess < trueVal) {

            Toast.makeText(this, "Too Low!" , Toast.LENGTH_LONG).show();

        } else {

            Toast.makeText(this, "Correct! Try again!" , Toast.LENGTH_LONG).show();
            generateNumber();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        generateNumber();

    }
}
