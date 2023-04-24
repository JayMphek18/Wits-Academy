package com.example.wits_academy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class main_menu_teacher extends AppCompatActivity {

    String userNumber;
    LinearLayout course_list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu_teacher);

        //changing background and title on toolbar
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.blue)));
        getSupportActionBar().setTitle("Make a new password");

        Intent user_number = getIntent();
        userNumber = user_number.getStringExtra("information");
        course_list = (LinearLayout) findViewById(R.id.t_courses);

        DataBase.teacher_courses(this, userNumber, course_list);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.teacher_toolbox,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.manage_course:
                Intent manage_course = new Intent(this, own_course.class);
                manage_course.putExtra("usernumber",userNumber);
                startActivity(manage_course);
                break;
            case R.id.profile:
                Intent profile = new Intent(this, profile.class);
                profile.putExtra("usernumber", userNumber);
                profile.putExtra("has_image", "");
                startActivity(profile);
                break;
            case R.id.logout:
                Intent logout = new Intent(this, MainActivity.class);
                logout.putExtra("usernumber", userNumber);

                startActivity(logout);
        }
        return super.onOptionsItemSelected(item);
    }

    public void create(View view) {
        Intent intent = new Intent(this, create_course.class);
        intent.putExtra("usernumber",userNumber);
        startActivity(intent);
    }
}