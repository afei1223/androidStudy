package com.example.androidstudy.RoomDatabaseStudy.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, version = 1 ,exportSchema = false )
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
