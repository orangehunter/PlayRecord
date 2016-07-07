package com.exl.playrecord.Adapter;

import android.content.Context;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.exl.playrecord.R;
import com.exl.playrecord.Struct.Item_datas;
import com.exl.playrecord.Variable;
import com.exl.playrecord.script.DateConvert;
import com.exl.playrecord.script.SQLController;

import java.util.ArrayList;

/**
 * Created by Soldat on 2016/6/7.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{

    Variable variable;

    public RecyclerAdapter(Variable variable){
        this.variable=variable;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        View view= LayoutInflater.from(context).inflate(R.layout.list_object,parent,false);

        RecyclerView.ViewHolder viewHolder=new ViewHolder(view);
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Item_datas itemDatas= variable.datas.get(position);
        TextView title= holder.title;
        TextView episoate= holder.episoate;
        TextView date= holder.date;

        title.setText(itemDatas.title);
        date.setText(DateConvert.msToString(itemDatas.update_date));
        String episoate_string="";
        if (itemDatas.season!=0){
            episoate_string+=("S"+itemDatas.season+" ");
        }
        if (itemDatas.episode_max.get(0)==0){
            episoate_string+=itemDatas.episode;
        }else {
            episoate_string+=(itemDatas.episode+"/"+itemDatas.episode_max);
        }
        episoate.setText(episoate_string);
    }

    @Override
    public int getItemCount() {
        return variable.datas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView title,episoate,date;
        public ViewHolder(View itemView) {
            super(itemView);

            title=(TextView)itemView.findViewById(R.id.title_main);
            date =(TextView)itemView.findViewById(R.id.update_date_main);
            episoate=(TextView)itemView.findViewById(R.id.episode_main);
        }
    }

}
