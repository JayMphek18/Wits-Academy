package com.example.wits_academy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class forgot_password extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String string;
    EditText email;
    EditText user_number;
    EditText new_password;
    EditText confirm_new_password;
    TextView role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password);
        email = (EditText) findViewById(R.id.f_enter_email);
        user_number = (EditText) findViewById(R.id.f_enter_number);
        new_password = (EditText) findViewById(R.id.f_reset_password);
        confirm_new_password = (EditText) findViewById(R.id.f_enter_corfirm_password);
        role = (TextView) findViewById(R.id.f_user_number);


        Spinner spinner = findViewById(R.id.f_role);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.role, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //String of the role Selected on the dropdown menu
        string = parent.getItemAtPosition(position).toString();
        if(string.equals("Teacher")){
            role.setText("Employee number");
            user_number.setHint("Enter employee number");
        }
        else if(string.equals("Student")){
            role.setText("Student number");
            user_number.setHint("Enter student number");
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void after_reset(View view) {
        if(validate_password()){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "Please fill in all the spaces", Toast.LENGTH_SHORT).show();
        }

    }
    public boolean validate_password(){
        if(!new_password.getText().toString().equals(confirm_new_password.getText().toString())){
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "Please try again", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (user_number.getText().toString().isEmpty()){
            return false;
        }
        else if (email.getText().toString().isEmpty()){
            return false;
        }
        return true;
    }

    public void back_to_login(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}