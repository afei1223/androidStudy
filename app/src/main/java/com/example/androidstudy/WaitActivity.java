package com.example.androidstudy;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.androidstudy.HttpStudy.Header;

import java.util.ArrayList;
import java.util.List;

public class WaitActivity extends Activity {
    private TextView textView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait);
        textView = findViewById(R.id.text_view);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startThread();
            }
        });
    }

    private void startThread() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while(true){
                    List<String> wait = new ArrayList<>();
                    wait.add("|");
                    wait.add("\\");
                    wait.add("—");
                    wait.add("/");
                    try {
                        for(String s : wait){
                            Message message = new Message();
                            message.what = 9527;
                            message.obj = s;
                            handler.sendMessage(message);
                            Thread.sleep(500);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        new Thread(runnable).start();
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if(msg.what == 9527){
                String s = (String) msg.obj;
                textView.setText(s);
            }
        }
    } ;

    Handler getHandler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
        }
    };


    private void test(){

    }
}
