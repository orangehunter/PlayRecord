package com.exl.playrecord.Adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by Soldat on 2016/5/26.
 */
public class MyListAdapter extends BaseAdapter {
    private LayoutInflater myLIF;
    public MyListAdapter(Context c){
        myLIF=LayoutInflater.from(c);
    }

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
        return null;
    }
}
