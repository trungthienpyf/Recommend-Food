package com.example.recommendfood.Model;
import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class AnswerAndQuestion {

    @Embedded
    public Question question;

    @Relation(
            parentColumn = "id",
            entityColumn = "question_id"
    )

    public
    List<Answer> answerList;


}
