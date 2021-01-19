package com.example.androidstudy.ViewModel;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.Observer;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.androidstudy.R;
import com.example.androidstudy.StaticFun;

import java.util.List;

/**
 * {@link androidx.appcompat.app.AppCompatActivity}
 * */
public class ViewModelActivity extends AppCompatActivity
        implements
        LifecycleOwner, ViewModelStoreOwner,
        View.OnClickListener {
    private Lifecycle lifecycle ;
    private ViewModelStore viewModelStore ;
    private MyViewModel model;

    private TextView textView;

    private StringBuilder stringBuilder;

    private int flag;
    private int mflag;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        textView = StaticFun.textviewRegister(R.id.textview,this);
        textView.setOnClickListener(this);
        textView.setMovementMethod(ScrollingMovementMethod.getInstance());


        stringBuilder = new StringBuilder();

        stringBuilder.append("点击屏幕观察\n\n\n\n\n");

        // Create a ViewModel the first time the system calls an activity's onCreate() method.
        // Re-created activities receive the same MyViewModel instance created by the first activity.

        ViewModelProvider viewModelProvider = new ViewModelProvider(this,new ViewModelProvider.NewInstanceFactory());
        model = viewModelProvider.get(MyViewModel.class);

        model.getFlag().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                stringBuilder.append("viewmodle flag: "+integer+"\n")
                        .append("activity flag: "+flag)
                        .append("\n\n++++++++++++++++++++++++\n");
                mflag = integer;

            }
        });
        model.getUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                stringBuilder.append("*************** the information of user ***************\n\n\n");
                for(User user : users){
                    stringBuilder.append("name: "+user.getName()+"\n")
                            .append("height: "+user.getHeight()+"\n")
                            .append("weight: "+user.getWeight()+"\n");
                    if(user.isWoman()){
                        stringBuilder.append("sex: woman\n");
                    }else {
                        stringBuilder.append("sex: man\n");
                    }
                    stringBuilder.append("==================================\n");
                }
                stringBuilder.append("\n\n\n************************* end *************************");
                textView.setText(stringBuilder.toString());
            }
        });
        flag = 1;

    }

    @NonNull
    @Override
    public ViewModelStore getViewModelStore() {
        if(viewModelStore == null)
            viewModelStore = new ViewModelStore();
        return viewModelStore;
    }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        if(lifecycle == null)
            lifecycle = new LifecycleRegistry(this);

        return lifecycle;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.textview){
            flag ++;
            model.addFlag();
            model.changeUser();

            if(mflag%2 == 0){
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            }else {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            }

        }

    }

}
