package com.example.musicdemo;

import java.io.IOException;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AsyncPlayer;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class AsyncPlayerPlayerMusic extends Activity{
	private AsyncPlayer asyncPlayer = null; 
	private String tag = "wei"; 
	 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.asyncplayerplayermusic);
		
	}

	@Override 
    protected void onResume() {  
        super.onResume(); 
        asyncPlayer = new AsyncPlayer(tag); 
        // 第二个参数是uri，AsyncPlayer可以播放本地的和网络的音频流。，第三个参数是isLooped。AsyncPlayer可以调用多次play()，当调用时，如果之前的音频还没有播放完，就马上停止播放旧的音频，并开始load新的音频，然后播放。
        asyncPlayer.play(this, Uri.parse("file://" + Environment.getExternalStorageDirectory() + "/Music/good.mp3"), false, AudioManager.STREAM_MUSIC);
    } 

    @Override 
    protected void onPause() { 
        // 停止播放。 
        asyncPlayer.stop(); 
        super.onPause(); 
    } 
	
}
