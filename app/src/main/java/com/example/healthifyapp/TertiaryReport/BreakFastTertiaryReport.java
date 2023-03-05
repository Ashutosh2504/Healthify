package com.example.healthifyapp.TertiaryReport;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healthifyapp.R;
import com.example.healthifyapp.SecondaryReports.BreakfastSecondaryReport;
import com.example.healthifyapp.SecondaryReports.SnackSecondaryReport;
import com.example.healthifyapp.SharedPreferences.SharedPreference;
import com.example.healthifyapp.adapter.BreakFastReportAdapters;
import com.example.healthifyapp.adapter.ReportAdapter;
import com.example.healthifyapp.api.LunchPrimaryReportAPI;
import com.example.healthifyapp.networkconnectivity.NetworkConnectivity;
import com.example.healthifyapp.report.PrimaryReport;
import com.example.healthifyapp.report.Root;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BreakFastTertiaryReport extends AppCompatActivity {
    int userAccountId =0;
    LinearLayout linearLayoutButton;
    Root modelList;
    static List<Root.Result.DietAnalysisDetails> dietAnalysisType;
    List<Root.Result> resultdiet;
    Root.Result.DietAnalysisDetails dietAnalysisTypeobj;
    ProgressBar progressBar;
    String fromdate="";
    String todaydate="";
    TextView fromdate_txt,todaydate_txt;
    Button sort_btn;
    String date;
    static List <Root.Result.DietAnalysisDetails>dietAnalysisDetails;
    List<Root.Result> resultList;
    Root.Result.DietAnalysisDetails dietAnalysisDetailsObj;
    Root.Result result ;
    // List<LunchPrimaryReportDataModel> lunchPrimaryReportDataModels;
    RecyclerView recyclerView;
    BreakFastReportAdapters reportAdapter;
    ReportAdapter lunchPrimaryReport;
    private int flag = 0;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_break_fast_tertiary_report);
        linearLayoutButton = findViewById(R.id.btn_BackBreakFast);
        //    lunchPrimaryReportDataModels = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.breakfastrecylesview);
        recyclerView.setHasFixedSize(true);
        dietAnalysisDetails = new ArrayList<Root.Result.DietAnalysisDetails>();
        dietAnalysisType = new ArrayList<Root.Result.DietAnalysisDetails>();
        progressBar=findViewById(R.id.pb);
        getLunchPrimaryAnalysisReport(this);
        /*try {

        }
        catch (Exception e)
        {Log.d("calling method",""+e.getMessage());}*/
        fromdate_txt=findViewById(R.id.selectdate);
        todaydate_txt=findViewById(R.id.selectdate1);
        sort_btn=findViewById(R.id.sort);
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);


        fromdate_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(BreakFastTertiaryReport.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        date = (dayOfMonth + "-" + (month + 1) + "-" + year);
                        fromdate_txt.setText(date);

                    }
                }, year, month, day);
                // dialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                dialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                dialog.show();
                //dialog.show();

            }
        });

        todaydate_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(BreakFastTertiaryReport.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        date = (dayOfMonth + "-" + (month + 1) + "-" + year);
                        todaydate_txt.setText(date);

                    }
                }, year, month, day);
                dialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                dialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                dialog.show();
                //dialog.show();

            }
        });

        sort_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                todaydate=todaydate_txt.getText().toString();
                fromdate=fromdate_txt.getText().toString();
                getLunchPrimaryAnalysisReport(BreakFastTertiaryReport.this);

            }
        });


        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

        Log.d("Adapterrr","Adapter before  createddd");

        dialogs(false);

        Log.d("Adapterrr","Adapter createddd");

        linearLayoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BreakFastTertiaryReport.this, TertaryReport.class);
                startActivity(intent);
            }
        });
    }

    private void dialogs(boolean b) {
        boolean networkConectivity =  NetworkConnectivity.isConnected(this);
        if (networkConectivity) {
            progressBar.setVisibility(View.VISIBLE);
            // Grid(Grid_URL,flag);
        }
        else {
            NetworkConnectivity.networkConnetivityShowDialog(this);

        }
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
                    try{
                    if (response.code() ==200)
                    {
                        modelList = response.body();
                        resultList = modelList.getResult();
                        resultdiet=modelList.getResult();
                        progressBar.setVisibility(View.GONE);
                        todaydate_txt.setText("");
                        fromdate_txt.setText("");
                        Log.d("TAG", "Response = " + response.message());
                        Toast.makeText(BreakFastTertiaryReport.this, "Add diet Successfully", Toast.LENGTH_SHORT).show();
                        Log.d("Response", "" + modelList.toString());
                        // Log.d("DietResult", "" + modelList.getDietAnalysisDetailsList().toString());
                     //   for(int i=0; i<resultList.size(); i++) {
                        //                            Log.d("DietResult", "" + resultList.get(i).getDietAnalysisDetailsList());
                        //                            Log.d("DietResultObj", "" + resultList.get(i));
                        //                            dietAnalysisDetailsObj= resultList.get(i).getDietAnalysisDetailsList().get(0);
                        //                            dietAnalysisDetails.add(dietAnalysisDetailsObj);
                        //                            Log.d("DietResult", "" + dietAnalysisDetails.toString());
                        //                            Log.d("DietResult", "" + dietAnalysisDetails.get(i).getkCal().toString());
                        //                        }
                        for (int i=0; i<resultList.size();i++){

                            try {
                                if (resultList.get(i).getDietAnalysisType().equalsIgnoreCase("Tertiary Analysis Of Your Diet")) {
                                    if (resultList.get(i).getDietType().equalsIgnoreCase("BREAKFAST")) {

                                        dietAnalysisTypeobj = resultdiet.get(i).getDietAnalysisDetailsList().get(0);
                                        dietAnalysisType.add(dietAnalysisTypeobj);


                                    } else if (resultList.get(i).getDietType().equalsIgnoreCase("ADD EXTRA BREAKFAST")) {

                                        dietAnalysisTypeobj = resultdiet.get(i).getDietAnalysisDetailsList().get(0);
                                        dietAnalysisType.add(dietAnalysisTypeobj);

                                    }

                                }

                            }  catch (Exception e)
                            {
                                Log.d("ReportRahul:","::::"+e.getMessage());
                            }

                        }


                        reportAdapter = new BreakFastReportAdapters(getApplicationContext(), dietAnalysisType);
                        recyclerView.setAdapter(reportAdapter);
                        reportAdapter.setDietAnalysisObjectList(dietAnalysisType);

                    }
                    }  catch (Exception e)
                    {
                        Log.d("ReportRahul:","::::"+e.getMessage());
                    }

                }

                @Override
                public void onFailure(Call<Root> call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "Failure in getting report", Toast.LENGTH_SHORT).show();
                }
            });
        }
        catch (Exception e)
        {
            Log.d("Report:","::::"+e.getMessage());
        }

/* call.enqueue(new Callback<Root>()
            {
                @Override
                public void onResponse(Call<Root> call, Response<Root> response) {
                    resultList = new ArrayList<>();
                    if (response.code() == 200) {

                        modelList = response.body();
                        resultList = modelList.getResult();
                        for (int i = 0; i < resultList.size(); i++) {
                            result = resultList.get(i);
                            dietAnalysisDetails = result.getDietAnalysisDetails();

                          //  Log.d("DietResult", "" + dietAnalysisDetails.get(i).getItem());
                            Log.d("DietResult", "" + result.toString());

                        }
                        Log.d("TAG", "Response = " + response.message());
                        Toast.makeText(LunchPrimaryAnalysisReport.this, "Add diet Successfully", Toast.LENGTH_SHORT).show();
                        Log.d("DietResult", "" + result.toString());

                    } else if (!response.isSuccessful()) {

                        Toast.makeText(LunchPrimaryAnalysisReport.this, "Failed", Toast.LENGTH_SHORT).show();
                        String responseString = "Response code :" + response.code();
                        Log.e("TAG", "Response =" + responseString);
                    }
                }

                @Override
                public void onFailure(Call<DietAnalysisDetails.Root> call, Throwable t) {

                }
            });*/
    }

}