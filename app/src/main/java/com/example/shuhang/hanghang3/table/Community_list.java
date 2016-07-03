package com.example.shuhang.hanghang3.table;

import com.example.shuhang.hanghang3.community.Community;

/**
 * Created by shuhang on 2016/5/12.
 */
public class Community_list {
    private  String  geming;
    private  String  pl;
    private  String  zan;
    private  String  flower;
    private  String  tu_url;
    private  String  music_id;
    private  String  music_url;
    private  String  user_name;
    private  String  user_sign;
    public Community_list(String  geming,String  pl,String  zan,String  flower,String tu_url,String music_id,String music_url,String  user_name,String  user_sign){
        setGeming(geming);
        setPl(pl);
        setZan(zan);
        setFlower(flower);
        setTu_url(tu_url);
        setMusic_id(music_id);
        setMusic_url(music_url);
        setUser_name(user_name);
        setUser_sign(user_sign);
    }
    public String getGeming() {
        return geming;
    }
    public void setGeming(String geming) {
        this.geming = geming;
    }
    public String getPl() {
        return pl;
    }
    public void setPl(String pl) {
        this.pl = pl;
    }
    public String getZan() {
        return zan;
    }
    public void setZan(String zan) {
        this.zan = zan;
    }
    public String getFlower() {
        return flower;
    }
    public void setFlower(String flower) {
        this.flower = flower;
    }
    public String getTu_url() {
        return tu_url;
    }
    public void setTu_url(String tu_url) {
        this.tu_url = tu_url;
    }

    public String getMusic_url() {
        return music_url;
    }

    public void setMusic_url(String music_url) {
        this.music_url = music_url;
    }

    public String getMusic_id() {
        return music_id;
    }

    public void setMusic_id(String music_id) {
        this.music_id = music_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_sign() {
        return user_sign;
    }

    public void setUser_sign(String user_sign) {
        this.user_sign = user_sign;
    }
}
