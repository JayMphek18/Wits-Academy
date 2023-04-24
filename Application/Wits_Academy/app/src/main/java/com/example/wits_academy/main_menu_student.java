package com.example.wits_academy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class main_menu_student extends AppCompatActivity {

    String userNumber;
    LinearLayout course_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu_student);

        //changing background and title on toolbar
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.blue)));
        getSupportActionBar().setTitle("Course Dashboard");

        Intent user_number = getIntent();
        userNumber = user_number.getStringExtra("information");
        course_list = (LinearLayout) findViewById(R.id.list_courses);

        DataBase.student_courses(this, userNumber, course_list);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.student_toolbox,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.search:
                Intent search = new Intent(this, search_courses.class);
                search.putExtra("usernumber",userNumber);
                startActivity(search);
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

}