package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button goButton;
    Button gameEndGoButton;
    Button resetButton;
    Button answerButton0;
    Button answerButton1;
    Button answerButton2;
    Button answerButton3;

    CountDownTimer countDownTimer;

    TextView timerTextView;
    TextView sumTextView;
    TextView scoreTextView;
    TextView finalScoreTextView;

    int gameLength = 30;
    int answer;
    int totalQuestions = 0;
    int answersCorrect = 0;

    Random rand = new Random();

    public String stringScore () {

        String score = String.format("%d/%d", answersCorrect, totalQuestions);

        return score;

    }

    public void generateSum () {

        androidx.gridlayout.widget.GridLayout gridLayout = findViewById(R.id.gridLayout);

        int firstNum = rand.nextInt(40) + 1;
        int secondNum = rand.nextInt(40) + 1;

        answer = firstNum + secondNum;

        sumTextView.setText(String.format("%d + %d", firstNum, secondNum));

        int setCorrectPosition = rand.nextInt(4);

        for (int i = 0; i < 4; i++) {

            Button button = (Button) gridLayout.getChildAt(i);

            if (i == setCorrectPosition) {

                button.setText(Integer.toString(answer));

            } else {

                int dummyAnswer = rand.nextInt(80) + 1;

                if (dummyAnswer == answer) {

                    button.setText(Integer.toString(dummyAnswer + rand.nextInt(13) + 1));

                } else {

                    button.setText(Integer.toString(dummyAnswer));

                }
            }
        }
    }

    public void setTimerTextView (int timeRemainingSeconds) {

        int seconds = timeRemainingSeconds % 60;
        int minutes = (int) timeRemainingSeconds / 60;

        timerTextView.setText(String.format("%d:%02d", minutes, seconds));

    }

    public void startGame (View view) {

        totalQuestions = 0;
        answersCorrect = 0;

        goButton.setVisibility(View.INVISIBLE);
        gameEndGoButton.setVisibility(View.INVISIBLE);
        finalScoreTextView.setVisibility(View.INVISIBLE);
        resetButton.setVisibility(View.VISIBLE);
        scoreTextView.setVisibility(View.VISIBLE);

        resetButton.setVisibility(View.VISIBLE);
        answerButton0.setVisibility(View.VISIBLE);
        answerButton1.setVisibility(View.VISIBLE);
        answerButton2.setVisibility(View.VISIBLE);
        answerButton3.setVisibility(View.VISIBLE);

        scoreTextView.setText(stringScore());

        generateSum();

        countDownTimer = new CountDownTimer(gameLength*1000 +100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                setTimerTextView((int)millisUntilFinished/1000);

            }

            @Override
            public void onFinish() {

                finalScoreTextView.setText(stringScore());
                finalScoreTextView.setVisibility(View.VISIBLE);
                answerButton0.setVisibility(View.INVISIBLE);
                answerButton1.setVisibility(View.INVISIBLE);
                answerButton2.setVisibility(View.INVISIBLE);
                answerButton3.setVisibility(View.INVISIBLE);
                scoreTextView.setVisibility(View.INVISIBLE);

                sumTextView.setText("Go again?");

                resetButton.setVisibility(View.INVISIBLE);
                gameEndGoButton.setVisibility(View.VISIBLE);

            }
        }.start();

    }

    public void resetCalled (View view) {

        countDownTimer.cancel();

        resetButton.setVisibility(View.INVISIBLE);
        goButton.setVisibility(View.VISIBLE);
        answerButton0.setVisibility(View.INVISIBLE);
        answerButton1.setVisibility(View.INVISIBLE);
        answerButton2.setVisibility(View.INVISIBLE);
        answerButton3.setVisibility(View.INVISIBLE);

        setTimerTextView(gameLength);
        sumTextView.setText("Go again?");

    }

    public void checkAnswer (View view) {

        totalQuestions++;

        Button answerSelectedButton = (Button) view;

        int attempt = Integer.parseInt(answerSelectedButton.getText().toString());

        if (attempt==answer) {

            answersCorrect++;

        }

        scoreTextView.setText(stringScore());
        generateSum();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerTextView = findViewById(R.id.timerTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        sumTextView = findViewById(R.id.sumTextView);
        finalScoreTextView = findViewById(R.id.finalScoreTextView);

        goButton = findViewById(R.id.goButton);
        resetButton = findViewById(R.id.resetButton);
        gameEndGoButton = findViewById(R.id.gameEndGoButton);
        answerButton0 = findViewById(R.id.answerButton0);
        answerButton1 = findViewById(R.id.answerButton1);
        answerButton2 = findViewById(R.id.answerButton2);
        answerButton3 = findViewById(R.id.answerButton3);

        setTimerTextView(gameLength);
        sumTextView.setText("Ready?");


    }
}
