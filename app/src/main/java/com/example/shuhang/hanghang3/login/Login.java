package com.example.shuhang.hanghang3.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shuhang.hanghang3.R;
import com.example.shuhang.hanghang3.fragment_main.FragmentMain;
import com.example.shuhang.hanghang3.table.User_Id;
import com.example.shuhang.hanghang3.table.PhpUrl;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;


/**
 * Created by shuhang on 2016/4/14.
 */
public class Login extends AppCompatActivity {

    private EditText username;
    private EditText password;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        username=(EditText)findViewById(R.id.login_username);
        password=(EditText)findViewById(R.id.login_password);

        SharedPreferences userDetails = getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        String username1 = userDetails.getString("username", "");
        String password1 = userDetails.getString("password", "");

        username.setText(username1);
        password.setText(password1);


        Button btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncHttpClient client = new AsyncHttpClient();
                RequestParams params = new RequestParams();
                String user_name = username.getText().toString();
                params.add("user_name", user_name);
               client.post(PhpUrl.getLOGIN(), params, new JsonHttpResponseHandler() {
                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        System.out.println("连接服务器失败");
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                        try {
                            JSONObject obj = response.getJSONObject(0);
                            String user_name = username.getText().toString();
                            String pass_word = password.getText().toString();
                            if(pass_word.equals(obj.getString("password"))) {
                                User_Id.setId(obj.getString("user_id"));
                                Toast.makeText(Login.this,"登陆成功", Toast.LENGTH_LONG).show();

                                SharedPreferences userDetails = getSharedPreferences("userinfo", Context.MODE_PRIVATE);
                                SharedPreferences.Editor edit = userDetails.edit();
                                edit.clear();
                                edit.putString("username", user_name.trim());
                                edit.putString("password", pass_word.trim());
                                edit.commit();

                                Intent intent = new Intent(Login.this, FragmentMain.class);
                                startActivity(intent);
                            }
                            else
                            {
                                Toast.makeText(Login.this,"用户名或密码不正确",Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
        Button btn_leave = (Button) findViewById(R.id.btn_leave);
        btn_leave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Button btn_register = (Button) findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });
    }

}
