package com.example.recommendfood;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.recommendfood.DataBase.AppDatabase;
import com.example.recommendfood.DataBase.UserDao;
import com.example.recommendfood.Model.User;

import java.net.DatagramPacket;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Register extends AppCompatActivity {

    EditText userName, userPass, userRePass, edtTuoi, edtChieuCao, edtCanNang;
    Button btnRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
            userName = findViewById(R.id.username);
            userPass = findViewById(R.id.password);
            userRePass = findViewById(R.id.repassword);
            btnRegister = findViewById(R.id.btnRegister);
            edtTuoi = findViewById(R.id.edtTuoi);
            edtChieuCao = findViewById(R.id.edtHeight);
            edtCanNang = findViewById(R.id.edtWeight);
            btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });

    }

/*
    private void showDate(EditText ngaysinh) {
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener  DateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.YEAR, year);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                ngaysinh.setText(simpleDateFormat.format(calendar.getTime()));
            }
        };
        new DatePickerDialog(Register.this, DateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }*/


    public boolean checkValidateInput(User user){
        if(user.getName().isEmpty() && !user.getPassword().isEmpty())
        {
            return false;
        }
        return true;
    }

    public void register(){
        User userEntites = new User(userName.getText().toString(), userPass.getText().toString());
            //init database
             AppDatabase.getInstance(this).userDao().registerUser(userEntites);
            //init dao
            Toast.makeText(getApplicationContext(), "Đăng ký thành công", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(Register.this, Login.class);
            startActivity(intent);
    }

}