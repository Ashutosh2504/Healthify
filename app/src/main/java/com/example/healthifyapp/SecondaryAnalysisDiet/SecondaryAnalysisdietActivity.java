package com.example.healthifyapp.SecondaryAnalysisDiet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.healthifyapp.AddExtraBreakfast;
import com.example.healthifyapp.AddExtraDinner;
import com.example.healthifyapp.AddExtraLunch;
import com.example.healthifyapp.AddWaterActivity;
import com.example.healthifyapp.Breakfast;
import com.example.healthifyapp.DinnerActivity;
import com.example.healthifyapp.DrawerActivity;
import com.example.healthifyapp.LunchActivity;
import com.example.healthifyapp.PrimaryAnalysisDietACtivity;
import com.example.healthifyapp.R;
public class SecondaryAnalysisdietActivity extends AppCompatActivity {
    CardView cardView1, cardView2,cardView3,cardview_WaterBtn, cardViewaddextrabreakfast,cardViewaddextralunch,cardViewaddextradinner,cardViewaddsnack_btn,cardViewaddextrasnack_btn;
    LinearLayout linearLayout;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary_analysisdiet);
        linearLayout=findViewById(R.id.btn_Back);

        cardView1=findViewById(R.id.cardview1);
        cardView2=findViewById(R.id.cardview2);
        cardView3=findViewById(R.id.cardview3);
        cardview_WaterBtn= findViewById(R.id.addwater);
        cardViewaddextrabreakfast=findViewById(R.id.addextrabreakfastSecondarycardview);
        cardViewaddextradinner=findViewById(R.id.addextradinnersecondarycard);
        cardViewaddextralunch=findViewById(R.id.addextralunchseconadarycardview);
        cardViewaddsnack_btn=findViewById(R.id.cardview4);
        cardViewaddextrasnack_btn=findViewById(R.id.addextrasnacksecondary);

        cardViewaddsnack_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SecondaryAnalysisdietActivity.this, AddSnackSecondaryActivity.class);
                startActivity(intent);
            }
        });

        cardViewaddextrasnack_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SecondaryAnalysisdietActivity.this, AddExtraSnackSecondary.class);
                startActivity(intent);
            }
        });

        cardview_WaterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SecondaryAnalysisdietActivity.this, AddWaterActivity.class);
                startActivity(intent);
            }
        });

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  Intent intent = new Intent(getApplicationContext(), DrawerActivity.class);
                //startActivity(intent);
                onBackPressed();
            }
        });
        cardViewaddextrabreakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SecondaryAnalysisdietActivity.this, AddExtraBreakFastSecondaryActivity.class);
                startActivity(intent);
            }
        });
        cardViewaddextradinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SecondaryAnalysisdietActivity.this, AddextraDinnerActivity.class);
                startActivity(intent);
            }
        });

        cardViewaddextralunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SecondaryAnalysisdietActivity.this, AddLunchSecondaryActivity.class);
                startActivity(intent);
            }
        });

        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SecondaryAnalysisdietActivity.this, BreakfastSeconadryActivity.class);
                startActivity(intent);
            }
        });
        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SecondaryAnalysisdietActivity.this, LunchSecondaryActivity.class);
                startActivity(intent);
            }
        });
        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SecondaryAnalysisdietActivity.this, DinnerSecondaryActivity.class);
                startActivity(intent);
            }
        });


    }
}