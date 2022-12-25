package com.example.recommendfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PageAdmin extends AppCompatActivity {
    Button btn_question_ql;
    Button btn_category_ql;
    Button btn_food_ql;
    Button btn_logout_admin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_admin);
        btn_category_ql=findViewById(R.id.btn_category_ql);
        btn_food_ql=findViewById(R.id.btn_food_ql);
        btn_question_ql=findViewById(R.id.btn_question_ql);
        btn_logout_admin=findViewById(R.id.btn_logout_admin);
        btn_category_ql.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PageAdmin.this, CategoryCRUD.class));
            }
        });
        btn_food_ql.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PageAdmin.this, FoodCRUD.class));
            }
        });
        btn_question_ql.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PageAdmin.this, QuestionCRUD.class));
            }
        });
        btn_logout_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PageAdmin.this, Login.class));
            }
        });
    }
}