package com.example.wits_academy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class student_quiz_view extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    String userNumber;
    LinearLayout available_quizzes;
    LinearLayout past_quizzes;
    DrawerLayout drawerLayout;
    String courseName;
    TextView logout;
//    int quiz_id;
    String role;
    NavigationView navigationView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_quiz_view);

        Intent user_number = getIntent();
        userNumber = user_number.getStringExtra("userNumber");
        courseName = user_number.getStringExtra("courseName");
        available_quizzes = (LinearLayout) findViewById(R.id.available);
        past_quizzes = (LinearLayout) findViewById(R.id.past);

        role = getIntent().getStringExtra("Role");
        navigationView = (NavigationView) findViewById(R.id.nav_s);
        navigationView.setNavigationItemSelectedListener(this);
        View view = navigationView.getHeaderView(0);
        TextView userName = view.findViewById(R.id.name);
        userName.setText(courseName);

        ImageView imageView = view.findViewById(R.id.imageView9);
        DataBase.get_course_image(this, courseName, imageView);

        drawerLayout = (DrawerLayout) findViewById(R.id.draw_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigator_open, R.string.navigator_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //changing background and title on toolbar
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.blue)));
        getSupportActionBar().setTitle("Quizzes");

        DataBase.written_quiz(this, courseName, available_quizzes, past_quizzes, userNumber);
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
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){

            case R.id.announcements:
                Intent intent1 = new Intent(this,Announcements.class);
                intent1.putExtra("userNumber",userNumber);
                intent1.putExtra("courseName",courseName);
                intent1.putExtra("Role","Student");
                startActivity(intent1);
                return true;
            case R.id.course_slides:
                Intent intent3 = new Intent(this,upload_file.class);
                intent3.putExtra("courseName",courseName);
                intent3.putExtra("type","Documents");
                intent3.putExtra("userNumber",userNumber);
                startActivity(intent3);
                return true;
            case R.id.videos:
                Intent intent2 = new Intent(this,upload_file.class);
                intent2.putExtra("courseName",courseName);
                intent2.putExtra("type","Videos");
                intent2.putExtra("userNumber",userNumber);
                startActivity(intent2);
                return true;
            case R.id.quiz:
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            case R.id.back:
                DataBase.back_to_menu(this,userNumber);
                return true;
            case R.id.logout:
                Intent intent = new Intent(this , MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.ViewUsers:
                intent = new Intent(this,viewUsers.class);
                intent.putExtra("courseName",courseName);
                intent.putExtra("Role","Student");
                startActivity(intent);
            case R.id.courseHomeActivity:
                Intent intent5 = new Intent(this,viewUsers.class);
                intent5.putExtra("courseName",courseName);
                intent5.putExtra("userNumber",userNumber);
                startActivity(intent5);


        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}