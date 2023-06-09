package com.example.wits_academy;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataBase{
    /**This string is for the IP address of our server/xampp where the PHP application is hosted**/
    final static String ip  = "http://10.100.15.104/wits_academy";
    public static boolean finishedForUpload  = false;
    /** Method to retrieve the courses taught by a teacher**/
    public static void teacher_courses(Context context, String user_number, LinearLayout courses_list,TextView display) {
        String url = ip + "/teaching_courses.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            /** Response listener**/
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    if(jsonArray.length() != 0 ) {
                        display.setVisibility(display.GONE);
                        ArrayList<String> course_names = new ArrayList<>();
                        ArrayList<String> course_code = new ArrayList<>();
                        ArrayList<String> teacher_name = new ArrayList<>();
                        ViewsClass.get_information_on_JSON(context, user_number, courses_list, jsonArray, teacher_name, course_code, course_names);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                /** Error listener**/
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, error.toString().trim(), Toast.LENGTH_SHORT).show();
                    }
                }){ /** Request parameters**/
        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            Map<String, String> data = new HashMap<>();
            data.put("teacher_number", user_number);
            return data;
        }
        };
        /**Create a request queue and add the string request to it**/
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    //Retrieves information about courses made by teacher
    public static void get_all_courses(Context context, String user_number, LinearLayout courses_list, String newText) {
        String url = ip +"/courses.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    ArrayList<String> course_names = new ArrayList<>();
                    ArrayList<String> course_code = new ArrayList<>();
                    ArrayList<String> teacher_name = new ArrayList<>();
                    ViewsClass.get_information(context, newText, courses_list, jsonArray, teacher_name, course_code, course_names,user_number);
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

//Retrieves information about courses that students are enrolled in

    public static void student_courses(Context context, String user_number, LinearLayout courses_list, TextView display) {
        String url = ip + "/enrolled_course.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onResponse(String response) {
                if(!response.isEmpty()){
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        if (jsonArray.length() != 0){
                            display.setVisibility(display.GONE);
                            ArrayList<String> course_names = new ArrayList<>();
                            ArrayList<String> course_code = new ArrayList<>();
                            ArrayList<String> teacher_name = new ArrayList<>();
                            ViewsClass.get_information_on_JSON(context, user_number, courses_list, jsonArray, teacher_name, course_code, course_names);
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
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
        String url = ip + "/login.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.trim().equals("teacher") || response.trim().equals("Teacher")){
                    Intent intent = new Intent(context, main_menu_teacher.class);
                    intent.putExtra("information",user_number);
                    intent.putExtra("role","teacher");
                    context.startActivity(intent);
                }
                else if(response.trim().equals("student") || response.trim().equals("Student")){
                    Intent intent = new Intent(context, main_menu_student.class);
                    intent.putExtra("information",user_number);
                    intent.putExtra("role","student");
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

    // check if its a teacher or student before going to the main pages of each user

    public static void back_to_menu(Context context, String user_number){
        String url = ip + "/back_to_menu.php";
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

    //to retrieve and view the course info on course homepage
    public static void course(Context context, String course_name, String number){
        String url = ip + "/back_to_menu.php";
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
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
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
        String url = ip + "/register.php";
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
        String url = ip + "/forgot_password.php";
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


    //for the teacher to create a course
    public static void create_course (Context context, Map < String, String > data_to_send){
        String url = ip + "/create_course.php";
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
        }) {  //Requesting parameters
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data = data_to_send;
                return data;
            }
        };
        // Create Folder Where Documents And Videos for Course Will be stored
        String url2 = ip +"/course_folder.php";
        StringRequest folderRequest = new StringRequest(Request.Method.POST, url2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }}, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data = data_to_send;
                return data;
            }
        };
        //Add the String and folder request to the request queue
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        requestQueue.add(folderRequest);
    }
    //To allow the student to enroll into a course when they have the course password
    public static void enroll_on(Context context, String course_name, String course_password, String student_number){
        String url = ip + "/enroll.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context, response.trim()+ " in " + course_name, Toast.LENGTH_SHORT).show();
                if(response.trim().equals("Successfully enrolled"))
                    course(context,course_name,student_number);
                else back_to_menu(context,student_number);
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
    //get the user's details from the server to the profile page
    public static void profile(Context context, String userNumber, TextView name, TextView surname , TextView email,
                               TextView number){
        String url = ip + "/view_profile.php";
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
                    else if (jsonObject.getString("user_role").equals("Student")){
                        number.setText(userNumber);
                    }
                    else if (jsonObject.getString("user_role").equals("Teacher")){
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
    //change the profile picture of the user
    public static void change_profile(Context context, Map<String, String> map){
        String url = ip +"/change_profile.php";
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
    //upload user's image to the edit profile page
    public static void upload_image(Context context, String image_intent, String userNumber,ImageView userImage,ImageView imageView){
        String url = ip +"/upload_image.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context, response.trim(), Toast.LENGTH_SHORT).show();

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
    public static void upload_image(Context context, String image_intent, String userNumber,ImageView userImage){
        String url = ip +"/upload_image.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context, response.trim(), Toast.LENGTH_SHORT).show();
                DataBase.get_image(context, userNumber, userImage);
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
    //get user's image
    public static void get_image(Context context, String userNumber, ImageView imageView){
        Picasso.get()
                .load(ip + "/profile_photos/" + userNumber + ".jpg")
                .memoryPolicy(MemoryPolicy.NO_CACHE)
                .networkPolicy(NetworkPolicy.NO_CACHE)
                .error(R.drawable.course_pic_1)
                .placeholder(R.drawable.profile_icon)
                .fit()
                .into(imageView);
    }
    //to retrieve course image
    public static void get_course_image(Context context, String courseName, ImageView imageView){
        Picasso.get()
                .load(ip + "/profile_photos/" + courseName + ".jpg")
                .error(R.drawable.course_pic_1)
                .placeholder(R.drawable.profile_icon)
                .fit()
                .into(imageView);
    }

    //this is for retrieving the announcements from the server
    /**
     *
     * @param context
     * @param courseName
     * @param announcementModels
     * @param recyclerView
     * @param NoAnnounced - Used as A default iff there are no annoucementModels
     * @param role - Used to Inflate Page with appropriate view so as to manage priviliges(
     *             1) Creating Announcement
     *             2) Deleting an announcement)
     */
    public static void get_announcements(Context context, String courseName,
                                         ArrayList<announcementModel> announcementModels,
                                         RecyclerView recyclerView,
                                         TextView NoAnnounced,
                                         String role) {
        String url = ip + "/get_announcements.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            /** Response listener*/
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for(int i =0; i < jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String date = jsonObject.getString("announcement_date");
                        String subject = jsonObject.getString("announcement_subject");
                        String announcement_text = jsonObject.getString("announcement_text");
                        // Create a new announcementModel object and add it to the list
                        announcementModels.add(new announcementModel(subject,announcement_text,date,R.drawable.read_more));
                    }
                    if(announcementModels.size()!=0){
                        // If there are any announcements, create and set the adapter for the RecyclerView
                        announcement_recyclerViewAdapter adapter = new announcement_recyclerViewAdapter(context,announcementModels,role);
                        recyclerView.setAdapter(adapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(context));
                    }else{
                        /** If there are no announcements, display a toast message**/
                        Toast.makeText(context,"No announcements currently, check in later",Toast.LENGTH_LONG).show();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        },/** Error listener**/
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, error.toString().trim(), Toast.LENGTH_SHORT).show();
                    }
                }){
            /** Request parameters**/
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("courseName", courseName);
                return data;
            }
        };
        /**Create a request queue and add the string request to it**/

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);

    }
    /*
        TODO
     */
    //This is for the teacher to delete announcements
    public static void delete_announcement(Context context,String announcementText){
        String url = ip + "/delete_announcement.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context, response, Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> data = new HashMap<>();
                data.put("text" ,announcementText);
                return data;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(request);
    }

    /**
     Retrieves the users information in a course for participants page from the server
     * @param context
     * @param courseName
     * @param userList - Stores All Cards For Users Once they have been retrieved from server and is used by Adapter to
     *                 dynamically populate views as they come into vision
     * @param recyclerView - Used to view all CardViews and is used once Network request returns.
     */
    public static void get_users(Context context, String courseName, ArrayList<userModel> userList, RecyclerView recyclerView) {
        String url = ip + "/get_users.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            //Response listener
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for(int i =0; i < jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String fullName = jsonObject.getString("first_name") +" "+ jsonObject.getString("last_name");
                        String email_address = jsonObject.getString("email_address");
                        String role = jsonObject.getString("user_role");

                        // Do Stuff
                        userList.add(new userModel(fullName,email_address,role,R.drawable.profile_icon,R.drawable.ic_baseline_delete_24));
                    }
                    if(userList.size()!=0){
                        /**If there are users in the course,Create an adapter for the user
                         list**/

                        view_users_recyclerViewAdapter adapter = new view_users_recyclerViewAdapter(context,userList);
                        recyclerView.setAdapter(adapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(context));
                    }else{
                        /** Display a toast message when there are no users in the course**/
                        Toast.makeText(context,"No People currently in this course, check in later",Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },   //Error listener to display an error message
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, error.toString().trim(), Toast.LENGTH_SHORT).show();
                    }
                }){   //Request parameters
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("courseName", courseName);
                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    /**
     * @param context
     * @param courseName
     * @param content This is the title of the PDF
     */
    private static void download(Context context, String courseName, TextView content,String type) {
        String url = ip+ "/downloadFile.php?"+"courseName="+courseName+"&file="+content.getText().toString().trim()+"&type="+type;
        String mUrl= url;
        InputStreamVolleyRequest request = new InputStreamVolleyRequest(Request.Method.GET, mUrl,
                new Response.Listener<byte[]>() {
                    @Override
                    public void onResponse(byte[] response) {
                        //  handle the response
                        try {
                            if (response!=null) {
                                File downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                                File file = new File(downloadsDir, content.getText().toString());

                                FileOutputStream outputStream = new FileOutputStream(file);

                                String name=content.getText().toString().trim();
//                                outputStream = context.openFileOutput(name, Context.MODE_PRIVATE);
                                outputStream.write(response);
                                outputStream.close();
                                Toast.makeText(context, "Your Download is Complete.", Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            Log.d("KEY_ERROR", "UNABLE TO DOWNLOAD FILE");
                            e.printStackTrace();
                        }
                    }
                } ,new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO handle the error
                error.printStackTrace();
            }
        },null);
        RequestQueue mRequestQueue = Volley.newRequestQueue(context.getApplicationContext(), new HurlStack());
        mRequestQueue.add(request);
    }

    // Method to delete file upon request
    static void delete(TextView DocTitle,Context view,String courseName,String userNumber) {
        String url = ip + "/delete_file.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String a = DocTitle.getText().toString().trim();
                Toast.makeText(view,response,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(view,teacher_course_view.class);
                intent.putExtra("courseName",courseName);
                intent.putExtra("userNumber",userNumber);
                view.startActivity(intent);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> data = new HashMap<>();
                data.put("file_name",DocTitle.getText().toString().trim());
                data.put("course_name",courseName);
                return data;
            }
        };

        RequestQueue requestQueue  = Volley.newRequestQueue(view);
        requestQueue.add(stringRequest);
    }
    /**
     * Parameters for method are belo
     * @param context
     * @param titles
     * @param courseName
     * @param role
     * @param userNumber
     * @param fragment
     *
     * Method is used to get names of All Documents for a specific Course
     */
    public static void get_files(Context context,ArrayList<String> titles, String courseName,String role,String userNumber,DocumentFragment fragment,String type) {
        String url = ip+"/get_file_names.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String n=null;
                try {
                    JSONArray array = new JSONArray(response);
                    for(int i=0;i < array.length(); i ++){
                        String title = array.getString(i);
                        titles.add(title);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                // Display them
                for(int i =0; i < titles.size();i++) {
                    LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    View view;
                    TextView content;
                    ImageView downloadButton;
                    ImageView Image;
                    ImageView deleteBtn;
                    LinearLayout Docs = fragment.getDocsLL();
                    if(role.equals("teacher")) {
                        view = layoutInflater.inflate(R.layout.document_view, null);
                        content = view.findViewById(R.id.titleContent);
                        // Set Properties and Content for a card then add to LL
                        Image = view.findViewById(R.id.imageView11);
                        Image.setImageResource(R.mipmap.pages);
                        downloadButton = view.findViewById(R.id.downloadBtn);
                        downloadButton.setImageResource(R.drawable.ic_baseline_arrow_downward_24);
                        deleteBtn = view.findViewById(R.id.deleteBtn);
                        deleteBtn.setImageResource(R.drawable.ic_baseline_delete_24);
                        content.setText(titles.get(i));
                        Docs.addView(view);

                        downloadButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                download(view.getContext(),courseName,content,"Documents");
                            }
                        });

                        view.findViewById(R.id.deleteBtn).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                delete(content,view.getContext(),courseName,userNumber);
                            }
                        });

                    }else{
                        view = layoutInflater.inflate(R.layout.document_view_student, null);
                        content = view.findViewById(R.id.titleContent);
                        content.setText(titles.get(i));
                        downloadButton = view.findViewById(R.id.downloadBtn);
                        downloadButton.setImageResource(R.drawable.ic_baseline_arrow_downward_24);
                        Image = view.findViewById(R.id.imageView11);
                        Image.setImageResource(R.mipmap.pages);
                        // Set Properties and Content for a card then add to LL
                        Docs.addView(view);

                        downloadButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                download(view.getContext(),courseName,content,"Documents");
                            }
                        });
                    }

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> data = new HashMap<>();
                data.put("courseName",courseName);
                data.put("type",type);
                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(request);
    }

    public static void get_files(Context context,ArrayList<String> titles, String courseName,String role,String userNumber,VideoFragment fragment,String type) {
        String url = ip+"/get_file_names.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String n=null;
                try {
                    JSONArray array = new JSONArray(response);
                    for(int i=0;i < array.length(); i ++){
                        String title = array.getString(i);
                        titles.add(title);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                // Display them
                for(int i =0; i < titles.size();i++) {
                    LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    View view;
                    TextView content;
                    TextView title;
                    ImageView downloadButton;
                    ImageView Image;
                    ImageView deleteBtn;
                    LinearLayout Docs = fragment.getVidsLL();
                    if(role.equals("teacher")) {
                        view = layoutInflater.inflate(R.layout.document_view, null);
                        content = view.findViewById(R.id.titleContent);
                        // Set Properties and Content for a card then add to LL
                        title = view.findViewById(R.id.title);
                        title.setText("Video Title");
                        Image = view.findViewById(R.id.imageView11);
                        Image.setImageResource(R.mipmap.pages);
                        downloadButton = view.findViewById(R.id.downloadBtn);
                        downloadButton.setImageResource(R.drawable.ic_baseline_arrow_downward_24);
                        deleteBtn = view.findViewById(R.id.deleteBtn);
                        deleteBtn.setImageResource(R.drawable.ic_baseline_delete_24);
                        content.setText(titles.get(i));
                        Docs.addView(view);

                        downloadButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                download(view.getContext(),courseName,content,"Videos");
                            }
                        });

                        view.findViewById(R.id.deleteBtn).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                delete(content,view.getContext(),courseName,userNumber);
                            }
                        });

                    }else{
                        view = layoutInflater.inflate(R.layout.document_view_student, null);
                        content = view.findViewById(R.id.titleContent);
                        title = view.findViewById(R.id.title);
                        title.setText("Video Title");
                        content.setText(titles.get(i));
                        downloadButton = view.findViewById(R.id.downloadBtn);
                        downloadButton.setImageResource(R.drawable.ic_baseline_arrow_downward_24);
                        Image = view.findViewById(R.id.imageView11);
                        Image.setImageResource(R.mipmap.pages);
                        // Set Properties and Content for a card then add to LL
                        Docs.addView(view);

                        downloadButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                download(view.getContext(),courseName,content,"Videos");
                            }
                        });
                    }

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> data = new HashMap<>();
                data.put("courseName",courseName);
                data.put("type",type);
                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(request);
    }


    public static void create_quiz(Context context, String courseName, String totalMarks, String quizName, LinearLayout course_list, String userNumber) {
        String url = ip + "/create_quiz.php";
        final int[] quiz_id = new int[1];
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            //Response listener
            @Override
            public void onResponse(String response) {
                create_quiz.save(context, course_list, quizName, courseName, userNumber);
                Toast.makeText(context, "Quiz successfully created!", Toast.LENGTH_SHORT).show();

            }
        },   //Error listener to display an error message
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, error.toString().trim(), Toast.LENGTH_SHORT).show();
                    }
                }) {   //Request parameters
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("course_name", courseName);
                data.put("total_marks", totalMarks);
                data.put("quiz_name", quizName);
                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
//        return quiz_id[0];
    }


    public static void add_input_question(Context context, String courseName, String question_number, String quizName,
                                          String question, String marks, String answer, RequestQueue requestQueue) {
        String url = ip + "/enter_question.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            //Response listener
            @Override
            public void onResponse(String response) {
                String m  = response;
            }
        },   //Error listener to display an error message
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, error.toString().trim(), Toast.LENGTH_SHORT).show();
                    }
                }) {   //Request parameters
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("course_name", courseName);
                data.put("question_num", question_number);
                data.put("quiz_name", quizName);
                data.put("answer", answer);
                data.put("mark", marks);
                data.put("question", question);
                return data;
            }
        };

        requestQueue.add(stringRequest);
//        return quiz_id[0];
    }


    public static void add_multiple_choice(Context context, String courseName, String question_number, String quizName,
                                           String question, String marks, String answer, String optionA, String optionB,
                                           String optionC, String optionD,RequestQueue requestQueue) {
        String url = ip + "/multiple_choice.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            //Response listener
            @Override
            public void onResponse(String response) {
                String m  = response;
            }
        },   //Error listener to display an error message
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, error.toString().trim(), Toast.LENGTH_SHORT).show();
                    }
                }) {   //Request parameters
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("course_name", courseName);
                data.put("question_num", question_number);
                data.put("quiz_name", quizName);
                data.put("answer", answer);
                data.put("mark", marks);
                data.put("question", question);
                data.put("optionA", optionA);
                data.put("optionB", optionB);
                data.put("optionC", optionC);
                data.put("optionD", optionD);
                return data;
            }
        };
        requestQueue.add(stringRequest);
//        return quiz_id[0];
    }

    public static void grade(Context context,String courseName,String quizName ,HashMap<String,Integer> studentAnswers,String userNumber){
        // Get All the correct answers for the quiz

        String url = ip + "/answer_quiz2.php?quiz_name="+quizName+"&course_name="+courseName;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            int total_mark =0;
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    int i =0;
                    for(String key : studentAnswers.keySet()){
                        if( key.equals(jsonArray.getString(i)) ){
                            total_mark += studentAnswers.get(key);
                        }
                    }
                    // Push Grades to the server
                    DataBase.send_grade(context,courseName,quizName,userNumber ,total_mark);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String r = error.toString();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    // Sends Grades for a student to the database for a quiz with QuizName
    private static void send_grade(Context context,String courseName, String quizName, String userNumber, int total_mark) {
        String url = ip + "/send_grades.php?quizName="+quizName+"&userNumber="+userNumber+"&mark="+Integer.toString(total_mark)+"&courseName="+courseName;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            int total_mark =0;
            @Override
            public void onResponse(String response) {
                Toast.makeText(context,response,Toast.LENGTH_LONG).show();

                Intent quiz = new Intent(context,student_quiz_view.class);
                quiz.putExtra("userNumber",userNumber);
                quiz.putExtra("courseName",courseName);
                context.startActivity(quiz);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    public static void get_all_quiz(Context context, String courseName, LinearLayout course_list, String userNumber) {
        String url = ip + "/all_quiz.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            //Response listener
            @Override
            public void onResponse(String response) {
                try {
                    ArrayList<String> quiz_name = new ArrayList<>();
                    ArrayList <String> avarage_marks = new ArrayList<>();
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++){
                        JSONObject new_quiz = array.getJSONObject(i);
                        String quizName = new_quiz.getString("quiz_name");
                        String total_marks = new_quiz.getString("total_marks");
                        quiz_name.add(quizName);
                        avarage_marks.add(total_marks);
                    }
                    ViewsClass.quizzes(context, course_list, quiz_name, avarage_marks, userNumber, courseName);
                    teacher_quiz_view.setDelete(context, course_list, courseName, userNumber);

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        },   //Error listener to display an error message
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, error.toString().trim(), Toast.LENGTH_SHORT).show();
                    }
                }) {   //Request parameters
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("course_name", courseName);
                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    public static void delete_quiz(Context context, String courseName, String quizName) {
        String url = ip + "/delete_quiz.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            //Response listener
            @Override
            public void onResponse(String response) {
                String n = response;
            }
        },   //Error listener to display an error message
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, error.toString().trim(), Toast.LENGTH_SHORT).show();
                    }
                }) {   //Request parameters
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("course_name", courseName);
                data.put("quiz_name", quizName);
                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
    public static void get_grades(Context context, String courseName, LinearLayout course_list, String quizName) {
        String url = ip + "/get_marks.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            //Response listener
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++){
                        JSONObject new_quiz = array.getJSONObject(i);
                        String studentNumber = new_quiz.getString("quiz_id");
                        String marks = new_quiz.getString("marks");
                        String student = new_quiz.getString("student_id");
                        ViewsClass.grades(context, course_list, studentNumber,student, marks);
                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        },   //Error listener to display an error message
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, error.toString().trim(), Toast.LENGTH_SHORT).show();
                    }
                }) {   //Request parameters
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("course_name", courseName);
                data.put("quiz_name", quizName);
                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
    public static void written_quiz(Context context, String courseName, LinearLayout available, LinearLayout past, String userNumber) {
        String url = ip + "/student_quiz.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            //Response listener
            @Override
            public void onResponse(String response) {
                try {
                    ArrayList<String> available_quizName = new ArrayList<>();
                    ArrayList <String> available_marks= new ArrayList<>();
                    ArrayList<String> past_quizName = new ArrayList<>();
                    ArrayList <String> past_marks= new ArrayList<>();
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++){
                        JSONObject new_quiz = array.getJSONObject(i);
                        String quizName = new_quiz.getString("quiz_name");
                        String written = new_quiz.getString("course_id");
                        String marks = new_quiz.getString("total_marks");
                        if (written.equals("written")){
                            past_quizName.add(quizName);
                            past_marks.add(marks);
                        }
                        else{
                            available_quizName.add(quizName);
                            available_marks.add(marks);
                        }
                    }
                    ViewsClass.available_quizzes(context, available, available_quizName, available_marks,userNumber, courseName);
                    ViewsClass.past_quizzes(context, past, past_quizName, past_marks,userNumber, courseName);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        },   //Error listener to display an error message
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, error.toString().trim(), Toast.LENGTH_SHORT).show();
                    }
                }) {   //Request parameters
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("course_name", courseName);
                data.put("student_number", userNumber);
                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    public static void get_questions(Context context, LinearLayout layout,String courseName,String quizName) {
        String url = ip + "/get_question.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            //Response listener
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        if (!jsonObject.has("optionA")){
                            ViewsClass.add_input_question(context,layout,jsonObject.getString("question"),
                                    jsonObject.getString("marks"),
                                    jsonObject.getString("question_number"));
                        }else{
                            ViewsClass.add_multiple_choice(context,layout,jsonObject.getString("question"),
                                    jsonObject.getString("marks"),
                                    jsonObject.getString("question_number"),
                                    jsonObject.getString("optionA"),
                                    jsonObject.getString("optionB"),
                                    jsonObject.getString("optionC"),
                                    jsonObject.getString("optionD"));
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },   //Error listener to display an error message
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, error.toString().trim(), Toast.LENGTH_SHORT).show();
                    }
                }) {   //Request parameters
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("course_name", courseName);
                data.put("quiz_name", quizName);
                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    public static void review_quiz(Context context, String courseName, LinearLayout review, String userNumber, String quizName) {
        String url = ip + "/review_quiz.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            //Response listener
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++){
                        JSONObject new_quiz = array.getJSONObject(i);
                        String question = new_quiz.getString("question");
                        String question_number = new_quiz.getString("question_number");
                        String correct_answer = new_quiz.getString("correct_answer");
                        String user_answer = new_quiz.getString("user_answer");
                        String marks = new_quiz.getString("marks");
                        ViewsClass.review(context, review,question_number, question, user_answer, correct_answer, marks);
                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        },   //Error listener to display an error message
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, error.toString().trim(), Toast.LENGTH_SHORT).show();
                    }
                }) {   //Request parameters
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("course_name", courseName);
                data.put("student_number", userNumber);
                data.put("quiz_name", quizName);
                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
    public static void save_answer(Context context, String courseName, String userNumber, String quizName, String quuestion_num, String answer) {
        String url = ip + "/answer_quiz.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            //Response listener
            @Override
            public void onResponse(String response) {
                String n = response;

            }
        },   //Error listener to display an error message
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, error.toString().trim(), Toast.LENGTH_SHORT).show();
                    }
                }) {   //Request parameters
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("course_name", courseName);
                data.put("student_number", userNumber);
                data.put("quiz_name", quizName);
                data.put("question_number", quuestion_num);
                data.put("answer" , answer);
                data.put("mark" , "0");

                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

}






