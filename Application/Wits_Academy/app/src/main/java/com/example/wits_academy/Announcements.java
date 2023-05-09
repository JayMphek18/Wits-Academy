package com.example.wits_academy;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class Announcements extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    String userNumber;
    String courseName;
    LinearLayout course_list;
    private DrawerLayout drawerLayout;
    TextView logout;
    NavigationView navigationView;
    // Array to store all announcements
    ArrayList<announcementModel> announcementsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set Content Based on whether user is a Teacher or a student
        if(this.getIntent().getStringExtra("Role").equals("Teacher"))
            setContentView(R.layout.activity_announcements);
        else setContentView(R.layout.activity_announcements_student);

        drawerLayout = (DrawerLayout) findViewById(R.id.draw_layout);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        navigationView = (NavigationView) findViewById(R.id.nav);
        navigationView.setNavigationItemSelectedListener(this);
        View view = navigationView.getHeaderView(0);
        TextView userName = view.findViewById(R.id.name);
        userName.setText(userNumber);
        ImageView imageView = view.findViewById(R.id.imageView9);

        DataBase.get_image(this, userNumber, imageView);

        ActionBarDrawerToggle toggle =  new ActionBarDrawerToggle(this, drawerLayout,toolbar,
                R.string.navigator_open,R.string.navigator_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //changing background and title on toolbar
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.blue)));
        getSupportActionBar().setTitle("Announcements");

        // Get Extra Information From Previous Activity
        courseName = getIntent().getStringExtra("courseName");


        // Get And Display Announcements If Any
        announcementsList = new ArrayList<announcementModel>();
        RecyclerView recyclerView = findViewById(R.id.mRecyclerView);
        DataBase.get_announcements(this,courseName,announcementsList,recyclerView);



    }



    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@Nullable MenuItem item) {
        switch(item.getItemId()){
            case R.id.questions:
                return true;
            case R.id.Announcement:
                Intent A = new Intent(this , Announcements.class);
                A.putExtra("userNumber",userNumber);
                A.putExtra("courseName",courseName);
                startActivity(A);
                return true;
            case R.id.course_slides:
                return true;
            case R.id.videos:
                return true;
            case R.id.quiz:
                return true;
            case R.id.assignment:
                return true;
            case R.id.grades:
                return true;
            case R.id.back:
                DataBase.back_to_menu(this,userNumber);
                return true;
            case R.id.logout:
                Intent intent = new Intent(this , MainActivity.class);
                startActivity(intent);
                return true;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    // Get Announcements for course from server
    private void getAnnouncements(){
        ArrayList<announcementModel> announcementList = new ArrayList<>();

    }


    private void displayAnnouncements(ArrayList<announcementModel> announcementModels){
    }

    public void go_to_CreateAnnounce(View view){
        Intent intent = new Intent(view.getContext(),create_announcement.class);
        intent.putExtra("courseName",courseName);
        intent.putExtra("userNumber",userNumber);
        startActivity(intent);
    }


}