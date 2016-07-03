package com.example.shuhang.hanghang3.table;

/**
 * Created by shuhang on 2016/6/23.
 */
public class Item {
    private String tu_url;
    private String user_name;
    private String user_sign;
    private String friend_id;

    public Item(String tu_url, String user_name, String user_sign,String friend_id) {
        setFriend_id(friend_id);
        setTu_url(tu_url);
        setUser_name(user_name);
        setUser_sign(user_sign);
    }

    public String getFriend_id() {
        return friend_id;
    }

    public void setFriend_id(String friend_id) {
        this.friend_id = friend_id;
    }

    public String getTu_url() {
        return tu_url;
    }

    public void setTu_url(String tu_url) {
        this.tu_url = tu_url;
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
