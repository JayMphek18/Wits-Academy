package com.example.wits_academy;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

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
            TextView courseCode = layout.findViewById(R.id.courseCode);
            TextView teacherName = layout.findViewById(R.id.teacherName);
            ImageView courseImage = layout.findViewById(R.id.imageView7);

            courseName.setText(course_names.get(index));
            courseCode.setText(course_code.get(index));
            teacherName.setText(teacher_name.get(index));
            Picasso.get()
                    .load("http://10.203.203.49/wits/php/profile_photos/" + courseCode + ".jpg")
                    .error(R.drawable.course_pic_1)
                    .fit()
                    .into(courseImage);

            TextView first = layout.findViewById(R.id.enroll_botton);
            first.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String course = courseName.getText().toString();
                    search_courses.enter_password(context, course, student_number);
                }
            });
            TextView courseName2 = layout.findViewById(R.id.courseName2);
            TextView courseCode2 = layout.findViewById(R.id.courseCode2);
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
            Picasso.get()
                    .load("http://10.203.203.49/wits/php/profile_photos/" + courseCode2 + ".jpg")
                    .error(R.drawable.course_pic_1)
                    .fit()
                    .into(courseImage2);

            course_list.addView(layout);
            left = left - 2;
        }
        else{
            View layout = View.inflate(context, R.layout.course_layout, null);
            TextView courseName = layout.findViewById(R.id.courseName);
            TextView courseCode = layout.findViewById(R.id.courseCode);
            TextView teacherName = layout.findViewById(R.id.teacherName);
            ImageView courseImage = layout.findViewById(R.id.imageView7);

            courseName.setText(course_names.get(index));
            courseCode.setText(course_code.get(index));
            teacherName.setText(teacher_name.get(index));
            Picasso.get()
                    .load("http://10.203.203.49/wits/php/profile_photos/" + courseCode + ".jpg")
                    .error(R.drawable.course_pic_1)
                    .fit()
                    .into(courseImage);

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


    public static void get_information(Context context,String _list,LinearLayout courses_list, JSONArray jsonArray,
                                       ArrayList<String> teacher_name, ArrayList<String> course_code,
                                       ArrayList<String> course_names,String student_number) {
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
                    left = viewlayout(left ,i ,context,course_names,student_number,course_code,teacher_name,courses_list);
                    i = i + 2;
                }
                else{
                    viewlayout(left ,i ,context,course_names,student_number,course_code,teacher_name,courses_list);
                    i = i + 1;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
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
            TextView courseCode = layout.findViewById(R.id.courseCode);
            TextView teacherName = layout.findViewById(R.id.teacherName);
            ImageView courseImage = layout.findViewById(R.id.imageView7);

            courseName.setText(course_names.get(index));
            courseCode.setText(course_code.get(index));
            teacherName.setText(teacher_name.get(index));
            Picasso.get()
                    .load("http://10.203.203.49/wits/php/profile_photos/" + courseCode + ".jpg")
                    .placeholder(R.drawable.course_pic_2)
                    .error(R.drawable.course_pic_1)
                    .fit()
                    .into(courseImage);

            RelativeLayout first = layout.findViewById(R.id.lay);
            first.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String course = courseName.getText().toString();
                    DataBase.course(context, course,number);
                }
            });
            TextView courseName2 = layout.findViewById(R.id.courseName2);
            TextView courseCode2 = layout.findViewById(R.id.courseCode2);
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
            Picasso.get()
                    .load("http://10.203.203.49/wits/php/profile_photos/" + courseCode2 + ".jpg")
                    .error(R.drawable.course_pic_1)
                    .placeholder(R.drawable.course_pic_2)
                    .fit()
                    .into(courseImage2);

            course_list.addView(layout);
            left = left - 2;
        }
        else{
            View layout = View.inflate(context, R.layout.list_layout, null);
            TextView courseName = layout.findViewById(R.id.courseName);
            TextView courseCode = layout.findViewById(R.id.courseCode);
            TextView teacherName = layout.findViewById(R.id.teacherName);
            ImageView courseImage = layout.findViewById(R.id.imageView7);

            courseName.setText(course_names.get(index));
            courseCode.setText(course_code.get(index));
            teacherName.setText(teacher_name.get(index));
            Picasso.get()
                    .load("http://10.203.203.49/wits/php/profile_photos/" + courseCode + ".jpg")
                    .error(R.drawable.course_pic_1)
                    .placeholder(R.drawable.course_pic_2)
                    .fit()
                    .into(courseImage);

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

    public static void get_nav_list_layout(Context context,String _list,LinearLayout courses_list, JSONArray jsonArray,
                                       ArrayList<String> course_names,String student_number) {
        try{
            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject course_information = jsonArray.getJSONObject(i);
                course_names.add(course_information.getString("course_name"));
            }
            nav_list_layout(context,course_names,student_number,courses_list);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void nav_list_layout( Context context , ArrayList<String> course_names, String number,
                                      LinearLayout course_list){
        String student_number = number;




    }

}
