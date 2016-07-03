package com.example.shuhang.hanghang3.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shuhang.hanghang3.R;
import com.example.shuhang.hanghang3.fragment_main.FragmentMain;
import com.example.shuhang.hanghang3.table.Var_Id;
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
public class Login extends AppCompatActivity implements OnClickListener{

    private EditText username;
    private EditText password;
    private CheckBox remember;
    private CheckBox automatic;
    AsyncHttpClient client = new AsyncHttpClient();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        Button btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);
        init();
        SharedPreferences userDetails = getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        String username1 = userDetails.getString("username", "");
        String password1 = userDetails.getString("password", "");
        Boolean remember1 = userDetails.getBoolean("remember", true);
        Boolean automatic1 = userDetails.getBoolean("automatic",false);
        username.setText(username1);
        if(remember1)
        {
            password.setText(password1);
            remember.setChecked(true);
        }
        if(automatic1)
        {
            automatic.setChecked(true);
            if(!Var_Id.isStop_login()){
                btn_login.performClick();
            }
        }
    }
    private void init(){
        Button btn_leave = (Button) findViewById(R.id.btn_leave);
        Button btn_register = (Button) findViewById(R.id.btn_register);
        btn_leave.setOnClickListener(this);
        btn_register.setOnClickListener(this);
        username=(EditText)findViewById(R.id.login_username);
        password=(EditText)findViewById(R.id.login_password);
        remember=(CheckBox)findViewById(R.id.remember);
        automatic=(CheckBox)findViewById(R.id.automatic);
        remember.setOnClickListener(this);
        automatic.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.remember:
                break;
            case R.id.automatic:
                break;
            case R.id.btn_login:
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
                                Var_Id.setId(obj.getString("user_id"));
                                Toast.makeText(Login.this,"登陆成功", Toast.LENGTH_SHORT).show();

                                SharedPreferences userDetails = getSharedPreferences("userinfo", Context.MODE_PRIVATE);
                                SharedPreferences.Editor edit = userDetails.edit();
                                edit.clear();
                                edit.putString("username", user_name.trim());
                                if(remember.isChecked()){
                                    edit.putString("password", pass_word.trim());
                                    edit.putBoolean("remember",true);
                                }
                                else
                                {
                                    edit.putBoolean("remember",false);
                                }
                                if(automatic.isChecked()){
                                    edit.putBoolean("automatic",true);
                                }
                                else
                                {
                                    edit.putBoolean("automatic", false);
                                }

                                edit.commit();
                                Intent intent = new Intent(Login.this, FragmentMain.class);
                                startActivity(intent);
                                finish();
                            }
                            else
                            {
                                Toast.makeText(Login.this,"用户名或密码不正确",Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                break;
            case R.id.btn_register:
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
                break;
            case R.id.btn_leave:
                finish();
                break;
            default:
                break;
        }
    }
}
