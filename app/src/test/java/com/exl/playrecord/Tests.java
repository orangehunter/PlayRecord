package com.exl.playrecord;

import android.test.AndroidTestCase;
import android.util.SparseArray;

import com.exl.playrecord.Struct.Item_datas;
import com.exl.playrecord.script.SeasonEpisoates.DeCode;
import com.exl.playrecord.script.SeasonEpisoates.EnCode;

import org.json.JSONObject;
import org.junit.Assert;

/**
 * Created by Soldat on 2016/7/3.
 */
public class Tests extends AndroidTestCase {
    Item_datas datas0;
    JSONObject seasonEpisoateData0;
    DeCode deCode;
    EnCode enCode;
    SparseArray<Integer> si;
    @Override
    public void setUp() throws Exception {
        datas0=new Item_datas();
        datas0.id=0;
        datas0.type="test0";
        datas0.title="testing0";
        datas0.season=0;
        seasonEpisoateData0= new JSONObject();
        seasonEpisoateData0.put("0","0/10");
        seasonEpisoateData0.put("1","1/20");
        seasonEpisoateData0.put("2","2/30");
        seasonEpisoateData0.put("3","3/0");
        deCode=new DeCode(seasonEpisoateData0.toString());
        deCode.getEpisode_EpisoateMax();
        datas0.episode=deCode.getEpisode();
        datas0.episode_max=deCode.getEpisode_max();

        super.setUp();
    }

    public void testAPI1(){
        Assert.assertTrue(!deCode.isCustomSeason());
    }
    public void testAPI2(){
        Assert.assertTrue(deCode.getSize()==4);
    }
    public void testAPI3(){
        String ans="";
        for(int i=0;i<deCode.getSize();i++){
            ans+=datas0.episode.get(i)+"/"+datas0.episode_max.get(i)+" ";
        }
        fail(ans);
    }
    public void testAPI4(){
        String ans=datas0.episode.size()+":"+datas0.episode_max.size();
        fail(ans);
    }
    public void testAPI5(){
        deCode.getEpisode_EpisoateMax();
        si=new SparseArray<Integer>();
        for (int i=0;i<5;i++){
            si.put(i,i);
        }
        Assert.fail(si.toString());
    }


    @Override
    public void tearDown() throws Exception {
            super.tearDown();
    }
}
