package com.example.shuhang.hanghang3.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.shuhang.hanghang3.R;
import com.example.shuhang.hanghang3.table.Space_list;

import java.util.List;

/**
 * Created by shuhang on 2016/6/22.
 */
public class SpaceAdapter extends BaseAdapter {
    private Context context;
    private List<Space_list> space_list;

    public SpaceAdapter(Context context, List<Space_list> space_list){

        this.context = context;
        this.space_list = space_list;
    }

    @Override
    public int getCount() {
        return space_list.size();
    }

    @Override
    public Space_list getItem(int position) {
        return space_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.rank_list, null);
        }

        TextView rank_user_name = (TextView) convertView.findViewById(R.id.rank_user_name);
        TextView rank_user_flower = (TextView) convertView.findViewById(R.id.rank_user_flower);
        TextView rank_user_zan = (TextView) convertView.findViewById(R.id.rank_user_zan);


        Space_list news = space_list.get(position);

        rank_user_name.setText(news.getGeming());
        rank_user_flower.setText(news.getFlower());
        rank_user_zan.setText(news.getZan());



        return  convertView;
    }
}
