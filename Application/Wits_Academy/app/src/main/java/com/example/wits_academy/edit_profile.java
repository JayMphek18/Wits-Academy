package com.example.wits_academy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class edit_profile extends AppCompatActivity {
    EditText name;
    EditText surname;
    EditText email;
    EditText userNumber;
    Intent user_number;
    ImageView userImage;
    Intent Image;
    String has = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile);

        user_number = getIntent();
        name = (EditText) findViewById(R.id.profile_name);
        surname = (EditText) findViewById(R.id.profile_surname);
        email = (EditText) findViewById(R.id.profile_email);
        userNumber = (EditText) findViewById(R.id.profile_number);
        userImage = (ImageView) findViewById(R.id.imageView2);

        name.setHint(user_number.getStringExtra("name"));
        userNumber.setHint(user_number.getStringExtra("userNumber"));
        surname.setHint(user_number.getStringExtra("surname"));
        email.setHint(user_number.getStringExtra("email"));

        if (!user_number.getStringExtra("has_image").isEmpty()){
            Image = user_number.getParcelableExtra("image");
            userImage.setImageURI(Image.getData());
            has = "yes";
        }
        else{
            Picasso.get()
                    .load("http://10.203.198.18/wits/php/profile_photos/" + user_number.getStringExtra("userNumber") + ".jpg")
                    .error(R.drawable.profile_icon)
                    .fit()
                    .into(userImage);
        }

    }

    public void confirm(View view) {
        Map<String, String> map = new HashMap<>();
        map.put("name", name.getText().toString());
        map.put("surname", surname.getText().toString());
        map.put("email", email.getText().toString());
        map.put("user_number", userNumber.getText().toString());
        map.put("pre_userNumber", user_number.getStringExtra("userNumber"));

        DataBase.change_profile(this, map);
    }

    public void back_to_login(View view) {
        Intent intent = new Intent(this, profile.class);
        intent.putExtra("usernumber",user_number.getStringExtra("userNumber"));
        intent.putExtra("image", Image);
        intent.putExtra("has_image", has);
        startActivity(intent);
    }
}