package com.example.wits_academy;

import static com.example.wits_academy.R.id.DocumentsLL;


import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.Nullable;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class student_course_view extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    String userNumber;
    LinearLayout course_list;
    private DrawerLayout drawerLayout;
    private LinearLayout Docs;
    String courseName;
    TextView logout;
    String role;
    NavigationView navigationView;
    // List Of all Documents in the class
    static ArrayList<String> titles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_course_view);

        Intent user_number = getIntent();
        userNumber = user_number.getStringExtra("userNumber");
        courseName = user_number.getStringExtra("courseName");
        course_list = (LinearLayout) findViewById(R.id.contents);
        role = getIntent().getStringExtra("role");
        navigationView = (NavigationView) findViewById(R.id .nav_s);
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
        getSupportActionBar().setTitle(courseName);
        Docs = findViewById(DocumentsLL);
        // Get Course Content and Display it
        Boolean wait = false;
        titles = new ArrayList<>();
        DataBase.get_Documents(this,titles,courseName,Docs,role);
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