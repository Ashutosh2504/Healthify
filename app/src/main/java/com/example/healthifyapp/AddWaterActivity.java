//11/17/2022/12:49
//Rahul choudhari
package com.example.healthifyapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.healthifyapp.SecondaryReports.BreakfastSecondaryReport;
import com.example.healthifyapp.SecondaryReports.SecondaryReport;
import com.example.healthifyapp.SharedPreferences.SharedPreference;
import com.example.healthifyapp.adapter.BreakFastReportAdapters;
import com.example.healthifyapp.adapter.WaterAdapter;
import com.example.healthifyapp.api.DietApi;
import com.example.healthifyapp.api.WaterTrackerApi;
import com.example.healthifyapp.model.AnalysisStatusModel;
import com.example.healthifyapp.model.AnalysisStatusResponseModel;
import com.example.healthifyapp.model.DietDataModel;
import com.example.healthifyapp.model.WaterTrackerModel;
import com.example.healthifyapp.model.WaterTrackerResponseModel;
import com.example.healthifyapp.model.WaterTrakerModel;
import com.example.healthifyapp.model.WatetIntakeModel;
import com.example.healthifyapp.model.apiclient.UserAccountclient;
import com.example.healthifyapp.report.Root;
import com.example.healthifyapp.tertiaryAnalysisDiet.AddExtraBreakfastTertiaryActivity;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.orbitalsonic.waterwave.WaterWaveView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddWaterActivity extends AppCompatActivity {
    ImageView increaseBtn, decreaseBtn;
    TextView datetext1, timetext,totalWater,remainingWater,totalWaterIntake;
    Button add_btn;
    EditText waterWaveView;
    int tempQuantity = 1;
    private ProgressBar progressBar;
    int minteger = 0;
    String Time, date,remark="Add Water";
    int waterTrakerId=0,userAccountId=0;
    int quanity;
    WaterTrackerModel waterTrackerModel;
    WatetIntakeModel watetIntakeModel;

    List<WaterTrakerModel> model;
  LinearLayout back_btn;

    RecyclerView recyclerView;
    WaterAdapter waterAdapter;
    boolean isAllFieldsChecked = false;

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_water);

        waterWaveView =findViewById(R.id.waterWaveView);
        timetext = findViewById(R.id.selecttime);
        datetext1 = findViewById(R.id.selectdate);
        add_btn=findViewById(R.id.addwater_btn);
        back_btn=findViewById(R.id.btn_backaddWater);

        totalWater=findViewById(R.id.totalwater);
        totalWaterIntake=findViewById(R.id.totalwaterintake);
        remainingWater=findViewById(R.id.remainingwater);



        model=new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.breakfastrecylesview);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.HORIZONTAL));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        waterAdapter=new WaterAdapter(getApplicationContext(), model);
        recyclerView.setAdapter(waterAdapter);


        Log.d("Adapterrr","Adapter before  createddd");



        Log.d("Adapterrr","Adapter createddd");

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddWaterActivity.this, DrawerActivity.class);
                startActivity(intent);
            }
        });



        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);

        userAccountId = SharedPreference.readSharedSetting(this, "userAccountId", 0);
        getWaterIntake(userAccountId);
        getWaterTracker(userAccountId);
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isAllFieldsChecked = CheckAllFields()) {

                    postWaterData(remark, waterTrakerId, userAccountId, datetext1.getText().toString(), timetext.getText().toString(), waterWaveView.getText().toString());
                }

            }
        });

        final Handler handler = new Handler();
        Runnable refresh = new Runnable() {
            @Override
            public void run() {
                getWaterIntake1(userAccountId);
                getWaterTracker(userAccountId);
                handler.postDelayed(this, 1000);
            }
        };
        handler.postDelayed(refresh, 1000);


        timetext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                TimePickerDialog mTimePicker=new TimePickerDialog(AddWaterActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {

                        Time=( hour+ ":" + minute);
                        timetext.setText(Time);
                    }
                },hour, minute, true);

                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });
        datetext1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(AddWaterActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        date = (dayOfMonth + "-" + (month + 1) + "-" + year);
                        datetext1.setText(date);

                    }
                }, year, month, day);
                dialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                dialog.show();

            }
        });



    }

    private boolean CheckAllFields() {

        if (datetext1.length() ==0) {
            return false;
        }
        if (timetext.length() == 0) {

            return false;
        }
        if (waterWaveView.length() == 0) {
            waterWaveView.setError("Water Add required");
            return false;
        }

        return true;

    }

    private void getWaterIntake1(int userAccountId) {

        try {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://easywaygst.theumangsociety.org")
                    .addConverterFactory(GsonConverterFactory.create()).build();
            WaterTrackerApi retrofitAPI = retrofit.create(WaterTrackerApi.class);


            Call<WatetIntakeModel> call=retrofitAPI.getWaterIntakeModel(userAccountId);
            call.enqueue(new Callback<WatetIntakeModel>() {
                @Override
                public void onResponse(Call<WatetIntakeModel> call, Response<WatetIntakeModel> response) {
                    try {
                        if (response != null && response.code() == 200) {
                            String responseString = "Response code :" + response.code();
                            // Toast.makeText(getContext(), "Analysis Status ", Toast.LENGTH_SHORT).show();
                            Log.e("TAG", "Response =" + responseString);
                            Gson gson = new Gson();
                            // watetIntakeModel=new WatetIntakeModel();
                            watetIntakeModel = response.body();
                            totalWater.setText(watetIntakeModel.getTotalWater());
                            totalWaterIntake.setText(watetIntakeModel.getTotalWaterIntake());
                            remainingWater.setText(watetIntakeModel.getRemainingWater());

                            String s1 = gson.toJson(response.body());
                            Log.e("Response", s1);
                            Log.d("Water Intake", ":" + watetIntakeModel.toString());
                            Log.d("Water Intakeeeeee", "Response =" + watetIntakeModel.toString());

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
                public void onFailure(Call<WatetIntakeModel> call, Throwable t) {

                }
            });







        }
        catch (Exception e)
        {
            Log.d("Report:","::::"+e.getMessage());
        }

    }

    private void getWaterTracker(int userAccountId) {
        try {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://easywaygst.theumangsociety.org")
                    .addConverterFactory(GsonConverterFactory.create()).build();
            WaterTrackerApi retrofitAPI = retrofit.create(WaterTrackerApi.class);

            Call<List<WaterTrakerModel>> call=retrofitAPI.getWaterTracker(userAccountId);
            call.enqueue(new Callback<List<WaterTrakerModel>>() {
                @Override
                public void onResponse(Call<List<WaterTrakerModel>> call, Response<List<WaterTrakerModel>> response) {
                    try {
                        if (response != null && response.code() == 200) {
                            String responseString = "Response code :" + response.code();
                          //  Toast.makeText(AddWaterActivity.this, "Water Display ", Toast.LENGTH_SHORT).show();
                            Log.e("TAG", "Response =" + responseString);
                            Gson gson = new Gson();

                            model = (List<WaterTrakerModel>) response.body();

                            waterAdapter.setDietAnalysisObjectList(model);
                            Log.d("Analysis Status", ":" + model.toString());
                            Log.d("Analysis Status", "Response =" + model.toString());
                        }

                    } catch (Exception e) {
                        Log.d("Report:", "::::" + e.getMessage());
                    }
                }

                @Override
                public void onFailure(Call<List<WaterTrakerModel>> call, Throwable t) {

                }
            });

        }
        catch (Exception e)
        {
            Log.d("Report:","::::"+e.getMessage());
        }



    }

    private void getWaterIntake(int userAccountId) {
        try {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://easywaygst.theumangsociety.org")
                    .addConverterFactory(GsonConverterFactory.create()).build();
            WaterTrackerApi retrofitAPI = retrofit.create(WaterTrackerApi.class);


            Call<WatetIntakeModel> call=retrofitAPI.getWaterIntakeModel(userAccountId);
            call.enqueue(new Callback<WatetIntakeModel>() {
                @Override
                public void onResponse(Call<WatetIntakeModel> call, Response<WatetIntakeModel> response) {
                    try {
                        if (response != null && response.code() == 200) {
                            String responseString = "Response code :" + response.code();
                            // Toast.makeText(getContext(), "Analysis Status ", Toast.LENGTH_SHORT).show();
                            Log.e("TAG", "Response =" + responseString);
                            Gson gson = new Gson();
                           // watetIntakeModel=new WatetIntakeModel();
                            watetIntakeModel = response.body();
                            totalWater.setText(watetIntakeModel.getTotalWater());
                            totalWaterIntake.setText(watetIntakeModel.getTotalWaterIntake());
                            remainingWater.setText(watetIntakeModel.getRemainingWater());

                            String s1 = gson.toJson(response.body());
                            Log.e("Response", s1);
                            Log.d("Water Intake", ":" + watetIntakeModel.toString());
                            Log.d("Water Intakeeeeee", "Response =" + watetIntakeModel.toString());

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
                public void onFailure(Call<WatetIntakeModel> call, Throwable t) {

                }
            });

        }
        catch (Exception e)
        {
            Log.d("Report:","::::"+e.getMessage());
        }




    }


    private void postWaterData(String remark, int waterTrakerId, int userAccountId, String todayDate, String todayTime, String quanity) {

        try {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://easywaygst.theumangsociety.org")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            WaterTrackerApi retrofitAPI = retrofit.create(WaterTrackerApi.class);
            WaterTrackerResponseModel waterTrackerResponseModel = new WaterTrackerResponseModel(
                   remark,
                    waterTrakerId,
                    userAccountId,
                    todayDate,
                    todayTime,quanity);

            Call<WaterTrackerModel> call = retrofitAPI.createPost(waterTrackerResponseModel);
            call.enqueue(new Callback<WaterTrackerModel>() {
                @Override
                public void onResponse(Call<WaterTrackerModel> call, Response<WaterTrackerModel> response) {
                    try {

                        if (response.code() == 200) {
                            String responseString = "Response code :" + response.code();
                            Toast.makeText(AddWaterActivity.this, "Add Water Successfully", Toast.LENGTH_SHORT).show();
                            Log.e("TAG", "Response =" + responseString);
                            Gson gson = new Gson();
                            String s1 = gson.toJson(response.body());
                            Log.e("Response", s1);
                            waterWaveView.setText("");
                            datetext1.setText("");
                            timetext.setText("");

                            Log.d("Response", "" + response.body());
                            Log.d("Success", "" + response.toString());
                            waterTrackerModel = response.body();
                            Log.d("Response", "" + waterTrackerModel.getMessage());



                        } else if (!response.isSuccessful()) {

                            Toast.makeText(AddWaterActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                            String responseString = "Response code :" + response.code();
                            Log.e("TAG", "Response =" + responseString);

                        }
                    }  catch (Exception e)
                    {
                        Log.d("ReportRahul:","::::"+e.getMessage());
                    }
                }

                @Override
                public void onFailure(Call<WaterTrackerModel> call, Throwable t) {

                }
            });


        }
        catch (Exception e)
        {
            Log.d("Report:","::::"+e.getMessage());
        }


    }



    public void increaseInteger(View view) {
        minteger = minteger + 5;
        display(minteger);

    }public void decreaseInteger(View view) {
        minteger = minteger - 5;
        display(minteger);
    }
    private void display(int number) {
       // waterWaveView.setText("" + number);
        //waterWaveView.setAnimationSpeed(Integer.parseInt(" "+number));
    }

}