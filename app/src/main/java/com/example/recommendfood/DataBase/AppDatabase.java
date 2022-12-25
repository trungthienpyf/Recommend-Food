package com.example.recommendfood.DataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.recommendfood.Model.Answer;
import com.example.recommendfood.Model.CategoryFood;
import com.example.recommendfood.Model.Food;
import com.example.recommendfood.Model.Question;
import com.example.recommendfood.Model.User;

@Database(entities = {
        User.class,
        CategoryFood.class,
        Food.class,
        Question.class,
        Answer.class
}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static final String DATABASE_NAME="RecommendSystem.db";

    private static AppDatabase instance;

    public static synchronized AppDatabase getInstance(Context context){
        if(instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public abstract UserDao userDao();
    public abstract FoodDao foodDao();
    public abstract CategoryFoodDao categoryFoodDao();
    public abstract QuestionDao questionDao();
    public abstract AnswerDao answerDao();

}