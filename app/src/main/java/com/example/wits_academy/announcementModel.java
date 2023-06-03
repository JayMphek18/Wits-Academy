package com.example.wits_academy;


public class announcementModel {
    String header;
    String AnnouncementText;
    String date;
    int image;

    public announcementModel(String header, String announcementText, String date,int image) {
        this.header = header;
        AnnouncementText = announcementText;
        this.image = image;
        this.date = date;
    }
}
