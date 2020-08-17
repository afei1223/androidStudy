package com.example.androidstudy.RoomDatabaseStudy.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAll();
    @Insert
    void insertAll(User... users);

    @Query("DELETE FROM user WHERE name = (:name) and password = :password")
    void delete(String name,String password);
}
