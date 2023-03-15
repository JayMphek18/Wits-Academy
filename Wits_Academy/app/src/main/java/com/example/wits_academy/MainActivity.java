package com.example.wits_academy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        RelativeLayout relativeLayout = findViewById(R.id.login_page);

        AnimationDrawable animationDrawable = (AnimationDrawable) relativeLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();
    }

    public void Main_page(View view) {
        Intent intent = new Intent(this, main_menu_student.class);
        startActivity(intent);
    }

    public void forgot_password_toolbox(View view) {
    }

    public void registration_page(View view) {
        Intent intent = new Intent(this, register_page.class);
        startActivity(intent);
    }
}
