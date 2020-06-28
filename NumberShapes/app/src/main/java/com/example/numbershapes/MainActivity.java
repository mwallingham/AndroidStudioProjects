package com.example.numbershapes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    class Number {

        int number;

        public boolean isTriangular() {

            int x = 1;
            int triangularNumber = 1;

            while (triangularNumber <= number) {

                x++;
                triangularNumber = triangularNumber + x;

                if (triangularNumber == number) {

                    return true;

                }
            }

            return false;

        }


        public boolean isSquare() {

            int x = 1;

            while (x*x <= number) {

                if (x*x == number) {

                    return true;

                } else {

                    x++;

                }
            }

            return false;

        }

    }

    public void testNumber(View view) {

        Log.i("Info", "Button Pressed");

        EditText editText = (EditText) findViewById(R.id.editTextNumber);

        String message;

        if (editText.getText().toString().isEmpty()) {

            message = "Please enter a number!";

        } else {

            Number test = new Number();

            test.number = Integer.parseInt(editText.getText().toString());

            message = editText.getText().toString();

            if (test.isSquare() && test.isTriangular()) {

                message += " is square and triangular";

            } else if (test.isSquare()) {

                message += " is square";

            } else if (test.isTriangular()) {

                message += " is triangular";

            } else {

                message += " is neither square nor triangular";
            }
        }

        Toast.makeText(this, message, Toast.LENGTH_LONG).show();

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
