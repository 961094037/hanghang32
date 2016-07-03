package com.example.shuhang.hanghang3.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shuhang.hanghang3.R;
import com.example.shuhang.hanghang3.table.PhpUrl;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;


/**
 * Created by shuhang on 2016/4/26.
 */
public class Register extends AppCompatActivity {
    private EditText uesrname;
    private EditText password;
    private EditText repassword;
    private EditText tel;
    private EditText validate;
    private Button   register;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_register);
        uesrname=(EditText)findViewById(R.id.rgs_name);
        password=(EditText)findViewById(R.id.rgs_password);
        repassword=(EditText)findViewById(R.id.rgs_repass);
     //   tel=(EditText)findViewById(R.id.rgs_tel);
     //   validate=(EditText)findViewById(R.id.rgs_validata);
        register=(Button)findViewById(R.id.register);
     //   Button btn_validate=(Button)findViewById(R.id.rgs_getvalidata);
        Button btn_cancle = (Button) findViewById(R.id.cancle);
        btn_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
            }
        });
        Button btn_register =(Button)findViewById(R.id.register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncHttpClient client = new AsyncHttpClient();
                RequestParams params = new RequestParams();
                String user_name = uesrname.getText().toString();
                String pass_word = password.getText().toString();
                String repass_word = repassword.getText().toString();
                if(pass_word.toString().equals(repass_word.toString())){
                    params.add("user_name",user_name);
                    params.add("pass_word",pass_word);
                    client.post(PhpUrl.getREGISTER(), params, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int i, Header[] headers, byte[] bytes) {
                            Toast.makeText(Register.this, "注册成功!", Toast.LENGTH_LONG).show();
                            onBackPressed();
                        }

                        @Override
                        public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                            Toast.makeText(Register.this, "注册失败!", Toast.LENGTH_LONG).show();
                        }
                    });
                }
                else {
                    Toast.makeText(Register.this, "两次输入的密码不相同！",Toast.LENGTH_LONG).show();
                }
            }
        });



    }

}
