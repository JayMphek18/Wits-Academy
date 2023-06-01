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

        ArrayList<String> quiz_name = new ArrayList<>();
        ArrayList <String> avarage_marks= new ArrayList<>();
//        quiz_name.add(0,"quiz1");
//        avarage_marks.add(0, "56");
        quiz_name.add("Quiz1");
        avarage_marks.add("56");
        quiz_name.add("Quiz1");
        avarage_marks.add("56");
        quiz_name.add("Quiz1");
        avarage_marks.add("56");
        quiz_name.add("Quiz1");
        avarage_marks.add("56");
        quiz_name.add("Quiz1");
        avarage_marks.add("56");
        ViewsClass.available_quizzes(this, available_quizzes, quiz_name, avarage_marks,userNumber, courseName);
        ViewsClass.past_quizzes(this, past_quizzes, quiz_name, avarage_marks,userNumber, courseName);

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
            case R.id.questions:
                return true;
            case R.id.announcements:
                Intent intent1 = new Intent(this,Announcements.class);
                intent1.putExtra("userNumber",userNumber);
                intent1.putExtra("courseName",courseName);
                intent1.putExtra("Role","Student");
                startActivity(intent1);
                return true;
            case R.id.course_slides:
                return true;
            case R.id.videos:
                return true;
            case R.id.quiz:
                drawerLayout.closeDrawer(GravityCompat.START);
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
            case R.id.ViewUsers:
                intent = new Intent(this,viewUsers.class);
                intent.putExtra("courseName",courseName);
                intent.putExtra("Role","Student");
                startActivity(intent);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}