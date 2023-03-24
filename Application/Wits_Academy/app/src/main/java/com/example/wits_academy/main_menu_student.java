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

    String usernumber;
    LinearLayout course_list;
    ArrayList<String> course_names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu_student);

        //changing background and title on toolbar
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.blue)));
        getSupportActionBar().setTitle("Course Dashboard");

        Intent user_number = getIntent();
        usernumber = user_number.getStringExtra("information");
        course_list = (LinearLayout) findViewById(R.id.list_courses);
        course_names = new ArrayList<>();
        course_names.add("Physics");
        course_names.add("Physics");
        course_names.add("Physics");
        course_names.add("Physics");
        course_names.add("Physics");
        course_names.add("Physics");
        course_names.add("Physics");
        course_names.add("Physics");
        course_names.add("Physics");
        course_names.add("Physics");
        course_names.add("Physics");
        course_names.add("Physics");
        course_names.add("Physics");
        course_names.add("Physics");
        course_names.add("Physics");
        course_names.add("Physics");
        course_names.add("Physics");
        course_names.add("Physics");
        course_names.add("Physics");
        course_names.add("Physics");
        course_names.add("Physics");
        course_names.add("Physics");
        course_names.add("Physics");
        course_names.add("Physics");
        add_layout();
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
                search.putExtra("usernumber",usernumber);
                startActivity(search);
                break;
            case R.id.profile:
                Intent profile = new Intent(this, profile.class);
                profile.putExtra("usernumber", usernumber);
                startActivity(profile);
                break;
            case R.id.logout:
                Intent logout = new Intent(this, MainActivity.class);
                logout.putExtra("usernumber", usernumber);
                startActivity(logout);
        }
        return super.onOptionsItemSelected(item);
    }

    public void add_layout(){
        int i = 0;
        int left = course_names.size();
        while (i < course_names.size()){
            if (left >= 2){
                View course_layout = getLayoutInflater().inflate(R.layout.course_layout, null);
                TextView course_name_1 = course_layout.findViewById(R.id.course_name_1);
                course_name_1.setText(course_names.get(i));
                TextView course_name_2 = course_layout.findViewById(R.id.course_name_2);
                course_name_2.setText(course_names.get(i + 1));
                course_list.addView(course_layout);
                i = i + 2;
                left = left - 2;
            }
            else{
                View course_layout = getLayoutInflater().inflate(R.layout.course_layout, null);
                TextView course_name_1 = course_layout.findViewById(R.id.course_name_1);
                course_name_1.setText(course_names.get(i));
                RelativeLayout last = course_layout.findViewById(R.id.second);
                last.setVisibility(View.GONE);
                course_list.addView(course_layout);
                i = i + 1;
            }
        }
    }

}