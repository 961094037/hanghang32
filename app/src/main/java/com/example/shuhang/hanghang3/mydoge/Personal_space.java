package com.example.shuhang.hanghang3.mydoge;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.shuhang.hanghang3.R;

import com.example.shuhang.hanghang3.adapter.SpaceAdapter;

import com.example.shuhang.hanghang3.community.Music_main;
import com.example.shuhang.hanghang3.table.PhpUrl;
import com.example.shuhang.hanghang3.table.Space_list;
import com.example.shuhang.hanghang3.table.Var_Id;
import com.example.shuhang.hanghang3.utils.HttpUtils;
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
 * Created by shuhang on 2016/4/15.
 */
public class Personal_space extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private String  user_id;
    private String  check;
    private ListView list1;
    private SpaceAdapter adapter;
    private List<Space_list> space;
    private TextView space_user_name;
    private TextView space_user_sign;
    private TextView space_music_number;
    private TextView space_user_flower;
    private TextView space_user_zan;
    private ImageView space_tu_url;
    AsyncHttpClient client = new AsyncHttpClient();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_personal_space);
        getuser_id();
        init();
        getupdata();
        getdata();



    }

    private void init(){
        list1=(ListView)findViewById(R.id.list_space);
        space = new ArrayList<Space_list>();
        adapter = new SpaceAdapter(this, space);
        list1.setAdapter(adapter);
        list1.setOnItemClickListener(this);

        space_user_name=(TextView)findViewById(R.id.space_user_name);
        space_user_sign=(TextView)findViewById(R.id.space_user_sign);
        space_tu_url=(ImageView)findViewById(R.id.space_head);
        space_music_number=(TextView)findViewById(R.id.space_music_number);
        space_user_flower=(TextView)findViewById(R.id.space_user_flower);
        space_user_zan=(TextView)findViewById(R.id.space_user_zan);

    }
    private void getuser_id(){
        Intent intent=getIntent();
        check=intent.getStringExtra("user_id");
        if(check.equals("self")){
            user_id= Var_Id.getId();
        }
        else
        {
            user_id=check;
        }
    }
    private void getdata(){
        RequestParams params = new RequestParams();
        params.add("user_id", user_id);
        client.post(PhpUrl.getSPACE(),params,new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                if (statusCode == 200) {
                    space.clear();
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject object = response.getJSONObject(i);
                            String geming = object.getString("geming");
                            String flower = object.getString("flower");
                            String zan = "                "+object.getString("zan")+" ";
                            String music_id=object.getString("music_id");
                            space.add(new Space_list(geming, flower, zan,music_id));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    adapter.notifyDataSetChanged();
                }

            }
        });
    }
    private void getupdata(){
        RequestParams params = new RequestParams();
        params.add("user_id", user_id);
        client.post(PhpUrl.getUPSPACE(),params,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    JSONObject object = response.getJSONObject(0);
                    space_user_name.setText(object.getString("user_name"));
                    space_user_sign.setText(object.getString("user_sign"));
                    space_music_number.setText("歌曲数:"+object.getString("music_number"));
                    space_user_flower.setText("获得花:"+object.getString("user_flower"));
                    space_user_zan.setText("获得赞:"+object.getString("user_zan"));
                    HttpUtils.setPicBitmap(space_tu_url, object.getString("tu_url"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String music_id = space.get(position).getMusic_id();
        Intent intent = new Intent(this, Music_main.class);
        intent.putExtra("music_id", music_id);
        startActivity(intent);
    }
}
