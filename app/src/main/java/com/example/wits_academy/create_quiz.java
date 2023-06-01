package com.example.wits_academy;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

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

import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.Nullable;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class create_quiz extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    LinearLayout quetions;
    int question_num = 1;
    Button done;

    String userNumber;
    LinearLayout course_list;
    private DrawerLayout drawerLayout;
    String courseName;
    TextView logout;
    NavigationView navigationView;
    Bitmap bitmap;
    ImageView imageView;

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




        done = findViewById(R.id.finised);
        Button button = findViewById(R.id.add_input_question);
        Button m_button = findViewById(R.id.add_multiple_choice);
        quetions =(LinearLayout) findViewById(R.id.quiz_questions);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                question_input( "2000000", "sdfjbsdiu");
            }
        });
        m_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                question_choice("7jdfjnsfdsf", "jhdjhwk");
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
            case R.id.questions:
                return true;
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
    public void question_input(String course_name, String student_number){

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

                    view_question.setText(input_question.getText().toString());
                    view_marks.setText("Marks: " + marks.getText().toString());
                    question_number.setText("Question " + String.valueOf(question_num)+ "\t\t Delete Question");

                    quetions.addView(add_question);
                    pop.dismiss();
                    question_num++;
                    question_number.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            quetions.removeView(add_question);
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

    public void question_choice(String course_name, String student_number){

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

                    view_question.setText(input_question.getText().toString());
                    view_marks.setText("Marks: " + marks.getText().toString());
                    question_number.setText("Question " + String.valueOf(question_num) + "           Delete Question");

                    quetions.addView(add_question);
                    pop.dismiss();
                    question_num++;
                    question_number.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            quetions.removeView(add_question);
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
    public void done(View view) {
//        after you are done creating the quiz
//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);
    }
}