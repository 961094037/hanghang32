package com.example.shuhang.hanghang3.community;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shuhang.hanghang3.R;
import com.example.shuhang.hanghang3.adapter.CommtAdapter;
import com.example.shuhang.hanghang3.table.Comment_list;
import com.example.shuhang.hanghang3.table.IconUrl;
import com.example.shuhang.hanghang3.table.User_Id;
import com.example.shuhang.hanghang3.table.PhpUrl;
import com.example.shuhang.hanghang3.utils.HttpUtils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shuhang on 2016/4/21.
 */


public class Music_main extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemClickListener,SeekBar.OnSeekBarChangeListener{
    private MediaPlayer mediaPlayer=new MediaPlayer();
    private ImageView play;
    private ImageView pause;
    private ImageView stop;
    private ImageView like;
    private ImageView flower;
    private ImageView comment;
    private ImageView delete;
    private ImageView add;
    private ImageView touxiang;
    private TextView  mm_name;
    private TextView  mm_sign;
    private TextView  mm_geming;
    private TextView  zan_number;
    private TextView  flower_number;
    private EditText  etComment;
    private String music_id;
    private String music_url ;
    private String user_name;
    private String user_sign;
    private String geming;
    private String mm_zan;
    private String mm_flower;
    private String tu_url;
    private SeekBar music_seekbar;



    private ListView list;
    private CommtAdapter adapter;
    private List<Comment_list> comment_;


    AsyncHttpClient client = new AsyncHttpClient();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_music_main);
        Intent intent=getIntent();
        music_id=intent.getStringExtra("music_id");
        init();
        getupdata();
        getdata();
    }


    private void getupdata(){
        RequestParams params = new RequestParams();
        params.add("music_id", music_id);
        client.post(PhpUrl.getMusicData(), params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    JSONObject object = response.getJSONObject(0);
                    music_url = object.getString("music_url");
                    user_name = object.getString("user_name");
                    user_sign = object.getString("user_sign");
                    geming = object.getString("geming");
                    mm_zan = object.getString("zan");
                    mm_flower = object.getString("flower");
                    tu_url = object.getString("tu_url");
                    aftergetupdata();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private void aftergetupdata(){
        initMediaPlayer();
        mm_name.setText(user_name);
        mm_sign.setText(user_sign);
        mm_geming.setText(geming);
        HttpUtils.setPicBitmap(touxiang, tu_url);
        zan_number.setText("赞:" + String.valueOf(mm_zan));
        flower_number.setText("花:" + String.valueOf(mm_flower));
    }
    private void init(){
        User_Id.setThread(true);
        music_seekbar=(SeekBar)findViewById(R.id.music_seekbar);
        music_seekbar.setOnSeekBarChangeListener(this);
        play=(ImageView)findViewById(R.id.mm_play);
        pause=(ImageView)findViewById(R.id.mm_pause);
        stop=(ImageView)findViewById(R.id.mm_stop);
        play.setOnClickListener(this);
        pause.setOnClickListener(this);
        stop.setOnClickListener(this);
        like=(ImageView)findViewById(R.id.mm_like);
        flower=(ImageView)findViewById(R.id.mm_flower);
        comment=(ImageView)findViewById(R.id.mm_comment);
        like.setOnClickListener(this);
        flower.setOnClickListener(this);
        comment.setOnClickListener(this);
        add=(ImageView)findViewById(R.id.mm_add);
        delete=(ImageView)findViewById(R.id.mm_delete);
        add.setOnClickListener(this);
        delete.setOnClickListener(this);
        zan_number=(TextView)findViewById(R.id.mm_zan_number);
        flower_number=(TextView)findViewById(R.id.mm_flower_number);
        etComment = (EditText) findViewById(R.id.mm_text_comment);
        list = (ListView) findViewById(R.id.list_music_main);
        comment_ = new ArrayList<Comment_list>();
        adapter = new CommtAdapter(this, comment_);
        list.setAdapter(adapter);
        list.setOnItemClickListener(this);
        mm_name=(TextView)findViewById(R.id.mm_name);
        mm_sign=(TextView)findViewById(R.id.mm_sign);
        mm_geming=(TextView)findViewById(R.id.mm_geming);
        zan_number=(TextView)findViewById(R.id.mm_zan_number);
        flower_number=(TextView)findViewById(R.id.mm_flower_number);
        touxiang=(ImageView)findViewById(R.id.mm_touxiang);


        HttpUtils.setPicBitmap(play, IconUrl.getPLAY());
        HttpUtils.setPicBitmap(stop, IconUrl.getSTOP());
        HttpUtils.setPicBitmap(pause, IconUrl.getPAUSE());
        HttpUtils.setPicBitmap(like, IconUrl.getLIKE());
        HttpUtils.setPicBitmap(flower, IconUrl.getFLOWER());
        HttpUtils.setPicBitmap(comment, IconUrl.getCOMMENT());
        HttpUtils.setPicBitmap(add, IconUrl.getADD());
        HttpUtils.setPicBitmap(delete, IconUrl.getDELETE());


    }
    private void getdata(){
        //获得当前歌曲的评论信息
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.add("music_id", music_id);
        client.post(PhpUrl.getCommentList(), params, new JsonHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                if (statusCode == 200) {
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject object = response.getJSONObject(i);
                            String username = object.getString("user_name");
                            String comment = object.getString("comment");
                            String tu_url = object.getString("tu_url");
                            comment_.add(new Comment_list(username, comment, tu_url));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }
    private void initMediaPlayer(){
        try{
            mediaPlayer.setDataSource(music_url);
            mediaPlayer.prepare();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mm_play:
                if(!mediaPlayer.isPlaying()){
                   mediaPlayer.start();
                    startProgressUpdate();
               }
                break;
            case R.id.mm_pause:
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                }
                break;
            case R.id.mm_stop:
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.reset();
                    initMediaPlayer();
                }
                break;
            case R.id.mm_like:
                   RequestParams params = new RequestParams();
                   params.add("user_id", User_Id.getId());
                   params.add("music_id", music_id);
                client.post(PhpUrl.getZAN(), params, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                        super.onSuccess(statusCode, headers, response);
                        try {
                            JSONObject object = response.getJSONObject(0);
                            String check = object.getString("check");
                            if (check.equals("haven")) {
                                Toast.makeText(Music_main.this, "你已经赞过了(┬＿┬)", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(Music_main.this, "你成功地给他点了赞Y(^_^)Y", Toast.LENGTH_LONG).show();
                                comment_.clear();
                                getupdata();
                                getdata();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
                break;
            case R.id.mm_flower:
                RequestParams params3 = new RequestParams();
                params3.add("music_id", music_id);
                params3.add("user_id", User_Id.getId());
                client.post(PhpUrl.getFLOWER(), params3, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                        super.onSuccess(statusCode, headers, response);
                        try {
                            JSONObject object = response.getJSONObject(0);
                            String check = object.getString("check");
                            if (check.equals("no")) {
                                Toast.makeText(Music_main.this, "我已经没有花了(┬＿┬)", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(Music_main.this, "你成功地送了一朵花Y(^_^)Y", Toast.LENGTH_LONG).show();
                                comment_.clear();
                                getupdata();
                                getdata();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        super.onFailure(statusCode, headers, responseString, throwable);
                    }
                });
                break;
            case R.id.mm_comment:
                String comment = etComment.getText().toString();
                RequestParams params2 = new RequestParams();
                params2.add("comment", comment);
                params2.add("user_id", User_Id.getId());
                params2.add("music_id", music_id);
                client.post(PhpUrl.getSentComment(), params2, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int i, Header[] headers, byte[] bytes) {
                        Toast.makeText(Music_main.this, "评论成功", Toast.LENGTH_LONG).show();
                        comment_.clear();
                        getupdata();
                        getdata();
                    }

                    @Override
                    public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                        Toast.makeText(Music_main.this, "连接服务器失败，评论不成功，请稍后再试", Toast.LENGTH_LONG).show();
                    }
                });
                break;
            case R.id.mm_add:
                RequestParams params4 = new RequestParams();
                params4.add("user_id", User_Id.getId());
                params4.add("music_id", music_id);
                client.post(PhpUrl.getADD(), params4, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                        super.onSuccess(statusCode, headers, response);
                        try {
                            JSONObject obj = response.getJSONObject(0);
                            if (obj.getString("check").equals("self"))
                            {
                                Toast.makeText(Music_main.this, "不能加自己为好友噢!", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                if (obj.getString("check").equals("haven")) {
                                    Toast.makeText(Music_main.this, "你和他已经是好友了", Toast.LENGTH_SHORT).show();
                                } else {
                                    if (obj.getString("check").equals("never")) {
                                        Toast.makeText(Music_main.this, "成功发送好友请求", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(Music_main.this, "你已经发送过请求了", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
                break;
            case R.id.mm_delete:
                new AlertDialog.Builder(this)
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
                                params5.add("user_id", User_Id.getId());
                                params5.add("music_id", music_id);
                                client.post(PhpUrl.getDELETE(), params5, new JsonHttpResponseHandler() {
                                    @Override
                                    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                                        super.onSuccess(statusCode, headers, response);
                                        try {
                                            JSONObject obj = response.getJSONObject(0);
                                            if (obj.getString("check").equals("ok")) {
                                                Toast.makeText(Music_main.this, "删除成功!", Toast.LENGTH_SHORT).show();
                                                finish();
                                            } else {
                                                Toast.makeText(Music_main.this, "这不是你的歌曲!", Toast.LENGTH_SHORT).show();
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                            }
                        }).show();
                break;
            default:
                break;
        }
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        if(mediaPlayer!=null)
        {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        User_Id.setThread(false);

        return super.onKeyDown(keyCode, event);

    }

    public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3){
        Toast.makeText(Music_main.this,"44papa",Toast.LENGTH_LONG).show();

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        int dest = music_seekbar.getProgress();
        int mMax = mediaPlayer.getDuration();
        int sMax = music_seekbar.getMax();
        mediaPlayer.seekTo(mMax * dest / sMax);
    }
    public void startProgressUpdate(){
        DelayThread dThread = new DelayThread(100);
        dThread.start();
    }
    private Handler mHandle = new Handler(){
        @Override
        public void handleMessage(Message msg){
            int position = mediaPlayer.getCurrentPosition();
            int mMax = mediaPlayer.getDuration();
            int sMax = music_seekbar.getMax();
            music_seekbar.setProgress(position*sMax/mMax);
        }
    };
    public class DelayThread extends Thread {
        int milliseconds;

        public DelayThread(int i){
            milliseconds = i;
        }
        public void run() {
            while(User_Id.isThread()){
                try {
                    sleep(milliseconds);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                mHandle.sendEmptyMessage(0);
            }
        }
    }


}



