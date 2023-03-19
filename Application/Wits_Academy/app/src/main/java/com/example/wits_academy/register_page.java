package com.example.wits_academy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class register_page extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    EditText user_id;
    EditText user_name;
    EditText user_last_name;
    EditText user_email;
    EditText create_password;
    EditText confirm_password;
    String string;
    TextView role ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_page) ;

        user_id = (EditText) findViewById(R.id.user_r_number);
        user_name = (EditText) findViewById(R.id.first_name);
        user_last_name = (EditText) findViewById(R.id.last_name);
        user_email = (EditText) findViewById(R.id.user_email);
        create_password = (EditText) findViewById(R.id.create_password);
        confirm_password = (EditText) findViewById(R.id.confirm_password);
        role =(TextView) findViewById(R.id.user_r_id);

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
        if (!filled_in()){
            Toast.makeText(this, "Please fill in all the spaces", Toast.LENGTH_SHORT).show();
        }

        else if (string.equals("Student")){
            Intent intent = new Intent(this, main_menu_student.class);
            startActivity(intent);
        }
        else if (string.equals("Teachers")){
            Intent intent = new Intent(this, main_menu_teacher.class);
            startActivity(intent);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //String of the role Selected on the dropdown menu
        string = parent.getItemAtPosition(position).toString();
        if(string.equals("Teacher")){
            role.setText("Employee number");
            user_id.setHint("Enter employee number");
        }
        else if(string.equals("Student")){
            role.setText("Student number");
            user_id.setHint("Enter student number");
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public boolean filled_in(){
        if (user_id.getText().toString().isEmpty()){
            return false;
        }
        else if (user_name.getText().toString().isEmpty()){
            return false;
        }
        else if (user_last_name.getText().toString().isEmpty()){
            return false;
        }
        else if (user_email.getText().toString().isEmpty()){
            return false;
        }
        else if (create_password.getText().toString().isEmpty()){
            return false;
        }
        else if (confirm_password.getText().toString().isEmpty()){
            return false;
        }
        if(!create_password.getText().toString().equals(confirm_password.getText().toString())){
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "Please try again", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}