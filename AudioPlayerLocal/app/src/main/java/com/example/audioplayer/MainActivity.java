package com.example.audioplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    private TextView playerPosition, playerDuration;
    private SeekBar seekBar;
    private ImageView btRew, btPause, btPlay, btFf, image;
    MediaPlayer mediaPlayer;
    Handler handler = new Handler();
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        AnhXa();
        //Khoi tao media player
        mediaPlayer = MediaPlayer.create(this,R.raw.music);
        //khoi tao runnable
        runnable = new Runnable() {
            @Override
            public void run() {
                // Set progress on seek bar
                seekBar.setProgress(mediaPlayer.getCurrentPosition());
                // handler post delay for 0.5s
                handler.postDelayed(this,500);


            }
        };
        // Lay duration cua media player
        int duration = mediaPlayer.getDuration();
        // convert mili giay sang phut giay
        String strDuration = convertFormat(duration);
        // set textView
        playerDuration.setText(strDuration);
        // Bat su kien Play
        btPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // An nut play
                btPlay.setVisibility(View.GONE);
                // Hien nut pause
                btPause.setVisibility(View.VISIBLE);
                // Start media player
                mediaPlayer.start();
                // Set max cho seekBar
                seekBar.setMax(mediaPlayer.getDuration());
                // Start handle
                handler.postDelayed(runnable,0);
                // Start animation circle image
                startAnimation();
            }
        });
        // Bat su kien Pause
        btPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // An nut Pause
                btPause.setVisibility(View.GONE);
                // Hien nut Play
                btPlay.setVisibility(View.VISIBLE);
                // Pause media player
                mediaPlayer.pause();
                // Dung handler
                handler.removeCallbacks(runnable);
                // Stop animation circle image
                stopAnimation();
            }
        });
        // Bat su kien Fast Forward
        btFf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get current position of media player
                int currentPosition = mediaPlayer.getCurrentPosition();
                // Get duration of media player
                int duration = mediaPlayer.getDuration();
                // kiem tra
                if (mediaPlayer.isPlaying() && duration != currentPosition){
                    // fast forward 5s
                    currentPosition = currentPosition + 5000;
                    // Set textView (current position)
                    playerPosition.setText(convertFormat(currentPosition));
                    // set trang thai seek bar
                    mediaPlayer.seekTo(currentPosition);
                }
            }
        });
        btRew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lay current position of media player
                int currentPosition = mediaPlayer.getCurrentPosition();
                if (mediaPlayer.isPlaying() && currentPosition > 5000){
                    // rewind fo 5s
                    currentPosition = currentPosition - 5000;
                    // Set textView (current position)
                    playerPosition.setText(convertFormat(currentPosition));
                    // set trang thai seek bar
                    mediaPlayer.seekTo(currentPosition);
                }

            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser){
                    mediaPlayer.seekTo(progress);
                }
                // set text (current position)
                playerPosition.setText(convertFormat(mediaPlayer.getCurrentPosition()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                // An nut Pause
                btPause.setVisibility(View.GONE);
                // Hien nut Play
                btPlay.setVisibility(View.VISIBLE);
                // set media player ve dau
                mediaPlayer.seekTo(0);
                // Stop animation circle image
                stopAnimation();
            }
        });
    }

    private void AnhXa() {
        playerDuration = findViewById(R.id.player_duration);
        playerPosition = findViewById(R.id.player_position);
        seekBar = findViewById(R.id.seek_bar);
        btFf = findViewById(R.id.bt_ff);
        btPause = findViewById(R.id.bt_pause);
        btPlay = findViewById(R.id.bt_play);
        btRew = findViewById(R.id.bt_rew);
        image = findViewById(R.id.img);

    }

    private void startAnimation() {
        Runnable mRunnable = new Runnable() {
            @Override
            public void run() {
                image.animate()
                        .rotationBy(360)
                        .withEndAction(this)
                        .setDuration(10000)
                        .setInterpolator(new LinearInterpolator()).start();
            }
        };
        image.animate()
                .rotationBy(360)
                .withEndAction(mRunnable)
                .setDuration(10000)
                .setInterpolator(new LinearInterpolator()).start();
    }

    private void stopAnimation() {

        image.animate().cancel();
    }

    @SuppressLint("DefaultLocale")
    private String convertFormat(int duration) {
        return String.format("%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(duration),
                TimeUnit.MILLISECONDS.toSeconds(duration) -
                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration)));
    }

}