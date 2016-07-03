package com.example.shuhang.hanghang3.community;


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
import com.example.shuhang.hanghang3.adapter.ComtyAdapter;
import com.example.shuhang.hanghang3.table.Community_list;
import com.example.shuhang.hanghang3.table.PhpUrl;
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
 * Created by shuhang on 2016/3/29.
 * //
 */
//
public class Community extends Fragment implements AdapterView.OnItemClickListener,View.OnClickListener {
    private ListView list1;
    private ComtyAdapter adapter;
    private List<Community_list> community;
    private TextView refresh;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_community, container, false);
        list1 = (ListView) view.findViewById(R.id.list_community);
        refresh=(TextView)view.findViewById(R.id.cmm_refresh);
        initView();
        getdata();
        return view;
    }

    private void initView() {
        community = new ArrayList<Community_list>();
        adapter = new ComtyAdapter(getActivity(), community);
        list1.setAdapter(adapter);
        list1.setOnItemClickListener(this);
        refresh.setOnClickListener(this);
    }

    private void getdata() {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.add("id", "1");
        client.post(PhpUrl.getMusicList(), params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                if (statusCode == 200) {
                    community.clear();
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject object = response.getJSONObject(i);
                            String geming = object.getString("geming");
                            String zan = object.getString("zan");
                            String flower = object.getString("flower");
                            String pl = object.getString("pl");
                            String tu_url = object.getString("tu_url");
                            String music_id = object.getString("music_id");
                            String music_url =object.getString("music_url");
                            String user_name=object.getString("user_name");
                            String user_sign=object.getString("user_sign");
                            community.add(new Community_list(geming, pl, zan, flower, tu_url, music_id,music_url,user_name,user_sign));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Log.e("xxx", "onFailure: " + responseString);
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String community_list = community.get(position).getMusic_id();
        String music_url = community.get(position).getMusic_url();
        String user_name =community.get(position).getUser_name();
        String user_sign =community.get(position).getUser_sign();
        String geming =community.get(position).getGeming();
        String zan=community.get(position).getZan();
        String flower=community.get(position).getFlower();
        String tu_ur=community.get(position).getTu_url();
        Intent intent = new Intent(getActivity(), Music_main.class);
        intent.putExtra("music_id", community_list);
        intent.putExtra("music_url",music_url);
        intent.putExtra("user_name",user_name);
        intent.putExtra("user_sign",user_sign);
        intent.putExtra("geming",geming);
        intent.putExtra("zan",zan);
        intent.putExtra("flower",flower);
        intent.putExtra("tu_url",tu_ur);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cmm_refresh:
                community.clear();
                getdata();
                break;
            default:
                break;
        }
    }
}



