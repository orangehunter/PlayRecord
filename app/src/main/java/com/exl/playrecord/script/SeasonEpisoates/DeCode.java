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
    public SparseArray<Integer> episode;
    public SparseArray<Integer> episode_max;
    public DeCode(String data){
        try {
            jason=new JSONObject(data);
            names=jason.names();
        } catch (JSONException e) {
            Log.e("Class DeCodeSE","SeasonEpisoates:"+e+" String:"+data);
            e.printStackTrace();
        }
    }

    public int getSize(){
        if (jason!=null) {
            return jason.length();
        }else {
            return 0;
        }
    }

    public Boolean isCustomSeason(){
        if (jason!=null) {
            for (int i = 0; i < jason.length(); i++) {
                if (names.optInt(i, -1) == -1) {
                    return true;
                }
            }
        }
        return false;
    }

    public SparseArray<String> getCustomSeason(){
        SparseArray<String> tmp=new SparseArray<String>();
        for(int i=0;i<jason.length();i++){
            tmp.put(i,names.optString(i,"S"+(i+1)));
        }
        return tmp;
    }
    public SparseArray<Integer> getEpisode(){
        return  episode;
    }

    public SparseArray<Integer> getEpisode_max(){
        return  episode_max;
    }

    public void getEpisode_EpisoateMax(){
        episode=new SparseArray<Integer>();
        episode_max=new SparseArray<Integer>();
        for(int i=0;i<jason.length();i++){
            int[] ep_epMax;
            ep_epMax=getEp_EpMax(i);
            episode.put(i,ep_epMax[0]);
            episode_max.put(i,ep_epMax[1]);
        }
    }

    private int[] getEp_EpMax(int i){
        try {
            int ep,epmax;
            String ep_s="",epmax_s="";
            boolean across=false;
            String tmp=jason.getString(names.getString(i));
            for(int k=0;k<tmp.length();k++){
                if (tmp.substring(k,k+1).equals("/")) {
                    across = true;
                    k++;
                }
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
            return new int[]{0,0};
        }
    }
}
