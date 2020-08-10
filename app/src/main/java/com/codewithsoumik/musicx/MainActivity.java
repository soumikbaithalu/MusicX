package com.codewithsoumik.musicx;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    MediaPlayer musicPlayer;
    AudioManager audioManger;
    public void play(View view)
    {
        musicPlayer.start();
    }
    public void pause(View view)
    {
        musicPlayer.pause();
    }
    public void stop(View view)
    {
        musicPlayer.stop();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        musicPlayer =MediaPlayer.create(this,R.raw.summer);

        audioManger = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int maxVol = audioManger.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int curVol = audioManger.getStreamVolume(AudioManager.STREAM_MUSIC);

        SeekBar seekVol = findViewById(R.id.seekVol);
        seekVol.setMax(maxVol);
        seekVol.setProgress(curVol);

        seekVol.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {


                audioManger.setStreamVolume(AudioManager.STREAM_MUSIC, progress,0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

         final SeekBar seekprog = findViewById(R.id.seekBar2);
         seekprog.setMax(musicPlayer.getDuration());

         new Timer().scheduleAtFixedRate(new TimerTask() {

             @Override
             public void run() {
                 seekprog.setProgress(musicPlayer.getCurrentPosition());
             }
         },0,900);
         seekprog.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
             @Override
             public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                 musicPlayer.seekTo(progress);
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
