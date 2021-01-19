package com.example.androidstudy.lifecycle;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;

/**
 * lifecycle只是一个工具，在需要观测activity的生命周期的时候还是很好用，
 * 主要是和viewmode之类的组合比较多，感觉在其他地方不太会用。
 * 这篇也很简单，主要是为了viewmodel的时候能更好的理解。
 *
 * -------** 入门 **-------
 *  lifecycle只是一个观察者模式，多个观察者，一个被观察者。
 *  当直接使用AppCompatActivity而不是Activity，不需要实现LifecycleOwner
 *  使用AppCompatActivity的话，只需要{getLifecycle().addObserver(myClass);}
 *  可以去看下AppCompatActivity继承的类对lifecycle的实现。
 *  {@link AppCompatActivity}
 *
 *  https://www.cnblogs.com/afei123/articles/14282943.html
 *
 * */
public class LifecycleActivity extends Activity implements LifecycleOwner {
    private LifecycleRegistry lifecycle = new LifecycleRegistry(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyClass myClass = new MyClass();
//
//        getLifecycle().addObserver(myClass);
        lifecycle.addObserver(myClass);
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_CREATE);
    }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return lifecycle;
    }

    @Override
    protected void onResume() {
        super.onResume();
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY);
    }

    @Override
    protected void onPause() {
        super.onPause();
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_STOP);
    }

    @Override
    protected void onStart() {
        super.onStart();
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_START);
    }
}
