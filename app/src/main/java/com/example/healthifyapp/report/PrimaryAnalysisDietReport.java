package com.example.healthifyapp.report;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.healthifyapp.DrawerActivity;
import com.example.healthifyapp.R;

public class PrimaryAnalysisDietReport extends AppCompatActivity {

    CardView breakFastReport_Button, lunchReport_Button,dinnerReport_Button;
    LinearLayout back_Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primary_analysis_diet_report);

      /*  breakFastReport_Button=findViewById(R.id.breakfastReportCardview);
        lunchReport_Button=findViewById(R.id.lunchReportCardView);
        dinnerReport_Button=findViewById(R.id.dinnerReportCardView);
        back_Btn=findViewById(R.id.btn_Backprimaryreport);*/



/*


        breakFastReport_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(PrimaryAnalysisDietReport.this, BreakFastprimaryanalysisReport.class);
                startActivity(intent);
            }
        });

        lunchReport_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(PrimaryAnalysisDietReport.this, LunchPrimaryAnalysisReport.class);
                startActivity(intent);
            }
        });

        dinnerReport_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(PrimaryAnalysisDietReport.this, DinnerPrimaryAnalysisReport.class);
                startActivity(intent);
            }
        });
*/


    }


}