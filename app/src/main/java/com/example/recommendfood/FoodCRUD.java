package com.example.recommendfood;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.recommendfood.Adapter.FoodApdater;
import com.example.recommendfood.Adapter.UserApdater;
import com.example.recommendfood.DataBase.AppDatabase;
import com.example.recommendfood.Model.Food;
import com.example.recommendfood.Model.User;
import com.example.recommendfood.databinding.CrudFoodIndexBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class FoodCRUD extends AppCompatActivity {

    CrudFoodIndexBinding binding;

    EditText edtFoodName;
    EditText edtFoodCalo;
    EditText edtFoodSession;
    Button btnAddUser;
    Button btnSelect;
    RecyclerView rcvUser;
    ActivityResultLauncher<String> mTakePhoto;
    FoodApdater foodApdater;
    List<Food> mListUser;
    Uri imageUri;
    StorageReference storageReference;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding=CrudFoodIndexBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initUi();

        foodApdater =new FoodApdater();
        mListUser=new ArrayList<>();

        getAllUser();


        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this);

        rcvUser.setLayoutManager(linearLayoutManager);


        rcvUser.setAdapter(foodApdater);

        mTakePhoto =registerForActivityResult(
                new ActivityResultContracts.GetContent(),

                new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri result) {
                        imageUri =result;
                        binding.imageView.setImageURI(result);
                    }
                }
        );
           binding.selectImagebtn.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {

                   mTakePhoto.launch("image/*");
               }
           });

        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addUser();

                CharSequence text = "Hello toast!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(FoodCRUD.this, text, duration);
                toast.show();
                uploadImage();

            }
        });

    }
    private void addUser() {
        String strName=edtFoodName.getText().toString().trim();
        String strCalo=edtFoodCalo.getText().toString().trim();
        String strSession=edtFoodSession.getText().toString().trim();

        if(TextUtils.isEmpty(strName)|| TextUtils.isEmpty(strCalo)||TextUtils.isEmpty(strSession)){
            return;
        }

        Food food =new Food(strName,strCalo,strSession);
        AppDatabase.getInstance(this).foodDao().insertUser(food);
        Toast.makeText(this,"Add user success fully",Toast.LENGTH_SHORT).show();
        edtFoodName.setText("");
        edtFoodCalo.setText("");
        edtFoodSession.setText("");
       getAllUser();
    }
    private  void getAllUser(){
        mListUser=AppDatabase.getInstance(this).foodDao().getAllFood();

        foodApdater.setData(mListUser);
    }

    private void loadImage() throws IOException {
        progressDialog =new ProgressDialog(FoodCRUD.this);
        progressDialog.setMessage("fetch..");
        progressDialog.setCancelable(false);
        progressDialog.show();

        storageReference =FirebaseStorage.getInstance().getReference("images/");
        File file= File.createTempFile("tempfile",".jpg");
        storageReference.getFile(file).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                if(progressDialog.isShowing()){

                    progressDialog.dismiss();
                    Bitmap bitmap= BitmapFactory.decodeFile(file.getAbsolutePath());
                    binding.imageView.setImageBitmap(bitmap);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {


                if (progressDialog.isShowing())
                    progressDialog.dismiss();
                Toast.makeText(FoodCRUD.this,"Failed to Upload",Toast.LENGTH_SHORT).show();


            }
        });;

    }
    private void uploadImage() {

        progressDialog = new ProgressDialog(FoodCRUD.this);
        progressDialog.setTitle("Uploading File....");
        progressDialog.show();

        int sum= mListUser.size() >=1 ? mListUser.size() +1 : 1;

        storageReference = FirebaseStorage.getInstance().getReference("images/"+sum);


        storageReference.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        binding.imageView.setImageURI(null);
                        Toast.makeText(FoodCRUD.this,"Successfully Uploaded",Toast.LENGTH_SHORT).show();
                        if (progressDialog.isShowing())
                            progressDialog.dismiss();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {


                        if (progressDialog.isShowing())
                            progressDialog.dismiss();
                        Toast.makeText(FoodCRUD.this,"Failed to Upload",Toast.LENGTH_SHORT).show();


                    }
                });

    }
    private void initUi(){
        edtFoodName=findViewById(R.id.edt_food_name);
        edtFoodCalo=findViewById(R.id.edt_food_calo);
        edtFoodSession=findViewById(R.id.edt_food_session);
        btnAddUser=findViewById(R.id.btn_addUser);
      //  btnSelect=findViewById(R.id.btn_selectImg);
        rcvUser=findViewById(R.id.rcv_user);

    }

}