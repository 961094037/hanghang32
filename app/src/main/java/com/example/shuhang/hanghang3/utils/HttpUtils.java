package com.example.shuhang.hanghang3.utils;

/**
 * Created by shuhang on 2016/5/11.
 */

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
public class HttpUtils {

    public static  void getListJSON(final String url,final Handler handler){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection conn;
                InputStream is;
                try {
                    conn = (HttpURLConnection) new URL(url).openConnection();
                    conn.setRequestMethod("GET");
                    is = conn.getInputStream();
                    BufferedReader reader =new BufferedReader(new InputStreamReader(is));
                    String line ="";
                    StringBuilder result =new StringBuilder();
                    while ((line =reader.readLine())!=null){
                        result.append(line);
                    }
                    Message msg =new Message();
                    msg.obj =result.toString();
                    handler.sendMessage(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
    public static void setPicBitmap(final ImageView ivPic, final String tu_url) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    HttpURLConnection conn = (HttpURLConnection) new URL(tu_url).openConnection();
//                    conn.connect();
//                    InputStream is = conn.getInputStream();
//                    Bitmap bitmap = BitmapFactory.decodeStream(is);
//                    ivPic.setImageBitmap(bitmap);
//                    is.close();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//    }
        new AsyncTask<String, Void, Bitmap>() {
            @Override
            protected Bitmap doInBackground(String... params) {
                HttpURLConnection conn = null;
                Bitmap bitmap = null;
                try {
                    conn = (HttpURLConnection) new URL(params[0]).openConnection();
                    conn.connect();
                    InputStream is = conn.getInputStream();
                    bitmap = BitmapFactory.decodeStream(is);
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return bitmap;
            }
            @Override
            protected void onPostExecute(Bitmap bitmap) {
                ivPic.setImageBitmap(bitmap);
            }
        }.execute(tu_url);
    }
}
