package com.example.shuhang.hanghang3.table;

/**
 * Created by shuhang on 2016/5/28.
 */
public class Comment_list {
    private  String username;
    private  String comment;
    private  String tu_url;

    public  Comment_list(String username,String comment ,String tu_url){
        setUsername(username);
        setComment(comment);
        setTu_url(tu_url);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTu_url() {
        return tu_url;
    }

    public void setTu_url(String tu_url) {
        this.tu_url = tu_url;
    }
}
