//package com.example.recommendfood;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.LinearLayout;
//import android.widget.Toast;
//
//import com.example.recommendfood.Adapter.UserApdater;
//import com.example.recommendfood.DataBase.AppDatabase;
//import com.example.recommendfood.Model.User;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class TestActivy extends AppCompatActivity {
//
//
//    EditText edtUsername;
//    EditText edtPassword;
//    Button btnAddUser;
//    RecyclerView rcvUser;
//
//    UserApdater userApdater;
//    List<User> mListUser;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_test_activy);
//        initUi();
//
//        userApdater =new UserApdater();
//        mListUser=new ArrayList<>();
//
//        getAllUser();
//
//
//        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this);
//
//        rcvUser.setLayoutManager(linearLayoutManager);
//
//
//        rcvUser.setAdapter(userApdater);
//
//        btnAddUser.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                addUser();
//            }
//        });
//
//    }
//    private void addUser() {
//        String strUsername=edtUsername.getText().toString().trim();
//        String strPassword=edtPassword.getText().toString().trim();
//        if(TextUtils.isEmpty(strPassword)|| TextUtils.isEmpty(strUsername)){
//            return;
//        }
//
//        User user =new User(strUsername,strPassword);
//        AppDatabase.getInstance(this).userDao().insertUser(user);
//        Toast.makeText(this,"Add user success fully",Toast.LENGTH_SHORT).show();
//        edtUsername.setText("");
//        edtPassword.setText("");
//
//        getAllUser();
//    }
//    private  void getAllUser(){
//        mListUser=AppDatabase.getInstance(this).userDao().getListUser();
//
//
//
//
//        userApdater.setData(mListUser);
//    }
//    private void initUi(){
//        edtPassword=findViewById(R.id.edt_password);
//        edtUsername=findViewById(R.id.edt_username);
//        btnAddUser=findViewById(R.id.btn_addUser);
//        rcvUser=findViewById(R.id.rcv_user);
//
//    }
//}