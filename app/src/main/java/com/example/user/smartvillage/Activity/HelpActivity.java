package com.example.user.smartvillage.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.user.smartvillage.R;

public class HelpActivity<btnWeb> extends AppCompatActivity {
    Button btnWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        btnWeb = (Button) findViewById(R.id.OpenWeb);
        btnWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse("http://192.168.1.7/yii/smart_village/frontend/web/")));
            }
        });
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
    }
}
