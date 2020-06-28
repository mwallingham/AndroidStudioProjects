package com.example.count_3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    // 0:yellow   1:red   2:empty

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

    int activePlayer = 0;

    boolean gameActive = true;

    String winner;

    public void dropIn (View view) {

        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 2 && gameActive) {

            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-1500);

            if (activePlayer == 0) {

                counter.setImageResource(R.drawable.yellow);

                counter.animate().translationYBy(1500).setDuration(300);

                activePlayer = 1;

            } else {

                counter.setImageResource(R.drawable.red);

                counter.animate().translationYBy(1500).setDuration(300);

                activePlayer = 0;

            }

            for (int[] winState : winningPositions) {

                if (gameState[winState[0]] == gameState[winState[1]] && gameState[winState[1]] == gameState[winState[2]] && gameState[winState[0]] != 2) {

                    // Someone has won

                    gameActive = false;

                    if (activePlayer == 1) {

                        winner = "Yellow";

                    } else {

                        winner = "Red";

                    }

                    TextView winnerTextView= (TextView) findViewById(R.id.winnerTextView);

                    winnerTextView.setText("Total victory for: " + winner);
                    winnerTextView.setVisibility(View.VISIBLE);


                }

            }
        } else if (gameActive == false){

            Toast.makeText(this, "Game Over!", Toast.LENGTH_SHORT).show();

        } else {

            Toast.makeText(this, "Space already taken!", Toast.LENGTH_SHORT).show();

        }
    }


    public void playAgain(View view) {

        TextView winnerTextView= (TextView) findViewById(R.id.winnerTextView);

        if (winnerTextView.getVisibility() == View.VISIBLE) {
            winnerTextView.setVisibility(View.INVISIBLE);
        }

        androidx.gridlayout.widget.GridLayout gridLayout = findViewById(R.id.gridLayout);

        Log.i("Info", "Read grid and text view");

        for (int i=0 ; i<gridLayout.getChildCount(); i++) {

            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);

        }

        for (int i=0; i<gameState.length; i++) {

            gameState[i] = 2;
        }

        activePlayer = 0;
        gameActive = true;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
