package com.example.healthifyapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class TrackersActivity extends AppCompatActivity {
    CardView waterTracker_btn;
    LinearLayout linearLayout_backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trackers);

        waterTracker_btn=findViewById(R.id.watertracker);

        linearLayout_backbtn=findViewById(R.id.btn_Back);

        linearLayout_backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(TrackersActivity.this,DrawerActivity.class);
                startActivity(intent);
            }
        });

        waterTracker_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(TrackersActivity.this,AddWaterActivity.class);
                startActivity(intent);
            }
        });
    }
}