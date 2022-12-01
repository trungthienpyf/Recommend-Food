package com.example.recommendfood.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recommendfood.Model.User;
import com.example.recommendfood.R;

import java.util.List;


public class UserApdater extends RecyclerView.Adapter<UserApdater.UserViewHolder>{

    private List<User> mListUser;
    public void setData(List<User> list){
        this.mListUser=list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.test,parent,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user= mListUser.get(position);
        if(user ==null){

            return;
        }
        holder.tvUser.setText(user.getTk());
        holder.tvPassword.setText(user.getPassword());
    }

    @Override
    public int getItemCount() {
        if(mListUser!=null){

            return mListUser.size();
        }
        return 0;
    }

    public  class UserViewHolder extends RecyclerView.ViewHolder{

        TextView tvUser;
        TextView tvPassword;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPassword=itemView.findViewById(R.id.tv_password);
            tvUser=itemView.findViewById(R.id.tv_username);
        }


    }
}
