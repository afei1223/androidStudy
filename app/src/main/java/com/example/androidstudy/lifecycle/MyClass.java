package com.example.androidstudy.lifecycle;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import com.example.androidstudy.StaticFun;

/**
 * 还可以使用{@link LifecycleEventObserver}
 * 如果使用👆这种，那么生命周期的事件会统一传到onStateChanged中
 * 然后解释器OnLifecycleEvent不生效
 * */
public class MyClass implements LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private void onCreate(){
        Log.i(StaticFun.TAG,"act is oncreate");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private void onResume(){
        Log.i(StaticFun.TAG,"act is onresume");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private void onStart(){
        Log.i(StaticFun.TAG,"act is onstart");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private void onDestory(){
        Log.i(StaticFun.TAG,"act is ondestory");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    private void onPause(){
        Log.i(StaticFun.TAG,"act is onpause");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    private void onStop(){
        Log.i(StaticFun.TAG,"act is onstop");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    private void onAny(){
        Log.i(StaticFun.TAG,"this is any event");
    }

//    @Override
//    public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
//        Log.i(StaticFun.TAG,"event "+event+"source "+source);
//    }

}
