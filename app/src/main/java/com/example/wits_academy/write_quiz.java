package com.example.wits_academy;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class write_quiz extends AppCompatActivity {

    int marks = 0;
    String userNumber;
    String courseName;
    String quizName;
    LinearLayout layout;
    ArrayList<String> questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write_quiz);

        Intent user_number = getIntent();
        userNumber = user_number.getStringExtra("userNumber");
        courseName = user_number.getStringExtra("courseName");
        quizName = user_number.getStringExtra("quizName");
        layout = findViewById(R.id.quiz);
//        drawerLayout = (DrawerLayout) findViewById(R.id.draw_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
//                R.string.navigator_open, R.string.navigator_close);
//        drawerLayout.addDrawerListener(toggle);
//        toggle.syncState();

        toolbar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.blue)));
        toolbar.setTitle(quizName);

        //fetch the quiz information frommthe database
        DataBase.get_questions(this,layout,courseName,quizName);



    }

    public void submit_answer(View view) {
        HashMap<String,Integer> studentAnswers = new HashMap();// Key is Answer and type is value

        //fetch the answers from dataBase and see if they are the same or not
        for (int i = 0; i < layout.getChildCount(); i++){
            TextView number = layout.getChildAt(i).findViewById(R.id.question_number);
            String q_number = number.getText().toString();
            String right_n = "";
            int question_number = 0;
            for (int j = "Question ".length(); j < q_number.length(); j++){
                right_n = right_n + q_number.charAt(j);
                question_number = Integer.parseInt(right_n);
            }
            // after getting the answers fo each question isolate the them into an arrayList and for every index we check if the answer found
            // at index + 1 is the same as the one provide
            EditText answer = layout.getChildAt(i).findViewById(R.id.enter_answer);
            TextView tv_mark = layout.getChildAt(i).findViewById(R.id.allocated_marks);
            int question_mark;
            String question_answer;
            if (answer == null){
                RadioButton choiceA = layout.getChildAt(i).findViewById(R.id.optionA);
                RadioButton choiceB = layout.getChildAt(i).findViewById(R.id.optionB);
                RadioButton choiceC = layout.getChildAt(i).findViewById(R.id.optionC);
                RadioButton choiceD = layout.getChildAt(i).findViewById(R.id.optionD);
                tv_mark = layout.getChildAt(i).findViewById(R.id.marks);
                if(choiceA.isChecked()){
                    question_answer = choiceA.getText().toString();
                    question_mark = Integer.parseInt((tv_mark.getText().toString()).substring(7));
                    studentAnswers.put(question_answer,question_mark);
                }
                else if(choiceB.isChecked()){
                    question_answer = choiceB.getText().toString();
                    question_mark = Integer.parseInt((tv_mark.getText().toString()).substring(7));
                    studentAnswers.put(question_answer,question_mark);
                }
                else if(choiceC.isChecked()){
                    question_answer = choiceC.getText().toString();
                    question_mark = Integer.parseInt((tv_mark.getText().toString()).substring(7));
                    studentAnswers.put(question_answer,question_mark);
                }
                else if(choiceD.isChecked()){
                    question_answer = choiceD.getText().toString();
                    question_mark = Integer.parseInt((tv_mark.getText().toString()).substring(7));
                    studentAnswers.put(question_answer,question_mark);
                }
                else{
                    question_answer = "";
                    question_mark = Integer.parseInt((tv_mark.getText().toString()).substring(7));
                    studentAnswers.put(question_answer,question_mark);
                }
                DataBase.save_answer(this, courseName,userNumber, quizName,String.valueOf(question_number), question_answer);

            }
            else{
                question_answer = answer.getText().toString();
                question_mark = Integer.parseInt((tv_mark.getText().toString()).substring(7));
                studentAnswers.put(question_answer,question_mark);
                DataBase.save_answer(this, courseName,userNumber, quizName,String.valueOf(question_number), question_answer);
            }
            // take the answer as instructed above and see if it is equal to question_answer and update marks according to the correct answers
//            Toast.makeText(this, question_answer,Toast.LENGTH_SHORT).show();
            //on response the student should be sent to the quiz page/ review quiz using intent
        }

        DataBase.grade(view.getContext(),courseName,quizName,studentAnswers,userNumber);
    }
}