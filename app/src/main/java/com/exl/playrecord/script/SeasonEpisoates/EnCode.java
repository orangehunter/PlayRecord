package com.exl.playrecord.script.SeasonEpisoates;

import android.util.Log;

import com.exl.playrecord.Struct.Item_datas;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by michael on 2016/7/1.
 */
public class EnCode {
    JSONObject jason;
    public EnCode(Item_datas datas){
        jason=new JSONObject();
        if (!datas.isCustomSeason){
            for(int i=0;i<datas.seasonMax;i++){
                try {
                    jason.put("" + i, datas.episode + "/" + datas.episode_max);
                }catch (JSONException e){
                    Log.e("SeEncode","EnCode"+" Title:"+datas.title+" i="+i+" ErrorCode:"+e);
                }
            }
        }else {
            for(int i=0;i<datas.seasonMax;i++){
                try {
                    jason.put(datas.customSeasonNames.get(i), datas.episode + "/" + datas.episode_max);
                }catch (JSONException e){
                    Log.e("SeEncode","EnCode"+" Title:"+datas.title+" Season="+datas.customSeasonNames.get(i)+" ErrorCode:"+e);
                }
            }
        }
    }

    public String getString(){
        return jason.toString();
    }
}
