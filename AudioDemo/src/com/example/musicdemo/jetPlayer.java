package com.example.musicdemo;

import java.io.IOException;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AsyncPlayer;
import android.media.AudioManager;
import android.media.JetPlayer;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class jetPlayer extends Activity{
	private final static String TAG="jetPlayer";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.asyncplayerplayermusic);
		
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.e(TAG,"onCreate");
		// 获取JetPlayer播放器
	    JetPlayer mJet = JetPlayer.getJetPlayer() ;
	    //清空播放队列
	    mJet.clearQueue() ;
	    //绑定事件监听
	    mJet.setEventListener(new JetPlayer.OnJetEventListener() {
	        //播放次数记录
	        int playNum = 1 ;
	        @Override
	        public void onJetEvent(JetPlayer player, short segment, byte track, byte channel, byte controller, byte value) {
	            //Log.i(TAG,"----->onJetEvent") ;
	        }

	        @Override
	        public void onJetUserIdUpdate(JetPlayer player, int userId, int repeatCount) {
	            //Log.i(TAG,"----->onJetUserIdUpdate") ;
	        }

	        @Override
	        public void onJetNumQueuedSegmentUpdate(JetPlayer player, int nbSegments) {
	            //Log.i(TAG,"----->onJetNumQueuedSegmentUpdate") ;
	        }

	        @Override
	        public void onJetPauseUpdate(JetPlayer player, int paused) {
	            //Log.i(TAG,"----->onJetPauseUpdate") ;
	            if(playNum == 2){
	                playNum = -1 ;
	                //释放资源，并关闭jet文件
	                player.release();
	                player.closeJetFile() ;
	            }else{
	                playNum++ ;
	            }
	        }
	    });
	    //加载资源
	    mJet.loadJetFile(getResources().openRawResourceFd(R.raw.attack02)) ;
	    byte sSegmentID = 0 ;
	    //指定播放序列
	    mJet.queueJetSegment(0, 0, 0, 0, 0, sSegmentID);
	    mJet.queueJetSegment(1, 0, 1, 0, 0, sSegmentID);
	    //开始播放
	    mJet.play() ;
	    Log.e(TAG,"onCreate---play");
	}
  
    
    
    
}
