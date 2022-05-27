package com.example.luyentaproom27052022.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.luyentaproom27052022.User;

@Database(entities = {User.class}, version = 1)
public abstract class UserDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "user.db";
    private static UserDatabase instance;

    //migrate database
//    static Migration migration_1_to_2 = new Migration(1,2) {
//        @Override
//        public void migrate(@NonNull SupportSQLiteDatabase database) {
//            //ALTER TABLE ten_bang ADD ten_cot dinh_nghia_cot
//            database.execSQL("ALTER TABLE user ADD COLUMN year String");
//        }
//    };

    public static synchronized UserDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), UserDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    //.addMigrations(migration_1_to_2)
                    .fallbackToDestructiveMigration()
                    .build();

        }
        return instance;
    }

    public abstract UserDAO userDAO();
}
