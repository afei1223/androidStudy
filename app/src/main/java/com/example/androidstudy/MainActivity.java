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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ScrollView scrollView = findViewById(R.id.main_scrollView);
        scrollView.setVerticalScrollBarEnabled(false);
        Button button_databaseActivity = findViewById(R.id.databaseActivity);
        button_databaseActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,databaseActivity.class);
                startActivity(intent);
            }
        });
    }
}