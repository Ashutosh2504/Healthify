package com.example.healthifyapp.report;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthifyapp.R;
import com.example.healthifyapp.SharedPreferences.SharedPreference;
import com.example.healthifyapp.adapter.BreakFastReportAdapters;
import com.example.healthifyapp.adapter.FilterAdaptors;
import com.example.healthifyapp.adapter.ReportAdapter;
import com.example.healthifyapp.api.LunchPrimaryReportAPI;
import com.example.healthifyapp.networkconnectivity.NetworkConnectivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SnackReportsActivity extends AppCompatActivity {

    int userAccountId =0;
    LinearLayout linearLayoutButton;
    Root modelList;
    static List<Root.Result.DietAnalysisDetails> dietAnalysisType;
    List<Root.Result> resultdiet;
    Root.Result.DietAnalysisDetails dietAnalysisTypeobj;
    ProgressBar progressBar;
    String fromdate="";
    String todaydate="";
    static List <Root.Result.DietAnalysisDetails>dietAnalysisDetails;
    List<Root.Result> resultList;
    Root.Result.DietAnalysisDetails dietAnalysisDetailsObj;
    Root.Result result ;
    // List<LunchPrimaryReportDataModel> lunchPrimaryReportDataModels;
    RecyclerView recyclerView, recyclerView1 ;
    BreakFastReportAdapters reportAdapter;
    ReportAdapter lunchPrimaryReport;
    private int flag = 0;
    TextView fromdate_txt,todaydate_txt;
    FilterAdaptors reportAdapternext;
    Button sort_btn;
    String date;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snack_reports);

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

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

        Log.d("Adapterrr","Adapter before  createddd");


        diglog(false);
        Log.d("Adapterrr","Adapter createddd");

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
                DatePickerDialog dialog = new DatePickerDialog(SnackReportsActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        date = (dayOfMonth + "-" + (month + 1) + "-" + year);

                        fromdate_txt.setText(date);
                        fromdate = (year+ "-" +  (month + 1) + "-" + dayOfMonth);


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
                DatePickerDialog dialog = new DatePickerDialog(SnackReportsActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        date = (dayOfMonth + "-" + (month + 1) + "-" + year);

                        todaydate_txt.setText(date);
                        todaydate = (year+ "-" +  (month + 1) + "-" + dayOfMonth);

                    }
                }, year, month, day);
                // dialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                dialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                dialog.show();
                //dialog.show();

            }
        });

        sort_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                clickdata(userAccountId,fromdate,todaydate);

            }
        });


        linearLayoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //   Intent intent = new Intent(SnackReportsActivity.this, PrimaryReport.class);
               // startActivity(intent);
                onBackPressed();

            }
        });
    }

    private void diglog(boolean flag) {
        boolean networkConectivity =  NetworkConnectivity.isConnected(this);
        if (networkConectivity) {
            progressBar.setVisibility(View.VISIBLE);
            // Grid(Grid_URL,flag);
        }
        else {
            NetworkConnectivity.networkConnetivityShowDialog(this);

        }
    }

    private void clickdata(int userAccountId, String fromdate1, String todaydate1) {

        try {
            //  userAccountId= SharedPreference.readSharedSetting(getApplicationContext(), "userAccountId", 0);
            Retrofit retrofit = new Retrofit.Builder().baseUrl("https://easywaygst.theumangsociety.org")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            LunchPrimaryReportAPI lunchPrimaryReportAPI = retrofit.create(LunchPrimaryReportAPI.class);
            Call<Root> call = lunchPrimaryReportAPI.getlunchprimaryReportDataModel(userAccountId,fromdate1,todaydate1);

            call.enqueue(new Callback<Root>() {
                @Override
                public void onResponse(Call<Root> call, Response<Root> response) {

                    if (response.code() ==200)
                    {
                        modelList = response.body();
                        dietAnalysisType.clear();
                        resultList = modelList.getResult();
                        resultdiet=modelList.getResult();
                        progressBar.setVisibility(View.GONE);


                        Log.d("Response", "" + modelList.toString());
                        // Log.d("DietResult", "" + modelList.getDietAnalysisDetailsList().toString());
                        for(int i=0; i<resultList.size(); i++) {
                            //   Log.d("DietResult", "" + resultList.get(i).getDietAnalysisDetailsList());
                            //  Log.d("DietResultObj", "" + resultList.get(i));
                            //  dietAnalysisDetailsObj= resultList.get(i).getDietAnalysisDetailsList().get(0);
                            //dietAnalysisDetails.add(dietAnalysisDetailsObj);
                            //  Log.d("DietResult", "" + dietAnalysisDetails.toString());
                            //Log.d("DietResult", "" + dietAnalysisDetails.get(i).getkCal().toString());
                        }



                        try {


                            for (int i = 0; i < resultList.size(); i++) {

                                if (resultList.get(i).getDietAnalysisType().equalsIgnoreCase("Primary Analysis Of Your Diet")) {


                                    if (resultList.get(i).getDietType().equalsIgnoreCase("ADD SNACK")
                                            || resultList.get(i).getDietType().equalsIgnoreCase("ADD EXTRA SNACK")) {

                                       dietAnalysisTypeobj = resultdiet.get(i).getDietAnalysisDetailsList().get(0);
                                        dietAnalysisType.add(dietAnalysisTypeobj);
                                        Log.d("No.:"+i,dietAnalysisTypeobj.toString());
                                        //}

                                    }

                                }
                            }


                            // todaydate_txt.setText("");
                            // fromdate_txt.setText("");

                        }  catch (Exception e)
                        {
                            Log.d("ReportRahul:","::::"+e.getMessage());
                        }

                           /* reportAdapternext = new FilterAdaptors(getApplicationContext(), dietAnalysisType);
                        recyclerView1.setAdapter(reportAdapternext);
                        //  recyclerView1.setAdapter(reportAdapter);
                          reportAdapternext.setDietAnalysis(dietAnalysisType);
*/
                        reportAdapternext = new FilterAdaptors(getApplicationContext(), dietAnalysisType);
                        recyclerView.setAdapter(reportAdapternext);
                        reportAdapternext.setDietAnalysis(dietAnalysisType);
                        // reportAdapternext.notifyDataSetChanged();


                    }
                }

                @Override
                public void onFailure(Call<Root> call, Throwable t) {

                }
            });
        }
        catch (Exception e)
        {
            Log.d("Report:","::::"+e.getMessage());
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

                    if (response.code() ==200)
                    {
                        dietAnalysisType.clear();
                        modelList = response.body();
                        resultList = modelList.getResult();
                        resultdiet=modelList.getResult();
                        progressBar.setVisibility(View.GONE);

                        Log.d("TAG", "Response = " + response.message());
                        Log.d("Response", "" + modelList.toString());
                        // Log.d("DietResult", "" + modelList.getDietAnalysisDetailsList().toString());
                        for(int i=0; i<resultList.size(); i++) {
                            Log.d("DietResult", "" + resultList.get(i).getDietAnalysisDetailsList());
                            Log.d("DietResultObj", "" + resultList.get(i));
                          //  dietAnalysisDetailsObj= resultList.get(i).getDietAnalysisDetailsList().get(0);
                           // dietAnalysisDetails.add(dietAnalysisDetailsObj);
                           // Log.d("DietResult", "" + dietAnalysisDetails.toString());
                           // Log.d("DietResult", "" + dietAnalysisDetails.get(i).getkCal().toString());
                        }

                        for (int i=0; i<resultList.size();i++){

                            try {
                                if (resultList.get(i).getDietAnalysisType().equalsIgnoreCase("Primary Analysis Of Your Diet")) {
                                    if (resultList.get(i).getDietType().equalsIgnoreCase("ADD SNACK")
                                            || (resultList.get(i).getDietType().equalsIgnoreCase("ADD EXTRA SNACK"))) {

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
                }

                @Override
                public void onFailure(Call<Root> call, Throwable t) {

                }
            });
        }
        catch (Exception e)
        {
            Log.d("Report:","::::"+e.getMessage());
        }


    }
}