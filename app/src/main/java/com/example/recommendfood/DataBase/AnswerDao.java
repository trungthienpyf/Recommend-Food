package com.example.recommendfood.DataBase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;


import com.example.recommendfood.Model.Answer;
import com.example.recommendfood.Model.AnswerAndQuestion;
import com.example.recommendfood.Model.CategoryAndFood;
import com.example.recommendfood.Model.Food;
import com.example.recommendfood.Model.Question;

import java.util.List;

@Dao
public interface AnswerDao {
    @Query("SELECT * FROM answer")
    List<Answer> getAllAnswer();

    @Insert
    void insertAll(List<Answer> answer);

    @Insert
    void insertAnswer(Answer answer);


    @Delete
    void delete(Answer answer);

    @Query("SELECT * FROM answer ORDER BY id DESC LIMIT 1")
    List<Answer> lastData();


}

