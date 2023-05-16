package com.example.wits_academy;

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

import java.util.HashMap;
import java.util.Map;

public class create_announcement extends Activity {

    String ip = "http://10.0.2.2/php_app";
    String courseName;
    EditText et_AnnounceText;
    TextView tv_SendBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_announcement);
        Intent user_number = getIntent();
        courseName = user_number.getStringExtra("courseName");
        et_AnnounceText = findViewById(R.id.AnnouncementText);
        tv_SendBtn = findViewById(R.id.sendAnnouncement);

    }

    public void Send(View view) {
        String url = ip + "/send_announcement.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, response -> Toast.makeText(create_announcement.this, response.trim(), Toast.LENGTH_SHORT).show(),
                error -> Toast.makeText(create_announcement.this, error.toString().trim(), Toast.LENGTH_SHORT).show()) {
            @Override
            protected Map<String, String> getParams() {

                String text = et_AnnounceText.getText().toString();
                Map<String, String> data = new HashMap<>();
                data.put("announcementText", text);
                data.put("courseName", courseName);
                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(view.getContext());
        requestQueue.add(stringRequest);

    }


}