package com.example.wits_academy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class view_users_recyclerViewAdapter extends RecyclerView.Adapter<view_users_recyclerViewAdapter.MyViewHolder> {

    Context context;
    ArrayList<userModel> userModels;

    public view_users_recyclerViewAdapter(Context context, ArrayList<userModel> userList) {
        this.context = context;
        this.userModels = userList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.user_card,parent,false);
        return new view_users_recyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // Assigning values as views come into line of vision
        holder.full_name.setText(userModels.get(position).full_name);
        holder.email_address.setText(userModels.get(position).email_address);
        holder.role.setText(userModels.get(position).role);
        holder.profilePicture.setImageResource(R.drawable.profile_icon);

    }

    @Override
    public int getItemCount() {
        return userModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView profilePicture;
        TextView full_name,role,email_address;

        public MyViewHolder(View itemView) {
            super(itemView);

            profilePicture = itemView.findViewById(R.id.view_user_profile);
            full_name = itemView.findViewById(R.id.view_user_name);
            role = itemView.findViewById(R.id.view_user_role);
            email_address = itemView.findViewById(R.id.view_user_email);
//            readMoreButton = itemView.findViewById(R.id.readMoreButton);
//            announcement_subject = itemView.findViewById(R.id.announcement_subject);

        }
    }
}
