package com.example.shuhang.hanghang3.mydoge;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.net.Uri;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shuhang.hanghang3.R;
import com.example.shuhang.hanghang3.fragment_main.FragmentMain;
import com.example.shuhang.hanghang3.login.Login;
import com.example.shuhang.hanghang3.table.PhpUrl;
import com.example.shuhang.hanghang3.table.Var_Id;
import com.example.shuhang.hanghang3.utils.HttpUtils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by shuhang on 2016/3/29.
 */
public class Mydoge extends Fragment implements View.OnClickListener{
    private LinearLayout linear1;
    private LinearLayout llt1;
    private LinearLayout llt2;
    private LinearLayout llt3;
    private LinearLayout llt4;
    private LinearLayout llt5;
    private TextView buy;
    private TextView doge_user_name;
    private TextView doge_user_sign;
    private TextView doge_leave_flower;
    private TextView doge_user_flower;
    private TextView doge_user_zan;
    private TextView doge_login;
    private ImageView imageView;

    AsyncHttpClient client = new AsyncHttpClient();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mydoge,container,false);
        linear1=(LinearLayout)view.findViewById(R.id.llt3_4);
      //  llt2=(LinearLayout)view.findViewById(R.id.llt3_2);
        llt3=(LinearLayout)view.findViewById(R.id.llt3_3);
        llt1=(LinearLayout)view.findViewById(R.id.llt3_1);
        llt4=(LinearLayout)view.findViewById(R.id.llt3_0);
        llt5=(LinearLayout)view.findViewById(R.id.llt3_5);
        buy=(TextView)view.findViewById(R.id.doge_mai);
        doge_user_name=(TextView)view.findViewById(R.id.doge_user_name);
        doge_user_sign=(TextView)view.findViewById(R.id.doge_sign);
        doge_leave_flower=(TextView)view.findViewById(R.id.doge_leaveflower);
        doge_user_zan=(TextView)view.findViewById(R.id.doge_zan);
        doge_user_flower=(TextView)view.findViewById(R.id.doge_flower);
        doge_login=(TextView)view.findViewById(R.id.doge_login);
        imageView=(ImageView)view.findViewById(R.id.doge_imageView);
        initView();
        getupdata();
        return view;
    }
    private void getupdata(){
        RequestParams params = new RequestParams();
        params.add("user_id", Var_Id.getId());
        client.post(PhpUrl.getUPSPACE(),params,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    JSONObject object = response.getJSONObject(0);
                    doge_user_name.setText(object.getString("user_name"));
                    doge_user_sign.setText(object.getString("user_sign"));
                    doge_leave_flower.setText("剩余花:"+object.getString("leave_flower"));
                    doge_user_flower.setText("获得花:"+object.getString("user_flower")+"     ");
                    doge_user_zan.setText("获得赞:"+object.getString("user_zan")+"     ");
                    HttpUtils.setPicBitmap(imageView, object.getString("tu_url"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private void initView(){
        linear1.setOnClickListener(this);
      //  llt2.setOnClickListener(this);
        llt3.setOnClickListener(this);
        llt1.setOnClickListener(this);
        llt4.setOnClickListener(this);
        llt5.setOnClickListener(this);
        buy.setOnClickListener(this);
        doge_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llt3_4:
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:15621459960"));
                startActivity(intent);
                break;
            case R.id.llt3_1:
                Intent intent1 = new Intent(getActivity(), Signature.class);
                startActivity(intent1);
                break;
//            case R.id.llt3_2:
//                Intent intent2 = new Intent(getActivity(), Personal_data.class);
//                startActivity(intent2);
//                break;
            case R.id.llt3_3:
                Intent intent3 = new Intent(getActivity(), Head.class);
                startActivity(intent3);
                break;
            case R.id.llt3_0:
                Intent intent4 = new Intent(getActivity(), Personal_space.class);
                intent4.putExtra("user_id","self");
                startActivity(intent4);
                break;
            case R.id.llt3_5:
                Intent intent5 = new Intent(getActivity(), State.class);
                startActivity(intent5);
                break;
            case  R.id.doge_mai:
                RequestParams params = new RequestParams();
                params.add("user_id", Var_Id.getId());
                client.post(PhpUrl.getGetFlower(), params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int i, Header[] headers, byte[] bytes) {
                        Toast.makeText(getActivity(), "成功买了一朵花", Toast.LENGTH_SHORT).show();
                        getupdata();
                    }

                    @Override
                    public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                    }
                });
                break;
            case R.id.doge_login:
                Var_Id.setStop_login(true);
                Intent intent6 = new Intent(getActivity(), Login.class);
                startActivity(intent6);
                getActivity().finish();
                break;
            default:
                break;
        }
    }
}
