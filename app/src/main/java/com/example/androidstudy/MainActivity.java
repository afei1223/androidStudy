package com.example.androidstudy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;

import com.example.androidstudy.RoomDatabaseStudy.database.AppDatabase;
import com.example.androidstudy.RoomDatabaseStudy.databaseActivity;
import com.example.androidstudy.ServiceStudy.ServiceActivity;
import com.example.androidstudy.Wheel.WheelActivity;
import com.lb.wheel.FirstWheel;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

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

    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.databaseActivity:
                intent = new Intent(MainActivity.this,databaseActivity.class);
                startActivity(intent);
                break;
            case R.id.serviceActivity:
                intent = new Intent(MainActivity.this, ServiceActivity.class);
                startActivity(intent);
                break;
            case R.id.wheelActivity:
                intent = new Intent(MainActivity.this, WheelActivity.class);
                startActivity(intent);
                break;
        }
    }
}