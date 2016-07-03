package com.example.shuhang.hanghang3.table;

/**
 * Created by shuhang on 2016/6/22.
 */
public class Space_list {
    private String geming;
    private String zan;
    private String flower;
    private String music_id;


    public Space_list(String geming,String flower,String zan,String music_id){
        setGeming(geming);
        setFlower(flower);
        setZan(zan);
        setMusic_id(music_id);
    }

    public String getFlower() {
        return flower;
    }

    public void setFlower(String flower) {
        this.flower = flower;
    }

    public String getGeming() {
        return geming;
    }

    public void setGeming(String geming) {
        this.geming = geming;
    }

    public String getZan() {
        return zan;
    }

    public void setZan(String zan) {
        this.zan = zan;
    }

    public String getMusic_id() {
        return music_id;
    }

    public void setMusic_id(String music_id) {
        this.music_id = music_id;
    }
}
