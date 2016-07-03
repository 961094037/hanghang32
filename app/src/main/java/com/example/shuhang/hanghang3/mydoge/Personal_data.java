package com.example.shuhang.hanghang3.mydoge;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shuhang.hanghang3.R;

/**
 * Created by shuhang on 2016/4/15.
 */
public class Personal_data extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_personal_data);
        TextView textView =(TextView)findViewById(R.id.ziliao_bao);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Personal_data.this, "保存完毕", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Personal_data.this, Mydoge.class);
                startActivity(intent);
            }
        });
    }
}
