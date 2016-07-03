package com.example.shuhang.hanghang3.friend;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.preference.DialogPreference;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shuhang.hanghang3.adapter.FriendExAdapter;
import com.example.shuhang.hanghang3.mydoge.Mydoge;
import com.example.shuhang.hanghang3.R;
import com.example.shuhang.hanghang3.mydoge.Personal_space;
import com.example.shuhang.hanghang3.rank.Rank;
import com.example.shuhang.hanghang3.community.Community;
import com.example.shuhang.hanghang3.table.Item;
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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shuhang on 2016/3/29.
 */
public class Friend extends Fragment implements  View.OnClickListener,ExpandableListView.OnChildClickListener,AdapterView.OnItemLongClickListener {

    AsyncHttpClient client =new AsyncHttpClient();
    private ExpandableListView mListView;
    private FriendExAdapter mAdapter;
    private List<List<Item>> mData = new ArrayList<List<Item>>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friend,container,false);
        mListView=(ExpandableListView)view.findViewById(R.id.friend_list_view);
        ifrequest();
        init();
        getdata();
        return view;
    }

    private void ifrequest(){
        RequestParams params = new RequestParams();
        params.add("user_id", User_Id.getId());
        client.post(PhpUrl.getFriendRequest(), params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    JSONObject obj = response.getJSONObject(0);
                    if (obj.getString("check").equals("have")) {
                        Toast.makeText(getActivity(), "你有未处理的好友请求", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void init(){
        mAdapter = new FriendExAdapter(getActivity(),mData);
        mListView.setAdapter(mAdapter);
        mListView.setDescendantFocusability(ExpandableListView.FOCUS_AFTER_DESCENDANTS);
        mListView.setOnChildClickListener(this);
        mListView.setOnItemLongClickListener(this);
    }
    private void  getdata() {
        RequestParams params = new RequestParams();
        params.add("user_id", User_Id.getId());
        client.post(PhpUrl.getFRIEND(), params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                if (statusCode == 200) {
                    mData.clear();
                    User_Id.setLength(0);
                    List<Item> list = new ArrayList<Item>();
                    List<Item> list2 = new ArrayList<Item>();
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject object = response.getJSONObject(i);
                            String user_name = object.getString("user_name");
                            String user_sign = object.getString("user_sign");
                            String tu_url = object.getString("tu_url");
                            String friend_id = object.getString("friend_id");
                            if(user_name.equals("961094037hang"))
                            {
                                mData.add(list);
                                User_Id.setLength(0);
                            }
                            else
                            {
                                list.add(new Item(tu_url, user_name, user_sign, friend_id));
                                User_Id.setLength(1);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    if(User_Id.getLength()==1)
                    {
                        mData.add(list);
                    }
                    mData.add(list2);
                    mData.add(list2);
                    getrequestdata();

                }
            }
        });
    }
    private void  getrequestdata(){
        RequestParams params = new RequestParams();
        params.add("user_id", User_Id.getId());
        client.post(PhpUrl.getREQUESTDATA(), params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                if (statusCode == 200) {
                    User_Id.setLength2(0);
                    List<Item> list = new ArrayList<Item>();
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject object = response.getJSONObject(i);
                            String user_name = object.getString("user_name");
                            String user_sign = object.getString("user_sign");
                            String tu_url = object.getString("tu_url");
                            String friend_id = object.getString("friend_id");
                            if(user_name.equals("961094037hang"))
                            {
                                 mData.add(list);
                                User_Id.setLength2(0);
                            }
                            else{
                                list.add(new Item(tu_url, user_name, user_sign, friend_id));
                                User_Id.setLength2(1);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    if(User_Id.getLength2()==1)
                    {
                        mData.add(list);
                    }
                    mAdapter.notifyDataSetChanged();
                }
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){

            default:
                break;
        }
    }
    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

        String user_id=mData.get(groupPosition).get(childPosition).getFriend_id();
        Intent intent =new Intent(getActivity(), Personal_space.class);
        intent.putExtra("user_id",user_id);
        startActivity(intent);

        return true;
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

        int groupId = -1;
        int childId = -1;
        long packedPosition = mListView.getExpandableListPosition(position);
        int itmeType = ExpandableListView.getPackedPositionType(packedPosition);
        switch (itmeType) {
            case 0://表示当前选中项为父条目，即Group
                groupId = ExpandableListView.getPackedPositionGroup(packedPosition);
                break;
            case 1://表示当前选中项为子条目
                groupId = ExpandableListView.getPackedPositionGroup(packedPosition);//当前子条目所在的Group
                childId = ExpandableListView.getPackedPositionChild(packedPosition);//当前子条目id
                break;
            default:
                break;
        }
        User_Id.setFriend_id(mData.get(groupId).get(childId).getFriend_id());
        new AlertDialog.Builder(getActivity())
                .setMessage("你要接收或拒绝好友请求或者删除好友吗")
                .setNegativeButton("删除或拒绝", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        RequestParams params = new RequestParams();
                        params.add("user_id", User_Id.getId());
                        params.add("friend_id", User_Id.getFriend_id());
                        client.post(PhpUrl.getRemoveRefuse(), params, new AsyncHttpResponseHandler() {
                            @Override
                            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                                Toast.makeText(getActivity(), "成功删除或拒绝了好友！", Toast.LENGTH_SHORT).show();
                                getdata();
                            }
                            @Override
                            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                            }
                        });
                    }
                })
                .setPositiveButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNeutralButton("接受", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                RequestParams params = new RequestParams();
                                params.add("user_id", User_Id.getId());
                                params.add("friend_id", User_Id.getFriend_id());
                                client.post(PhpUrl.getACCEPT(), params, new JsonHttpResponseHandler() {
                                    @Override
                                    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                                        super.onSuccess(statusCode, headers, response);
                                        try {
                                            JSONObject obj = response.getJSONObject(0);
                                            if (obj.getString("check").equals("haven")) {
                                                Toast.makeText(getActivity(), "你跟他已经是好友了！", Toast.LENGTH_SHORT).show();
                                            } else {
                                                Toast.makeText(getActivity(), "添加好友成功！", Toast.LENGTH_SHORT).show();
                                                getdata();
                                            }

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                            }
                        }
                )
                .show();


        return true;
    }


}
