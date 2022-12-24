package com.example.recommendfood.Model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "answer", foreignKeys = {
        @ForeignKey(
                entity = Question.class,
                parentColumns = {"id"},
                childColumns = {"question_id"},
                onDelete = ForeignKey.CASCADE,
                onUpdate = ForeignKey.CASCADE
        ),

})
public class Answer {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String answer;
    private  int question_id;

    public Answer(String answer, int question_id) {
        this.answer = answer;
        this.question_id = question_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }
}
