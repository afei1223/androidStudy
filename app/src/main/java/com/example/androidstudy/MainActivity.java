package com.example.androidstudy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;

import com.example.androidstudy.DataStore.DataStoreActivity;
import com.example.androidstudy.HttpStudy.HttpActivity;
import com.example.androidstudy.RoomDatabaseStudy.databaseActivity;
import com.example.androidstudy.ServiceStudy.ServiceActivity;
import com.example.androidstudy.ToJson.ToJson;
import com.example.androidstudy.ToJson.ToJsonActivity;
import com.example.androidstudy.ViewModel.ViewModelActivity;
import com.example.androidstudy.Wheel.WheelActivity;
import com.example.androidstudy.lifecycle.LifecycleActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private List<Integer> buttons = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ScrollView scrollView = findViewById(R.id.main_scrollView);
        scrollView.setVerticalScrollBarEnabled(false);

        Button button_databaseActivity = findViewById(R.id.databaseActivity);
        button_databaseActivity.setOnClickListener(this);
        Button button_serviceActivity = findViewById(R.id.serviceActivity);
        button_serviceActivity.setOnClickListener(this);
        Button button_wheelActivity = findViewById(R.id.wheelActivity);
        button_wheelActivity.setOnClickListener(this);

        //在这里添加按钮，即自动注册了click事件
        buttons.add(R.id.http_study);
        buttons.add(R.id.view_model);
        buttons.add(R.id.data_store);
        buttons.add(R.id.lifecycle);
        buttons.add(R.id.tojson);

        StaticFun.buttonRegister(buttons, this, this);
        StaticFun.buttonRegister(R.id.button9,this,this);

        startActivity(startAct(ToJsonActivity.class));
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()){
            case R.id.databaseActivity:
                intent = new Intent(MainActivity.this,databaseActivity.class);
                break;
            case R.id.serviceActivity:
                intent = new Intent(MainActivity.this, ServiceActivity.class);
                break;
            case R.id.wheelActivity:
                intent = new Intent(MainActivity.this, WheelActivity.class);
                break;
            case R.id.http_study:
                intent = new Intent(MainActivity.this, HttpActivity.class);
                break;
            case R.id.button9:
                intent = new Intent(MainActivity.this, WaitActivity.class);
                break;
            case R.id.view_model:
                intent = startAct(ViewModelActivity.class);
                break;
            case R.id.data_store:
                intent = startAct(DataStoreActivity.class);
                break;
            case R.id.lifecycle:
                intent = startAct(LifecycleActivity.class);
                break;
            case R.id.tojson:
                intent = startAct(ToJsonActivity.class);
                break;
        }
        if(intent!=null)
            startActivity(intent);
    }

    private Intent startAct(Class<?> c){
        return new Intent(MainActivity.this,c);
    }
}