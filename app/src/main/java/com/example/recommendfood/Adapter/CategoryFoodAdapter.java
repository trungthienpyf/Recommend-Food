package com.example.recommendfood.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recommendfood.Model.CategoryFood;
import com.example.recommendfood.Model.User;
import com.example.recommendfood.R;

import java.util.List;


public class CategoryFoodAdapter extends RecyclerView.Adapter<CategoryFoodAdapter.CategoryFoodViewHolder>{

    private List<CategoryFood> mListUser;
    public void setData(List<CategoryFood> list){
        this.mListUser=list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public CategoryFoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.crud_category_food,parent,false);
        return new CategoryFoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryFoodViewHolder holder, int position) {
        CategoryFood user= mListUser.get(position);
        if(user ==null){

            return;
        }

        holder.category_name.setText(user.getName());
    }

    @Override
    public int getItemCount() {
        if(mListUser!=null){

            return mListUser.size();
        }
        return 0;
    }

    public  class CategoryFoodViewHolder extends RecyclerView.ViewHolder{

        TextView category_name;


        public CategoryFoodViewHolder(@NonNull View itemView) {
            super(itemView);
            category_name= itemView.findViewById(R.id.category_name);

        }


    }
}
