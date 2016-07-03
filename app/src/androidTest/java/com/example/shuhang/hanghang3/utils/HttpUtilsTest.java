package com.example.shuhang.hanghang3.utils;

import android.os.Handler;

import junit.framework.TestCase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by shuhang on 2016/6/3.
 */
public class HttpUtilsTest extends TestCase {

     String zan=null;
    public void setUp() throws Exception {


    }

    public void tearDown() throws Exception {

    }

    public void testGetListJSON() throws Exception {
        String url = "http://localhost:81/community_list.php";
        while (zan==null) {
            Handler getListHandler = new Handler() {
                public void handleMessage(android.os.Message msg) {
                    String jsonData = (String) msg.obj;
                    try {
                        JSONArray jsonArray = new JSONArray(jsonData);
                        //       for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(0);
                        String geming = object.getString("geming");
                        zan = object.getString("zan");
                        String flower = object.getString("flower");
                        String pl = object.getString("pl");
                        String tu_url = object.getString("tu_url");
                        String id = object.getString("id");

                        //       }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };

        HttpUtils.getListJSON(url,getListHandler);
        }

        assertEquals("1",zan);
    }

    public void testSetPicBitmap() throws Exception {

    }

    public void testGetListJSON1() throws Exception {

    }

    public void testSetPicBitmap1() throws Exception {

    }
}