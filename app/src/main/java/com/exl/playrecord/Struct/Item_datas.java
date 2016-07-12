package com.exl.playrecord.Struct;


import android.util.SparseArray;

/**
 * Created by Soldat on 2016/6/7.
 */
public class Item_datas {
    public int id;
    public String  type;
    public String  title;
    public int     season;
    public int     seasonMax;
    public SparseArray<Integer> episode;
    public SparseArray<Integer> episode_max;
    public String  message;
    public Long    create_date;
    public Long    update_date;
    public Long    remind;

    public Boolean isCustomSeason;
    public SparseArray<String> customSeasonNames;

    public Item_datas(){
        id=0;
        type="";
        title="";
        season=0;
        seasonMax=0;
        episode=new SparseArray<Integer>();
        episode_max=new SparseArray<Integer>();
        message="";
        create_date=Long.valueOf("0");
        update_date=Long.valueOf("0");
        remind=Long.valueOf("0");
        customSeasonNames=new SparseArray<String>();
    }
}
