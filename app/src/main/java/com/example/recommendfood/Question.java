package com.example.recommendfood;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.recommendfood.DataBase.AppDatabase;
import com.example.recommendfood.Logic.Train;
import com.example.recommendfood.Model.Answer;
import com.example.recommendfood.Model.AnswerAndQuestion;
import com.example.recommendfood.Model.CategoryFood;

import java.util.ArrayList;
import java.util.List;

public class Question extends AppCompatActivity implements View.OnClickListener {
    private TextView tvcauhoi;
    private TextView tvnoidungcauhoi;
    private TextView tvtraloi1,tvtraloi2;
    private List<AnswerAndQuestion> mListQuestion;
    private AnswerAndQuestion mQuestion1;
    private int currentQuestion = 0;
    List<CategoryFood> mListUser;
    List<String> saveAnswer=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        initUI();
        mListUser= AppDatabase.getInstance(this).categoryFoodDao().getAllCategory();
        mListQuestion =getListQuestion();
        if(mListQuestion.isEmpty()){
            return;
        }
        setDataQuesion(mListQuestion.get(currentQuestion));
    }

    private void setDataQuesion(AnswerAndQuestion question) {
        if(question ==null){
            return;
        }
        mQuestion1 = question;

        tvcauhoi.setText("Question "+currentQuestion);
        tvnoidungcauhoi.setText(question.question.getQuestion_content());
        tvtraloi1.setText(question.answerList.get(0).getAnswer());
        tvtraloi2.setText(question.answerList.get(1).getAnswer());
//        if(!question.answerList.get(3).getAnswer().isEmpty())
//        tvtraloi2.setText(question.answerList.get(3).getAnswer());
        tvtraloi1.setOnClickListener(this);
        tvtraloi2.setOnClickListener(this);
    }


    private void initUI(){
        tvcauhoi = findViewById(R.id.tv_question);
        tvnoidungcauhoi = findViewById(R.id.tv_content_question);
        tvtraloi1 = findViewById(R.id.tv_answer1);
        tvtraloi2 = findViewById(R.id.tv_answer2);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_answer1:
              //  tvtraloi1.setBackgroundResource(R.drawable.bg_green_conner_30);
                checkAnswer(tvtraloi1,  mQuestion1.answerList.get(0));
                break;
            case R.id.tv_answer2:
                //tvtraloi2.setBackgroundResource(R.drawable.bg_green_conner_30);
                checkAnswer(tvtraloi1,  mQuestion1.answerList.get(1));
                break;
        }
    }
    private void checkAnswer(TextView textView, Answer answer){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                saveAnswer.add(new String(answer.getAnswer()));
                    nextQuestion();

            }
        },100);
    }

    private void nextQuestion() {
        if(currentQuestion == mListQuestion.size()-1){

            for(int i=0;i<saveAnswer.size();i++){
                switch (saveAnswer.get(i)){
                    case "thanh nien (>16)":
                        saveAnswer.set(i,"thanhnien");
                                break;
                    case "thieu nien":
                        saveAnswer.set(i,"thieunien");
                        break;
                    case "Tuc gian":
                        saveAnswer.set(i,"tucgian");
                        break;
                    case "Vui ve":
                        saveAnswer.set(i,"vuive");
                        break;
                    case "Buon":
                        saveAnswer.set(i,"buon");
                        break;
                    case "Mau sang":
                        saveAnswer.set(i,"sang");
                        break;
                    case "Mau toi":
                        saveAnswer.set(i,"toi");
                        break;
                    case "Ngot":
                        saveAnswer.set(i,"ngot");
                        break;
                    case "Man":
                        saveAnswer.set(i,"man");
                        break;
                    case "Nu":
                        saveAnswer.set(i,"nu");
                        break;
                    case "Nam":
                        saveAnswer.set(i,"nam");
                        break;
                }

            }
            Train tr=new Train();
            Log.d("test", tr.logicfunc(mListUser,saveAnswer));
            Intent myIntent = new Intent(this, ShowFood.class);
            myIntent.putExtra("key", '1'); //Optional parameters
            this.startActivity(myIntent);
//            for (String a:saveAnswer){
//                Log.d("test", a);
//            }
        }
        else{
            currentQuestion++;
            setDataQuesion(mListQuestion.get(currentQuestion));
        }
    }
    private void showDialog(String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setPositiveButton("các món ăn dành cho bạn", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                currentQuestion = 0;
                setDataQuesion(mListQuestion.get(currentQuestion));
                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();

    }

    private List<AnswerAndQuestion> getListQuestion(){


            List<AnswerAndQuestion> listQuestion= new ArrayList<>();
        listQuestion= AppDatabase.getInstance(this).questionDao().getAnswerAndQuestion();


        return listQuestion;
    }
}