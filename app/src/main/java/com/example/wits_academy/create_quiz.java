package com.example.wits_academy;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.Nullable;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class create_quiz extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    LinearLayout quetions;
    int question_num = 1;
    String q_name;
    String userNumber;
    LinearLayout course_list;
    private DrawerLayout drawerLayout;
    String courseName;
    TextView logout;
    NavigationView navigationView;
    Bitmap bitmap;
    ImageView imageView;
    Integer total_marks = 0;

    private final int GALLERY_REQ_CODE = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_quiz);

        Intent user_number = getIntent();
        userNumber = user_number.getStringExtra("userNumber");
        courseName = user_number.getStringExtra("courseName");
        course_list = (LinearLayout) findViewById(R.id.quiz_questions);

        navigationView = (NavigationView) findViewById(R.id.nav_t);
        navigationView.setNavigationItemSelectedListener(this);
        View view = navigationView.getHeaderView(0);
        TextView userName = view.findViewById(R.id.name);
        userName.setText(courseName);

        imageView = view.findViewById(R.id.imageView9);
        DataBase.get_course_image(this, courseName, imageView);
        drawerLayout = (DrawerLayout) findViewById(R.id.draw_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent access_gallary = new Intent(Intent.ACTION_PICK);
//                access_gallary.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                startActivityForResult(access_gallary, GALLERY_REQ_CODE);
//            }
//        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigator_open, R.string.navigator_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //changing background and title on toolbar
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.blue)));
        getSupportActionBar().setTitle(courseName);

        Button button = findViewById(R.id.add_input_question);
        Button m_button = findViewById(R.id.add_multiple_choice);
        quetions =(LinearLayout) findViewById(R.id.quiz_questions);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                question_input();
            }
        });
        m_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                question_choice();
            }
        });

    }

//    private void add_image(){
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        if (bitmap != null){
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
//            byte[] bytes = byteArrayOutputStream.toByteArray();
//            final String base64Image = Base64.encodeToString(bytes, Base64.DEFAULT);
//            DataBase.upload_image(this, base64Image, courseName);
////            DataBase.get_course_image(this, courseName,imageView);
//
////            userNumber.setText(base64Image);
//        }
//    }

    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (resultCode == RESULT_OK){
//            if (requestCode == GALLERY_REQ_CODE){
//                try {
//                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
//                    imageView.setImageBitmap(bitmap);
//                    add_image();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }

    @Override
    public boolean onNavigationItemSelected(@Nullable MenuItem item) {
        switch(item.getItemId()){

            case R.id.Announcement:
                Intent A = new Intent(this , Announcements.class);
                A.putExtra("userNumber",userNumber);
                A.putExtra("courseName",courseName);
                A.putExtra("Role","Teacher");
                startActivity(A);
                return true;
            case R.id.course_slides:
                Intent intent1 = new Intent(this,upload_file.class);
                intent1.putExtra("courseName",courseName);
                intent1.putExtra("type","Documents");
                intent1.putExtra("userNumber",userNumber);
                startActivity(intent1);
                return true;
            case R.id.videos:
                Intent intent2 = new Intent(this,upload_file.class);
                intent2.putExtra("courseName",courseName);
                intent2.putExtra("type","Videos");
                intent2.putExtra("userNumber",userNumber);
                startActivity(intent2);
                return true;
            case R.id.quiz:
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            case R.id.other_quizzes:
                Intent quiz = new Intent(this,teacher_quiz_view.class);
                quiz.putExtra("courseName",courseName);
                quiz.putExtra("userNumber",userNumber);
                startActivity(quiz);
                return true;
            case R.id.back:
                DataBase.back_to_menu(this,userNumber);
                return true;
            case R.id.logout:
                Intent intent = new Intent(this , MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.ViewUsers:
                intent = new Intent(this,viewUsers.class);
                intent.putExtra("courseName",courseName);
                intent.putExtra("userNumber",userNumber);
                intent.putExtra("Role","Teacher");
                startActivity(intent);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    public void question_input(){

        View origin = View.inflate(this, R.layout.create_quiz, null);

        View pop_question = View.inflate(this , R.layout.input_question_layout, null);
        final PopupWindow pop = new PopupWindow(pop_question,1000,1300, true);
        pop.showAtLocation(origin, Gravity.CENTER,0,0);

        EditText input_question = pop_question.findViewById(R.id.enter_question);
        EditText answer = pop_question.findViewById(R.id.correct_question);
        EditText marks = pop_question.findViewById(R.id.question_mark);
        TextView cancel = pop_question.findViewById(R.id.cancel_button);
        TextView save = pop_question.findViewById(R.id.save_button);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop.dismiss();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (entered(input_question, answer, marks)){
                    //send to DataBase
                    View add_question = View.inflate(create_quiz.this , R.layout.input_layout_view, null);

                    TextView view_question = add_question.findViewById(R.id.view_question);
                    TextView view_marks = add_question.findViewById(R.id.allocated_marks);
                    TextView question_number = add_question.findViewById(R.id.question_number);
                    EditText answer_q = add_question.findViewById(R.id.enter_answer);

                    view_question.setText(input_question.getText().toString());
                    view_marks.setText("Marks: " + marks.getText().toString());
                    question_number.setText("Question " + String.valueOf(question_num)+ "       Delete Question");
                    answer_q.setText(answer.getText().toString());

                    quetions.addView(add_question);
                    pop.dismiss();
                    total_marks = total_marks + Integer.parseInt(marks.getText().toString());
                    question_num++;
                    question_number.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            quetions.removeView(add_question);
                            total_marks = total_marks - Integer.parseInt(marks.getText().toString());
                            delete_question();
                            // also remove it on the database
                        }
                    });
                }
                else{
                    pop.dismiss();
                    Toast.makeText(create_quiz.this, "Please enter all spaces",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public boolean entered(EditText input, EditText answer, EditText mark){
        if (input.getText().toString().isEmpty()){
            return false;
        }
        else if(answer.getText().toString().isEmpty()){
            return false;
        }
        else if(mark.getText().toString().isEmpty()){
            return false;
        }
        else{
            return true;
        }
    }

    public void question_choice(){

        View origin = View.inflate(this, R.layout.create_quiz, null);

        View pop_question = View.inflate(this , R.layout.multiple_choice_layout, null);
        final PopupWindow pop = new PopupWindow(pop_question,1000,1300, true);
        pop.showAtLocation(origin, Gravity.CENTER,0,0);

        EditText input_question = pop_question.findViewById(R.id.enter_question);
        EditText optionA = pop_question.findViewById(R.id.optionA);
        EditText optionB = pop_question.findViewById(R.id.optionB);
        EditText optionC = pop_question.findViewById(R.id.optionC);
        EditText optionD = pop_question.findViewById(R.id.optionD);
        EditText correct_option = pop_question.findViewById(R.id.correct_option);
        EditText marks = pop_question.findViewById(R.id.m_marks);
        TextView cancel = pop_question.findViewById(R.id.cancel);
        TextView save = pop_question.findViewById(R.id.save_question);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop.dismiss();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (entered_m(input_question, correct_option, marks, optionA, optionB, optionC, optionD)){
                    //send to DataBase

                    View add_question = View.inflate(create_quiz.this , R.layout.multiple_choice_view, null);

                    TextView view_question = add_question.findViewById(R.id.question);
                    TextView view_marks = add_question.findViewById(R.id.marks);
                    TextView question_number = add_question.findViewById(R.id.question_number);

                    RadioButton choiceA = add_question.findViewById(R.id.optionA);
                    RadioButton choiceB = add_question.findViewById(R.id.optionB);
                    RadioButton choiceC = add_question.findViewById(R.id.optionC);
                    RadioButton choiceD = add_question.findViewById(R.id.optionD);

                    choiceA.setText(optionA.getText().toString());
                    choiceB.setText(optionB.getText().toString());
                    choiceC.setText(optionC.getText().toString());
                    choiceD.setText(optionD.getText().toString());

                    if (correct_option.getText().toString().equals("A")){
                        choiceA.setChecked(true);
                        choiceB.setChecked(false);
                        choiceC.setChecked(false);
                        choiceD.setChecked(false);
                    }
                    else if (correct_option.getText().toString().equals("B")){
                        choiceA.setChecked(false);
                        choiceB.setChecked(true);
                        choiceC.setChecked(false);
                        choiceD.setChecked(false);
                    }
                    else if (correct_option.getText().toString().equals("C")){
                        choiceA.setChecked(false);
                        choiceB.setChecked(false);
                        choiceC.setChecked(true);
                        choiceD.setChecked(false);
                    }
                    else if (correct_option.getText().toString().equals("D")){
                        choiceA.setChecked(false);
                        choiceB.setChecked(false);
                        choiceC.setChecked(false);
                        choiceD.setChecked(true);
                    }

                    view_question.setText(input_question.getText().toString());
                    view_marks.setText("Marks: " + marks.getText().toString());
                    question_number.setText("Question " + String.valueOf(question_num) + "      Delete Question");

                    quetions.addView(add_question);
                    pop.dismiss();
                    total_marks = total_marks + Integer.parseInt(marks.getText().toString());
                    question_num++;
                    question_number.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            quetions.removeView(add_question);
                            total_marks = total_marks - Integer.parseInt(marks.getText().toString());
                            delete_question();
                            // also remove it on the database
                        }
                    });
                }
                else{
                    pop.dismiss();
                    Toast.makeText(create_quiz.this, "Please enter all spaces",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public boolean entered_m(EditText input, EditText answer, EditText mark, EditText optionA, EditText optionB, EditText optionC, EditText optionD){
        if (input.getText().toString().isEmpty()){
            return false;
        }
        else if(answer.getText().toString().isEmpty()){
            return false;
        }
        else if(mark.getText().toString().isEmpty()){
            return false;
        }
        else if(optionA.getText().toString().isEmpty()){
            return false;
        }
        else if(optionB.getText().toString().isEmpty()){
            return false;
        }
        else if(optionC.getText().toString().isEmpty()){
            return false;
        }
        else if(optionD.getText().toString().isEmpty()){
            return false;
        }
        else{
            return true;
        }
    }
    public static void save(Context context, LinearLayout course_list, String q_name, String courseName, String userNumber){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        for (int i = 3; i < course_list.getChildCount(); i++){
            TextView number = course_list.getChildAt(i).findViewById(R.id.question_number);
            String q_number = number.getText().toString();
            String right_n = "";
            int question_number;
            for (int j = "Question ".length(); j < (q_number.length()); j++){
                if (q_number.charAt(j) == ' '){
                    break;
                }
                right_n = right_n + q_number.charAt(j);
            }
            question_number = Integer.parseInt(right_n);
            // after getting the answers fo each question isolate the them into an arrayList and for every index we check if the answer found
            // at index + 1 is the same as the one provide
            EditText answer = course_list.getChildAt(i).findViewById(R.id.enter_answer);
            String question_answer = null;
            if (answer == null){
                RadioButton choiceA = course_list.getChildAt(i).findViewById(R.id.optionA);
                RadioButton choiceB = course_list.getChildAt(i).findViewById(R.id.optionB);
                RadioButton choiceC = course_list.getChildAt(i).findViewById(R.id.optionC);
                RadioButton choiceD = course_list.getChildAt(i).findViewById(R.id.optionD);
                if(choiceA.isChecked()){
                    question_answer = choiceA.getText().toString();
                }
                else if(choiceB.isChecked()){
                    question_answer = choiceB.getText().toString();
                }
                else if(choiceC.isChecked()){
                    question_answer = choiceC.getText().toString();
                }
                else if(choiceD.isChecked()){
                    question_answer = choiceD.getText().toString();
                }
                TextView get_marks = course_list.getChildAt(i).findViewById(R.id.marks);
                String marks = "";
                for (int j = "Marks: ".length(); j < get_marks.getText().toString().length(); j++){
                    if (get_marks.getText().toString().charAt(j) == ' '){
                        break;
                    }
                    marks = marks + get_marks.getText().toString().charAt(j);
                }
                TextView question = course_list.getChildAt(i).findViewById(R.id.question);
                String finalMarks = marks;
                String finalQuestion_answer1 = question_answer;
                DataBase.add_multiple_choice(context, courseName, String.valueOf(question_number), q_name,
                                question.getText().toString(), finalMarks, finalQuestion_answer1, choiceA.getText().toString(),
                                choiceB.getText().toString(), choiceC.getText().toString(), choiceD.getText().toString(),requestQueue);
            }
            else{
                question_answer = answer.getText().toString();
                TextView question = course_list.getChildAt(i).findViewById(R.id.view_question);
                TextView get_marks = course_list.getChildAt(i).findViewById(R.id.allocated_marks);
                String marks = "";
                for (int j = "Marks: ".length(); j < get_marks.getText().toString().length(); j++){
                    if (get_marks.getText().toString().charAt(j) == ' '){
                        break;
                    }
                    marks = marks + get_marks.getText().toString().charAt(j);
                }
                String finalQuestion_answer = question_answer;
                String finalMarks = marks;

                DataBase.add_input_question(context, courseName, String.valueOf(question_number), q_name,
                        question.getText().toString(), finalMarks, finalQuestion_answer,requestQueue);
            }
        }
        Intent quiz = new Intent(context, teacher_quiz_view.class);
        quiz.putExtra("userNumber", userNumber);
        quiz.putExtra("courseName", courseName);
        context.startActivity(quiz);
    }
    public void done(View view){
        View origin = View.inflate(this, R.layout.quiz_name, null);
        View pop_question = View.inflate(this , R.layout.quiz_name, null);
        final PopupWindow pop = new PopupWindow(pop_question,1000,300, true);
        pop.showAtLocation(origin, Gravity.CENTER,0,0);

        EditText quiz_name = pop_question.findViewById(R.id.quiz_name);
        TextView cancel = pop_question.findViewById(R.id.cancel_quiz);
        TextView save = pop_question.findViewById(R.id.save_quiz);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop.dismiss();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!quiz_name.getText().toString().isEmpty()){
                    //send to DataBase
                    q_name = quiz_name.getText().toString();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            DataBase.create_quiz(create_quiz.this, courseName, String.valueOf(total_marks), q_name, course_list, userNumber);
                        }
                    });
                }
                else{
                    pop.dismiss();
                    Toast.makeText(create_quiz.this, "Please enter all spaces",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void delete_question(){
        question_num = 1;
        for (int i = 3; i < course_list.getChildCount(); i++){
            TextView number = course_list.getChildAt(i).findViewById(R.id.question_number);
            number.setText("Question " + String.valueOf(question_num) + "       Delete Question");
            question_num++;
        }
    }
}