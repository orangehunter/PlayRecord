package com.exl.playrecord.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.exl.playrecord.R;

import java.util.zip.Inflater;

/**
 * Created by michael on 2016/6/4.
 */
class ListAdapter extends BaseAdapter {

    private LayoutInflater myInfalter;


    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView=myInfalter.inflate(R.layout.list_object,null);
        TextView title,date,episoate;
        
        return null;
    }
}
