package com.example.wits_academy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

public class search_courses extends AppCompatActivity {

    String userNumber;
    LinearLayout course_list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_courses);

        Intent user_number = getIntent();
        userNumber = user_number.getStringExtra("usernumber");
        course_list = (LinearLayout) findViewById(R.id.search);

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
}