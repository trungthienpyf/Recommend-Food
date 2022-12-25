package com.example.recommendfood;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.recommendfood.DataBase.AppDatabase;
import com.example.recommendfood.DataBase.UserDao;
import com.example.recommendfood.Model.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login extends AppCompatActivity {
    EditText txtUsername, txtpassword;
    Button btnDangKy, btnDangNhap;


    SharedPreferences pref;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("ĐĂNG NHẬP");
        setContentView(R.layout.activity_login);
        txtUsername = findViewById(R.id.username);
        txtpassword = findViewById(R.id.password);
        btnDangNhap = findViewById(R.id.btnLogin);
        btnDangKy = findViewById(R.id.btnDangKy);


        String usernamePattern = "^[a-zA-Z0-9]{8,}$";
        String passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

        pref=getSharedPreferences("acc",MODE_PRIVATE);
        if(pref.contains("username")&&pref.contains("password")){
            startActivity(new Intent(Login.this, HomePage.class));
        }
        btnDangNhap.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                final String username = txtUsername.getText().toString();
                final String password = txtpassword.getText().toString();
                        if(username.equals("admin")&&password.equals("admin") ){

                            startActivity(new Intent(Login.this, PageAdmin.class));
                        }else{
                            Pattern usernameP = Pattern.compile(usernamePattern);
                            Matcher usernameM = usernameP.matcher(username);
                            Pattern passwordP = Pattern.compile(passwordPattern);
                            Matcher passwordM = passwordP.matcher(password);

                            AppDatabase appDatabase = AppDatabase.getInstance(getApplicationContext());
                            final UserDao userDao = appDatabase.userDao();
                            User user = userDao.getUser(username, password);

                            if(java.util.regex.Pattern.matches(usernamePattern, username) && java.util.regex.Pattern.matches(passwordPattern, password))
                            {
                                if(user == null){
                                    Toast.makeText(getApplicationContext(), "Wrong password", Toast.LENGTH_LONG).show();
                                }else {
                                    SharedPreferences.Editor editor= pref.edit();
                                    editor.putString("username",username);
                                    editor.putString("password", password);
                                    editor.commit();
                                    Toast.makeText(getApplicationContext(), "Logged in successfully", Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(Login.this, HomePage.class));
                                }
                            }else{

                                Toast.makeText(getApplicationContext(), "Invalid", Toast.LENGTH_LONG).show();
                            }

                        }





            }

        });

        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Intent intent = new Intent(Login.this, Register.class);
//                startActivity(intent);




                Intent myIntent = new Intent(Login.this, Register.class);
                //myIntent.putExtra("key", '1'); //Optional parameters

                Login.this.startActivity(myIntent);

            }
        });
    }




}