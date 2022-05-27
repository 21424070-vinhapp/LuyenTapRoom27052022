package com.example.luyentaproom27052022.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.luyentaproom27052022.User;

import java.util.List;

@Dao
public interface UserDAO {
    @Insert
    void insertUser(User user);

    @Query("select * from user")
    List<User> getUser();

    @Query("select * from user where username= :username")
    List<User> checkUser(String username);

    @Update
    void updateUser(User user);

    @Delete
    void deleteUser(User user);

    @Query("delete from user")
    void deleteAllUser();

    @Query("select * from user where username like '%' || :name || '%'")
    List<User> searchUser(String name);
}
