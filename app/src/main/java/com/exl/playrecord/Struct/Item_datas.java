package com.exl.playrecord.Struct;


import android.util.SparseArray;

/**
 * Created by Soldat on 2016/6/7.
 */
public class Item_datas {
    public Long     id;
    public String  type;
    public String  title;
    public int     season;
    public int     seasonMax;
    public SparseArray<Integer> episode;
    public SparseArray<Integer> episode_max;
    public String  message;
    public int      create_date;
    public int      update_date;
    public int      remind;

    public Boolean isCustomSeason;
    public SparseArray<String> customSeasonNames;
}
