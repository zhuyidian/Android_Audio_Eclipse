package com.example.musicdemo;

import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class MediaPlayerMusic extends Activity{
    private String title;
    
    private String artist;
    
    private String url;
    
    private TextView title_music;
    
    private TextView artist_music;
    
    private SeekBar seekbar;
    
    private Handler handler;
    
    private AudioManager audio;
    
    // 声音最大值，当前音量
    private int music_max, music_now;
    
    private MyThread thread;
    
    MediaPlayer mp = new MediaPlayer();
    
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        title_music = (TextView)findViewById(R.id.title_music);
        artist_music = (TextView)findViewById(R.id.geshou);
        seekbar = (SeekBar)findViewById(R.id.seekbar);
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        artist = intent.getStringExtra("artist");
        url = intent.getStringExtra("url");
        
        title_music.setText(title);
        artist_music.setText(artist);
        
        try{
            mp.setDataSource(url);
            mp.prepare();
            mp.start();
        }
        catch (IllegalArgumentException e){
            e.printStackTrace();
        }catch (SecurityException e){
            e.printStackTrace();
        }catch (IllegalStateException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        
        seekbar.setOnSeekBarChangeListener(new OnSeekBarChangeListener(){
            
            @Override
            public void onStopTrackingTouch(SeekBar seekBar){
                
            }
            
            @Override
            public void onStartTrackingTouch(SeekBar seekBar){
                
            }
            
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                audio.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
            }
        });
        
        thread = new MyThread();
        thread.start();
        
        handler = new Handler(){
            public void handleMessage(android.os.Message msg){
                initVoice();
            };
        };
        
    }
    
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        mp.stop();
    }
    
    private void initVoice(){
        audio = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        music_max = audio.getStreamMaxVolume(AudioManager.STREAM_MUSIC);// 获取最大音量
        seekbar.setMax(music_max);
        music_now = audio.getStreamVolume(AudioManager.STREAM_MUSIC);// 当前音量
    }
    
    class MyThread extends Thread{
        @Override
        public void run(){
            super.run();
            
            try{
                sleep(100);
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
            handler.sendEmptyMessage(0);
        }
    }
}
