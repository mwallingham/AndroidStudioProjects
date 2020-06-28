package com.example.egg_timer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar timeSetting;
    TextView timerText;
    Button resetButton;
    Button startButton;
    boolean counterIsActive = false;
    int timeRequested;
    CountDownTimer countDownTimer;


    public String setTime (int timeRemainingSeconds) {

        int seconds = timeRemainingSeconds % 60;
        int minutes = (int) timeRemainingSeconds / 60;

        return String.format("%d:%02d", minutes, seconds);

    }

    public void timerFreezeOut (View view) {

        counterIsActive = true;
        timeSetting.setEnabled(false);
        startButton.setEnabled(false);
        resetButton.setEnabled(true);

        countDownTimer = new CountDownTimer(timeRequested * 1000 + 100, 1000) {

            public void onTick(long millisecondsUntilDone) {

                timerText.setText(setTime((int) millisecondsUntilDone / 1000));
                timeSetting.setProgress((int) millisecondsUntilDone / 1000);

            }

            public void onFinish() {

                startButton.setEnabled(true);
                resetButton.setEnabled(false);
                timeSetting.setEnabled(true);
                timerText.setText(setTime(timeRequested));

            }

        }.start();

    }

    public void timerReset (View view) {

        timeSetting.setEnabled(true);
        countDownTimer.cancel();
        resetButton.setEnabled(false);
        startButton.setEnabled(true);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        int maxTime = 600;
        int initialProgress = 30;

        timeRequested = initialProgress;
        timerText = findViewById(R.id.timerTextView);
        timerText.setText(setTime(initialProgress));

        timeSetting = (SeekBar) findViewById(R.id.timeSettingSeekBar);
        timeSetting.setProgress(initialProgress);
        timeSetting.setMax(maxTime);
        timeSetting.incrementProgressBy(15);

        resetButton = (Button) findViewById(R.id.resetButton);
        resetButton.setEnabled(false);

        startButton = (Button) findViewById(R.id.startButton);

        timeSetting.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                timerText.setText(setTime(progress));


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                timeRequested = seekBar.getProgress();
            }
        });



    }
}
