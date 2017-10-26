package com.example.musicdemo;

import java.util.ArrayList;
import java.util.List;

import com.example.musicdemo.utils.MusicBean;
import com.example.musicdemo.utils.MyAdapter;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity{
    private List<MusicBean> mlist;  
    private ListView list;
    private Button mSoundPoolPlayerMusic;
    private Button mAsyncPlayerPlayerMusic;
    private Button mjetPlayer;
    private Button mRingtonePlayer;
    
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mlist = new ArrayList<MusicBean>();
        list = (ListView)findViewById(R.id.list);
        
        mSoundPoolPlayerMusic = (Button)findViewById(R.id.SoundPoolPlayerMusic);
        mAsyncPlayerPlayerMusic = (Button)findViewById(R.id.AsyncPlayerPlayerMusic);
        mjetPlayer = (Button)findViewById(R.id.jetPlayer);
        mRingtonePlayer = (Button)findViewById(R.id.RingtonePlayer);
        
        Cursor cursor = getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
        // 遍历媒体数据库
        if (cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                MusicBean musicBean = new MusicBean();
                // 歌曲编号
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID));
                // 歌曲id
                int trackId = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID));
                // 歌曲标题
                String title = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE));
                musicBean.setTitle(title);
                
                // 歌曲的专辑名：MediaStore.Audio.Media.ALBUM
                String album = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM));
                musicBean.setAlbum(album);
                
                // 歌曲的歌手名： MediaStore.Audio.Media.ARTIST
                String artist = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST));
                musicBean.setArtist(artist);
                
                // 歌曲文件的路径 ：MediaStore.Audio.Media.DATA
                String url = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
                musicBean.setUrl(url);
                
                // 歌曲的总播放时长：MediaStore.Audio.Media.DURATION
                int duration = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION));
                musicBean.setDuration(duration + "");
                
                // 歌曲文件的大小 ：MediaStore.Audio.Media.SIZE
                Long size = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE));
                // 歌曲文件显示名字
                String disName = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME));
                musicBean.setDisName(disName);
                
                mlist.add(musicBean);
                
                cursor.moveToNext();
            }
            cursor.close();
        }
        
        MyAdapter adapter = new MyAdapter(mlist, this); 
        list.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        
        list.setOnItemClickListener(new OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Intent intent = new Intent(MainActivity.this,MediaPlayerMusic.class);
                intent.putExtra("url", mlist.get(position).getUrl());
                intent.putExtra("title", mlist.get(position).getTitle());
                intent.putExtra("artist", mlist.get(position).getArtist());
                startActivity(intent);
            }
        });
        mSoundPoolPlayerMusic.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this,SoundPoolPlayerMusic.class);
                startActivity(intent);
			}
            
        });
        mAsyncPlayerPlayerMusic.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this,AsyncPlayerPlayerMusic.class);
                startActivity(intent);
			}

        });
        mjetPlayer.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this,jetPlayer.class);
                startActivity(intent);
			}
        	
        });
        mRingtonePlayer.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this,RingtonePlayerMusic.class);
                startActivity(intent);
			}
        	
        });
    }  
}
