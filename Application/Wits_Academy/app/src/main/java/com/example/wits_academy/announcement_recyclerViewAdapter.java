package com.example.wits_academy;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class announcement_recyclerViewAdapter extends RecyclerView.Adapter<announcement_recyclerViewAdapter.MyViewHolder> {
    Context context;
    ArrayList<announcementModel> announcementModels;

    public announcement_recyclerViewAdapter(Context context, ArrayList<announcementModel> items){
        this.context = context;
        this.announcementModels = items;
    }

    @Override
    public announcement_recyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflates the layout and gives look to layouts
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.m_recycler_announcement,parent,false);
        return new announcement_recyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(announcement_recyclerViewAdapter.MyViewHolder holder, int position) {
        // Assigning values as views come into line of vision
//        holder.announcement_subject.setText(announcementModels.get(position).header);
        holder.announcement_body.setText(announcementModels.get(position).AnnouncementText);
        holder.date.setText(announcementModels.get(position).date);
        holder.imageView.setImageResource(R.drawable.read_more);
    }

    @Override
    public int getItemCount() {
        // Number of items to be displayed in the recycler view
        return announcementModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView announcement_subject,announcement_body,date;
        Button readMoreButton;

        public MyViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView4);
            announcement_body = itemView.findViewById(R.id.Announcement);
            date = itemView.findViewById(R.id.dateView);
//            readMoreButton = itemView.findViewById(R.id.readMoreButton);
//            announcement_subject = itemView.findViewById(R.id.announcement_subject);

        }
    }
}
