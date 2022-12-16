package com.example.recommendfood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Database;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.recommendfood.DataBase.AppDatabase;
import com.example.recommendfood.DataBase.UserDao;
import com.example.recommendfood.Model.Food;
import com.example.recommendfood.Model.User;

import com.example.recommendfood.DataBase.FoodDao;

public class Login extends AppCompatActivity {
    EditText txtUsername, txtpassword;
    Button btnDangKy, btnDangNhap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("ĐĂNG NHẬP");
        setContentView(R.layout.activity_login);
        txtUsername = findViewById(R.id.username);
        txtpassword = findViewById(R.id.password);
        btnDangNhap = findViewById(R.id.btnLogin);
        btnDangKy = findViewById(R.id.btnDangKy);
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String username = txtUsername.getText().toString();
                final String password = txtpassword.getText().toString();
                if(username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Vui lòng điền đẩy đủ thông tin", Toast.LENGTH_LONG).show();
                }else
                {
                    AppDatabase appDatabase = AppDatabase.getInstance(getApplicationContext());
                    final UserDao userDao = appDatabase.userDao();
                    User user = userDao.getUser(username, password);
                    if(user == null){

                        Toast.makeText(getApplicationContext(), "Sai tài khoản", Toast.LENGTH_LONG).show();
                    }else {

                        Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(Login.this, ShowFood.class));
                    }
                }
            }

        });

        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);

                Intent myIntent = new Intent(Login.this, FoodCRUD.class);
                myIntent.putExtra("key", '1'); //Optional parameters
                Login.this.startActivity(myIntent);

            }
        });
    }
}