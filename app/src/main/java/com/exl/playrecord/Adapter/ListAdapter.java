package com.exl.playrecord.Adapter;


import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.exl.playrecord.Struct.Item_names;

import org.json.JSONObject;


/**
 * Created by michael on 2016/6/4.
 */
class ListAdapter extends BaseAdapter {

    private LayoutInflater myInfalter;
    private Context context;

    private SparseArray<JSONObject> array;

    public ListAdapter(Context context, SparseArray<JSONObject> array){
        this.context=context;
        myInfalter=LayoutInflater.from(context);
        this.array=array;
    }

    @Override
    public int getCount() {
        return array.size();
    }

    @Override
    public JSONObject getItem(int position) {
        return array.get(position);
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
        holder.title.setText(getString(position, Item_names.KEY_TITLE));
        holder.date.setText(getString(position, Item_names.KEY_UPDATE));

        if (getString(position, Item_names.KEY_EPISODE_MAX).equals("")) {
            holder.episoate.setText(getString(position, Item_names.KEY_EPISODE));
        }else{
            holder.episoate.setText(getString(position, Item_names.KEY_EPISODE)+"/"+getString(position, Item_names.KEY_EPISODE_MAX));
        }
        return convertView;
    }

    private String getString(int position,String name){
        return array.get(position).optString(name,"*Error!Data Null*");
    }
}
