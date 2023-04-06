package com.example.wits_academy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;       
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText user_id;
    EditText user_password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        //changing background and title on toolbar
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.blue)));
        getSupportActionBar().setTitle("Log into your account");

        user_id = (EditText) findViewById(R.id.user_number);
        user_password = (EditText) findViewById(R.id.user_password);

    }

    public void Main_page(View view) {
        if (!validate_input()){
            Toast.makeText(this, "Please fill in all the spaces", Toast.LENGTH_SHORT).show();
        }
        else{
            DataBase user_info = new DataBase(user_id.getText().toString());
            user_info.exists(this,  user_password.getText().toString());
            if (user_info.user_exists){

                if (user_info.role.equals("Teacher")){
                    Intent intent = new Intent(this, main_menu_teacher.class);
                    intent.putExtra("information", user_info.user_number);
                    startActivity(intent);
                }
                else if (user_info.role.equals("Student")){
                    Intent intent = new Intent(this, main_menu_student.class);
                    intent.putExtra("information", user_info.user_number);
                    startActivity(intent);
                }
            }
            else {
                Toast.makeText(this, "Incorrect password", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void forgot_password_toolbox(View view) {
        Intent intent = new Intent(this, forgot_password.class);
        startActivity(intent);
    }

    public void registration_page(View view) {
        Intent intent = new Intent(this, register_page.class);
        startActivity(intent);
    }
    public boolean validate_input(){
        if (user_id.getText().toString().isEmpty()){
            return false;
        }
        else if(user_password.getText().toString().isEmpty()){
            return false;
        }
        return true;
    }
}
