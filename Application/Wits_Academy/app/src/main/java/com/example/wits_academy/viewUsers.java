package com.example.wits_academy;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class viewUsers extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    String userNumber;
    String courseName;
    private DrawerLayout drawerLayout;
    NavigationView navigationView;
    // Array to store all announcements
    ArrayList<userModel> userList;
    String role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set Content Based on whether user is a Teacher or a student TODO
        role = getIntent().getStringExtra("Role");
        userNumber = getIntent().getStringExtra("userNumber");
        if(role.equals("Teacher"))
        setContentView(R.layout.activity_view_users);
        else setContentView(R.layout.activity_view_users_students);


        drawerLayout = (DrawerLayout) findViewById(R.id.draw_layout);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigationView = (NavigationView) findViewById(R.id.nav);
        navigationView.setNavigationItemSelectedListener(this);
        View view = navigationView.getHeaderView(0);
        ImageView imageView = view.findViewById(R.id.imageView9);

        DataBase.get_image(this, userNumber, imageView);

        ActionBarDrawerToggle toggle =  new ActionBarDrawerToggle(this, drawerLayout,toolbar,
                R.string.navigator_open,R.string.navigator_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        courseName = getIntent().getStringExtra("courseName");
        //changing background and title on toolbar
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.blue)));
        getSupportActionBar().setTitle("Participants");

        // Get List of all People in Course and display them
        userList = new ArrayList<userModel>();
        RecyclerView recyclerView = findViewById(R.id.viewUsersRecyclerView);
        DataBase.get_users(this,courseName,userList,recyclerView);
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
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.questions:
                return true;
            case R.id.announcements:
                Intent intent1 = new Intent(this,Announcements.class);
                intent1.putExtra("userNumber",userNumber);
                intent1.putExtra("courseName",courseName);
                intent1.putExtra("Role",role);
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
                intent.putExtra("Role",role);
                intent.putExtra("courseName",courseName);
                intent.putExtra("userNumber",userNumber);
                startActivity(intent);
        }
        return true;
    }
}