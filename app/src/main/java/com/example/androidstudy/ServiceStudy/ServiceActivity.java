package com.example.androidstudy.ServiceStudy;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.example.androidstudy.R;

import java.util.concurrent.Executor;

public class ServiceActivity extends Activity implements View.OnClickListener{
    private String TAG = "ServiceIntentActivity";
    private Boolean flag = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        Button button_start = findViewById(R.id.intentservce_study_start);
        button_start.setOnClickListener(this);

        Button button_stop = findViewById(R.id.intentservce_study_stop);
        button_stop.setOnClickListener(this);

        Button button_bind = findViewById(R.id.intentservce_study_bind);
        button_bind.setOnClickListener(this);

        Button button_unbind = findViewById(R.id.intentservce_study_unbind);
        button_unbind.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this,StudyIntentService.class);
        switch (view.getId()){
            //启动服务
            case R.id.intentservce_study_start:
                this.startService(intent);
                break;
            //结束服务
            case R.id.intentservce_study_stop:
                this.stopService(intent);
                break;
            //绑定服务
            case R.id.intentservce_study_bind:
                gotoBindService(intent);
                break;
            //解绑服务
            case R.id.intentservce_study_unbind:
                gotoUnbindService(intent);
                break;
        }
    }

    @Override
    public boolean bindService(Intent service, ServiceConnection conn, int flags) {
        if(conn != null){
            Log.i(TAG,"conn is not null !");
        }else {
            Log.i(TAG,"conn is null");
        }
        if(service != null){
            Log.i(TAG,"Intent is not null");
        }else {
            Log.i(TAG,"Intent is null");
        }
        Log.i(TAG,"this is flag "+flags);
        return super.bindService(service, conn, flags);
    }

    @Override
    public boolean bindService(Intent service, int flags, Executor executor, ServiceConnection conn) {
        Log.i(TAG,"this is bindService");
        return super.bindService(service, flags, executor, conn);
    }

//    //启动服务
//    public void gotoStartService(){
//        Intent intent = new Intent(this,StudyIntentService.class);
//        this.startService(intent);
//    }
//
//    //停止服务
//    public void gotoStopService(){
//        Intent intent = new Intent(this,StudyIntentService.class);
//        this.stopService(intent);
//    }

    //绑定服务
    public void gotoBindService(Intent intent){
        this.bindService(intent,conn,BIND_AUTO_CREATE);
    }

    //解绑服务
    public void gotoUnbindService(Intent intent){
        if(flag){
            this.unbindService(conn);
            flag = false;
        }
    }

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            StudyIntentService.ServiceBind serviceBind = (StudyIntentService.ServiceBind) iBinder;
            StudyIntentService studyIntentService = serviceBind.getService();
            flag = true;
            studyIntentService.setCallBack(new ServiceCallback() {
                @Override
                public void sendMsgs(String msg) {
                    Log.i(TAG,"there is msg : " + msg);
                }
            });
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

}
