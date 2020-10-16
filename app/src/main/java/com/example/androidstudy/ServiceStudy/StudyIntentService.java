package com.example.androidstudy.ServiceStudy;

import android.app.IntentService;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class StudyIntentService extends IntentService {
    private String TAG = "ServiceStudyIntent";
    int i = 0;
    private ServiceCallback callback;
    private ServiceBind serviceBind = new ServiceBind();
    /**
     * @deprecated
     */
    public StudyIntentService() {
        super("name");
        Log.i(TAG,"name:");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return serviceBind;
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        try {
            i++;
            if(callback != null){
                for(int j = 0;j<=100;j++){
                    callback.sendMsgs("this is message "+ j);
                }
            }
            Log.i(TAG,"onHandleIntent"+i);
            Thread.sleep(5000);
            Log.i(TAG,"onHandleIntent5000");
        } catch (InterruptedException e) {
            // Restore interrupt status.
            Thread.currentThread().interrupt();
        }
    }

    public void setCallBack(ServiceCallback callBack){
        this.callback = callBack;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"i am killed!");
    }


    public class ServiceBind extends Binder{
        /**
         * 声明方法返回值是MyService本身
         * @return
         */
        public StudyIntentService getService() {
            return StudyIntentService.this;
        }
    }
}
