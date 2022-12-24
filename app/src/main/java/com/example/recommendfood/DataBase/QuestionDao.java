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
public interface QuestionDao {
    @Query("SELECT * FROM question")
    List<Question> getAllQuestion();

    @Insert
    void insertAll(Question... question);

    @Insert
    long insertQuestion(Question question);


    @Delete
    void delete(Question question);

    @Query("SELECT * FROM question ORDER BY id DESC LIMIT 1")
    List<Question> lastData();

    @Query("SELECT * FROM question")
    List<AnswerAndQuestion> getAnswerAndQuestion();
}

