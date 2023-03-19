package com.example.healthifyapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class PrimaryAnalysisDietACtivity extends AppCompatActivity {

    CardView cardView1, cardView2,cardView3,cardview_WaterBtn, cardViewaddextrabreakfast,cardViewaddextralunch,cardViewaddextradinner, cardView_AddSnackBtn, cardView_AddExtrasnackBtn;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_primary_analysis_diet);

        linearLayout=findViewById(R.id.btn_Back);

        cardView1=findViewById(R.id.cardview1);
        cardView2=findViewById(R.id.cardview2);
        cardView3=findViewById(R.id.cardview3);
        cardview_WaterBtn= findViewById(R.id.addwater);
        cardView_AddSnackBtn=findViewById(R.id.cardview4);
        cardView_AddExtrasnackBtn=findViewById(R.id.addextrasnack);
        cardViewaddextrabreakfast=findViewById(R.id.addextrabreakfast);
        cardViewaddextradinner=findViewById(R.id.addextradinner);
        cardViewaddextralunch=findViewById(R.id.addextralunch);


        cardView_AddExtrasnackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(PrimaryAnalysisDietACtivity.this,
                        AddExtraSnackActivity.class);
                startActivity(intent);
            }
        });
        cardView_AddSnackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(PrimaryAnalysisDietACtivity.this,
                        AddSnack.class);
                startActivity(intent);
            }
        });

        cardview_WaterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(PrimaryAnalysisDietACtivity.this,
                        AddWaterActivity.class);
                startActivity(intent);
            }
        });

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               onBackPressed();
               // Intent intent = new Intent(getApplicationContext(), DrawerActivity.class);
               // startActivity(intent);
            }
        });
        cardViewaddextrabreakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(PrimaryAnalysisDietACtivity.this,
                        AddExtraBreakfast.class);
                startActivity(intent);
            }
        });
        cardViewaddextradinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(PrimaryAnalysisDietACtivity.this,
                            AddExtraDinner.class);
                startActivity(intent);
            }
        });

        cardViewaddextralunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(PrimaryAnalysisDietACtivity.this,AddExtraLunch.class);
                startActivity(intent);
            }
        });

        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(PrimaryAnalysisDietACtivity.this,Breakfast.class);
                startActivity(intent);
            }
        });
        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(PrimaryAnalysisDietACtivity.this,LunchActivity.class);
                startActivity(intent);
            }
        });
        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(PrimaryAnalysisDietACtivity.this,DinnerActivity.class);
                startActivity(intent);
            }
        });




    }
}
