package com.example.recommendfood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.recommendfood.Adapter.CategoryFoodAdapter;

import com.example.recommendfood.Adapter.QuestionAdapter;
import com.example.recommendfood.DataBase.AppDatabase;
import com.example.recommendfood.Model.Answer;
import com.example.recommendfood.Model.AnswerAndQuestion;
import com.example.recommendfood.Model.CategoryFood;
import com.example.recommendfood.Model.Question;


import java.util.ArrayList;
import java.util.List;

public class QuestionCRUD extends AppCompatActivity {

    EditText edtQuestion;
    EditText edtAnswer1;
    EditText edtAnswer2;
    EditText edtAnswer3;

    Button btnAddCategory;
    RecyclerView rcvUser;

    QuestionAdapter questionAdapter;
    List<AnswerAndQuestion> mListQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_crud);
        initUi();

        questionAdapter =new QuestionAdapter();
        mListQuestion=new ArrayList<>();

        getAllUser();


        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this);

        rcvUser.setLayoutManager(linearLayoutManager);


        rcvUser.setAdapter(questionAdapter);

        btnAddCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addUser();
            }
        });

    }
    private void addUser() {
        String strQuestion=edtQuestion.getText().toString().trim();
        String strA1=edtAnswer1.getText().toString().trim();
        String strA2=edtAnswer2.getText().toString().trim();
        String strA3=edtAnswer3.getText().toString().trim();

        if(TextUtils.isEmpty(strQuestion) || TextUtils.isEmpty(strA1) ){
            return;
        }

        Question question =new Question(strQuestion);

        long lastId=AppDatabase.getInstance(this).questionDao().insertQuestion(question);

        Toast.makeText(this,lastId+"",Toast.LENGTH_SHORT).show();
        List<Answer> listA=new ArrayList<>();
        listA.add(new Answer(strA1,(int)lastId));
        listA.add(new Answer(strA2,(int)lastId));
        listA.add(new Answer(strA3,(int)lastId));
        AppDatabase.getInstance(this).answerDao().insertAll( listA);






        Toast.makeText(this,"Add question success fully",Toast.LENGTH_SHORT).show();
        edtQuestion.setText("");
        edtAnswer1.setText("");
        edtAnswer2.setText("");
        edtAnswer3.setText("");


        getAllUser();
    }
    private  void getAllUser(){


        mListQuestion=AppDatabase.getInstance(this).questionDao().getAnswerAndQuestion();


        questionAdapter.setData(mListQuestion);
    }
    private void initUi(){
        edtQuestion=findViewById(R.id.edt_question_content);
        edtAnswer1=findViewById(R.id.edt_answer_1);
        edtAnswer2=findViewById(R.id.edt_answer_2);
        edtAnswer3=findViewById(R.id.edt_answer_3);

        btnAddCategory=findViewById(R.id.btn_add_question);
        rcvUser=findViewById(R.id.rcv_question);

    }
}