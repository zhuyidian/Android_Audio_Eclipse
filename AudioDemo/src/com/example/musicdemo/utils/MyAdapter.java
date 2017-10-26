package com.example.musicdemo.utils;

import java.util.List;

import com.example.musicdemo.R;
import com.example.musicdemo.R.id;
import com.example.musicdemo.R.layout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter
{
    private List<MusicBean> mlist;
    
    private Context context;
    
    public MyAdapter(List<MusicBean> mlist, Context context)
    {
        this.mlist = mlist;
        this.context = context;
    }
    
    @Override
    public int getCount()
    {
        return mlist.size();
    }
    
    @Override
    public Object getItem(int position)
    {
        return mlist.get(position);
    }
    
    @Override
    public long getItemId(int position)
    {
        return position;
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        
        Holder holder = null;
        if (convertView == null)
        {
            holder = new Holder();
            
            convertView = LayoutInflater.from(context).inflate(R.layout.item, null);
            
            holder.title = (TextView)convertView.findViewById(R.id.item_title);
            
            convertView.setTag(holder);
            
        }
        else
        {
            holder=(Holder)convertView.getTag();
        }
        holder.title.setText(mlist.get(position).getTitle());
        
        
        return convertView;
    }
    
    class Holder
    {
        TextView title;
    }
    
}
