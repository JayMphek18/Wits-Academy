package com.example.wits_academy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

public class search_courses extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    String userNumber;
    LinearLayout course_list;
    private DrawerLayout drawerLayout;
    String courseName;
    TextView logout;
    NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_courses);

        Intent user_number = getIntent();
        userNumber = user_number.getStringExtra("usernumber");
        course_list = (LinearLayout) findViewById(R.id.search);


        navigationView = (NavigationView) findViewById(R.id.nav);
        navigationView.setNavigationItemSelectedListener(this);
        View view = navigationView.getHeaderView(0);

        TextView userName = view.findViewById(R.id.name);
        userName.setText(userNumber);
        ImageView imageView = view.findViewById(R.id.imageView9);
        Picasso.get()
                .load("http://10.203.203.49/wits/php/profile_photos/" + userNumber + ".jpg")
                .error(R.drawable.profile_icon)
                .fit()
                .into(imageView);


        drawerLayout = (DrawerLayout) findViewById(R.id.draw_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tooolbar);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigator_open, R.string.navigator_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //changing background and title on toolbar

        DataBase.get_all_courses(this,userNumber,course_list);
    }

    public static void enter_password(Context context, String course_name, String student_number){

        View origin = View.inflate(context,R.layout.search_courses, null);

        View pop_password = View.inflate(context , R.layout.enrrollment_password, null);
        final PopupWindow pop = new PopupWindow(pop_password,900,200, true);
        pop.showAtLocation(origin, Gravity.CENTER,0,0);

        EditText password = pop_password.findViewById(R.id.course_password);
        TextView cancel = pop_password.findViewById(R.id.cancel);
        TextView enroll = pop_password.findViewById(R.id.enroll);
        TextView massage = pop_password.findViewById(R.id.error);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop.dismiss();
            }
        });

        enroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (password.getText().toString().isEmpty()){
                    massage.setText("Please enter the password provided by the techer before you can continue");
                }
                else{
                    DataBase.enroll_on(context, course_name, password.getText().toString(), student_number);
                    pop.dismiss();
                }
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
            case R.id.profile:
                Intent search = new Intent(this, profile.class);
                search.putExtra("usernumber",userNumber);
                search.putExtra("has_image", "ggh");
                startActivity(search);
                return true;

            case R.id.menu_page:
                DataBase.back_to_menu(search_courses.this,userNumber);
                return true;

            case R.id.search:
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;

            case R.id.logout:
                Intent log = new Intent(this, MainActivity.class);
                startActivity(log);
                return true;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}