package com.example.healthifyapp.tertiaryAnalysisDiet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.healthifyapp.AddWaterActivity;
import com.example.healthifyapp.DrawerActivity;
import com.example.healthifyapp.R;
import com.example.healthifyapp.SecondaryAnalysisDiet.AddExtraBreakFastSecondaryActivity;
import com.example.healthifyapp.SecondaryAnalysisDiet.AddLunchSecondaryActivity;
import com.example.healthifyapp.SecondaryAnalysisDiet.AddextraDinnerActivity;
import com.example.healthifyapp.SecondaryAnalysisDiet.BreakfastSeconadryActivity;
import com.example.healthifyapp.SecondaryAnalysisDiet.DinnerSecondaryActivity;
import com.example.healthifyapp.SecondaryAnalysisDiet.LunchSecondaryActivity;
import com.example.healthifyapp.SecondaryAnalysisDiet.SecondaryAnalysisdietActivity;

public class TertiaryAnalysisDietActivity extends AppCompatActivity {
    CardView cardView1, cardView2,cardView3,cardview_WaterBtn, cardViewaddextrabreakfast,cardViewaddextralunch,cardViewaddextradinner,cardView_snackBtn,cardView_ExtraSnackBtn;

    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tertiary_analysis_diet);
        linearLayout=findViewById(R.id.btn_Back);
        cardView_snackBtn=findViewById(R.id.cardview4);
        cardView_ExtraSnackBtn=findViewById(R.id.addextrasnack);
        cardView1=findViewById(R.id.cardview1);
        cardView2=findViewById(R.id.cardview2);
        cardView3=findViewById(R.id.cardview3);
        cardview_WaterBtn= findViewById(R.id.addwater);
        cardViewaddextrabreakfast=findViewById(R.id.addextrabreakfastSecondarycardview);
        cardViewaddextradinner=findViewById(R.id.addextradinnersecondarycard);
        cardViewaddextralunch=findViewById(R.id.addextralunchseconadarycardview);

        cardView_snackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(TertiaryAnalysisDietActivity.this, AddSnackTertiaryActivity.class);
                startActivity(intent);
            }
        });
        cardView_ExtraSnackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(TertiaryAnalysisDietActivity.this, AddExtraSnackTertiaryActivity.class);
                startActivity(intent);
            }
        });

        cardview_WaterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(TertiaryAnalysisDietActivity.this, AddWaterActivity.class);
                startActivity(intent);
            }
        });

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               onBackPressed();
            }
        });
        cardViewaddextrabreakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(TertiaryAnalysisDietActivity.this, AddExtraBreakfastTertiaryActivity.class);
                startActivity(intent);
            }
        });
        cardViewaddextradinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(TertiaryAnalysisDietActivity.this, AddExtraDinnerTertiaryActivity.class);
                startActivity(intent);
            }
        });

        cardViewaddextralunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(TertiaryAnalysisDietActivity.this, AddExtraLunchTertiaryActivity.class);
                startActivity(intent);
            }
        });

        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(TertiaryAnalysisDietActivity.this, BreakFastTertiaryActivity.class);
                startActivity(intent);
            }
        });
        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(TertiaryAnalysisDietActivity.this, LunchTertiaryActivity.class);
                startActivity(intent);
            }
        });
        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(TertiaryAnalysisDietActivity.this, DinnerTertiaryActivity.class);
                startActivity(intent);
            }
        });


    }

}