package com.example.shuhang.hanghang3.rank;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.shuhang.hanghang3.R;
import com.example.shuhang.hanghang3.adapter.RankAdapter;
import com.example.shuhang.hanghang3.mydoge.Personal_space;
import com.example.shuhang.hanghang3.table.PhpUrl;
import com.example.shuhang.hanghang3.table.Rank_list;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shuhang on 2016/6/22.
 */
public class Rank   extends Fragment implements AdapterView.OnItemClickListener {


    private ListView list1;
    private RankAdapter adapter;
    private List<Rank_list> rank;
    AsyncHttpClient client = new AsyncHttpClient();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_rank, container, false);
        list1 = (ListView) view.findViewById(R.id.list_rank);

        init();
        getdata();
        return view;
    }

    private void init() {
        rank = new ArrayList<Rank_list>();
        adapter = new RankAdapter(getActivity(), rank);
        list1.setAdapter(adapter);
        list1.setOnItemClickListener(this);
    }

    private void getdata() {
        RequestParams params = new RequestParams();
        params.add("id", "1");
        client.post(PhpUrl.getRANK(), params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {

                if (statusCode == 200) {
                    rank.clear();
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject object = response.getJSONObject(i);
                            String rank_user_name = " "+object.getString("rank_user_name");
                            String rank_user_flower = object.getString("rank_user_flower");
                            String rank_user_zan = "                "+object.getString("rank_user_zan")+" ";
                            String rank_user_id =object.getString("rank_user_id");
                            rank.add(new Rank_list(rank_user_name, rank_user_flower, rank_user_zan,rank_user_id));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

        @Override
        public void onItemClick (AdapterView < ? > parent, View view,int position, long id){
            String user_id=rank.get(position).getRank_user_id();
            Intent intent = new Intent(getActivity(), Personal_space.class);
            intent.putExtra("user_id", user_id);
            startActivity(intent);

        }


}