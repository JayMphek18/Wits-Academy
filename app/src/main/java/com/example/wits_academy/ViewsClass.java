package com.example.wits_academy;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ViewsClass {

    public static int viewlayout(int left, int index, Context context , ArrayList<String> course_names, String number,
                                 ArrayList<String> course_code, ArrayList<String> teacher_name, LinearLayout course_list){
        String student_number = number;
        if (left >= 2){
            View layout = View.inflate(context, R.layout.course_layout, null);
            TextView courseName = layout.findViewById(R.id.courseName);
            TextView courseCode = layout.findViewById(R.id.quiz1_name);
            TextView teacherName = layout.findViewById(R.id.teacherName);
            ImageView courseImage = layout.findViewById(R.id.imageView7);

            courseName.setText(course_names.get(index));
            courseCode.setText(course_code.get(index));
            teacherName.setText(teacher_name.get(index));

            DataBase.get_course_image(context, courseName.getText().toString(), courseImage);

            TextView first = layout.findViewById(R.id.enroll_botton);
            first.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String course = courseName.getText().toString();
                    search_courses.enter_password(context, course, student_number);
                }
            });
            TextView courseName2 = layout.findViewById(R.id.quiz2_name);
            TextView courseCode2 = layout.findViewById(R.id.quiz2_details);
            TextView teacherName2 = layout.findViewById(R.id.teacherName2);
            ImageView courseImage2 = layout.findViewById(R.id.imageView7);

            TextView last = layout.findViewById(R.id.enroll_botton2);



            last.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String course = courseName2.getText().toString();
                    search_courses.enter_password(context, course, student_number);
                }
            });
            courseName2.setText(course_names.get(index + 1));
            courseCode2.setText(course_code.get(index + 1));
            teacherName2.setText(teacher_name.get(index + 1));
            DataBase.get_course_image(context, courseName2.getText().toString(), courseImage2);
            course_list.addView(layout);
            left = left - 2;
        }
        else{
            View layout = View.inflate(context, R.layout.course_layout, null);
            TextView courseName = layout.findViewById(R.id.courseName);
            TextView courseCode = layout.findViewById(R.id.quiz1_name);
            TextView teacherName = layout.findViewById(R.id.teacherName);
            ImageView courseImage = layout.findViewById(R.id.imageView7);

            courseName.setText(course_names.get(index));
            courseCode.setText(course_code.get(index));
            teacherName.setText(teacher_name.get(index));
            DataBase.get_course_image(context, courseName.getText().toString(), courseImage);

            TextView first = layout.findViewById(R.id.enroll_botton);
            first.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String course = courseName.getText().toString();
                    search_courses.enter_password(context, course, student_number);
                }
            });
            RelativeLayout last = layout.findViewById(R.id.lay2);
            last.setVisibility(View.GONE);
            course_list.addView(layout);
        }
        return left;
    }


    public static void get_information(Context context,String newText,LinearLayout courses_list, JSONArray jsonArray,
                                       ArrayList<String> teacher_name, ArrayList<String> course_code,
                                       ArrayList<String> course_names,String student_number) {
        courses_list.removeAllViews();
        try{
            if(newText.equals("")){
                for (int i = 0; i < jsonArray.length(); i++){
                    JSONObject course_information = jsonArray.getJSONObject(i);
                    teacher_name.add(course_information.getString("teacher_id"));
                    course_code.add(course_information.getString("course_code"));
                    course_names.add(course_information.getString("course_name"));
                }
            }
            else{
                for (int i = 0; i < jsonArray.length(); i++){
                    JSONObject course_information = jsonArray.getJSONObject(i);
                    if (course_information.getString("course_name").startsWith(newText)){
                        teacher_name.add(course_information.getString("teacher_id"));
                        course_code.add(course_information.getString("course_code"));
                        course_names.add(course_information.getString("course_name"));
                    }
                }
            }

        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        int i = 0;
            int left = course_names.size();
            while (i < course_names.size()){
                if (left >= 2){
                    left = viewlayout(left ,i ,context,course_names,student_number,course_code,teacher_name,courses_list);
                    i = i + 2;
                }
                else{
                    viewlayout(left ,i ,context,course_names,student_number,course_code,teacher_name,courses_list);
                    i = i + 1;
                }
            }
        }

    public static void get_information_on_JSON(Context context,String number,LinearLayout courses_list, JSONArray jsonArray,
                                               ArrayList<String> teacher_name, ArrayList<String> course_code,
                                               ArrayList<String> course_names) {
        try{
            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject course_information = jsonArray.getJSONObject(i);
                teacher_name.add(course_information.getString("teacher_id"));
                course_code.add(course_information.getString("course_code"));
                course_names.add(course_information.getString("course_name"));
            }
            int i = 0;
            int left = course_names.size();
            while (i < course_names.size()){
                if (left >= 2){
                    left = add_layout(left ,i ,context,course_names,course_code,teacher_name,courses_list,number);
                    i = i + 2;
                }
                else{
                    add_layout(left ,i ,context,course_names,course_code,teacher_name,courses_list,number);
                    i = i + 1;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static int add_layout(int left, int index, Context context , ArrayList<String> course_names, ArrayList<String> course_code,
                                 ArrayList<String> teacher_name, LinearLayout course_list, String number){
        if (left >= 2){
            View layout = View.inflate(context, R.layout.list_layout, null);
            TextView courseName = layout.findViewById(R.id.courseName);
            TextView courseCode = layout.findViewById(R.id.quiz1_name);
            TextView teacherName = layout.findViewById(R.id.teacherName);
            ImageView courseImage = layout.findViewById(R.id.imageView7);

            courseName.setText(course_names.get(index));
            courseCode.setText(course_code.get(index));
            teacherName.setText(teacher_name.get(index));
            DataBase.get_course_image(context, courseName.getText().toString(), courseImage);

            RelativeLayout first = layout.findViewById(R.id.lay);
            first.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String course = courseName.getText().toString();
                    DataBase.course(context, course,number);
                }
            });
            TextView courseName2 = layout.findViewById(R.id.quiz2_name);
            TextView courseCode2 = layout.findViewById(R.id.quiz2_details);
            TextView teacherName2 = layout.findViewById(R.id.teacherName2);
            ImageView courseImage2 = layout.findViewById(R.id.imageView8);

            RelativeLayout last = layout.findViewById(R.id.lay2);
            last.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String course = courseName2.getText().toString();
                    DataBase.course(context, course,number);
                }
            });
            courseName2.setText(course_names.get(index + 1));
            courseCode2.setText(course_code.get(index + 1));
            teacherName2.setText(teacher_name.get(index + 1));
            DataBase.get_course_image(context, courseName2.getText().toString(), courseImage2);

            course_list.addView(layout);
            left = left - 2;
        }
        else{
            View layout = View.inflate(context, R.layout.list_layout, null);
            TextView courseName = layout.findViewById(R.id.courseName);
            TextView courseCode = layout.findViewById(R.id.quiz1_name);
            TextView teacherName = layout.findViewById(R.id.teacherName);
            ImageView courseImage = layout.findViewById(R.id.imageView7);

            courseName.setText(course_names.get(index));
            courseCode.setText(course_code.get(index));
            teacherName.setText(teacher_name.get(index));
            DataBase.get_course_image(context, courseName.getText().toString(), courseImage);

            RelativeLayout first = layout.findViewById(R.id.lay);
            first.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String course = courseName.getText().toString();
                    DataBase.course(context, course,number);
                }
            });
            RelativeLayout last = layout.findViewById(R.id.lay2);
            last.setVisibility(View.GONE);  
            course_list.addView(layout);
        }
        return left;
    }

    public static void available_quizzes(Context context, LinearLayout view, ArrayList<String> quizName, ArrayList<String> quizMark,
                                String userNumber, String courseName){
        for (int i = 0; i < quizName.size(); i++){
            View layout = View.inflate(context, R.layout.quiz_start, null);
            TextView quiz = layout.findViewById(R.id.quiz_name);
            TextView marks = layout.findViewById(R.id.avarage_marks);
            TextView write_quiz = layout.findViewById(R.id.review_quiz);

            quiz.setText(quizName.get(i));
            marks.setText(quizMark.get(i));
            final int index = i;
            write_quiz.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent write = new Intent(context, write_quiz.class);
                    write.putExtra("userNumber", userNumber);
                    write.putExtra("courseName", courseName);
                    write.putExtra("quizName", quizName.get(index));
                    write.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(write);
                }
            });
            view.addView(layout);
        }
    }


    public static void quizzes(Context context, LinearLayout view, ArrayList<String> quizName, ArrayList<String> quizMark,
                                         String userNumber, String courseName){
        for (int i = 0; i < quizName.size(); i++){
            View layout = View.inflate(context, R.layout.delete_quiz, null);
            TextView quiz = layout.findViewById(R.id.quiz_name);
            TextView marks = layout.findViewById(R.id.avarage_marks);
            ImageView delete = layout.findViewById(R.id.imageView10);

            quiz.setText(quizName.get(i));
            marks.setText(quizMark.get(i));

            view.addView(layout);
        }
    }

    public static void past_quizzes(Context context, LinearLayout view, ArrayList<String> quizName, ArrayList<String> quizMark,
                                         String userNumber, String courseName){
        for (int i = 0; i < quizName.size(); i++){
            View layout = View.inflate(context, R.layout.quiz_details, null);
            TextView quiz = layout.findViewById(R.id.quiz_name);
            TextView marks = layout.findViewById(R.id.avarage_marks);
            TextView write_quiz = layout.findViewById(R.id.review_quiz);

            quiz.setText(quizName.get(i));
            marks.setText(quizMark.get(i) + " %");

            final int index = i;
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent write = new Intent(context, review_quiz.class);
                    write.putExtra("userNumber", userNumber);
                    write.putExtra("courseName", courseName);
                    write.putExtra("quizName", quizName.get(index));
                    write.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(write);
                }
            });
            view.addView(layout);
        }
    }
    public static void add_input_question(Context context, LinearLayout questions, String input_question, String marks, String question_num){
        View add_question = View.inflate(context , R.layout.input_layout_view, null);

        TextView view_question = add_question.findViewById(R.id.view_question);
        TextView view_marks = add_question.findViewById(R.id.allocated_marks);
        TextView question_number = add_question.findViewById(R.id.question_number);

        view_question.setText(input_question);
        view_marks.setText("Marks: " + marks);
        question_number.setText("Question " + question_num);

        questions.addView(add_question);
    }
    public static void add_multiple_choice(Context context, LinearLayout question, String input_question, String marks, String question_num, String optionA,
                                           String optionB, String optionC, String optionD){
        View add_question = View.inflate(context , R.layout.multiple_choice_view, null);

        TextView view_question = add_question.findViewById(R.id.question);
        TextView view_marks = add_question.findViewById(R.id.marks);
        TextView question_number = add_question.findViewById(R.id.question_number);

        RadioButton choiceA = add_question.findViewById(R.id.optionA);
        RadioButton choiceB = add_question.findViewById(R.id.optionB);
        RadioButton choiceC = add_question.findViewById(R.id.optionC);
        RadioButton choiceD = add_question.findViewById(R.id.optionD);

        choiceA.setText(optionA);
        choiceB.setText(optionB);
        choiceC.setText(optionC);
        choiceD.setText(optionD);

        view_question.setText(input_question);
        view_marks.setText("Marks: " + marks);
        question_number.setText("Question " + question_num);

        question.addView(add_question);
    }

    public static void review(Context context, LinearLayout review_view, String question_number, String question, String my_answer,
                       String correct_answer, String Marks){
        View layout = View.inflate(context, R.layout.quiz_review, null);

        TextView q_number = layout.findViewById(R.id.question_number);
        TextView q_string = layout.findViewById(R.id.question);
        TextView your_answer = layout.findViewById(R.id.your_answer);
        TextView actual_answer = layout.findViewById(R.id.actual_answer);
        TextView marks = layout.findViewById(R.id.marks);

        q_number.setText("Question " + question_number);
        q_string.setText(question);
        your_answer.setText("You answered : \n" + my_answer);
        actual_answer.setText("The correct answer : \n" + correct_answer);
        marks.setText("Marks : " + Marks);

        review_view.addView(layout);
    }

    public static void grades(Context context, LinearLayout grades, String student_number, String student_name, String marks){
        View layout = View.inflate(context, R.layout.student_grade, null);

        TextView name = layout.findViewById(R.id.student_name);
        TextView stu_number = layout.findViewById(R.id.student_number);
        TextView stu_mark = layout.findViewById(R.id.mark);

        name.setText(student_name);
        stu_number.setText(student_number);
        stu_mark.setText(marks + " %");

        grades.addView(layout);
    }

}
