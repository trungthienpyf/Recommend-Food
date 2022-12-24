package com.example.recommendfood;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.recommendfood.Adapter.FoodApdater;
import com.example.recommendfood.DataBase.AppDatabase;
import com.example.recommendfood.Model.CategoryAndFood;
import com.example.recommendfood.Model.CategoryFood;
import com.example.recommendfood.Model.Food;
import com.example.recommendfood.databinding.CrudFoodIndexBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;

public class FoodCRUD extends AppCompatActivity {

    CrudFoodIndexBinding binding;

    EditText edtFoodName;
    EditText edtFoodCalo;
    EditText edtFoodSession;
    Spinner spin;
    Button btnAddUser;
    Button btnSelect;
    RecyclerView rcvUser;
    ActivityResultLauncher<String> mTakePhoto;
    FoodApdater foodApdater;
    List<Food> mListUser;
    List<CategoryFood> categoryList;
    List<String> spinnerList;
    Uri imageUri;
    StorageReference storageReference;
    ProgressDialog progressDialog;
    public  int item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding=CrudFoodIndexBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initUi();

        foodApdater =new FoodApdater(new FoodApdater.IClickItemFood() {
            @Override
            public void deleleFood(Food food) {
                clickDeleteFood(food);


            }
        });
        mListUser=new ArrayList<>();

        getAllUser();
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                CategoryFood categoryFood1 = ((CategoryFood) categoryList.get(i));

                item = categoryFood1.getId();
                Toast.makeText(FoodCRUD.this,""+item,Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this);

        rcvUser.setLayoutManager(linearLayoutManager);


        rcvUser.setAdapter(foodApdater);

        mTakePhoto =registerForActivityResult(
                new ActivityResultContracts.GetContent(),

                new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri result) {


                        if(result==null){
                            return;


                        }
                        else{
                            imageUri =result;
                            binding.imageView.setImageURI(result);
                        }
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


                uploadImage();

            }
        });

    }

    private void clickDeleteFood(Food food) {
        new AlertDialog.Builder(this).setTitle("Xóa món ăn")
                .setMessage("Bạn có chắc chắn muốn xóa không?")
                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                AppDatabase.getInstance(FoodCRUD.this).foodDao().delete(food);
                getAllUser();
                Toast.makeText(FoodCRUD.this,"Xóa thành công",Toast.LENGTH_SHORT).show();
            }
        }).setNegativeButton("Không",null).show();
    }

    private void addUser() {

        String strName=edtFoodName.getText().toString().trim();
        String strCalo=edtFoodCalo.getText().toString().trim();
        String strSession=edtFoodSession.getText().toString().trim();

        if(TextUtils.isEmpty(strName)|| TextUtils.isEmpty(strCalo)||TextUtils.isEmpty(strSession)){
            Toast.makeText(FoodCRUD.this,"Vui lòng nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
            return;
        }else{


             Food food =new Food(strName,strCalo,strSession,(item)+"");


            AppDatabase.getInstance(this).foodDao().insertUser(food);
            Toast.makeText(this,"Thêm thành công",Toast.LENGTH_SHORT).show();
            edtFoodName.setText("");
            edtFoodCalo.setText("");
            edtFoodSession.setText("");
            getAllUser();
        }


    }
    private  void getAllUser(){

        List<CategoryAndFood> allList=AppDatabase.getInstance(this).foodDao().categoryAndFood();

        categoryList=AppDatabase.getInstance(this).categoryFoodDao().getAllCategory();
        spinnerList=new ArrayList<>();
        for (int i = 0; i<categoryList.size(); i++) {


            String name_promotion = categoryList.get(i).getName();

            spinnerList.add(name_promotion);
        }
        spin.setAdapter(new ArrayAdapter<>(this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item
                ,spinnerList));

        foodApdater.setData(allList);
    }

//    private void loadImage() throws IOException {
//        progressDialog =new ProgressDialog(FoodCRUD.this);
//        progressDialog.setMessage("fetch..");
//        progressDialog.setCancelable(false);
//        progressDialog.show();
//
//        storageReference =FirebaseStorage.getInstance().getReference("images/");
//        File file= File.createTempFile("tempfile",".jpg");
//        storageReference.getFile(file).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
//            @Override
//            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
//                if(progressDialog.isShowing()){
//
//                    progressDialog.dismiss();
//                    Bitmap bitmap= BitmapFactory.decodeFile(file.getAbsolutePath());
//                    binding.imageView.setImageBitmap(bitmap);
//                }
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//
//
//                if (progressDialog.isShowing())
//                    progressDialog.dismiss();
//                Toast.makeText(FoodCRUD.this,"Failed to Upload",Toast.LENGTH_SHORT).show();
//
//
//            }
//        });;
//
//    }
    private void uploadImage() {

        progressDialog = new ProgressDialog(FoodCRUD.this);
        progressDialog.setTitle("Uploading File....");
        progressDialog.show();

        List<Food> getLast;
        getLast=AppDatabase.getInstance(FoodCRUD.this).foodDao().lastData();
        int lastId=getLast.get(0).getId()+1;


        storageReference = FirebaseStorage.getInstance().getReference("images/"+lastId);


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
        spin=findViewById(R.id.spinner);

    }

}