package com.example.shuhang.hanghang3.community;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shuhang.hanghang3.R;
import com.example.shuhang.hanghang3.adapter.ComtyAdapter;
import com.example.shuhang.hanghang3.table.Community_list;
import com.example.shuhang.hanghang3.table.PhpUrl;
import com.example.shuhang.hanghang3.table.Var_Id;
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
public class Community extends Fragment implements AdapterView.OnItemClickListener,View.OnClickListener,AdapterView.OnItemLongClickListener {
    private ListView list1;
    private ComtyAdapter adapter;
    private List<Community_list> community;
    private TextView refresh;

    AsyncHttpClient client = new AsyncHttpClient();

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
        list1.setOnItemLongClickListener(this);
        refresh.setOnClickListener(this);
    }

    private void getdata() {
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
        String music_id = community.get(position).getMusic_id();
        String music_url = community.get(position).getMusic_url();
        String user_name =community.get(position).getUser_name();
        String user_sign =community.get(position).getUser_sign();
        String geming =community.get(position).getGeming();
        String zan=community.get(position).getZan();
        String flower=community.get(position).getFlower();
        String tu_ur=community.get(position).getTu_url();
        Intent intent = new Intent(getActivity(), Music_main.class);
        intent.putExtra("music_id", music_id);
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

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

        Var_Id.setMusic_id(community.get(position).getMusic_id());

        new AlertDialog.Builder(getActivity())
                .setMessage("删除歌曲会使你失去从这首歌的得到的赞或者鲜花噢")
                .setPositiveButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setNeutralButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        RequestParams params5 = new RequestParams();
                        params5.add("user_id", Var_Id.getId());
                        params5.add("music_id", Var_Id.getMusic_id());
                        client.post(PhpUrl.getDELETE(), params5, new JsonHttpResponseHandler() {
                            @Override
                            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                                super.onSuccess(statusCode, headers, response);
                                try {
                                    JSONObject obj = response.getJSONObject(0);
                                    if (obj.getString("check").equals("ok")) {
                                        Toast.makeText(getActivity(), "删除成功!", Toast.LENGTH_SHORT).show();
                                        community.remove(position);
                                        adapter.notifyDataSetChanged();
                                    } else {
                                        Toast.makeText(getActivity(), "这不是你的歌曲!", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                }).show();




        return false;
    }
}



