package com.example.wits_academy;

<<<<<<< HEAD
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
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
=======
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
>>>>>>> 24838f0a76c329a96400e8e81b3a2c4af4151360

import java.util.HashMap;
import java.util.Map;

<<<<<<< HEAD
public class create_announcement extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    String ip = "http://10.0.2.2/php_app";
    String userNumber;
    private DrawerLayout drawerLayout;
    String courseName;
    NavigationView navigationView;
=======
public class create_announcement extends Activity {
   
    
    //This is the URL to the phpfiles 
    String ip = "http://10.100.15.104/wits_academy";
    //Declaring the variables
    String courseName;
    EditText et_AnnounceText;
    TextView tv_SendBtn;
>>>>>>> 24838f0a76c329a96400e8e81b3a2c4af4151360


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
<<<<<<< HEAD
        setContentView(R.layout.activity_create_announcement);

        Intent user_number = getIntent();
        userNumber = user_number.getStringExtra("userNumber");

        courseName = user_number.getStringExtra("courseName");

        navigationView = (NavigationView) findViewById(R.id.nav_t);
        navigationView.setNavigationItemSelectedListener(this);
        View view = navigationView.getHeaderView(0);

        TextView userName = view.findViewById(R.id.name);
        userName.setText(userNumber);
        ImageView imageView = view.findViewById(R.id.imageView9);
        DataBase.get_image(this, userNumber, imageView);

        drawerLayout = (DrawerLayout) findViewById(R.id.draw_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigator_open, R.string.navigator_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //changing background and title on toolbar
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.blue)));
        getSupportActionBar().setTitle("Make announcement");
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.questions:
                return true;
            case R.id.Announcement:
                Intent A = new Intent(this , Announcements.class);
                startActivity(A);
                return true;
            case R.id.course_slides:
                return true;
            case R.id.videos:
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
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    public void Send(View view) {
        String url = ip + "/send_announcement.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(create_announcement.this, response.trim(), Toast.LENGTH_SHORT).show();
                DataBase.back_to_menu(view.getContext(),userNumber);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(create_announcement.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                TextView Textview = (TextView) findViewById(R.id.AnnouncementText);
                String text = Textview.getText().toString();

=======
        //Initialising the variables
        setContentView(R.layout.activity_create_announcement);
        Intent user_number = getIntent();
        courseName = user_number.getStringExtra("courseName");
        et_AnnounceText = findViewById(R.id.AnnouncementText);
        tv_SendBtn = findViewById(R.id.sendAnnouncement);

    }
    
    /** This method sends a POST request to the specified URL, includes parameters to connect to the phpfile
     and handles the response and displays a Toast message indicating the response or error**/
    public void Send(View view) {
        String url = ip + "/send_announcement.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, response -> Toast.makeText(create_announcement.this, response.trim(), Toast.LENGTH_SHORT).show(),
                error -> Toast.makeText(create_announcement.this, error.toString().trim(), Toast.LENGTH_SHORT).show()) {
            @Override
            protected Map<String, String> getParams() {

                String text = et_AnnounceText.getText().toString();
>>>>>>> 24838f0a76c329a96400e8e81b3a2c4af4151360
                Map<String, String> data = new HashMap<>();
                data.put("announcementText", text);
                data.put("courseName", courseName);
                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(view.getContext());
        requestQueue.add(stringRequest);

    }
<<<<<<< HEAD
}
=======


}
>>>>>>> 24838f0a76c329a96400e8e81b3a2c4af4151360
