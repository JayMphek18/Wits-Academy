package com.example.wits_academy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
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

        user_id = (EditText) findViewById(R.id.user_number);
        user_password = (EditText) findViewById(R.id.user_password);

    }

    public void Main_page(View view) {
        if (!validate_input()){
            Toast.makeText(this, "Please fill in all the spaces", Toast.LENGTH_SHORT).show();
        }
        else{
            Intent intent = new Intent(this, main_menu_student.class);
            startActivity(intent);
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
