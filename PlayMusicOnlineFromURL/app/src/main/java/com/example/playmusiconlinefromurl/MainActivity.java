package com.example.playmusiconlinefromurl;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ImageView imagePlayPause;
    TextView textCurrentTime,textTotalDuration;
    SeekBar playerSeekBar;
    MediaPlayer mediaPlayer;
    Handler handler=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imagePlayPause=findViewById(R.id.imagePlayPause);
        textCurrentTime=findViewById(R.id.textCurrentTime);
        textTotalDuration=findViewById(R.id.textTotalDuration);
        playerSeekBar=findViewById(R.id.playerSeekBar);
        mediaPlayer=new MediaPlayer();
        playerSeekBar.setMax(100);

        imagePlayPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()){
                    handler.removeCallbacks(updater);
                    mediaPlayer.pause();
                    imagePlayPause.setImageResource(R.drawable.ic_baseline_play_circle_filled_24);
                }
                else
                {
                    mediaPlayer.start();
                    imagePlayPause.setImageResource(R.drawable.ic_baseline_pause_circle_filled_24);
                    updateSeekBar();
                }
            }
        });
        prepareMediaPlayer();
        playerSeekBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                SeekBar seekBar= (SeekBar) v;
                int playPosition=(mediaPlayer.getDuration()/100)*seekBar.getProgress();
                mediaPlayer.seekTo(playPosition);
                textCurrentTime.setText(milliSecondsToTimer(mediaPlayer.getCurrentPosition()));
                return false;
            }
        });
        mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mp, int percent) {
                playerSeekBar.setSecondaryProgress(percent);
            }
        });

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                playerSeekBar.setProgress(0);
                imagePlayPause.setImageResource(R.drawable.ic_baseline_play_circle_filled_24);
                textCurrentTime.setText(R.string.zero);
                textTotalDuration.setText(R.string.zero);
                mp.reset();
                prepareMediaPlayer();
            }
        });
    }
    private void prepareMediaPlayer(){
        try {
            mediaPlayer.setDataSource("https://cdn.uploader-assets.win/user-uploads/4757/04-Aug-2021/04-Aug-2021/mp3.mp3");
            mediaPlayer.prepare();
            textTotalDuration.setText(milliSecondsToTimer(mediaPlayer.getDuration()));
        }catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
    private Runnable updater=new Runnable() {
        @Override
        public void run() {
            updateSeekBar();
            long currentDuration=mediaPlayer.getCurrentPosition();
            textCurrentTime.setText(milliSecondsToTimer(currentDuration));
        }
    };
    private  void updateSeekBar(){
        if(mediaPlayer.isPlaying()){
            playerSeekBar.setProgress((int) (((float) mediaPlayer.getCurrentPosition()) / mediaPlayer.getDuration()) * 100);
            handler.postDelayed(updater,1000);
        }
    }
    private String  milliSecondsToTimer(long milliSeconds){
        String timerString="";
        String secondsString;
        int hours= (int) (milliSeconds/(1000*60*60));
        int minutes= (int) ((milliSeconds%(1000*60*60))/(1000*60));
        int seconds= (int) ((milliSeconds%(1000*60*60))%(1000*60)/1000);
        if(hours>0){
            timerString=hours+":";

        }
        if(seconds<10){
            secondsString="0"+seconds;
        }
        else
        {
            secondsString=""+seconds;

        }
        timerString=timerString+minutes+":"+secondsString;
        return timerString;
    }
}