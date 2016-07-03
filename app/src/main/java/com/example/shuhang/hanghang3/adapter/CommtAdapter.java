package com.example.shuhang.hanghang3.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shuhang.hanghang3.R;
import com.example.shuhang.hanghang3.table.Comment_list;
import com.example.shuhang.hanghang3.utils.HttpUtils;

import java.util.List;

/**
 * Created by shuhang on 2016/5/28.
 */
public class CommtAdapter extends BaseAdapter {
    private Context context;
    private List<Comment_list> comment_list;

    public CommtAdapter(Context context, List<Comment_list> comment_list){
        this.context = context;
        this.comment_list = comment_list;
    }

    @Override
    public int getCount() {
        return comment_list.size();
    }

    @Override
    public Comment_list getItem(int position) {
        return comment_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.music_main_list, null);
        }
        TextView username = (TextView) convertView.findViewById(R.id.list_music_name);
        TextView comment = (TextView) convertView.findViewById(R.id.list_music_comment);
        ImageView ivPic = (ImageView) convertView.findViewById(R.id.list_music_image);
        Comment_list news = comment_list.get(position);
        username.setText(news.getUsername());
        comment.setText(news.getComment());
        String tu_url = news.getTu_url();
        HttpUtils.setPicBitmap(ivPic,tu_url);

        return convertView;
    }

}
