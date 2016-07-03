package com.example.shuhang.hanghang3.table;

/**
 * Created by shuhang on 2016/6/21.
 */
public class Rank_list {
    private  String   rank_user_name;
    private  String   rank_user_flower;
    private  String   rank_user_zan;
    private  String   rank_user_id;


    public  Rank_list(String  rank_user_name,String   rank_user_flower,String   rank_user_zan,String rank_user_id){
        setRank_user_name(rank_user_name);
        setRank_user_flower(rank_user_flower);
        setRank_user_zan(rank_user_zan);
        setRank_user_id(rank_user_id);
    }




    public String getRank_user_flower() {
        return rank_user_flower;
    }

    public void setRank_user_flower(String rank_user_flower) {
        this.rank_user_flower = rank_user_flower;
    }

    public String getRank_user_name() {
        return rank_user_name;
    }

    public void setRank_user_name(String rank_user_name) {
        this.rank_user_name = rank_user_name;
    }

    public String getRank_user_zan() {
        return rank_user_zan;
    }

    public void setRank_user_zan(String rank_user_zan) {
        this.rank_user_zan = rank_user_zan;
    }

    public String getRank_user_id() {
        return rank_user_id;
    }

    public void setRank_user_id(String rank_user_id) {
        this.rank_user_id = rank_user_id;
    }
}
