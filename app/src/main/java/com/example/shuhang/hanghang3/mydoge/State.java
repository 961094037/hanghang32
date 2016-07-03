package com.example.shuhang.hanghang3.mydoge;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.example.shuhang.hanghang3.R;

/**
 * Created by shuhang on 2016/4/15.
 */
public class State extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_state);
        Button button1 = (Button) findViewById(R.id.btn_zhuangtai);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(State.this, "当前版本已是最新", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
