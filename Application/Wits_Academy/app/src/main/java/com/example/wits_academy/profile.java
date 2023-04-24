package com.example.wits_academy;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class profile extends AppCompatActivity {
    TextView name;
    TextView surname;
    TextView email;
    TextView userNumber;
    String user;
    ImageView userImage;
    Intent Image;
    String has = "";
    Bitmap bitmap;

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

        if (!user_number.getStringExtra("has_image").isEmpty()){
            Image = user_number.getParcelableExtra("image");
            userImage.setImageURI(Image.getData());
        }else{
            Picasso.get()
                    .load("http://10.203.198.18/wits/php/profile_photos/" + user + ".jpg")
                    .error(R.drawable.profile_icon)
                    .fit()
                    .into(userImage);
        }

        userImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent access_gallary = new Intent(Intent.ACTION_PICK);
                access_gallary.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(access_gallary, GALLERY_REQ_CODE);

            }
        });


        DataBase.profile(this, user, name, surname, email, userNumber);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            if (requestCode == GALLERY_REQ_CODE){
                Image = data;
                has = "yes";
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
                    userImage.setImageBitmap(bitmap);
                    add_image();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public void editprofile(View view) {
        Intent intent = new Intent(this, edit_profile.class);
        intent.putExtra("userNumber", user);
        intent.putExtra("email", email.getText().toString());
        intent.putExtra("surname", surname.getText().toString());
        intent.putExtra("name", name.getText().toString());
        intent.putExtra("image", Image);
        intent.putExtra("has_image", has);
        startActivity(intent);
    }

    public void back_to_login(View view) {
        DataBase.exists(this, "", user);
    }

    private void add_image(){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (bitmap != null){
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            byte[] bytes = byteArrayOutputStream.toByteArray();
            final String base64Image = Base64.encodeToString(bytes, Base64.DEFAULT);
            DataBase.upload_image(this, base64Image, user);
        }
    }
}