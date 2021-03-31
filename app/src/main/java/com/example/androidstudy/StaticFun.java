package com.example.androidstudy;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class StaticFun {
    public static final String TAG = "lbtest";

    public static Button buttonRegister(int i, View.OnClickListener onClickListener, Activity activity){
        Button button = activity.findViewById(i);
        button.setOnClickListener(onClickListener);
        return button;
    }
    public static void buttonRegister(int i, View.OnClickListener onClickListener, Dialog dialog){
        Button button = dialog.findViewById(i);
        button.setOnClickListener(onClickListener);
    }
    public static TextView textviewRegister(int i, Activity activity){
        TextView textView = activity.findViewById(i);
        return textView;
    }

    public static void buttonRegister(List<Integer> buttons, View.OnClickListener onClickListener, Activity activity) {
        for(int i : buttons){
            Button button = activity.findViewById(i);
            button.setOnClickListener(onClickListener);
        }
    }

    /**
     * 从sharedPerences中获取保存的内容，
     * @param context  上下文，用于获取sharedPerences
     * @param key      存入sharedPerences中的key
     * @param object   获取sharedPerences时，如无数据则返回此值
     * */
    public static Object getLocalParams(Context context, String key, Object object ){
        SharedPreferences sp = context.getSharedPreferences("androidStudy",Context.MODE_PRIVATE);
        if (object instanceof String){
            return sp.getString(key, (String) object);
        } else if (object instanceof  Integer){
            return sp.getInt(key, (Integer) object);
        } else if (object instanceof Boolean){
            return sp.getBoolean(key, (Boolean) object);
        } else if (object instanceof  Float){
            return sp.getFloat(key, (Float) object);
        } else if (object instanceof Long){
            return sp.getLong(key, (Long) object);
        } else {
            throw new RuntimeException("请输入String,int,boolean");
        }
    }

    /**
     * 从sharedPerences中保存内容，
     * @param context  上下文，用于获取sharedPerences
     * @param key      存入sharedPerences中的key
     * @param object   存入sharedPerences中的value
     * */
    public static void putLocalParams(Context context, String key, Object object){
        SharedPreferences sp = context.getSharedPreferences("androidStudy",Context.MODE_PRIVATE);

        SharedPreferences.Editor ed = sp.edit();
        if (object instanceof String){
            ed.putString(key, (String) object);
        } else if (object instanceof  Integer){
            ed.putInt(key, (Integer) object);
        } else if (object instanceof Boolean){
            ed.putInt(key, (Integer) object);
        } else if (object instanceof  Float){
            ed.putFloat(key, (Float) object);
        } else if (object instanceof Long){
            ed.putLong(key, (Long) object);
        } else {
            ed.putString(key, object.toString());
        }
        //切切不可忘
        ed.commit();
    }
}