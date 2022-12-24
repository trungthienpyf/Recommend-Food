package com.example.recommendfood.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recommendfood.DataBase.AppDatabase;
import com.example.recommendfood.Model.Answer;
import com.example.recommendfood.Model.AnswerAndQuestion;
import com.example.recommendfood.Model.Question;
import com.example.recommendfood.R;

import java.util.List;


public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder>{

    private List<AnswerAndQuestion> mListQuestion;

    public void setData(List<AnswerAndQuestion> list){
        this.mListQuestion=list;

        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.crud_question,parent,false);
        return new QuestionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position) {
        AnswerAndQuestion question= mListQuestion.get(position);

        if(question ==null){

            return;
        }

        holder.question_content.setText(question.question.getQuestion_content());

        for( Answer c : question.answerList){
          Log.i("trainning",c.getAnswer()+"abc");
        }


       holder.answer1.setText("1 "+question.answerList.get(0).getAnswer());
        holder.answer2.setText( "2 " +question.answerList.get(1).getAnswer());
        if(!question.answerList.get(2).getAnswer().isEmpty())
            holder.answer3.setText( "3 " +question.answerList.get(2).getAnswer());
    }
    @Override
    public int getItemCount() {
        if(mListQuestion!=null){

            return mListQuestion.size();
        }
        return 0;
    }

    public  class QuestionViewHolder extends RecyclerView.ViewHolder{

        TextView question_content;
        TextView answer1;
        TextView answer2;
        TextView answer3;


        public QuestionViewHolder(@NonNull View itemView) {
            super(itemView);
            question_content= itemView.findViewById(R.id.question_content);
            answer1= itemView.findViewById(R.id.answer1);
            answer2= itemView.findViewById(R.id.answer2);
            answer3= itemView.findViewById(R.id.answer3);

        }


    }
}
