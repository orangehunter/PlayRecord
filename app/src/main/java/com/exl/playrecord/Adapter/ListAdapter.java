package com.exl.playrecord.Adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.exl.playrecord.R;


/**
 * Created by michael on 2016/6/4.
 */
class ListAdapter extends BaseAdapter {

    private LayoutInflater myInfalter;

    public ListAdapter(){

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

    private static class ViewHolder {
        TextView title, date, episoate;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null) {
            convertView = myInfalter.inflate(R.layout.list_object,parent, false);
            holder=new ViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.date = (TextView) convertView.findViewById(R.id.update_date);
            holder.episoate = (TextView) convertView.findViewById(R.id.episode);
            convertView.setTag(holder);
        }else {
            holder=(ViewHolder)convertView.getTag();
        }
        holder.title.setText("test");
        holder.date.setText("test");
        holder.episoate.setText("test");
        //TODO
        return convertView;
    }
}
