package com.exl.playrecord.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.exl.playrecord.MainActivity;
import com.exl.playrecord.R;
import com.exl.playrecord.Struct.Item_datas;
import com.exl.playrecord.Variable;
import com.exl.playrecord.script.DateConvert;

/**
 * Created by Soldat on 2016/6/7.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{
    //ViewHolder子類
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView title,episoate,date;
        public MyViewHolderClick clk;
        public ViewHolder(View view, MyViewHolderClick listener){
            super(view);

            clk=listener;
            view.setOnClickListener(this);
            title=(TextView)itemView.findViewById(R.id.title_main);
            date =(TextView)itemView.findViewById(R.id.update_date_main);
            episoate=(TextView)itemView.findViewById(R.id.episode_main);
        }

        @Override
        public void onClick(View v) {
            clk.clickOnView(v,getLayoutPosition());
        }

        public interface MyViewHolderClick{
            void clickOnView(View v, int position);
        }

    }
    //ViewHolder子類結束

    private SparseArray<Item_datas> datasSparseArray;
    private Activity mainActivity;

    public RecyclerAdapter(SparseArray<Item_datas> item_datasSparseArray, MainActivity activity){
        datasSparseArray=item_datasSparseArray;
        this.mainActivity=activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        View view=LayoutInflater.from(context).inflate(R.layout.list_object,parent,false);

        ViewHolder viewHolder=new ViewHolder(view, new ViewHolder.MyViewHolderClick() {
            @Override
            public void clickOnView(View v, int position) {
                Log.i("RA Click","no:"+position);
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Item_datas item_datas=datasSparseArray.get(position);

        TextView title= holder.title;
        TextView episoate= holder.episoate;
        TextView date= holder.date;

        title.setText(item_datas.title);
        date.setText(DateConvert.msToString(item_datas.update_date));
        String episoate_string="";
        if (item_datas.seasonMax>=1){
            if (item_datas.isCustomSeason){
                episoate_string+=(item_datas.customSeasonNames+" ");
            }
            episoate_string+=("S"+(item_datas.season+1)+" ");
        }
        if (item_datas.episode.get(item_datas.season,-1)!=-1){
            episoate_string+=(item_datas.episode.get(item_datas.season)+"");
        }else {
            episoate_string+=("0");
        }
        if (item_datas.episode_max.get(item_datas.season,-1)!=-1){
            episoate_string+=("/"+item_datas.episode_max.get(item_datas.season));
        }
        episoate.setText(episoate_string);

    }

    @Override
    public int getItemCount(){
        return datasSparseArray.size();
    }

    public void setData(SparseArray<Item_datas> data){
        this.datasSparseArray =data;
        notifyDataSetChanged();
    }

   /* SparseArray<Item_datas> datasSparseArray;

    public RecyclerAdapter(){
        datasSparseArray =new SparseArray<Item_datas>();
    }

    public void setData(SparseArray<Item_datas> data){
        this.datasSparseArray =data;
        notifyDataSetChanged();
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
        Item_datas itemDatas= datasSparseArray.get(position);
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
        return datasSparseArray.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView title,episoate,date;
        public ViewHolder(View itemView) {
            super(itemView);

            title=(TextView)itemView.findViewById(R.id.title_main);
            date =(TextView)itemView.findViewById(R.id.update_date_main);
            episoate=(TextView)itemView.findViewById(R.id.episode_main);
        }
    }*/

}
