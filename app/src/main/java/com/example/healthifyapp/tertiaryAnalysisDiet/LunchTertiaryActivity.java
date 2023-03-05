package com.example.healthifyapp.tertiaryAnalysisDiet;

import androidx.appcompat.app.AppCompatActivity;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.healthifyapp.R;
import com.example.healthifyapp.SecondaryAnalysisDiet.LunchSecondaryActivity;
import com.example.healthifyapp.SecondaryAnalysisDiet.SecondaryAnalysisdietActivity;
import com.example.healthifyapp.SharedPreferences.SharedPreference;
import com.example.healthifyapp.adapter.ItemsAdapters;
import com.example.healthifyapp.api.DietApi;
import com.example.healthifyapp.api.LunchPrimaryReportAPI;
import com.example.healthifyapp.model.apiclient.UserAccountclient;
import com.example.healthifyapp.model.DietDataModel;
import com.example.healthifyapp.model.FoodDietModel;
import com.example.healthifyapp.networkconnectivity.NetworkConnectivity;
import com.example.healthifyapp.report.Root;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LunchTertiaryActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
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
    RecyclerView recyclerView;
    ItemsAdapters reportAdapter;

EditText  textViewquantity;
    TextView datetext, textViewdiettype, timetext,textViewprimaryanalysis;
    AutoCompleteTextView lunch;
    String    kcalstext;
    Button btnsubmit;
    int minteger = 1, userAccountId=0;
    ArrayList<FoodDietModel> foodList;
    ArrayList<String> food;
    String lunchh;
    HashMap<String, String> foodWithKcal;
    static int kcalorie;
    String dietAnalysisTypes="Tertiary Analysis Of Your Diet";

    String mobilno = "";
    ImageView increaseBtn, decreaseBtn;
    String Time, date;
    String dietType;
    int minusinteger=0;
    static int originalKcal = 1;
    int tempQuantity = 1;

    String[] unit={"Gram","Plates","Pcs","Ml"};
    Spinner unitspinner;
    LinearLayout back_btn;
    boolean isAllFieldsChecked = false;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lunch_tertiary);
        textViewdiettype = findViewById(R.id.lunchh);
        timetext = findViewById(R.id.selecttime);
      //  kcalstext = findViewById(R.id.kcal);
        //increaseBtn = findViewById(R.id.increase);
        //decreaseBtn = findViewById(R.id.decrease);

        datetext = findViewById(R.id.selectdate);
        //  textViewprimaryanalysis=findViewById(R.id.lunchprimary);
        unitspinner=findViewById(R.id.spinnerdignosis);

        textViewquantity = findViewById(R.id.quantity);
        lunch = findViewById(R.id.addlunch);
        btnsubmit = findViewById(R.id.breakbtnadd);

        back_btn=findViewById(R.id.btn_backadd);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LunchTertiaryActivity.this, TertiaryAnalysisDietActivity.class);
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
        mobilno = SharedPreference.readSharedSetting(this, "mobile_no", "");
        foodList = new ArrayList<FoodDietModel>();
        food = new ArrayList<>();
        foodWithKcal = new HashMap<>();
        dietType = textViewdiettype.getText().toString();
        userAccountId = SharedPreference.readSharedSetting(this, "userAccountId", 0);

        getReports(userAccountId,fromdate,todaydate);
        recyclerView = (RecyclerView) findViewById(R.id.breakfastrecylesview);
        recyclerView.setHasFixedSize(true);
        dietAnalysisDetails = new ArrayList<Root.Result.DietAnalysisDetails>();
        dietAnalysisType = new ArrayList<Root.Result.DietAnalysisDetails>();


        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);


        getAllFoodItems();
        ArrayAdapter dignosisspinner=new ArrayAdapter(getApplicationContext(),android.R.layout.simple_spinner_item,unit);
        dignosisspinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Log.d("unit", "");
        unitspinner.setAdapter(dignosisspinner);

        unitspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String newItem=unitspinner.getSelectedItem().toString();
                Toast.makeText(getApplicationContext(), "You Selected" + newItem, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, food);

        //  breakfast.setThreshold(1);
        lunch.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
        // breakFastfood = breakfast.getText().toString();

        //getting the breakfast which is selected
        lunch.setOnItemClickListener(this);
      //  increaseBtn.setOnClickListener(new View.OnClickListener() {
        //            @Override
        //            public void onClick(View view) {
        //                try {
        //                    tempQuantity = Integer.parseInt(textViewquantity.getText().toString());
        //                    tempQuantity += 1;
        //                    if (tempQuantity <= 1) {
        //                        decreaseBtn.setVisibility(View.VISIBLE);
        //
        //                    }
        //                    kcalorie = Integer.parseInt(kcalstext.getText().toString());
        //                    kcalorie =  originalKcal * tempQuantity;
        //                    kcalstext.setText(String.valueOf(kcalorie));
        //                    display(tempQuantity);
        //
        //                }  catch (Exception e)
        //                {
        //                    Log.d("ReportRahul:","::::"+e.getMessage());
        //                }
        //            }
        //        });
        //        decreaseBtn.setOnClickListener(new View.OnClickListener() {
        //            @Override
        //            public void onClick(View view) {
        //                try {
        //
        //
        //                    tempQuantity = Integer.parseInt(textViewquantity.getText().toString());
        //                    tempQuantity = tempQuantity - 1;
        //                    if (tempQuantity >0) {
        //                        kcalorie = Integer.parseInt(kcalstext.getText().toString());
        //                        kcalorie = kcalorie - originalKcal;
        //                        kcalstext.setText(String.valueOf( kcalorie));
        //                        display(tempQuantity);
        //
        //                    }
        //
        //                    //else {
        //                    //  decreaseBtn.setVisibility(View.GONE);
        //                    // display(tempQuantity);
        //                    //}
        //
        //                }  catch (Exception e)
        //                {
        //                    Log.d("ReportRahul:","::::"+e.getMessage());
        //                }
        //            }
        //        });
        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {

                    try {
                        boolean networkConectivity =  NetworkConnectivity.isConnected(LunchTertiaryActivity.this);
                        if (networkConectivity) {
                            if (isAllFieldsChecked = CheckAllFields()) {
                                PostBreakfast(
                                        userAccountId,
                                        dietAnalysisTypes,
                                        textViewdiettype.getText().toString()
                                        //textViewprimaryanalysis.getText().toString()
                                        , textViewquantity.getText().toString()
                                        , lunchh
                                        , datetext.getText().toString()
                                        , timetext.getText().toString()
                                        , kcalstext);

                                lunch.setText("");
                                datetext.setText("");
                                textViewquantity.setText("");
                                timetext.setText("");
                            }
                             Toast.makeText(LunchTertiaryActivity.this, "Add Lunch Successfully", Toast.LENGTH_SHORT).show(); // Due to no response from get from api but data is stored.. 200 code is returned and check log for response
                        }
                        else {
                            NetworkConnectivity.networkConnetivityShowDialog(LunchTertiaryActivity.this);

                        }
                    }
                    catch (Exception e)
                    {
                        Log.d("Report:","::::"+e.getMessage());
                    }

                }
                catch (Exception e)
                {
                    Log.d("Report:","::::"+e.getMessage());
                }

            }
        });
        final Handler handler = new Handler();
        Runnable refresh = new Runnable() {
            @Override
            public void run() {

                // getReports(userAccountId);

                handler.postDelayed(this, 1000);
            }
        };
        handler.postDelayed(refresh, 1000);



        timetext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{

                    TimePickerDialog mTimePicker=new TimePickerDialog(LunchTertiaryActivity.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int i, int i1) {

                            Time=( hour+ ":" + minute);
                            timetext.setText(Time);
                        }
                    },hour, minute, true);

                    mTimePicker.setTitle("Select Time");
                    mTimePicker.show();
                }
                catch (Exception e)
                {
                    Log.d("Report:","::::"+e.getMessage());
                }

            }
        });


        datetext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {


                    DatePickerDialog dialog = new DatePickerDialog(LunchTertiaryActivity.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                            date = (dayOfMonth + "-" + (month + 1) + "-" + year);
                            datetext.setText(date);

                        }
                    }, year, month, day);
                    dialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                     dialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                    dialog.show();
                    //dialog.show();
                }
                catch (Exception e)
                {
                    Log.d("Report:","::::"+e.getMessage());
                }

            }
        });
    }

    private void getReports(int userAccountId,String fromdate,String todaydate) {


        try {
            //userAccountId= SharedPreference.readSharedSetting(getApplicationContext(), "userAccountId", 0);
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
                        modelList = response.body();
                        resultList = modelList.getResult();
                        resultdiet=modelList.getResult();
                        // progressBar.setVisibility(View.GONE);

                        Log.d("TAG", "Response = " + response.message());
                        // Toast.makeText(BreakFastprimaryanalysisReport.this, "Add diet Successfully", Toast.LENGTH_SHORT).show();
                        Log.d("Response", "" + modelList.toString());
                        // Log.d("DietResult", "" + modelList.getDietAnalysisDetailsList().toString());


                        for (int i=0; i<resultList.size();i++){

                            try {
                                if (resultList.get(i).getDietAnalysisType().equalsIgnoreCase("Tertiary Analysis Of Your Diet")) {
                                    if (resultList.get(i).getDietType().equalsIgnoreCase("LUNCH")) {

                                        dietAnalysisTypeobj = resultdiet.get(i).getDietAnalysisDetailsList().get(0);
                                        dietAnalysisType.add(dietAnalysisTypeobj);

                                    }

                                }

                            }  catch (Exception e)
                            {
                                Log.d("ReportRahul:","::::"+e.getMessage());
                            }

                        }


                        reportAdapter = new ItemsAdapters(getApplicationContext(), dietAnalysisType);
                        recyclerView.setAdapter(reportAdapter);
                        reportAdapter.setDietAnalysisObjectList(dietAnalysisType);

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

    private void PostBreakfast(int userAccountId, String  dietAnalysisType, String dietType, String quantity, String item, String date, String dietTime, String kCal) {
//
        try {



            DietApi dietApi = UserAccountclient.getClient().create(DietApi.class);

            DietDataModel dietAnalysisDetailList = new DietDataModel(quantity, item, kCal);
            ArrayList<DietDataModel> dietAnalysisDetailList2 = new ArrayList<DietDataModel>();
            dietAnalysisDetailList2.add(dietAnalysisDetailList);
            DietDataModel.Root dietDataModel = new DietDataModel.Root(userAccountId,dietAnalysisType, dietTime, dietType, date, dietAnalysisDetailList2);
            Gson g1 = new Gson();
            Log.v("Sending data:", g1.toJson(dietDataModel));
            Call<DietDataModel.Root> call = dietApi.createPost(dietDataModel);

            call.enqueue(new Callback<DietDataModel.Root>() {
                @Override
                public void onResponse(Call<DietDataModel.Root> call, Response<DietDataModel.Root> response) {

                    Toast.makeText(LunchTertiaryActivity.this, "Add diet", Toast.LENGTH_SHORT).show();

                    if (response.code() == 200) {
                        String responseString = "Response code :" + response.code();
                        Toast.makeText(LunchTertiaryActivity.this, "Add diet Successfully", Toast.LENGTH_SHORT).show();
                        Log.e("TAG", "Response =" + responseString);
                        Gson gson = new Gson();
                        String s1 = gson.toJson(response.body());
                        Log.e("Response", s1);

                    } else if (!response.isSuccessful()) {

                        Toast.makeText(LunchTertiaryActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                        String responseString = "Response code :" + response.code();
                        Log.e("TAG", "Response =" + responseString);

                    }
                }

                @Override
                public void onFailure(Call<DietDataModel.Root> call, Throwable t) {

                }


            });

        }
        catch (Exception e)
        {
            Log.d("Report:","::::"+e.getMessage());
        }


    }

    private void getAllFoodItems() {
        try {



            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://easywaygst.theumangsociety.org")
                    // on below line we are calling add
                    // Converter factory as Gson converter factory.
                    .addConverterFactory(GsonConverterFactory.create())
                    // at last we are building our retrofit builder.
                    .build();

            // below line is to create an instance for our retrofit api class.
            DietApi retrofitAPI = retrofit.create(DietApi.class);

            // on below line we are calling a method to get all the cities from API.
            Call<ArrayList<FoodDietModel>> call = retrofitAPI.getFoodItemDataModel();

            call.enqueue(new Callback<ArrayList<FoodDietModel>>() {
                @Override
                public void onResponse(Call<ArrayList<FoodDietModel>> call, Response<ArrayList<FoodDietModel>> response) {
                    // inside on response method we are checking
                    // if the response is success or not.
                    if (response.isSuccessful()) {
                        // below line is to add our data from api to our array list.
                        foodList = response.body();
                        // below line we are running a loop to add data to our adapter class.
                        for (int i = 0; i < foodList.size(); i++) {
                            Log.d("foodList", ":" + foodList.get(i).getItemName());
                            food.add(foodList.get(i).getItemName());
                            Log.d("foodName", ":" + food.get(i));

                            foodWithKcal.put(foodList.get(i).getItemName(), foodList.get(i).getKCal());
                            Log.d("KCAL", ":" + foodList.get(i).getKCal());
                            Log.d("KCAL", ":" + foodWithKcal.get(foodList.get(i).getItemName()));

                        }
                        for (Map.Entry<String, String> breakfast : foodWithKcal.entrySet()) {
                            Log.d("KCAL", ":" + breakfast.getValue());
                        }
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<FoodDietModel>> call, Throwable t) {

                }
            });
        }
        catch (Exception e)
        {
            Log.d("Report:","::::"+e.getMessage());
        }

    }

    public void increaseInteger(View view) {
        minteger = minteger + 1;
        display(minteger);

    }public void decreaseInteger(View view) {
        minteger = minteger - 1;
        display(minteger);
    }

    private void display(int number) {

        textViewquantity.setText("" + number);
    }
    private boolean CheckAllFields() {

        if (datetext.length() ==0) {

            return false;
        }
        if (timetext.length() == 0) {

            return false;
        }
        if (lunch.length() == 0) {
            lunch.setError("Add Diet required");
            return false;
        }

        return true;


    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

        try {


            lunchh = adapterView.getItemAtPosition(position).toString();
            Log.d("breakfast", ":" + lunchh);
            for (Map.Entry<String, String> breakfast : foodWithKcal.entrySet()) {
                if (lunchh.equalsIgnoreCase(breakfast.getKey())) {
                   // kcalstext.setText(breakfast.getValue());
                    originalKcal =Integer.parseInt( breakfast.getValue());
                   // Log.d("found", ":" + kcalstext.getText().toString());
                    Log.d("foundBBBB", ":" + lunchh);
                }
            }
            // create Toast with user selected value
            Toast.makeText(LunchTertiaryActivity.this, "Selected Item is: \t" + lunchh, Toast.LENGTH_LONG).show();
            SharedPreference.saveSharedSetting(LunchTertiaryActivity.this, "lunch", lunchh);

        }
        catch (Exception e)
        {
            Log.d("Report:","::::"+e.getMessage());
        }

    }
}