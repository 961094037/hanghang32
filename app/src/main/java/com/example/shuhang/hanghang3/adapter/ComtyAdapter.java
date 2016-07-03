package com.example.shuhang.hanghang3.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.shuhang.hanghang3.R;
import com.example.shuhang.hanghang3.table.Community_list;
import com.example.shuhang.hanghang3.utils.HttpUtils;
import java.util.List;

/**
 * Created by shuhang on 2016/5/12.
 */
public class ComtyAdapter extends BaseAdapter {
    private Context context;
    private List<Community_list> community_list;

    public ComtyAdapter(Context context, List<Community_list> community_list){
        this.context = context;
        this.community_list = community_list;
    }

    @Override
    public int getCount() {
        return community_list.size();
    }

    @Override
    public Community_list getItem(int position) {
        return community_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.community_list, null);
        }
        TextView text_zan = (TextView) convertView.findViewById(R.id.text_zan);
        TextView text_geming = (TextView) convertView.findViewById(R.id.text_geming);
        TextView text_pl = (TextView) convertView.findViewById(R.id.text_pl);
        TextView text_flower = (TextView) convertView.findViewById(R.id.text_flower);
        ImageView ivPic = (ImageView) convertView.findViewById(R.id.touxiang_1);
        Community_list news = community_list.get(position);
        text_zan.setText(news.getZan());
        text_geming.setText(news.getGeming());
        text_pl.setText(news.getPl());
        text_flower.setText(news.getFlower());
        String tu_url = news.getTu_url();
        HttpUtils.setPicBitmap(ivPic, tu_url);

        return convertView;
    }
}
