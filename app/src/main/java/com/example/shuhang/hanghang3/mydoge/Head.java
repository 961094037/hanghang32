package com.example.shuhang.hanghang3.mydoge;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.example.shuhang.hanghang3.R;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.example.shuhang.hanghang3.table.User_Id;
import com.example.shuhang.hanghang3.table.PhpUrl;
import com.loopj.android.http.*;
import org.apache.http.Header;



/**
 * Created by shuhang on 2016/4/15.
 */
public class Head extends AppCompatActivity implements View.OnClickListener{
    private LinearLayout llt1;
    private LinearLayout llt2;
    private static int CAMERA_REQUEST_CODE=1;
    private static int GALLERY_REQUEST_CODE=2;
    private static int CROP_REQUEST_CODE=3;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_head);
        llt2=(LinearLayout)findViewById(R.id.llt3_t_2);
        llt2.setOnClickListener(this);
        llt1=(LinearLayout)findViewById(R.id.llt3_t_1);
        llt1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.llt3_t_1:
                Intent intent =new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, CAMERA_REQUEST_CODE);
                break;
            case R.id.llt3_t_2:
                Intent intent2 =new Intent(Intent.ACTION_GET_CONTENT);
                intent2.setType("image/*");
                startActivityForResult(intent2,GALLERY_REQUEST_CODE);
                break;
            default:
                break;
        }
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);{
            if(requestCode == CAMERA_REQUEST_CODE)
            {
                if(data==null)
                {
                    return;
                }
                else
                {
                    Bundle extra=data.getExtras();
                    if(extra!=null)
                    {
                        Bitmap bm=extra.getParcelable("data");
                        //   ImageView imageView=(ImageView)findViewById(R.id.imageView);
                        //    imageView.setImageBitmap(bm);
                        Uri uri=saveBitmap(bm);
                        startImageZoom(uri);
                    }
                }
            }
            else if(requestCode==GALLERY_REQUEST_CODE){
                if (data==null){
                    return;
                }
                Uri uri;
                uri=data.getData();
                Uri fileUri=convertUri(uri);
                startImageZoom(fileUri);
            }
            else if(requestCode==CROP_REQUEST_CODE)
            {
                if(data==null){
                    return;
                }
                Bundle extras =data.getExtras();
                Bitmap bm=extras.getParcelable("data");
//                ImageView imageView=(ImageView)findViewById(R.id.imageView);
//                imageView.setImageBitmap(bm);
                sendImage(bm);
            }
        }
    }



    private Uri saveBitmap(Bitmap bm)
    {
        File tmpDir =new File(Environment.getExternalStorageDirectory()+"/com.mengmiangewang.avatar");
        if(!tmpDir.exists())
        {
            tmpDir.mkdirs();
        }
        File image =new File(tmpDir.getAbsolutePath()+"avatar.png");
        try {
            FileOutputStream fos=new FileOutputStream(image);
            bm.compress(Bitmap.CompressFormat.PNG,85,fos);
            fos.flush();
            fos.close();
            return  Uri.fromFile(image);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Uri convertUri(Uri uri)
    {
        InputStream is=null;
        try {
            is =getContentResolver().openInputStream(uri);
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            is.close();
            return saveBitmap(bitmap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void startImageZoom(Uri uri)
    {
        Intent intent=new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri,"image/*");
        intent.putExtra("crop", true);
        intent.putExtra("aspectx", 1);
        intent.putExtra("aspecty", 1);
        intent.putExtra("outputx", 150);
        intent.putExtra("outputy", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent,CROP_REQUEST_CODE);
    }
    private void sendImage(Bitmap bm)
    {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 60, stream);
        byte[] bytes = stream.toByteArray();
        String img = new String(Base64.encodeToString(bytes, Base64.DEFAULT));

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.add("img", img);
        params.add("user_id", User_Id.getId());
        client.post(PhpUrl.getUploadHead(), params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                Toast.makeText(Head.this, "上传头像成功", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                Toast.makeText(Head.this, "Upload Fail!", Toast.LENGTH_LONG).show();
            }
        });
    }
}
