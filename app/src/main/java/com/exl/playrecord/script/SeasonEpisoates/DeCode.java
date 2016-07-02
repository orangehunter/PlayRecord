package com.exl.playrecord.script.SeasonEpisoates;

import android.util.Log;
import android.util.SparseArray;

import com.exl.playrecord.Struct.Item_datas;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by michael on 2016/6/30.
 */
public class DeCode {
    //Season與Episoate的格式為 jsonobject 例{"1":"12/30","2":"0/100"}
    //Season預設採純數字 但是也可以使用客制化的字串 例{"起":"12/30","承":"0/100","轉":"12/30","合":"12/30"}
    JSONObject jason;
    JSONArray names;
    public DeCode(String data){
        try {
            jason=new JSONObject(data);
            names=jason.names();
        } catch (JSONException e) {
            Log.e("Class DeCodeSE","SeasonEpisoates:"+e);
            e.printStackTrace();
        }
    }

    public int getSize(){
        return jason.length();
    }

    public Boolean isCustomSeason(){
        boolean tmpFlag=true;
        for(int i=0;i<jason.length();i++){
            if (names.optInt(i, -1) == -1) {
                tmpFlag=false;
            }
        }
        return tmpFlag;
    }

    public SparseArray<String> getCustomSeason(){
        SparseArray<String> tmp=new SparseArray<String>();
        for(int i=0;i<jason.length();i++){
            tmp.put(i,names.optString(i,"S"+(i+1)));
        }
        return tmp;
    }

    public Item_datas getEpisode_EpisoateMax(Item_datas datas){
        datas.episode=new SparseArray<>();
        datas.episode_max=new SparseArray<>();
        for(int i=0;i<jason.length();i++){
            int[] ep_epMax=getEp_EpMax(i);
            datas.episode.put(i,ep_epMax[0]);
            datas.episode_max.put(i,ep_epMax[1]);
        }
        return datas;
    }

    private int[] getEp_EpMax(int i){
        try {
            int ep,epmax;
            String ep_s="",epmax_s="";
            boolean across=false;
            String tmp=jason.getString(names.getString(i));
            for(int k=0;k<tmp.length();k++){
                if (tmp.substring(k,k+1).equals("/"))
                    across=true;
                if (!across){
                    ep_s+=tmp.substring(k,k+1);
                }else {
                    epmax_s+=tmp.substring(k,k+1);
                }
            }
            ep=Integer.valueOf(ep_s);
            epmax=Integer.valueOf(epmax_s);
            return new int[]{ep,epmax};
        }catch (JSONException e){
            Log.e("Class DeCodeSE","getEp_EpMax:"+e);
        }finally {
            return new int[]{0,0};
        }
    }
}
