package com.example.luyentaproom27052022;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "user")
public class User implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String username;
    private String address;
    //private String year;

//    public String getYear() {
//        return year;
//    }

//    public void setYear(String year) {
//        this.year = year;
//    }

    public User(String username, String address) {
        this.username = username;
        this.address = address;
    }


//    public User(String username, String address, String year) {
//        this.username = username;
//        this.address = address;
//        this.year = year;
//    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
