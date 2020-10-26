package com.example.androidstudy.Wheel;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.androidstudy.R;
import com.lb.wheel.NavToolBar;

import java.util.ArrayList;

public class WheelActivity extends Activity {
    private Button button1223;
    private ArrayList<Button> buttons = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wheel);
        Log.i("TAG","this is Wheel activity");

        final NavToolBar navToolBar = findViewById(R.id.nav_tool_bar);
        TextView textView = navToolBar.getTitleLable();
        textView.setText("导航栏");

        final Button button = findViewById(R.id.wheel_add_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button1223 = navToolBar.addButton(60,"bt"+buttons.size());
                buttons.add(button1223);
            }
        });
        Button button1 = findViewById(R.id.wheel_remove_button);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(buttons.size()>1){
                    navToolBar.delButton(buttons.get(1));
                    buttons.remove(1);
                }
            }
        });
    }
}
