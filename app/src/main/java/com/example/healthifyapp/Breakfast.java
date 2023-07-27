package com.example.healthifyapp;

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

import com.example.healthifyapp.SharedPreferences.SharedPreference;
import com.example.healthifyapp.adapter.AddItemAdapters;
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
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Breakfast extends AppCompatActivity implements AdapterView.OnItemClickListener {

    Root modelList;
    static List <Root.Result.DietAnalysisDetails>dietAnalysisType;
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
  EditText textViewquantity;
    TextView datetext1, textViewdiettype,  timetext,textViewDiettypeprimary;
    AutoCompleteTextView breakfast;
  //  TextView    kcalstext;
    Button btnsubmit;
    int minteger = 1;
    ArrayAdapter<String> arrayAdapter;
    AddItemAdapters addItemAdapters;
    //ArrayList<DietDataModel> dietDataModelArrayList;
    private Handler mHandler = new Handler();

    String mobilno = "";
    int  userAccountId=0;
    ArrayList<FoodDietModel> foodList;
    ArrayList<String> food;
    String breakFastfood ;
    HashMap<String, String> foodWithKcal;
   static int kcalorie;
    String dietAnalysisTypes="Primary Analysis Of Your Diet";

    TextView display;
   // ImageView increaseBtn, decreaseBtn;
    static String Time, date;
    String dietType;
    static int originalKcal = 0;
    int tempQuantity = 0;
    Spinner unitspinner;
    String[] unit={"Gram","Plates","Pcs","Ml"};
    LinearLayout back_btn;
    boolean isAllFieldsChecked = false;
    String    kcalstext;
    private boolean started = false;
    private Handler handler = new Handler();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breakfast);
        // display = (TextView) findViewById(R.id.quantity);
        textViewdiettype = findViewById(R.id.breakfast);
        timetext = findViewById(R.id.selecttime);
     //   kcalstext = findViewById(R.id.kcal);
      //  increaseBtn = findViewById(R.id.increase);
        //decreaseBtn = findViewById(R.id.decrease);
        back_btn=findViewById(R.id.btn_backadd);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                  onBackPressed();



            }
        });


        unitspinner=findViewById(R.id.spinnerdignosisQ);

        textViewquantity = findViewById(R.id.quantity);
        breakfast = findViewById(R.id.addbreakfast);
        btnsubmit = findViewById(R.id.breakbtnadd);
        mobilno = SharedPreference.readSharedSetting(this, "mobile_no", "");
        userAccountId = SharedPreference.readSharedSetting(this, "userAccountId", 0);
        //breakfast = (AutoCompleteTextView) findViewById(R.id.addbreakfast);





      //getReports1(userAccountId);
        datetext1 = findViewById(R.id.selectdate);
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        foodList = new ArrayList<FoodDietModel>();
        food = new ArrayList<>();
        foodWithKcal = new HashMap<>();
        dietType = textViewdiettype.getText().toString();

        getAllFoodItems();
        getReports(userAccountId,fromdate,todaydate);
        recyclerView = (RecyclerView) findViewById(R.id.breakfastrecylesview);
        recyclerView.setHasFixedSize(true);
        dietAnalysisDetails = new ArrayList<Root.Result.DietAnalysisDetails>();
        dietAnalysisType = new ArrayList<Root.Result.DietAnalysisDetails>();
        //Double  = breakfast.getText().toString().equals("") ;


        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);




      //  unitspinner.setOnItemClickListener(this);
                ArrayAdapter dignosisspinner=new ArrayAdapter(getApplicationContext(),android.R.layout.simple_spinner_item,unit);
                dignosisspinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                unitspinner.setAdapter(dignosisspinner);

                unitspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        String newItem=unitspinner.getSelectedItem().toString();
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, food);
       // breakfast = (AutoCompleteTextView) findViewById(R.id.addbreakfast);
        //  breakfast.setThreshold(1);
        breakfast.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
        // breakFastfood = breakfast.getText().toString();

        //getting the breakfast which is selected
        breakfast.setOnItemClickListener(this);


        textViewquantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    tempQuantity = Integer.parseInt(textViewquantity.getText().toString());
                    tempQuantity += 1;

                    //kcalorie = Integer.parseInt(kcalstext.getText().toString());
                  //  kcalorie = Integer.parseInt(kcalstext.getText().toString());
                    kcalorie =  originalKcal * tempQuantity;


                 //   // Toast.makeText(AddExtraBreakfast.this, "cal"+kcalorie, Toast.LENGTH_SHORT).show();
                   // kcalstext.setText(String.valueOf(kcalorie));

                    display(tempQuantity);
                }  catch (Exception e)
                {
                    Log.d("ReportRahul:","::::"+e.getMessage());
                }



            }
        });




        LinearLayout linearLayout1 = findViewById(R.id.layout_breakfast);
        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              try {
                  try {
                      boolean networkConectivity =  NetworkConnectivity.isConnected(Breakfast.this);
                      if (networkConectivity) {
                          if (isAllFieldsChecked = CheckAllFields()) {
                              if(!(breakFastfood == null))
                              {
                                  PostBreakfast(
                                          userAccountId,
                                          dietAnalysisTypes

                                          , textViewdiettype.getText().toString()
                                          , textViewquantity.getText().toString()
                                          ,breakFastfood
                                          , date
                                          , timetext.getText().toString()
                                          , kcalstext);


                                  getReports(userAccountId,fromdate,todaydate);
                                  breakfast.setText("");
                                  datetext1.setText("");
                                  textViewquantity.setText("");
                                  timetext.setText("");
                              }
                              else {
                                  Toast.makeText(Breakfast.this, "Food Item can't be found", Toast.LENGTH_SHORT).show();

                              }




                          }


                      }
                      else {
                          NetworkConnectivity.networkConnetivityShowDialog(Breakfast.this);

                      }
                  }
                  catch (Exception e)
                  {
                      Log.d("Report:","::::"+e.getMessage());
                  }
              }
              catch (Exception e)
              {
                  Toast.makeText(Breakfast.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                  Log.d("Errorin Time",":"+e.getMessage().toString());
              }

            }
        });



        try {
           timetext.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {


                   TimePickerDialog mTimePicker = new TimePickerDialog(Breakfast.this, new TimePickerDialog.OnTimeSetListener() {
                       @Override
                       public void onTimeSet(TimePicker timePicker, int i, int i1) {

                           Time = (i + ":" + i1);
                           timetext.setText(Time);
                       }
                   }, hour, minute, true);
                   mTimePicker.setTitle("Select Time");
                   mTimePicker.show();
               }
           });


           datetext1.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   DatePickerDialog dialog = new DatePickerDialog(Breakfast.this, (view1, year1, month1, dayOfMonth) -> {

                       date = (dayOfMonth + "-"
                               + (month1 + 1) + "-" + year1);

                       datetext1.setText(date);

                   }, year, month, day);
                   // mCalendar will be set to current/today's date
                   final Calendar mCalendar = Calendar.getInstance();

                   mCalendar.set(year, month, day-1);


                   dialog.getDatePicker().setMinDate(mCalendar.getTimeInMillis());
                   dialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                   dialog.show();


               }
           });
       }
       catch(Exception e)
       {
           Log.d("Errorin picking Date",":"+e.getMessage().toString());
       }


    }

    private boolean CheckAllFields() {

        if (datetext1.length() ==0) {

            return false;
        }
        if (timetext.length() == 0) {

            return false;
        }
        if (breakfast.length() == 0) {
            breakfast.setError("Add Diet required");
            return false;
        }

        return true;


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
                        dietAnalysisType.clear();
                        modelList = response.body();
                        try {
                            if(modelList != null){
                                resultList = modelList.getResult();
                            }
                            else {
                                Toast.makeText(Breakfast.this, "Data not generated", Toast.LENGTH_SHORT).show();
                            }
                        }
                        catch(Exception e)
                        {
                            Toast.makeText(Breakfast.this, "Caused exception Data not generated", Toast.LENGTH_SHORT).show();

                        }
                        resultdiet=modelList.getResult();
                       // progressBar.setVisibility(View.GONE);

                        Log.d("TAG", "Response = " + response.message());
                       // Toast.makeText(BreakFastprimaryanalysisReport.this, "Add diet Successfully", Toast.LENGTH_SHORT).show();
                        // Log.d("DietResult", "" + modelList.getDietAnalysisDetailsList().toString());
                        /*for(int i=0; i<resultList.size(); i++) {
                            Log.d("DietResult", "" + resultList.get(i).getDietAnalysisDetailsList().get(0));
                           // Log.d("DietResultObj", "" + resultList.get(i));
                        //    dietAnalysisDetailsObj= resultList.get(i).getDietAnalysisDetailsList().get(0);
                         //   dietAnalysisDetails.add(dietAnalysisDetailsObj);
                          //  Log.d("DietResult", "" + dietAnalysisDetails.toString());
                           // Log.d("DietResult", "" + dietAnalysisDetails.get(i).getkCal().toString());
                        }*/

                        for (int i=0; i<resultList.size();i++){

                            try {
                                if (resultList.get(i).getDietAnalysisType().equalsIgnoreCase("Primary Analysis Of Your Diet")) {
                                    if (resultList.get(i).getDietType().equalsIgnoreCase("BREAKFAST")) {

                                        dietAnalysisTypeobj = resultdiet.get(i).getDietAnalysisDetailsList().get(0);
                                        dietAnalysisType.add(dietAnalysisTypeobj);


                                    }

                                }

                                reportAdapter = new ItemsAdapters(getApplicationContext(), dietAnalysisType);
                                recyclerView.setAdapter(reportAdapter);
                                reportAdapter.setDietAnalysisObjectList(dietAnalysisType);
                                reportAdapter.notifyDataSetChanged();
                                Log.d("Rahul:","::::AAAAAAAA");
                            }  catch (Exception e)
                            {
                                Log.d("ReportRahul:","::::"+e.getMessage());
                            }

                        }


                       /* reportAdapter = new ItemsAdapters(getApplicationContext(), dietAnalysisType);
                        recyclerView.setAdapter(reportAdapter);
                        reportAdapter.setDietAnalysisObjectList(dietAnalysisType);
                        reportAdapter.notifyDataSetChanged();*/


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


    private void PostBreakfast(int userAccountId, String dietAnalysisType, String dietType, String quantity, String item, String date, String dietTime, String kCal) {

          try {


        DietApi dietApi = UserAccountclient.getClient().create(DietApi.class);

        DietDataModel dietAnalysisDetailList = new DietDataModel(quantity, item, kCal);
        ArrayList<DietDataModel> dietAnalysisDetailList2 = new ArrayList<DietDataModel>();
        dietAnalysisDetailList2.add(dietAnalysisDetailList);
        DietDataModel.Root dietDataModel = new DietDataModel.Root(userAccountId, dietAnalysisType, dietTime, dietType, date, dietAnalysisDetailList2);
        Gson g1 = new Gson();
        Log.v("Sending data:", g1.toJson(dietDataModel));
        Call<DietDataModel.Root> call = dietApi.createPost(dietDataModel);

        call.enqueue(new Callback<DietDataModel.Root>() {
            @Override
            public void onResponse(Call<DietDataModel.Root> call, Response<DietDataModel.Root> response) {


                if (response.code() == 200) {
                    String responseString = "Response code :" + response.code();
                   Toast.makeText(Breakfast.this, "Add diet Successfully", Toast.LENGTH_SHORT).show();
                    Log.d("ADD","Added diet by ashutosh");


                    Log.e("TAG", "Response =" + responseString);
                    Gson gson = new Gson();
                    String s1 = gson.toJson(response.body());
                    Log.e("Response", s1);

                } else if (!response.isSuccessful()) {

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
                try {
                    if (response.isSuccessful()) {
                        // below line is to add our data from api to our array list.
                        foodList = response.body();
                        // below line we are running a loop to add data to our adapter class.
                        for (int i = 0; i < foodList.size(); i++) {
                            Log.d("foodList", ":" + foodList.get(i).getItemName());
                            food.add(foodList.get(i).getItemName());

                            if (!(foodList.get(i).getKCal().equalsIgnoreCase(null)
                                    || foodList.get(i).getKCal().equalsIgnoreCase(""))) {
                                foodWithKcal.put(foodList.get(i).getItemName(), foodList.get(i).getKCal());
                            } else {
                                foodWithKcal.put(foodList.get(i).getItemName(), "0");
                            }

                            Log.d("KCAL", ":" + foodWithKcal.get(foodList.get(i).getItemName()));

                        }

                    }
                } catch (Exception e)
                {
                    Log.d("Getting user items",e.getMessage());
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


    private void display(int number) {

        textViewquantity.setText("" + number);
    }



    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        breakFastfood = adapterView.getItemAtPosition(position).toString();
        try {
            for (Map.Entry<String, String> breakfast : foodWithKcal.entrySet()) {
                if (breakFastfood.equalsIgnoreCase(breakfast.getKey())) {
                    // kcalstext.setText(breakfast.getValue());

                    originalKcal =Integer.parseInt( breakfast.getValue());
                    // Log.d("found", ":" + kcalstext.getText().toString());
                    Log.d("foundBBBB", ":" + breakFastfood);
                }

            }
        }
        catch(Exception e)
        {
            Toast.makeText(this, "Unable to select food", Toast.LENGTH_SHORT).show();
            Log.d("FoodList error", e.getMessage());
        }
        // create Toast with user selected value
        SharedPreference.saveSharedSetting(Breakfast.this, "breakfast", breakFastfood);
    }
}

