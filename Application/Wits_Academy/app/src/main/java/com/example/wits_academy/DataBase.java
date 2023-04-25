package com.example.wits_academy;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataBase {

    public static void course(Context context, String course_name, String number){
        if (context.equals(main_menu_teacher.class)){
            Intent intent = new Intent(context, teacher_course_view.class);
            intent.putExtra("courseName" , course_name);
            intent.putExtra("userNumber", number);
            context.startActivity(intent);
        }
        else if (context.equals(main_menu_student.class)){
            Intent intent = new Intent(context, student_course_view.class);
            intent.putExtra("courseName" , course_name);
            intent.putExtra("userNumber", number);
            context.startActivity(intent);
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
                    .load("http://10.203.197.211/wits/php/profile_photos/" + courseCode + ".jpg")
                    .error(R.drawable.course_pic_1)
                    .fit()
                    .into(courseImage);

                RelativeLayout first = layout.findViewById(R.id.lay);
                first.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String course = courseName.getText().toString();
                        course(context, course,number);
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
                        String course = courseName.getText().toString();
                        course(context, course,number);
                    }
                });
                courseName2.setText(course_names.get(index + 1));
                courseCode2.setText(course_code.get(index + 1));
                teacherName2.setText(teacher_name.get(index + 1));
                Picasso.get()
                    .load("http://10.203.197.211/wits/php/profile_photos/" + courseCode2 + ".jpg")
                    .error(R.drawable.course_pic_1)
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
                    .load("http://10.203.197.211/wits/php/profile_photos/" + courseCode + ".jpg")
                    .error(R.drawable.course_pic_1)
                    .fit()
                    .into(courseImage);

                RelativeLayout first = layout.findViewById(R.id.lay);
                first.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String course = courseName.getText().toString();
                        course(context, course,number);
                    }
                });
                RelativeLayout last = layout.findViewById(R.id.lay2);
                last.setVisibility(View.GONE);
                course_list.addView(layout);
            }
            return left;
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

    public static int viewlayout(int left, int index, Context context , ArrayList<String> course_names,String number,
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
                    .load("http://10.203.197.211/wits/php/profile_photos/" + courseCode + ".jpg")
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
                    String course = courseName.getText().toString();
                    search_courses.enter_password(context, course, student_number);
                }
            });
            courseName2.setText(course_names.get(index + 1));
            courseCode2.setText(course_code.get(index + 1));
            teacherName2.setText(teacher_name.get(index + 1));
            Picasso.get()
                    .load("http://10.203.197.211/wits/php/profile_photos/" + courseCode2 + ".jpg")
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
                    .load("http://10.203.197.211/wits/php/profile_photos/" + courseCode + ".jpg")
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

    public static void teacher_courses(Context context, String user_number, LinearLayout courses_list) {
        String url = "http://10.203.197.211/wits/php/teaching_courses.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    ArrayList<String> course_names = new ArrayList<>();
                    ArrayList<String> course_code = new ArrayList<>();
                    ArrayList<String> teacher_name = new ArrayList<>();
                    get_information_on_JSON(context, user_number, courses_list, jsonArray, teacher_name, course_code, course_names);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("student_number", user_number);
                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }


    public static void get_all_courses(Context context, String user_number, LinearLayout courses_list) {
        String url = "http://10.203.197.211/wits/php/courses.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    ArrayList<String> course_names = new ArrayList<>();
                    ArrayList<String> course_code = new ArrayList<>();
                    ArrayList<String> teacher_name = new ArrayList<>();
                    get_information(context, user_number, courses_list, jsonArray, teacher_name, course_code, course_names,user_number);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }


    public static void student_courses(Context context, String user_number, LinearLayout courses_list) {
        String url = "http://10.203.197.211/wits/php/enrolled_course.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    ArrayList<String> course_names = new ArrayList<>();
                    ArrayList<String> course_code = new ArrayList<>();
                    ArrayList<String> teacher_name = new ArrayList<>();
                    get_information_on_JSON(context, user_number, courses_list, jsonArray, teacher_name, course_code, course_names);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("student_number", user_number);
                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    //check if the user does exist in the current data base

    public static void exists(Context context, String user_password, String user_number){
        String url = "http://10.203.197.211/wits/php/login.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.trim().equals("teacher")){
                    Intent intent = new Intent(context, main_menu_teacher.class);
                    intent.putExtra("information",user_number);
                    context.startActivity(intent);
                }
                else if(response.trim().equals("student")){
                    Intent intent = new Intent(context, main_menu_student.class);
                    intent.putExtra("information",user_number);
                    context.startActivity(intent);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("user_number", user_number);
                data.put("password", user_password);
                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    // Allow data to be saved on the database

    public static void save (Context context, Map < String, String > data_to_send){
        String url = "http://10.203.197.211/wits/php/register.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context, response.trim(), Toast.LENGTH_SHORT).show();

            }}, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.toString().trim(), Toast.LENGTH_SHORT).show();
                System.out.println(error.toString().trim());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data = data_to_send;
                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    //changes the password of the user

    public static void change_password(Context context, Map < String, String > data_to_send){
        String url = "http://10.203.197.211/wits/php/forgot_password.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context, response.trim(), Toast.LENGTH_SHORT).show();
            }}, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data = data_to_send;
                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    public static void create_course (Context context, Map < String, String > data_to_send){
        String url = "http://10.203.197.211/wits/php/create_course.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context, response.trim(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, teacher_course_view.class);
                intent.putExtra("courseName", data_to_send.get("course_name"));
                intent.putExtra("userNumber" , data_to_send.get("employee_number"));
                context.startActivity(intent);

            }}, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.toString().trim(), Toast.LENGTH_SHORT).show();
                System.out.println(error.toString().trim());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data = data_to_send;
                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    public static void enroll_on(Context context, String course_name, String course_password, String student_number){
        String url = "http://10.203.197.211/wits/php/enroll.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context, response.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("student_number", student_number);
                data.put("course_password", course_password);
                data.put("course_name", course_name);
                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    public static void profile(Context context, String userNumber, TextView name, TextView surname , TextView email,
                               TextView number){
        String url = "http://10.203.197.211/wits/php/view_profile.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    name.setText("Name : " + jsonObject.getString("first_name"));
                    surname.setText("Surname : " + jsonObject.getString("last_name"));
                    email.setText("Email Address : " + jsonObject.getString("email_address"));

                    if (jsonObject.getString("user_role").equals("student")){
                        number.setText("Student Number : " + userNumber);
                    }
                    else if (jsonObject.getString("user_role").equals("teacher")){
                        number.setText("Employee Number : " + userNumber);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("user_number", userNumber);
                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    public static void change_profile(Context context, Map<String, String> map){
        String url = "http://10.203.197.211/wits/php/change_profile.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.trim().equals("teacher")){
                    Intent intent = new Intent(context, main_menu_teacher.class);
                    intent.putExtra("information",map.get("user_number"));
                    context.startActivity(intent);
                }
                else if(response.trim().equals("student")){
                    Intent intent = new Intent(context, main_menu_student.class);
                    intent.putExtra("information",map.get("user_number"));
                    context.startActivity(intent);
                }
            }}, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data = map;
                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    public static void upload_image(Context context, String image_intent, String userNumber){
        String url = "http://10.203.197.211/wits/php/upload_image.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context, response.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("userNumber", userNumber);
                data.put("imageURL", image_intent);
                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

}
