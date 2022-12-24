package com.example.recommendfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.recommendfood.DataBase.AppDatabase;
import com.example.recommendfood.Model.User;

import java.util.regex.Pattern;

public class Register extends AppCompatActivity {


    EditText userName, userPass, userRePass, edtTuoi, edtChieuCao, edtCanNang;

    Button btnRegister;
    boolean passwordVisible;
    RadioButton Male, Female;
    RadioGroup genderRadioGroup;


    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";

    private static final String USERNAME_PATTERN = "^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$";

    private static final String HEIGHT_PATTERN = "^[0-9]";

    private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
    private static final Pattern pattern1 = Pattern.compile(USERNAME_PATTERN);
    private static final Pattern pattern2 = Pattern.compile(HEIGHT_PATTERN);


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
                boolean check = validate(userName.getText().toString(), userPass.getText().toString());
                if (check) {
                    registerAccount();
                }
            }
        });

    }



    public void registerAccount() {

        User user = new User(userName.getText().toString(), userPass.getText().toString());
        AppDatabase.getInstance(this).userDao().registerUser(user);
        Toast.makeText(getApplicationContext(), "Successful account registration", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(Register.this, Login.class);
        startActivity(intent);
    }


    public boolean checkValidateInput(User user) {
        return !user.getName().isEmpty() || user.getPassword().isEmpty();
    }


    public boolean validate(String username, String password) {
        String ten = userName.getText().toString();
        String pass = userPass.getText().toString();
        String repass = userRePass.getText().toString();
        String tuoi = edtTuoi.getText().toString();
        String chieucao = edtChieuCao.getText().toString();
        String cannang = edtCanNang.getText().toString();

        if (!ten.matches("^(?=.{3,20}$)[a-zA-Z0-9._-]*[a-zA-Z][a-zA-Z0-9._-]*$")) {

            userName.setError(" 3 to 20 characters long, starts and ends with a letter, and can contain letters, numbers, periods, underscores, and hyphens in between.");
            return false;
        } else if (!pass.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$")) {
            userPass.requestFocus();
            userPass.setError("The password must contain at least one lowercase character, one uppercase character, one digit, one special character, and a length between 8 to 20.");
            return false;
        } else if (!repass.equals(pass)) {
            userRePass.requestFocus();
            userRePass.setError("Password do not match");
            return false;
        } else if (!tuoi.matches("^(1[0-2]|[1-9])$")) {
            edtTuoi.requestFocus();
            edtTuoi.setError("Age must be a positive integer between 1 and 120");
            return false;
        } else if (!chieucao.matches("^[0-9]+(\\.[0-9]{1,2})?$")) {
            edtChieuCao.requestFocus();
            edtChieuCao.setError("Height must be decimal");
            return false;
        } else if (!cannang.matches("^([1-9][0-9]|1[01][0-9]|120)$")) {
            edtCanNang.requestFocus();
            edtCanNang.setError("Weight between 10 and 120");
            return false;
        } else {
            return true;
        }
    }

}