package com.example.wits_academy;

import android.content.Intent;
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

import java.lang.ref.ReferenceQueue;
import java.util.HashMap;
import java.util.Map;

public class documentView {
    private ImageView deleteBtn;
    private ImageView downloadBtn;
    private ImageView pageShow;
    private TextView DocTitleHeader;
    private TextView DocTitle;
    String courseName;
    public documentView(ImageView deleteBtn, ImageView downloadBtn, ImageView pageShow, TextView docTitleHeader, TextView docTitle,String courseName) {
        this.deleteBtn = deleteBtn;
        this.downloadBtn = downloadBtn;
        this.pageShow = pageShow;
        this.courseName = courseName;
        DocTitleHeader = docTitleHeader;
        DocTitle = docTitle;


        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete(DocTitle,view);
            }
        });
    }

    private void delete(TextView DocTitle,View view) {
        String ip = "http://10.0.2.2/php_app";
        String url = ip + "/delete_file.php";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(view.getContext(),response,Toast.LENGTH_SHORT);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> data = new HashMap<>();
                data.put("fileName",DocTitle.getText().toString().trim());
                data.put("courseName",courseName);
                return data;
            }
        };

        RequestQueue requestQueue  = Volley.newRequestQueue(view.getContext());
        requestQueue.add(stringRequest);
    }
}
