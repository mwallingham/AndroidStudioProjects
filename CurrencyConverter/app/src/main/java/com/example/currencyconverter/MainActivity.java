package com.example.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void convert(View view) {

        EditText amountEditText = findViewById(R.id.amountEditText);

        double entered = Double.parseDouble(amountEditText.getText().toString());

        entered = entered * 1.3;

        String string = "$" + String.format("%.2f", entered);

        Toast.makeText(this, string , Toast.LENGTH_LONG).show();

        Log.i("Information", string);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
