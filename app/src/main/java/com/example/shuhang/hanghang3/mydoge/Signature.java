package com.example.shuhang.hanghang3.mydoge;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shuhang.hanghang3.R;
import com.example.shuhang.hanghang3.table.PhpUrl;
import com.example.shuhang.hanghang3.table.User_Id;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by shuhang on 2016/4/15.
 */
public class Signature extends AppCompatActivity implements View.OnClickListener{
    private TextView user_sign;
    private TextView textView;
    AsyncHttpClient client = new AsyncHttpClient();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_signature);
        init();
        getsign();
    }
    private void getsign(){
        RequestParams params = new RequestParams();
        params.add("user_id", User_Id.getId());
        client.post(PhpUrl.getUPSPACE(), params, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    JSONObject object = response.getJSONObject(0);
                    user_sign.setText(object.getString("user_sign"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private void init(){
        user_sign=(TextView)findViewById(R.id.user_sign);
        textView= (TextView) findViewById(R.id.gexin_bao);
        textView.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.gexin_bao:
                RequestParams params = new RequestParams();
                params.add("user_sign",user_sign.getText().toString());
                params.add("user_id", User_Id.getId());
                client.post(PhpUrl.getUPSIGN(), params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int i, Header[] headers, byte[] bytes) {
                        Toast.makeText(Signature.this, "保存成功", Toast.LENGTH_SHORT).show();
                        onBackPressed();
                    }

                    @Override
                    public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

                    }
                });
                break;
            default:
                break;
        }
    }
}
