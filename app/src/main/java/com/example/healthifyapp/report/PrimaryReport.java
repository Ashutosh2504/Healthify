package com.example.healthifyapp.report;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healthifyapp.DrawerActivity;
import com.example.healthifyapp.R;
import com.example.healthifyapp.SharedPreferences.SharedPreference;
import com.example.healthifyapp.adapter.BreakFastReportAdapters;
import com.example.healthifyapp.api.LunchPrimaryReportAPI;
import com.example.healthifyapp.api.WaterTrackerApi;
import com.example.healthifyapp.model.EnergyModel;
import com.example.healthifyapp.model.WatetIntakeModel;
import com.google.gson.Gson;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PrimaryReport extends AppCompatActivity {
CardView breakfast_Button,dinner_Button, lunchReport_Button,snackReport_btn;
ImageView back_btn;
TextView textView_eer,textView_kcals;
int  userAccountId=0;
   // String sum1= String.valueOf(0);

    //String sum= String.valueOf(new ArrayList<String>());
    int avg;
     String calscount="";
String  averageMedLevel="";
    Root modelList;

     List<Root.Result.DietAnalysisDetails> dietAnalysisType;
    List<Root.Result> resultdiet;
    List<Root.Result.DietAnalysisDetails> resultdiets;

    Root.Result.DietAnalysisDetails dietAnalysisTypeobj;
    ProgressBar progressBar;
    TextView fromdate_txt,todaydate_txt;
    String fromdate="";
    String todaydate="";

    double totalKcal = 0.0;
    ArrayList <Root.Result.DietAnalysisDetails>dietAnalysisDetails;
    List<Root.Result> resultList;
    Root.Result.DietAnalysisDetails dietAnalysisDetailsObj;
    Root.Result result ;
   EnergyModel model;
    private static final DecimalFormat decfor = new DecimalFormat("0.00");
    int sum=0;
  //  Button lunchReport_Button;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primary_report);
        lunchReport_Button = findViewById(R.id.lunchReportCardView);
        breakfast_Button=findViewById(R.id.breakfastReportCardView);
        dinner_Button=findViewById(R.id.dinnerReportCardView);
        back_btn=findViewById(R.id.backbtn);
        textView_eer=findViewById(R.id.eer);
        textView_kcals=findViewById(R.id.kcals);


        userAccountId = SharedPreference.readSharedSetting(this, "userAccountId", 0);
       getEER(userAccountId);
        getLunchPrimaryAnalysisReport(this);

        snackReport_btn=findViewById(R.id.snackReportCardView);
//change this button

        snackReport_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(PrimaryReport.this, SnackReportsActivity.class);
                startActivity(intent);
            }
        });
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  Intent intent=new Intent(PrimaryReport.this,  DrawerActivity.class);
                //startActivity(intent);
                onBackPressed();
            }
        });

        breakfast_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(PrimaryReport.this, BreakFastprimaryanalysisReport.class);
                startActivity(intent);


            }
        });

        dinner_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(PrimaryReport.this,DinnerPrimaryAnalysisReport.class);
                startActivity(intent);
            }
        });

        lunchReport_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(PrimaryReport.this, LunchPrimaryAnalysisReport.class);
                startActivity(intent);
            }
        });
    }

    private void getLunchPrimaryAnalysisReport(Context context) {

        try {
            userAccountId= SharedPreference.readSharedSetting(getApplicationContext(), "userAccountId", 0);
            Retrofit retrofit = new Retrofit.Builder().baseUrl("https://easywaygst.theumangsociety.org")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            LunchPrimaryReportAPI lunchPrimaryReportAPI = retrofit.create(LunchPrimaryReportAPI.class);
            Call<Root> call = lunchPrimaryReportAPI.getlunchprimaryReportDataModel(userAccountId,fromdate,todaydate);

            call.enqueue(new Callback<Root>() {
                @Override
                public void onResponse(Call<Root> call, Response<Root> response) {

                    if (response.code() ==200) {
                        modelList = response.body();
                        resultList = modelList.getResult();
                        resultdiet = modelList.getResult();
                        //  if (dietAnalysisType.get(i).getTotalConsumedKcal().equalsIgnoreCase("Primary Analysis Of Your Diet")) {
                        //   dietAnalysisDetails = (List<Root.Result.DietAnalysisDetails>) dietAnalysisType.get(i);
                        //     sum = Integer.parseInt(sum + dietAnalysisDetails.get(i).getTotalConsumedKcal());
                        // }
//float total = 0;
//for(int i = 0; i < myList.size(); i++){
//    MyObject myObject = myList.get(i);
//    total += myObject.getPrice();
                        try {

                        for (int i = 0; i < resultList.size(); i++) {



                                    if (resultList.get(i).getDietAnalysisType().equalsIgnoreCase("Primary Analysis Of Your Diet")) {
                                        dietAnalysisTypeobj = resultdiet.get(i).getDietAnalysisDetailsList().get(0);


                                        sum +=dietAnalysisTypeobj.getTotalConsumedKcal();
                                     // sum = Integer.parseInt(sum + dietAnalysisTypeobj.getTotalConsumedKcal());

                                      }
                                // for (int j = 0; j < dietAnalysisType.size(); j++)
                                // {

                                // sum= Integer.parseInt(sum+dietAnalysisType.get(i).getTotalConsumedKcal());
                                // avg=sum/dietAnalysisType.get(i).getDietAnalysisId();
                                //  sum += Integer.valueOf(dietAnalysisType.get(i).getTotalConsumedKcal());

                                //  sum = Integer.valueOf(dietAnalysisType.get(i).getTotalConsumedKcal());
                                // }


                                //   textView_kcals.setText(calscount);





                        }
                        } catch (Exception e) {
                            Log.d("ReportRahul:", "::::" + e.getMessage());
                        }
                        totalKcal = (sum/4.184);

                        textView_kcals.setText(decfor.format(totalKcal));

                    }
                }

                @Override
                public void onFailure(Call<Root> call, Throwable t) {

                    Toast.makeText(getApplicationContext(), "Failure in getting report", Toast.LENGTH_SHORT).show();
                }
            });
        }
        catch (Exception e)
        {
            Log.d("Report:","::::"+e.getMessage());
        }

    }

    private void getEER(int userAccountId) {
        try {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://easywaygst.theumangsociety.org")
                    .addConverterFactory(GsonConverterFactory.create()).build();
            WaterTrackerApi retrofitAPI = retrofit.create(WaterTrackerApi.class);


            Call<EnergyModel> call=retrofitAPI.getEER(userAccountId);
            call.enqueue(new Callback<EnergyModel>() {
                @Override
                public void onResponse(Call<EnergyModel> call, Response<EnergyModel> response) {
                    try {
                        if (response != null && response.code() == 200) {
                            String responseString = "Response code :" + response.code();
                            // Toast.makeText(getContext(), "Analysis Status ", Toast.LENGTH_SHORT).show();
                            Log.e("TAG", "Response =" + responseString);
                            Gson gson = new Gson();
                            // watetIntakeModel=new WatetIntakeModel();
                            model = response.body();

                            textView_eer.setText(model.getResult());


                            String s1 = gson.toJson(response.body());
                            Log.e("Response", s1);
                            Log.d("ErRR", ":" + model.toString());
                            Log.d(" estimate  energy", "Response =" + model.toString());

                        } else if (!response.isSuccessful()) {

                            //  Toast.makeText(getActivity(), "Analysis  Failed", Toast.LENGTH_SHORT).show();
                            String responseString = "Response code :" + response.code();
                            Log.e("TAG", "Response =" + responseString);
                        }

                    } catch (Exception e) {

                    }
                    Log.e("TAG", "Response =" + response);
                }

                @Override
                public void onFailure(Call<EnergyModel> call, Throwable t) {

                }
            });

        }
        catch (Exception e)
        {
            Log.d("Report:","::::"+e.getMessage());
        }

    }
}