package com.example.sounds_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

import static android.media.AudioManager.STREAM_MUSIC;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    AudioManager audioManager;

    public void play(View view) {

        mediaPlayer.start();

    }

    public void pause(View view) {

        mediaPlayer.pause();

    }

    public void reset(View view) {

        mediaPlayer.reset();
        mediaPlayer = MediaPlayer.create(this, R.raw.marbles);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

        int maxVolume = audioManager.getStreamMaxVolume(STREAM_MUSIC);
        int currentVolume = audioManager.getStreamVolume(STREAM_MUSIC);
        mediaPlayer = MediaPlayer.create(this, R.raw.marbles);

        SeekBar volumeControl = (SeekBar) findViewById(R.id.volumeSeekBar);
        final   SeekBar positionControl = (SeekBar) findViewById(R.id.scrubSeekBar);
        positionControl.setMax(mediaPlayer.getDuration());

        volumeControl.setMax(maxVolume);
        volumeControl.setProgress(currentVolume);

        volumeControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.i("Seekbar changed", Integer.toString(progress));
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



        positionControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int scrub, boolean fromUser) {
                Log.i("Info", Integer.toString(scrub));
                mediaPlayer.seekTo(scrub);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                mediaPlayer.pause();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.start();
            }
        });

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                positionControl.setProgress(mediaPlayer.getCurrentPosition());
            }
        }, 0, 500);
    }



}
