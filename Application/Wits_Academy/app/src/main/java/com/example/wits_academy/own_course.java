package com.example.wits_academy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

public class own_course extends AppCompatActivity {

    String userNumber;
    LinearLayout course_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.own_course);

        Intent user_number = getIntent();
        userNumber = user_number.getStringExtra("usernumber");
        course_list = (LinearLayout) findViewById(R.id.search);
    }
}