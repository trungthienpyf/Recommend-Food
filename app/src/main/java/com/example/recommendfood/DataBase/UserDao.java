package com.example.recommendfood.DataBase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.recommendfood.Model.User;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user where tk= (:tk) and password = (:password)")
    User getUser(String tk, String password);



    @Insert
    void registerUser(User users);

    @Delete
    void delete(User user);
}

