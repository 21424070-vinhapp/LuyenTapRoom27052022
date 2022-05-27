package com.example.luyentaproom27052022.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.luyentaproom27052022.User;

import java.util.List;

@Dao
public interface UserDAO {
    @Insert
    void insertUser(User user);

    @Query("select * from user")
    List<User> getUser();
}
