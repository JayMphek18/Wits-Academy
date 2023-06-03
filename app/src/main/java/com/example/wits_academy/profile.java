package com.example.wits_academy;


import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.Nullable;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class profile extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    TextView name;
    TextView surname;
    TextView email;
    TextView userNumber;
    String user;
    ImageView userImage;
    ImageView imageView;
    Intent Image;
    String has = "";
    Bitmap bitmap;
    private DrawerLayout drawerLayout;
    String courseName;
    TextView logout;
    NavigationView navigationView;

    private final int GALLERY_REQ_CODE = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        Intent user_number = getIntent();
        user = user_number.getStringExtra("usernumber");
        name = findViewById(R.id.profile_name);
        surname = findViewById(R.id.profile_surname);
        email = findViewById(R.id.profile_email);
        userNumber = findViewById(R.id.profile_number);
        userImage = (ImageView)findViewById(R.id.profile_image);
        navigationView = (NavigationView) findViewById(R.id.nav);
        navigationView.setNavigationItemSelectedListener(this);
        View view = navigationView.getHeaderView(0);
        imageView = view.findViewById(R.id.imageView9);


        ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {

                        if (result.getResultCode() == RESULT_OK){
//                            if (result.getResultCode() == GALLERY_REQ_CODE){
                            Image = result.getData();
                            has = "yes";
                            try {
                                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), result.getData().getData());
                                userImage.setImageBitmap(bitmap);
                                imageView.setImageBitmap(bitmap);
                                add_image();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                });

        DataBase.get_image(this, user, userImage);
        DataBase.get_image(this, user, imageView);

        userImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent access_gallary = new Intent(Intent.ACTION_PICK);
                access_gallary.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                resultLauncher.launch(access_gallary);

            }
        });


        drawerLayout = (DrawerLayout) findViewById(R.id.draw_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigator_open, R.string.navigator_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //changing background and title on toolbar


        if (user_number.getStringExtra("has_image").isEmpty()){
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.student_toolbox);
        }
        else{
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.teacher_toolbox);
        }

        DataBase.profile(this, user, name, surname, email, userNumber);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.blue)));
        getSupportActionBar().setTitle("Profile");
    }


    public void back_to_login(View view) {
        DataBase.back_to_menu(this, user);
    }

    private void add_image(){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (bitmap != null) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            byte[] bytes = byteArrayOutputStream.toByteArray();
            final String base64Image = Base64.encodeToString(bytes, Base64.DEFAULT);
            DataBase.upload_image(this, base64Image, user, userImage,imageView);


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
    public boolean onNavigationItemSelected(@Nullable MenuItem item) {
        switch(item.getItemId()){
            case R.id.search:
                Intent search = new Intent(this, search_courses.class);
                search.putExtra("usernumber",user);
                startActivity(search);
                return true;
            case R.id.create:
                Intent create = new Intent(this, create_course.class);
                create.putExtra("usernumber",user);
                startActivity(create);
                return true;

            case R.id.logout:
                Intent log = new Intent(this, MainActivity.class);
                startActivity(log);
                return true;

            case R.id.menu_page:
                DataBase.back_to_menu(profile.this, user);
                return true;

            case R.id.profile:
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}

