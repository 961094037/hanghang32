package com.example.shuhang.hanghang3.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.shuhang.hanghang3.R;
import com.example.shuhang.hanghang3.table.Rank_list;

import java.util.List;

/**
 * Created by shuhang on 2016/6/21.
 */
public class RankAdapter extends BaseAdapter {

    private Context context;
    private List<Rank_list> rank_list;

    public RankAdapter(Context context, List<Rank_list> rank_list){

        this.context = context;
        this.rank_list = rank_list;
    }

    @Override
    public int getCount() {
        return rank_list.size();
    }

    @Override
    public Rank_list getItem(int position) {
        return rank_list.get(position);
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

        Rank_list news = rank_list.get(position);
        rank_user_name.setText(news.getRank_user_name());
        rank_user_flower.setText(news.getRank_user_flower());
        rank_user_zan.setText(news.getRank_user_zan());


        return   convertView;
    }
}
