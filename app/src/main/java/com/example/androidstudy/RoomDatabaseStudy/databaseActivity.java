package com.example.androidstudy.RoomDatabaseStudy;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.androidstudy.MainActivity;
import com.example.androidstudy.R;
import com.example.androidstudy.RoomDatabaseStudy.database.AppDatabase;
import com.example.androidstudy.RoomDatabaseStudy.database.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.TreeMap;

public class databaseActivity extends Activity {
    String TAG = "databaseActivity";
    RecyclerView recyclerView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        recyclerView = findViewById(R.id.database_recycler);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        final AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").build();

        Button button_database_insert = findViewById(R.id.button_database_insert);
        button_database_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final User user = getUser();
                insertDatabase(db,user);
            }
        });

        Button button_database_delete = findViewById(R.id.button_database_delete);
        button_database_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final User user = getUser();
                user.uid = 3;
                deleteDatabase(db,user);
            }
        });

        Button button_database_select = findViewById(R.id.button_database_select);
        button_database_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               selectDatabase(db,handler);
            }
        });
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 9527) {
                List<User> users = new ArrayList<>();
                users = (List<User>) msg.obj;
                Log.i(TAG,"!!!!"+users.size());
                if(users.size()<1){
                    Toast.makeText(getApplicationContext(),"数据库暂无数据!",Toast.LENGTH_LONG).show();
                }else {
                    addToRecycle(users);
                }
            }
        }
    };

    private User getUser() {
        EditText editTextName = findViewById(R.id.edit_name);
        EditText editTextPassword = findViewById(R.id.edit_password);
        String name = editTextName.getText().toString();
        String password = editTextPassword.getText().toString();
        User user = new User();
        if(name!=null&&name!=""){
            user.name = name;
        }else{
            user.name = "姓名";
        }
        if(password!=null&&password!=""){
            user.password = password;
        }else{
            user.password = "密码";
        }
        return user;
    }

    public void insertDatabase(final AppDatabase db, final User... user){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    db.userDao().insertAll(user);
                }catch (Exception e){
                    Log.i(TAG,"insert error: "+e);
                }
            }
        };
        new Thread(runnable).start();
    }

    public void deleteDatabase(final AppDatabase db, final User user){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    db.userDao().delete(user.name,user.password);
                }catch (Exception e){
                    Log.i(TAG,"delete error: "+e);
                }
            }
        };
        new Thread(runnable).start();
    }

    public void selectDatabase(final AppDatabase db, final Handler handler){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                try {
                    Message msg = handler.obtainMessage();
                    List<User> users = db.userDao().getAll();
                    msg.obj = users;
                    msg.what = 9527;
                    handler.sendMessage(msg);
                    for(User user : users){
//                        Toast.makeText(getApplicationContext(),"name:"+user.name+"!"
//                                +"password:"+user.password+"!"
//                                +"uid:"+user.uid+"!",Toast.LENGTH_LONG).show();

                        Log.i(TAG,"name:"+user.name+"!\n"
                                +"password:"+user.password+"!\n"
                                +"uid:"+user.uid+"!\n");
                    }
                }catch (Exception e){
                    Log.i(TAG,"select error: "+e);
                }
                Looper.loop();
            }
        };
        new Thread(runnable).start();
    }

    private void addToRecycle(List<User> users) {
        // specify an adapter (see also next example)
        MyAdapter mAdapter = new MyAdapter(users);
        recyclerView.setAdapter(mAdapter);
    }

}
