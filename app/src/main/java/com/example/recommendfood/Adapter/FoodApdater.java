package com.example.recommendfood.Adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.recommendfood.Model.CategoryAndFood;
import com.example.recommendfood.Model.CategoryFood;
import com.example.recommendfood.Model.Food;
import com.example.recommendfood.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FoodApdater extends RecyclerView.Adapter<FoodApdater.FoodViewHolder>{

    private List<CategoryAndFood> mListUser;
    ProgressDialog progressDialog;
    StorageReference storageReference;
    private IClickItemFood iClickItemFood;
    public interface IClickItemFood{
        void deleleFood(Food food);
    }

    public void setData(List<CategoryAndFood> list){
        this.mListUser=list;
        notifyDataSetChanged();

    }

    public FoodApdater(IClickItemFood iClickItemFood) {
        this.iClickItemFood = iClickItemFood;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.crud_food,parent,false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
       final CategoryAndFood food= mListUser.get(position);
        if(food ==null){

            return;
        }
        holder.name.setText(food.food.getName());

        storageReference = FirebaseStorage.getInstance().getReference("images/"+food.food.getId());


        try {
            File file = File.createTempFile("tempfile",".jpg");
            storageReference.getFile(file).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {

                    Bitmap bitmap= BitmapFactory.decodeFile(file.getAbsolutePath());
                    holder.img.setImageBitmap(bitmap);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }


        holder.calo.setText(food.food.getCalo());
        holder.session.setText(food.food.getSession());
        holder.category.setText(food.categoryFoodListList.get(0).getName());
        holder.btnDelele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickItemFood.deleleFood(food.food);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(mListUser!=null){

            return mListUser.size();
        }
        return 0;
    }

    public  class FoodViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        TextView calo;
        TextView session;
        TextView category;
        ImageView img;
        Button btnDelele;
        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.food_name);
            calo=itemView.findViewById(R.id.food_calo);
            session=itemView.findViewById(R.id.food_session);
            category=itemView.findViewById(R.id.categoryfood_name);
            img=itemView.findViewById(R.id.img);
            btnDelele=itemView.findViewById(R.id.btn_delete);
        }


    }
}