package com.example.wits_academy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.Nullable;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class teacher_quiz_view extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    String userNumber;
    LinearLayout course_list;
    private DrawerLayout drawerLayout;
    String courseName;
    TextView logout;
    NavigationView navigationView;
    Bitmap bitmap;
    ImageView imageView;

    private final int GALLERY_REQ_CODE = 1000;

//    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_quiz_view);

        Intent user_number = getIntent();
        userNumber = user_number.getStringExtra("userNumber");
        courseName = user_number.getStringExtra("courseName");
        course_list = (LinearLayout) findViewById(R.id.quiz_view);

        navigationView = (NavigationView) findViewById(R.id.nav_t);
        navigationView.setNavigationItemSelectedListener(this);
        View view = navigationView.getHeaderView(0);
        TextView userName = view.findViewById(R.id.name);
        userName.setText(courseName);

        imageView = view.findViewById(R.id.imageView9);
        DataBase.get_course_image(this, courseName, imageView);
        drawerLayout = (DrawerLayout) findViewById(R.id.draw_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent access_gallary = new Intent(Intent.ACTION_PICK);
//                access_gallary.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                startActivityForResult(access_gallary, GALLERY_REQ_CODE);
//            }
//        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigator_open, R.string.navigator_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //changing background and title on toolbar
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.blue)));
        getSupportActionBar().setTitle("Quizzes");

        DataBase.get_all_quiz(this, courseName, course_list, userNumber);

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

            case R.id.Announcement:
                Intent A = new Intent(this , Announcements.class);
                A.putExtra("userNumber",userNumber);
                A.putExtra("courseName",courseName);
                A.putExtra("Role","Teacher");
                startActivity(A);
                return true;
            case R.id.course_slides:
                Intent intent1 = new Intent(this,upload_file.class);
                intent1.putExtra("courseName",courseName);
                intent1.putExtra("type","Documents");
                intent1.putExtra("userNumber",userNumber);
                startActivity(intent1);
                return true;
            case R.id.videos:
                Intent intent2 = new Intent(this,upload_file.class);
                intent2.putExtra("courseName",courseName);
                intent2.putExtra("type","Videos");
                intent2.putExtra("userNumber",userNumber);
                startActivity(intent2);
                return true;
            case R.id.quiz:
                Intent create_quiz = new Intent(this,create_quiz.class);
                create_quiz.putExtra("courseName",courseName);
                create_quiz.putExtra("userNumber",userNumber);
                startActivity(create_quiz);
                return true;
            case R.id.other_quizzes:
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
                intent.putExtra("userNumber",userNumber);
                intent.putExtra("Role","Teacher");
                startActivity(intent);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    public static void setDelete(Context context, LinearLayout course_list, String courseName, String userNumber){
        for (int i = 1; i < course_list.getChildCount(); i++){
            View quiz = course_list.getChildAt(i);
            ImageView delete = quiz.findViewById(R.id.imageView10);
            ImageView review_grades = quiz.findViewById(R.id.grades);

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    course_list.removeView(quiz);
                    TextView quizName = quiz.findViewById(R.id.quiz_name);
                    DataBase.delete_quiz(context, courseName, quizName.getText().toString());
                }
            });
            review_grades.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TextView quizName = quiz.findViewById(R.id.quiz_name);
                    Intent create_quiz = new Intent(context, grades.class);
                    create_quiz.putExtra("courseName",courseName);
                    create_quiz.putExtra("userNumber",userNumber);
                    create_quiz.putExtra("quizName", quizName.getText().toString());
                    context.startActivity(create_quiz);
                }
            });
        }
    }

}