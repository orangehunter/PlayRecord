package com.exl.playrecord;

import android.util.SparseArray;

import com.exl.playrecord.Struct.Item_datas;
import com.exl.playrecord.script.SeasonEpisoates.DeCode;

import org.json.JSONObject;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


import static org.mockito.Mockito.when;

/**
 * created by soldat on 2016/7/3.
 */
@RunWith(MockitoJUnitRunner.class)
public class testsV2 {

    Item_datas datas0;
    JSONObject seasonEpisoateData0;
    DeCode deCode;

    @BeforeClass
    public static void beforeClass() throws Exception {
        System.out.println("beforeClass");
    }

    @Before
    public void setUp() throws Exception {
        when(datas0=new Item_datas());
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
        System.out.println("Before");
    }

    @Test
    public void test1(){
        String ans="";
        for(int i=0;i<deCode.getSize();i++){
            ans+=datas0.episode.get(i)+"/"+datas0.episode_max.get(i)+" ";
        }
        System.out.println("test1:"+ans);
    }

    @Test
    public void test2(){
        System.out.println("test2:"+deCode.isCustomSeason());
    }

    @Test
    public void test3(){
        System.out.println("test3:"+deCode.getSize());
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("After");
    }

    @AfterClass
    public static void afterClass() throws Exception{
        System.out.print("AfterClass");
    }
}
