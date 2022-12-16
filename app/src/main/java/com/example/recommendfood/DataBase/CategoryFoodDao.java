package com.example.recommendfood.DataBase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;


import com.example.recommendfood.Model.CategoryFood;

import java.util.List;

@Dao
public interface CategoryFoodDao {
    @Query("SELECT * FROM category_foods")
    List<CategoryFood> getAllCategory();

    //    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
//    List<User> loadAllByIds(int[] userIds);
//
//    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
//            "last_name LIKE :last LIMIT 1")
//    Food findByName(String first, String last);


    @Insert
    void insertAll(CategoryFood... category);

    @Insert
    void insertCategory(CategoryFood category);


    @Delete
    void delete(CategoryFood category);
//    @Query("SELECT * FROM category_foods ORDER BY id DESC LIMIT 1")
//    List<CategoryFood> lastData();
}

