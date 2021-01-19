package com.example.androidstudy.ViewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.androidstudy.StaticFun;

import java.util.ArrayList;
import java.util.List;

public class MyViewModel extends ViewModel {
    private MutableLiveData<List<User>> users;
    private MutableLiveData<Integer> flag;

    public LiveData<List<User>> getUsers() {
        if (users == null) {
            users = new MutableLiveData<List<User>>();
            loadUsers();
        }
        return users;
    }

    public LiveData<Integer> getFlag() {
        if(flag == null){
            flag = new MutableLiveData<>();
            flag.setValue(0);
        }
        return flag;
    }

    public void addFlag(){
        flag.setValue(flag.getValue()+1);
    }

    public void loadUsers() {
        // Do an asynchronous operation to fetch users.
        List<User> userList = new ArrayList<>();
        User user1 = new User("afei",171,120,false);
        User user = new User("ahu",162,89,true);

        userList.add(user);
        userList.add(user1);
        users.setValue(userList);
    }

    public void changeUser(){
        List<User> userList = new ArrayList<>();
        User user1 = new User("ahu",171,120,false);
        User user = new User("afei",162,89,true);

        userList.add(user);
        userList.add(user1);
        users.setValue(userList);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.i(StaticFun.TAG,"qwe");
    }
}