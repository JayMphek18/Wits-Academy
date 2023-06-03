package com.example.wits_academy;



import static com.example.wits_academy.R.id.Fragment_DocsLL;


import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.Nullable;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class teacher_course_view extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {



    String userNumber;
    LinearLayout course_list;
    private DrawerLayout drawerLayout;
    String courseName;
    TextView logout;
    NavigationView navigationView;
    BottomNavigationView navigationView1;
    Bitmap bitmap;
    ImageView imageView;
    private LinearLayout Docs;
    private LinearLayout Vids;
    // List Of all Documents in the class
    static ArrayList<String> titles;
    private final int GALLERY_REQ_CODE = 1000;
    ArrayList<documentView> documentViews;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_course_view);
        Intent user_number = getIntent();
        userNumber = user_number.getStringExtra("userNumber");
        courseName = user_number.getStringExtra("courseName");
        course_list = (LinearLayout) findViewById(R.id.contents);

        navigationView1 = findViewById(R.id.bottomNavigationView);
//        Make sure that Home Fragment is always set as default
        replaceFragement(new HomeFragment());

        // This is the method for creating the side navigation Br
        setSideNavigationBar();

        // Get Course Content and Display it
        Boolean wait = false;
        titles = new ArrayList<>();
        documentViews  = new ArrayList<documentView>();

        navigationView1.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.courseHome:
                        replaceFragement(new HomeFragment());
                        break;
                    case R.id.courseDocument:
                        titles.clear();
                        DocumentFragment fragment = new DocumentFragment();
                        replaceFragement(fragment);

                        Docs = fragment.getDocsLL();
                        DataBase.get_files(getApplicationContext(),titles,courseName,"teacher",userNumber,fragment,"Documents");
                        break;
                    case R.id.courseVideos:
                        titles.clear();
                        VideoFragment Vidfragment = new VideoFragment();
                        replaceFragement(Vidfragment);
                        Vids = Vidfragment.getVidsLL();
                        DataBase.get_files(getApplicationContext(),titles,courseName,"teacher",userNumber,Vidfragment,"Videos");
                        break;
                }
                return true;
            }
        });






    }
    private void add_image(){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (bitmap != null){
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            byte[] bytes = byteArrayOutputStream.toByteArray();
            final String base64Image = Base64.encodeToString(bytes, Base64.DEFAULT);
            DataBase.upload_image(this, base64Image, courseName, imageView);
//            DataBase.get_course_image(this, courseName,imageView);

//            userNumber.setText(base64Image);
        }
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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            if (requestCode == GALLERY_REQ_CODE){
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
                    imageView.setImageBitmap(bitmap);
                    add_image();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean onNavigationItemSelected(@Nullable MenuItem item) {
        switch(item.getItemId()){

            case R.id.other_quizzes:
                Intent quiz1 = new Intent(this,teacher_quiz_view.class);
                quiz1.putExtra("userNumber",userNumber);
                quiz1.putExtra("courseName",courseName);
                this.startActivity(quiz1);
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
                intent1.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                startActivity(intent1);
                return true;
            case R.id.videos:
                Intent intent2 = new Intent(this,upload_file.class);
                intent2.putExtra("courseName",courseName);
                intent2.putExtra("type","Videos");
                intent2.putExtra("userNumber",userNumber);
                intent2.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                startActivity(intent2);
                return true;
            case R.id.quiz:
                Intent quiz = new Intent(this,create_quiz.class);
                quiz.putExtra("userNumber",userNumber);
                quiz.putExtra("courseName",courseName);
                this.startActivity(quiz);
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
                return true;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public void uploadFile(View view) {
        Intent intent1 = new Intent(this,upload_file.class);
        intent1.putExtra("courseName",courseName);
        intent1.putExtra("type","Documents");
        intent1.putExtra("userNumber",userNumber);

        startActivity(intent1);
    }

    public void uploadVideo(View view) {
        Intent intent1 = new Intent(this,upload_file.class);
        intent1.putExtra("courseName",courseName);
        intent1.putExtra("type","Videos");
        intent1.putExtra("userNumber",userNumber);
        startActivity(intent1);
    }
    // Navigation Bar Setters
    private void setSideNavigationBar(){
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
        ActivityResultLauncher<Intent> resultLauncher =  registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK){
                                try {
                                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), result.getData().getData());
                                    imageView.setImageBitmap(bitmap);
                                    add_image();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                        }

                    }
                });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent access_gallary = new Intent(Intent.ACTION_PICK);
                access_gallary.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                resultLauncher.launch(access_gallary);
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigator_open, R.string.navigator_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //changing background and title on toolbar
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.blue)));
        getSupportActionBar().setTitle(courseName);
    }

    private void replaceFragement(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }

}

