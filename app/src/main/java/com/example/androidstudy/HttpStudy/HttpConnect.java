package com.example.androidstudy.HttpStudy;

import android.util.Log;

import com.example.androidstudy.StaticFun;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.xml.namespace.QName;

public class HttpConnect {
    @NewThread
    public static String post(String urlAddress, String data, List<Header> headers, File file) {
        try {
            URL url = new URL(urlAddress);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //设置输入输出流
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setRequestMethod("POST");//设置为POST方法
            //开始设置请求头
            connection.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
            connection.setRequestProperty("Content-Type", "text/markdown");
            connection.setRequestProperty("token", "123");
            for(Header header : headers){
                connection.setRequestProperty(header.name, ""+header.con);
//                Log.i(StaticFun.TAG,"name:"+ header.name +"con "+header.con+"\n");
            }
            //开始连接
            connection.connect();
            //以输出流的形式进行给服务器传输数据
            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
            outputStream.writeBytes(data);

            if(file != null){
                // 获取文件流
                FileInputStream fileInputStream = new FileInputStream(file);
                DataInputStream inputStream = new DataInputStream(fileInputStream);

                // 每次上传文件的大小(文件会被拆成几份上传)
                int bytes = 0;

                // 每次上传的大小
                byte[] bufferOut = new byte[1024];
                // 上传文件
                while ((bytes = inputStream.read(bufferOut)) != -1) {
                    // 上传文件(一份)
                    outputStream.write(bufferOut, 0, bytes);
//                utils.logD("progress:" +(count / fileSize * 100) +"%");
                }
            }


            outputStream.flush();
            outputStream.close();
            //服务器进行响应
            if (connection.getResponseCode() == 200) {
                InputStream is = connection.getInputStream();
                //读取信息
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                StringBuffer stringBuffer = new StringBuffer();//使用StringBuffer来存储所有信息
                String readLine = "";//使用readLine方法来存储整行信息
                while ((readLine = reader.readLine()) != null) {
                    stringBuffer.append(readLine+"\r\n");
                }
                is.close();
                reader.close();
                connection.disconnect();
                return stringBuffer.toString();
            } else {
                //打印错误的信息
                return "ERROR:"+connection.getResponseCode();
            }
        } catch (IOException e) {
            return e.toString();
        }
    }

}
