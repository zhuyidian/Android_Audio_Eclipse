package com.example.musicdemo;

import java.io.IOException;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AsyncPlayer;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
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

public class RingtonePlayerMusic extends Activity{
	private Ringtone ringtone;
	 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.asyncplayerplayermusic);
		ringtone = initRingtone(this);
		
		ringtone.play();
	}
	private Ringtone initRingtone(Activity context) {
        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        return RingtoneManager.getRingtone(context, notification);
    }

	@Override 
    protected void onResume() {  
        super.onResume(); 
        
    } 

    @Override 
    protected void onPause() { 
        super.onPause(); 
    } 
	
}
