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
        // �ڶ���������uri��AsyncPlayer���Բ��ű��صĺ��������Ƶ������������������isLooped��AsyncPlayer���Ե��ö��play()��������ʱ�����֮ǰ����Ƶ��û�в����꣬������ֹͣ���žɵ���Ƶ������ʼload�µ���Ƶ��Ȼ�󲥷š�
        asyncPlayer.play(this, Uri.parse("file://" + Environment.getExternalStorageDirectory() + "/Music/good.mp3"), false, AudioManager.STREAM_MUSIC);
    } 

    @Override 
    protected void onPause() { 
        // ֹͣ���š� 
        asyncPlayer.stop(); 
        super.onPause(); 
    } 
	
}
