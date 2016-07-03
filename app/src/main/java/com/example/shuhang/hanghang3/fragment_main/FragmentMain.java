package com.example.shuhang.hanghang3.fragment_main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;

import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.shuhang.hanghang3.R;
import com.example.shuhang.hanghang3.community.Community;
import com.example.shuhang.hanghang3.friend.Friend;
import com.example.shuhang.hanghang3.mydoge.Mydoge;
import com.example.shuhang.hanghang3.rank.Rank;
import com.example.shuhang.hanghang3.table.Var_Id;
import com.example.shuhang.hanghang3.table.PhpUrl;
import com.example.shuhang.hanghang3.utils.FileUtils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

import java.io.File;
import java.io.FileNotFoundException;


/**
 * Created by shuhang on 2016/6/10.
 */
public class FragmentMain extends AppCompatActivity implements
        RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    private RadioGroup rg_tab;
    private ImageView iv_add;

    private Fragment communityFragment;
    private Fragment rankFragment;
    private Fragment friendFragment;
    private Fragment mydogeFragment;

    private static boolean isExit = false;
    private  String path ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        rg_tab = (RadioGroup) findViewById(R.id.rg_tab);
        iv_add = (ImageView) findViewById(R.id.iv_add);
        rg_tab.setOnCheckedChangeListener(this);
        iv_add.setOnClickListener(this);
        communityFragment = new Community();
        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, new Community()).commit();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_home:
                System.out.println("1");
                if (communityFragment == null) {
                    communityFragment = new Community();
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, communityFragment).commit();
                break;
            case R.id.rb_meassage:
                if (rankFragment == null) {
                    rankFragment = new Rank();
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, rankFragment).commit();
                System.out.println("2");
                break;
            case R.id.rb_search:
                System.out.println("3");
                if (friendFragment == null) {
                    friendFragment = new Friend();
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, friendFragment).commit();
                break;
            case R.id.rb_user:
                System.out.println("4");
                if (mydogeFragment == null) {
                    mydogeFragment = new Mydoge();
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, mydogeFragment).commit();
                break;
            default:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_add:
                Toast.makeText(this,"上传的音乐要小于20M噢~",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("audio/*");
                startActivityForResult(intent, 1);
                break;
            default:
                break;
        }
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }

        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            // 利用handler延迟发送更改状态信息
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
            finish();
            System.exit(0);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Uri uri = data.getData();
                 path = FileUtils.getPath(this, uri);
                try {
                    upLoadByAsyncHttpClient();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private void upLoadByAsyncHttpClient() throws FileNotFoundException {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("uploadfile", new File(path));
        params.add("user_id", Var_Id.getId());
        client.post(PhpUrl.getUploadMusic(),params,new AsyncHttpResponseHandler(){
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                Toast.makeText(FragmentMain.this,"文件上传成功",Toast.LENGTH_LONG).show();
                getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, new Community()).commit();
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                Toast.makeText(FragmentMain.this,"文件上传失败",Toast.LENGTH_LONG).show();
            }
        });
    }

}
