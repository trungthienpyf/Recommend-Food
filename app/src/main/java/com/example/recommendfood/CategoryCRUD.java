package com.example.recommendfood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.recommendfood.Adapter.CategoryFoodAdapter;

import com.example.recommendfood.DataBase.AppDatabase;
import com.example.recommendfood.Model.CategoryFood;


import java.util.ArrayList;
import java.util.List;

public class CategoryCRUD extends AppCompatActivity {

    EditText edtName;

    Button btnAddCategory;
    RecyclerView rcvUser;

    CategoryFoodAdapter categoryFoodAdapter;
    List<CategoryFood> mListUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_crud);
        initUi();

        categoryFoodAdapter =new CategoryFoodAdapter();
        mListUser=new ArrayList<>();

        getAllUser();


        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this);

        rcvUser.setLayoutManager(linearLayoutManager);


       rcvUser.setAdapter(categoryFoodAdapter);

        btnAddCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addUser();
            }
        });

    }
    private void addUser() {
        String strName=edtName.getText().toString().trim();

        if(TextUtils.isEmpty(strName)){
            return;
        }

        CategoryFood user =new CategoryFood(strName);
        AppDatabase.getInstance(this).categoryFoodDao().insertCategory(user);
        Toast.makeText(this,"Add user success fully",Toast.LENGTH_SHORT).show();
        edtName.setText("");


        getAllUser();
    }
    private  void getAllUser(){
        mListUser=AppDatabase.getInstance(this).categoryFoodDao().getAllCategory();




        categoryFoodAdapter.setData(mListUser);
    }
    private void initUi(){
        edtName=findViewById(R.id.edt_category_name);

        btnAddCategory=findViewById(R.id.btn_add_category);
        rcvUser=findViewById(R.id.rcv_category);

    }
}