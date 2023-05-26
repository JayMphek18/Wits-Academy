package com.example.wits_academy;

<<<<<<< HEAD
import androidx.annotation.NonNull;
=======



>>>>>>> 24838f0a76c329a96400e8e81b3a2c4af4151360
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
<<<<<<< HEAD
import android.view.Menu;
=======
import android.provider.ContactsContract;
import android.view.LayoutInflater;
>>>>>>> 24838f0a76c329a96400e8e81b3a2c4af4151360
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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
    String role;
    NavigationView navigationView;
    // List Of all Documents in the class
    static ArrayList<String> titles;
    ArrayList<documentView> documentViews;
    private BottomNavigationView navigationView1;

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
        Toolbar toolbar = (Toolbar) findViewById(R.id.tooolbar);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigator_open, R.string.navigator_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //changing background and title on toolbar
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.blue)));
        getSupportActionBar().setTitle(courseName);

        navigationView1 = findViewById(R.id.bottomNavigationView);
//        Make sure that Home Fragment is always set as default
        replaceFragement(new HomeFragment());

        // Get Course Content and Display it
        Boolean wait = false;
        titles = new ArrayList<>();
        documentViews  = new ArrayList<documentView>();

        navigationView1.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.courseHome:
                        replaceFragement(new HomeFragment());
                        break;
                    case R.id.courseDocument:
                        DocumentFragment fragment = new DocumentFragment();
                        replaceFragement(fragment);
                        Docs = fragment.getDocsLL();
                        DataBase.get_Documents(getApplicationContext(),titles,courseName,"student",userNumber,fragment);
                        break;
                    case R.id.courseVideos:
                        replaceFragement(new VideoFragment());
                        break;
                }
                return true;
            }
        });


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
<<<<<<< HEAD
                return true;
            case R.id.course_slides:
                return true;
            case R.id.videos:
=======
                Intent intent1 = new Intent(this,Announcements.class);
                intent1.putExtra("userNumber",userNumber);
                intent1.putExtra("courseName",courseName);
                intent1.putExtra("Role","Student");
                startActivity(intent1);
>>>>>>> 24838f0a76c329a96400e8e81b3a2c4af4151360
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

    private void replaceFragement(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }
}