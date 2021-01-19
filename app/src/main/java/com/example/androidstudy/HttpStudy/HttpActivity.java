package com.example.androidstudy.HttpStudy;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.androidstudy.R;
import com.example.androidstudy.StaticFun;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.String.valueOf;

public class HttpActivity extends Activity implements View.OnClickListener {
    private String urls = "";

    private String path = "/storage/emulated/0/1223.jpg";
    private String videoPath = "/storage/emulated/0/1234.mp4";
    private String videoUrl = "";

    private List<Integer> buttons = new ArrayList<>();

    private URL url;
    private HttpURLConnection connection;
    private BufferedReader reader = null;
    private String res = "";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);
        buttons.add(R.id.button1);
        buttons.add(R.id.button2);
        Log.i(StaticFun.TAG, Uri.encode("{\"filePartName\":\"\",\"BUSI_FILE_TYPE\":\"\",\"CREATEDATE\":\"\",\"BUSI_SERIAL_NO\":\"JC1609244513429\",\"modelCode\":\"\",\"contentId\":\"\",\"BUSI_FILE_PAGENUM\":\"0\",\"UPLOAD_TRADE_NAME\":\"VCP\",\"BUSI_FILE_SCANUSER\":\"jin\",\"TRUENAME\":\"JC1609244513429\",\"identityId\":\"110101199607252367\",\"prodCode\":\"vcp\",\"prodName\":\"音视频平台测试\",\"purAmount\":\"10000\",\"department\":\"科技部\"}"));
        String s = Uri.encode("{\"filePartName\":\"\",\"BUSI_FILE_TYPE\":\"\",\"CREATEDATE\":\"\",\"BUSI_SERIAL_NO\":\"JC1609244513429\",\"modelCode\":\"\",\"contentId\":\"\",\"BUSI_FILE_PAGENUM\":\"0\",\"UPLOAD_TRADE_NAME\":\"VCP\",\"BUSI_FILE_SCANUSER\":\"jin\",\"TRUENAME\":\"JC1609244513429\",\"identityId\":\"110101199607252367\",\"prodCode\":\"vcp\",\"prodName\":\"音视频平台测试\",\"purAmount\":\"10000\",\"department\":\"科技部\"}");
        String s1 = "%7B%22filePartName%22%3A%22%22%2C%22BUSI_FILE_TYPE%22%3A%22%22%2C%22CREATEDATE%22%3A%22%22%2C%22BUSI_SERIAL_NO%22%3A%22JC1609244513429%22%2C%22modelCode%22%3A%22%22%2C%22contentId%22%3A%22%22%2C%22BUSI_FILE_PAGENUM%22%3A%220%22%2C%22UPLOAD_TRADE_NAME%22%3A%22VCP%22%2C%22BUSI_FILE_SCANUSER%22%3A%22jin%22%2C%22TRUENAME%22%3A%22JC1609244513429%22%2C%22identityId%22%3A%22110101199607252367%22%2C%22prodCode%22%3A%22vcp%22%2C%22prodName%22%3A%22%E9%9F%B3%E8%A7%86%E9%A2%91%E5%B9%B3%E5%8F%B0%E6%B5%8B%E8%AF%95%22%2C%22purAmount%22%3A%2210000%22%2C%22department%22%3A%22%E7%A7%91%E6%8A%80%E9%83%A8%22%7D";

        Log.i(StaticFun.TAG, "s = s1 "+(s==s1));
        Log.i(StaticFun.TAG, "equals" + s.equals(s1));
        StaticFun.buttonRegister(buttons, this, this);
//        ImageParams imageParams = new ImageParams.Builder("1","2","2",1,1).setAgentId("2").build();
//        ParamsToString(imageParams);
//        ImageParams.Builder builder = new ImageParams.Builder();
//        builder.setAgentId("1").setBucketName("2");
//        Log.i(StaticFun.TAG,"params:"+imageParams.getAgentId()
//                +"\n"+imageParams.getAppKey()+"\n" +imageParams.getBucketName()
//                +"\n"+imageParams.getCallId()+"\n"+imageParams.getFileName());
    }

    private void Test2(String s) {
//        try {
//            JSONObject jsonObject = new JSONObject(s);
//            String msg = (String) jsonObject.get("msg");
//            if(msg.equals("ok")){
//                JSONObject data = (JSONObject) jsonObject.get("data");
//                String filePath = (String) data.get("filePath");
//            }
//            Log.i(StaticFun.TAG, "this is map"+jsonObject);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
    }

    private String ParamsToString(ParamString imageParams) {
        HashMap hashMap = imageParams.toMap();
        String s = "?";
        //利用反射获取类名
        List<String> names = imageParams.getNames();
        for(String name : names){
            if(hashMap.get(name) != null)
                s = s+name+"="+hashMap.get(name)+"&";

        }
        return s;
    }

//    public void post(){
//        try {
//            url = new URL(urls);
//            connection = (HttpURLConnection) url.openConnection();
//            //设置请求方法
//            connection.setRequestMethod("POST");
//            //设置连接超时时间（毫秒）
//            connection.setConnectTimeout(5000);
//            //设置读取超时时间（毫秒）
//            connection.setReadTimeout(5000);
//            //返回输入流
//            InputStream in = connection.getInputStream();
//
//            //读取输入流
//            reader = new BufferedReader(new InputStreamReader(in));
//            StringBuilder result = new StringBuilder();
//            String line;
//            while ((line = reader.readLine()) != null) {
//                result.append(line);
//            }
//            Log.i(StaticFun.TAG,"this is result "+result.toString());
////            show(result.toString());
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }



    @Override
    public void onClick(View v) {
        Runnable runnable = null;
        switch (v.getId()){
            case R.id.button1:
                runnable = new Runnable() {
                    @Override
                    public void run() {
                        String url = "http://192.168.12.54:9997/v1/upload/video?appKey=ff306788549f316527bf5093&callId=12334444&recordTime\n" +
                                "                         =4013632&recordBeginTimestamp=12133232321&recordEndTimestamp=121332323212&fileName=xx.mp4";
                        File file = new File(videoPath);
                        try {
                            res = OkHttpConnect.post(url, file, new JCUploadCallback() {
                                @Override
                                public void onFileUpdate(float percentage) {
                                    Log.i(StaticFun.TAG, "已完成"+percentage);
                                }

                                @Override
                                public void onFileResult(String response) {
                                    Log.i(StaticFun.TAG, "任务完成"+response);
                                }

                                @Override
                                public void onFileError(long id, Exception e) {
                                    Log.i(StaticFun.TAG,"id "+id+"!e "+e);
                                }
                            });
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Test2(res);
                    }
                };
                break;
            case R.id.button2:
                runnable = new Runnable() {
                    @Override
                    public void run() {
                        File file = new File(path);
//                        ImageParams imageParams = new ImageParams.Builder("ff306788549f316527bf5093", "12334444", "fileName", 19971223, 1).setAgentId("agent").build();

//                        ImageParams imageParams = new ImageParams.Builder().build();
                        String a = "ff306788549f316527bf5093";
                        String a1 = "12334444";
                        int size = 0;
                        try {
                            FileInputStream fileInputStream = new FileInputStream(file);
                            size = fileInputStream.available();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        ImageParams imageParams =
                                new ImageParams.Builder(a,a1,"1223.jpg",19971223,size)
                                        .setAgentId("agentId")
                                        .build();
                        String data = ParamsToString(imageParams);
//                        String data = "";
                        List<Header> headers = new ArrayList<>();
//                        for(String name:imageParams.getNames()){
//                            Header header = new Header();
//                            header.name = name;
//                            header.con = imageParams.toMap().get(name);
//                            headers.add(header);
//                        }

                        try {
                            res = OkHttpConnect.post(urls+data, file, new JCUploadCallback() {
                                @Override
                                public void onFileUpdate(float percentage) {
                                    Log.i(StaticFun.TAG, "已完成"+percentage);
                                }

                                @Override
                                public void onFileResult(String response) {
                                    Log.i(StaticFun.TAG, "任务完成"+response);
                                }

                                @Override
                                public void onFileError(long id, Exception e) {
                                    Log.i(StaticFun.TAG,"id "+id+"!e "+e);
                                }
                            });
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
//                        res = HttpConnect.post(urls, data, headers, file);
                        Test2(res);
//                        Log.i(StaticFun.TAG,"post result "+res);

                    }
                };
                break;
        }
        if (runnable != null){
            Thread thread = new Thread(runnable);
            thread.start();
        }
    }
}
