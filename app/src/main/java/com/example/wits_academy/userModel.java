package com.example.wits_academy;


import android.graphics.ImageDecoder;
import android.widget.ImageView;

public class userModel {
    String full_name;
    String email_address;
    String role;
    int status;
    int deleteImage;
    int image;

    public userModel(String full_name, String email_address, String role,int image,int deleteImage) {
        this.full_name = full_name;
        this.email_address = email_address;
        this.image = image;
        this.role = role;
        this.deleteImage = deleteImage;
    }
}
