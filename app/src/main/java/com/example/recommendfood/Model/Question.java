package com.example.recommendfood.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "question")
public class Question {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String question_content;


    public Question(String question_content) {
        this.question_content = question_content;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion_content() {
        return question_content;
    }

    public void setQuestion_content(String question_content) {
        this.question_content = question_content;
    }


}


