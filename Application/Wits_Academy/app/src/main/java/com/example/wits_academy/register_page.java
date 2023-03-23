package com.example.wits_academy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

<<<<<<< HEAD
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class register_page extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    EditText user_id;
    EditText user_first_name;
=======
import java.util.HashMap;
import java.util.Map;

public class register_page extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    EditText user_number;
    EditText user_name;
>>>>>>> 954dd75b9ec66a4037c3bcaa87e6b2953eb6b763
    EditText user_last_name;
    EditText user_email;
    EditText password;
    EditText confirm_password;
    String string;
    TextView role ;

            @Override
    protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.register_page);

<<<<<<< HEAD
                user_id = (EditText) findViewById(R.id.user_r_number);
                user_first_name = (EditText) findViewById(R.id.first_name);
                user_last_name = (EditText) findViewById(R.id.last_name);
                user_email = (EditText) findViewById(R.id.user_email);
                password = (EditText) findViewById(R.id.create_password);
                confirm_password = (EditText) findViewById(R.id.confirm_password);
                role = (TextView) findViewById(R.id.user_r_id);

=======
        //changing background and title on toolbar
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.blue)));
        getSupportActionBar().setTitle("Register your account");

        user_number = (EditText) findViewById(R.id.user_r_number);
        user_name = (EditText) findViewById(R.id.first_name);
        user_last_name = (EditText) findViewById(R.id.last_name);
        user_email = (EditText) findViewById(R.id.user_email);
        create_password = (EditText) findViewById(R.id.create_password);
        confirm_password = (EditText) findViewById(R.id.confirm_password);
        role = (TextView) findViewById(R.id.user_r_id);

        // spinner is for the dropdown menu

        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.role, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
>>>>>>> 954dd75b9ec66a4037c3bcaa87e6b2953eb6b763

                // spinner is for the dropdown menu
                Spinner spinner = findViewById(R.id.spinner);
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.role, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);
                spinner.setOnItemSelectedListener(this);

            }

    public void login(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void main_menu(View view) {
        if (!filled_in()) {
            return;
        }
        else {
            adding_to_databasa();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    //part of the spinner to get info from the spinner

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
        return;
    }

    // checks if all attributes have been correctly filled in

    public boolean filled_in(){
        if (user_number.getText().toString().isEmpty()){
            Toast.makeText(this, "Please fill in all the spaces", Toast.LENGTH_SHORT).show();
            return false;
        }
<<<<<<< HEAD
        else if (user_first_name.getText().toString().isEmpty()){
=======
        else if (user_name.getText().toString().isEmpty()){
            Toast.makeText(this, "Please fill in all the spaces", Toast.LENGTH_SHORT).show();
>>>>>>> 954dd75b9ec66a4037c3bcaa87e6b2953eb6b763
            return false;
        }
        else if (user_last_name.getText().toString().isEmpty()){
            Toast.makeText(this, "Please fill in all the spaces", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (!isEmailValid()){
            Toast.makeText(this, "Email Address is invalide", Toast.LENGTH_SHORT).show();
            return false;
        }
<<<<<<< HEAD
        else if (password.getText().toString().isEmpty()){
=======
        else if (create_password.getText().toString().length() < 6){
            Toast.makeText(this, "Password is too short", Toast.LENGTH_SHORT).show();
>>>>>>> 954dd75b9ec66a4037c3bcaa87e6b2953eb6b763
            return false;
        }
        else if (confirm_password.getText().toString().length() < 6){
            Toast.makeText(this, "Password is too short", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!password.getText().toString().equals(confirm_password.getText().toString())){
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "Please try again", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

<<<<<<< HEAD
=======
    //it creates a map which consists of all the user info and call a save function in the DataBase class to save on the database

    public void adding_to_databasa(){
        Map<String, String> map = new HashMap<>();
        map.put("name", user_name.getText().toString());
        map.put("surname", user_last_name.getText().toString());
        map.put("email", user_email.getText().toString());
        map.put("user_number", user_number.getText().toString());
        map.put("password", create_password.getText().toString());
        map.put("role", string);

        DataBase.save(this, map);
    }

    //checks if the email is an email

    boolean isEmailValid() {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(user_email.getText().toString()).matches();
    }
>>>>>>> 954dd75b9ec66a4037c3bcaa87e6b2953eb6b763

}