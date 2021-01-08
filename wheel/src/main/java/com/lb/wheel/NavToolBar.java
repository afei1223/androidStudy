package com.lb.wheel;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class NavToolBar extends LinearLayout {

    private String TAG = this.getClass().getSimpleName();

    private TextView titleLable;
    private Context context;

    public NavToolBar(final Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        //设置布局
        LayoutInflater.from(context).inflate(R.layout.toolbar_layout,this,true);

        titleLable = findViewById(R.id.toolbar_title);

        //根据xml中的参数选择如何显示组件
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.ToolbarLayout);
        if(attributes != null){
            String title = attributes.getString(R.styleable.ToolbarLayout_titleLabel);
            titleLable.setText(title);
//            boolean leftButtonVisible = attributes.getBoolean(R.styleable.ToolbarLayout_haveLeftButton, true);
//            if (leftButtonVisible) {
//                buttonLeft.setVisibility(View.VISIBLE);
//            } else {
//                buttonLeft.setVisibility(View.INVISIBLE);
//            }
//            boolean rightButtonVisible = attributes.getBoolean(R.styleable.ToolbarLayout_haveRightButton, true);
//            if (rightButtonVisible) {
//                buttonRight.setVisibility(View.VISIBLE);
//            } else {
//                buttonRight.setVisibility(View.INVISIBLE);
//            }
        }
    }

    //返回textview
    public TextView getTitleLable(){
        return titleLable;
    }

    //添加按钮
    public Button addButton(int dp, String text){
        final Button button = new Button(context);

        final int px = DPtoPX.dip2px(context,dp);
        button.setLayoutParams(new LinearLayout.LayoutParams(px,px));
        button.setMinimumHeight(0);
        button.setMinimumWidth(0);
        button.setText(text);
//        button.setBackground(R.drawable.ic_more);
//        button.setBackgroundResource(R.drawable.ic_more);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"!"+"我是新来的，不要欺负我哦！",Toast.LENGTH_LONG).show();
            }
        });
        LinearLayout linearLayout = findViewById(R.id.lineraLayout_button_list);
        linearLayout.addView(button);

        return button;
    }

    //删除按钮
    public void delButton(Button button){
        LinearLayout linearLayout = findViewById(R.id.lineraLayout_button_list);
        linearLayout.removeView(button);
    }

}
