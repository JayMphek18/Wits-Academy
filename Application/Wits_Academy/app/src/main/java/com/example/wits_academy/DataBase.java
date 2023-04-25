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

    public static void teacher_courses(Context context, String user_number, LinearLayout courses_list) {
        String url = "http://10.203.203.49/wits/php/teaching_courses.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    ArrayList<String> course_names = new ArrayList<>();
                    ArrayList<String> course_code = new ArrayList<>();
                    ArrayList<String> teacher_name = new ArrayList<>();
                    ViewsClass.get_information_on_JSON(context, user_number, courses_list, jsonArray, teacher_name, course_code, course_names);
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
        String url = "http://10.203.203.49/wits/php/courses.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    ArrayList<String> course_names = new ArrayList<>();
                    ArrayList<String> course_code = new ArrayList<>();
                    ArrayList<String> teacher_name = new ArrayList<>();
                    ViewsClass.get_information(context, user_number, courses_list, jsonArray, teacher_name, course_code, course_names,user_number);
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
        String url = "http://10.203.203.49/wits/php/enrolled_course.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    ArrayList<String> course_names = new ArrayList<>();
                    ArrayList<String> course_code = new ArrayList<>();
                    ArrayList<String> teacher_name = new ArrayList<>();
                    ViewsClass.get_information_on_JSON(context, user_number, courses_list, jsonArray, teacher_name, course_code, course_names);
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
        String url = "http://10.203.203.49/wits/php/login.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.trim().equals("teacher") || response.trim().equals("Teacher")){
                    Intent intent = new Intent(context, main_menu_teacher.class);
                    intent.putExtra("information",user_number);
                    context.startActivity(intent);
                }
                else if(response.trim().equals("student") || response.trim().equals("Student")){
                    Intent intent = new Intent(context, main_menu_student.class);
                    intent.putExtra("information",user_number);
                    context.startActivity(intent);
                }
                else{
                    Toast.makeText(context, response.trim(), Toast.LENGTH_SHORT).show();
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

    // check if its a teacher or student befor going to the main pages of each user

    public static void back_to_menu(Context context, String user_number){
        String url = "http://10.203.203.49/wits/php/back_to_menu.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.trim().equals("teacher") || response.trim().equals("Teacher")){
                    Intent intent = new Intent(context, main_menu_teacher.class);
                    intent.putExtra("information",user_number);
                    context.startActivity(intent);
                }
                else if(response.trim().equals("student") || response.trim().equals("Student")){
                    Intent intent = new Intent(context, main_menu_student.class);
                    intent.putExtra("information",user_number);
                    context.startActivity(intent);
                }
                else{
                    Toast.makeText(context, response.trim(), Toast.LENGTH_SHORT).show();
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
                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }


    public static void course(Context context, String course_name, String number){
        String url = "http://10.203.203.49/wits/php/back_to_menu.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.trim().equals("teacher") || response.trim().equals("Teacher")){
                    Intent intent = new Intent(context, teacher_course_view.class);
                    intent.putExtra("courseName" , course_name);
                    intent.putExtra("userNumber", number);
                    context.startActivity(intent);
                }
                else if(response.trim().equals("student") || response.trim().equals("Student")){
                    Intent intent = new Intent(context, student_course_view.class);
                    intent.putExtra("courseName" , course_name);
                    intent.putExtra("userNumber", number);
                    context.startActivity(intent);
                }
                else{
                    Toast.makeText(context, response.trim(), Toast.LENGTH_SHORT).show();
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
                data.put("user_number", number);
                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }


    // Allow data to be saved on the database

    public static void save (Context context, Map < String, String > data_to_send){
        String url = "http://10.203.203.49/wits/php/register.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context, response.trim(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, MainActivity.class);
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

    //changes the password of the user

    public static void change_password(Context context, Map < String, String > data_to_send){
        String url = "http://10.203.203.49/wits/php/forgot_password.php";
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
        String url = "http://10.203.203.49/wits/php/create_course.php";
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
        String url = "http://10.203.203.49/wits/php/enroll.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context, response.trim()+ " in " + course_name, Toast.LENGTH_SHORT).show();
                course(context,course_name,student_number);
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
        String url = "http://10.203.203.49/wits/php/view_profile.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    name.setText(jsonObject.getString("first_name"));
                    surname.setText(jsonObject.getString("last_name"));
                    email.setText(jsonObject.getString("email_address"));

                    if (jsonObject.getString("user_role").equals("student")){
                        number.setText(userNumber);
                    }
                    else if (jsonObject.getString("user_role").equals("teacher")){
                        number.setText(userNumber);
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
        String url = "http://10.203.203.49/wits/php/change_profile.php";
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
        String url = "http://10.203.203.49/wits/php/upload_image.php";
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
