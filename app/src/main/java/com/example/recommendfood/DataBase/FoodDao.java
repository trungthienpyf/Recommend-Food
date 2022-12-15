package com.example.recommendfood.DataBase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.recommendfood.Model.Food;


import java.util.List;

@Dao
public interface FoodDao {
    @Query("SELECT * FROM foods")
    List<Food> getAllFood();

    //    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
//    List<User> loadAllByIds(int[] userIds);
//
//    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
//            "last_name LIKE :last LIMIT 1")
//    Food findByName(String first, String last);


    @Insert
    void insertAll(Food... foods);

    @Insert
    void insertUser(Food foods);


    @Delete
    void delete(Food food);
    @Query("SELECT * FROM foods ORDER BY id DESC LIMIT 1")
    List<Food> lastData();
}

