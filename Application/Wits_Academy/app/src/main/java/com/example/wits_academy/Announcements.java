package com.example.wits_academy;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.Inflater;

public class Announcements extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    String userNumber;
    String courseName;
    LinearLayout course_list;
    private DrawerLayout drawerLayout;
    TextView logout;
    String role;
    NavigationView navigationView;

    ImageView deleteButton;
    // Array to store all announcements
    ArrayList<announcementModel> announcementsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set Content Based on whether user is a Teacher or a student
        role = this.getIntent().getStringExtra("Role");
        if(role.equals("Teacher"))
           setContentView(R.layout.activity_announcements);
        else
            setContentView(R.layout.activity_announcements_student);

        drawerLayout = (DrawerLayout) findViewById(R.id.draw_layout);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        navigationView = (NavigationView) findViewById(R.id.nav);
        navigationView.setNavigationItemSelectedListener(this);
        View view = navigationView.getHeaderView(0);
        TextView userName = view.findViewById(R.id.name);
        userName.setText(userNumber);
        ImageView imageView = view.findViewById(R.id.imageView9);

        DataBase.get_image(this, userNumber, imageView);

        ActionBarDrawerToggle toggle =  new ActionBarDrawerToggle(this, drawerLayout,toolbar,
                R.string.navigator_open,R.string.navigator_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //changing background and title on toolbar
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.blue)));
        getSupportActionBar().setTitle("Announcements");

        // Get Extra Information From Previous Activity
        userNumber = getIntent().getStringExtra("userNumber");
        courseName = getIntent().getStringExtra("courseName");

        // Get And Display Announcements If Any
        announcementsList = new ArrayList<announcementModel>();
        RecyclerView recyclerView = findViewById(R.id.mRecyclerView);
        TextView NoAnnounced =new TextView(this);
        DataBase.get_announcements(this,courseName,announcementsList,recyclerView,NoAnnounced,role);

    }



    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@Nullable MenuItem item) {
        switch(item.getItemId()){
            case R.id.questions:
                return true;
            case R.id.Announcement:
                Intent A = new Intent(this , Announcements.class);
                A.putExtra("userNumber",userNumber);
                A.putExtra("courseName",courseName);
                A.putExtra("Role",role);
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
                return true;
            case R.id.quiz:
                return true;
            case R.id.assignment:
                return true;
            case R.id.grades:
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
                intent.putExtra("Role",role);
                startActivity(intent);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    // Get Announcements for course from server
    private void getAnnouncements(){
        ArrayList<announcementModel> announcementList = new ArrayList<>();

    }


    private void displayAnnouncements(ArrayList<announcementModel> announcementModels){
    }

    public void go_to_CreateAnnounce(View view){
//        Intent intent = new Intent(view.getContext(),create_announcement.class);
//        intent.putExtra("courseName",courseName);
//        intent.putExtra("userNumber",userNumber);
//        startActivity(intent);

        // Get an Inflater and inflate popup using layout
        String ip = "http://10.0.2.2/php_app";
        LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
        View view1 = inflater.inflate(R.layout.activity_create_announcement,null);


        EditText announce_text = (EditText) view1.findViewById(R.id.AnnouncementText);
        TextView tvSend = (TextView)view1.findViewById(R.id.sendAnnouncement);
        tvSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = ip + "/send_announcement.php";
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(Announcements.this,response,Toast.LENGTH_LONG).show();
                        finish();
                        startActivity(getIntent());
                    }
                },
                        error -> Toast.makeText(Announcements.this, error.toString().trim(), Toast.LENGTH_SHORT).show()) {
                    @Override
                    protected Map<String, String> getParams() {

                        String text = announce_text.getText().toString();
                        Map<String, String> data = new HashMap<>();
                        data.put("announcementText", text);
                        data.put("courseName", courseName);
                        return data;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(view.getContext());
                requestQueue.add(stringRequest);
            }
        });
        // Create popup Window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it

        final PopupWindow popupWindow = new PopupWindow(view1, width, height, focusable);
        popupWindow.setAnimationStyle(Animation.START_ON_FIRST_FRAME);
        // Show Window
        popupWindow.showAtLocation(view, Gravity.CENTER,0,0);



        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                popupWindow.dismiss();
            }
        });

    }
}