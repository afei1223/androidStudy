package com.example.androidstudy.ToJson;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.androidstudy.R;
import com.example.androidstudy.StaticFun;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class ToJsonActivity extends Activity {
    private TestClass t;
    private TestClassList testClassList = test3();
    private TestClassMap map = test4();
    private TestClassClazz testClassClazz = test5();
    private TestClassOther testClassOther = test7();

    private TestClassOther test7() {
        Exception exception = new Exception();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        queue.add(2);
        queue.add(3);
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        return new TestClassOther(exception,queue,linkedList);
    }

    private TestClassClazz test5() {
        TestClassList [] testClassLists = {test2(),test2()};
        return new TestClassClazz(test4(),test6(),testClassLists);
    }

    private TestClassMap test4() {
        HashMap map = new HashMap();
        map.put("qwe","qweq");
        map.put("qw","qweqe");
        HashMap<String,Integer> hashMap = new HashMap<>();
        hashMap.put("first",1);
        hashMap.put("second",22);
        Map<Integer,String> map1 = new HashMap<>();
        map1.put(1,"first");
        map1.put(3,"three");
        return new TestClassMap(map,hashMap,map1);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        TextView textView = findViewById(R.id.textview);
        t = test1();
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(StaticFun.TAG,testClassClazz.toJson().toString()+"!!!toString");
            }
        });
    }

    @NotNull
    private TestClass test1(){
        TestClass testClass;
        int[] i = {1,3};
        String[] strings = {"1","qwe"};
        Long q = new Long(1231);
        float q1 = 12;
        char q2 = "qwertyui".charAt(1);
        Float q3 = Float.valueOf(q1);
        byte q4 = Byte.parseByte("1");
        short q5 = 12;
        long q6 = 121;
        testClass = new TestClass(i,strings,"2",true,1,1.01,
                q,new Byte("1"),new Short("2"),q3,3,q2,
                q4,q5,432432,q6,23.42,false,test4());
        return testClass;
    }

    private TestClassList test2(){
        TestClassList testClassList;
        int[] q = {1,2};
        String[] w = {"1","2"};
        List<Integer> q1 = new ArrayList<>();
        q1.add(3);
        q1.add(4);
        List<String> w1 = new ArrayList<>();
        w1.add("3");
        w1.add("4");
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(2);
        arrayList.add(3);
        testClassList = new TestClassList(q,w,q1,w1,arrayList);
        return testClassList;
    }

    private TestClassList test3(){
        TestClassList testClassList;
        int[] q = {1,2};
        String[] w = {"1","2"};
        List<Integer> q1 = new ArrayList<>();
        q1.add(3);
        q1.add(4);
        List<String> w1 = new ArrayList<>();
        w1.add("3");
        w1.add("4");
        Integer[] integers = {5,6};
        char[] chars = {'1','2'};
        short[] shorts = {1,2};
        Short[] shorts1 = {3,4};
        long[] longs = {1,2};
        Long[] longs1 = {Long.valueOf(3), Long.valueOf(4)};
        float[] num = {1,3};
        Float[] num1 = {Float.valueOf(2), Float.valueOf(4)};
        double[] num2 = {1.3,2.1};
        Double[] num3 = {1.4,5.2};
        boolean[] booleans = {true,true};
        Boolean[] booleans1 = {false,false};
        byte[] bytes = {1,2};
        Byte[] bytes1 = {1,3};
//        testClassList = new TestClassList(q,w,q1,w1);
        testClassList = new TestClassList(q,w,q1,w1,booleans1,integers,num3,longs1,bytes1,shorts1,num1,chars,bytes,shorts,longs,num,num2,booleans);
        return testClassList;
    }

    private TestClass test6(){
        return new TestClass("new",123);
    }
}
