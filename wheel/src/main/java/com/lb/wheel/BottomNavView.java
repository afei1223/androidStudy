package com.lb.wheel;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class BottomNavView extends LinearLayout {

    public BottomNavView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.bottom_nav_view,this,true);
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.BottomNavView);
        if(attributes != null){
            int i = attributes.getResourceId(R.styleable.BottomNavView_menu,0);
            if(attributes.hasValue(R.styleable.BottomNavView_menu)){
                LinearLayout linearLayout = findViewById(R.id.bnv);
                linearLayout.addView(findViewById(i));
            }
        }

    }

}
