package com.example.healthifyapp.ashutoshseven;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.healthifyapp.FirstActivity;
import com.example.healthifyapp.MainActivity;
import com.example.healthifyapp.R;
import com.example.healthifyapp.SharedPreferences.SharedPreference;
import com.example.healthifyapp.ThirdActivity;
import com.example.healthifyapp.adapter.CityAdapters;
import com.example.healthifyapp.api.UserAccountAPI;
import com.example.healthifyapp.model.CityDataModel;
import com.example.healthifyapp.model.UserAccountDataModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Cities4 extends AppCompatActivity  implements AdapterView.OnItemClickListener {
    private static final String JSON_URL = "https://easywaygst.theumangsociety.org/api/Cities/GetCities";
    private static final int TRIGGER_AUTO_COMPLETE = 100;
    private static final long AUTO_COMPLETE_DELAY = 300;
    AutoCompleteTextView autocomplete;
    TextView nextBtn, backBtn;
    String name, mobileno, city;
    ArrayList<CityDataModel> cities;
    ArrayList<String> cityList;
    AutoCompleteTextView autoCities;
    CardView banguluru,mumbai,newdelhi,kolkata,chennai,nagpur,hydrabad,pune,surat;
    private Handler handler;
    private CityAdapters autoSuggestAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
       // autoCities = (AutoCompleteTextView) findViewById(R.id.city_names);

        banguluru=findViewById(R.id.btn_bangaluru);
        mumbai=findViewById(R.id.btn_mumbai);
        newdelhi=findViewById(R.id.btn_newdelhi);
        kolkata=findViewById(R.id.btn_kolkata);
        chennai=findViewById(R.id.btn_chennai);
        nagpur=findViewById(R.id.btn_nagpur);
        hydrabad=findViewById(R.id.btn_hydrabad);
        pune=findViewById(R.id.btn_pune);
        surat=findViewById(R.id.btn_surat);



        nextBtn = findViewById(R.id.nextBtn);
        backBtn = findViewById(R.id.backbtn);
        // makeApiCall();
        cities = new ArrayList<>();
        cityList = new ArrayList<>();

        getAllCities();
        for (int i = 0; i < cityList.size(); i++) {
            Log.d("Cityyy", ":" + cityList.get(i));

        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, cityList);
        autocomplete = (AutoCompleteTextView) findViewById(R.id.city_names);
        // autocomplete.setThreshold(1);
        autocomplete.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
        autocomplete.setOnItemClickListener(this);
       // city = autocomplete.getText().toString();
        // city = autocomplete.getText().toString();
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreference.saveSharedSetting(Cities4.this, "city", city);

                openActivity(city);
            }
        });

        banguluru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               autocomplete.setText("Bangluru");
            }
        });
        mumbai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                autocomplete.setText("Mumbai");
            }
        });
        newdelhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                autocomplete.setText("New Delhi");
            }
        });
        kolkata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                autocomplete.setText("Kolkata");
            }
        });
        chennai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                autocomplete.setText("Chennai");
            }
        });
        nagpur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                autocomplete.setText("Nagpur");
            }
        });
        hydrabad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                autocomplete.setText("Hyderabad");
            }
        });
        pune.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                autocomplete.setText("Pune");
            }
        });
        surat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                autocomplete.setText("Surat");
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Cities4.this, Name3.class);
                startActivity(intent);
            }
        });

    }


    private void getAllCities() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://easywaygst.theumangsociety.org")
                // on below line we are calling add
                // Converter factory as Gson converter factory.
                .addConverterFactory(GsonConverterFactory.create())
                // at last we are building our retrofit builder.
                .build();

        // below line is to create an instance for our retrofit api class.
        UserAccountAPI retrofitAPI = retrofit.create(UserAccountAPI.class);

        // on below line we are calling a method to get all the cities from API.
        Call<ArrayList<CityDataModel>> call = retrofitAPI.getcitydatamodel();

        call.enqueue(new Callback<ArrayList<CityDataModel>>() {
            @Override
            public void onResponse(Call<ArrayList<CityDataModel>> call, Response<ArrayList<CityDataModel>> response) {
                // inside on response method we are checking
                // if the response is success or not.
                try {


                if (response.isSuccessful()) {
                    // below line is to add our data from api to our array list.
                    cities = response.body();
                    // below line we are running a loop to add data to our adapter class.
                    for (int i = 0; i < cities.size(); i++) {
                        Log.d("city", ":" + cities.get(i).getValue());
                        cityList.add(cities.get(i).getValue());
                    }
                }
                }  catch (Exception e)
                {
                    Log.d("Report:","::::"+e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<CityDataModel>> call, Throwable t) {

            }
        });
    }

    public void openActivity(String city) {
        String name, mobileno;
        name = getIntent().getStringExtra("login_name");
        mobileno = getIntent().getStringExtra("mobile_no");

        Intent intent = new Intent(Cities4.this, Gender5.class);
        intent.putExtra("login_name", name);
        intent.putExtra("mobile_no", mobileno);
        intent.putExtra("city", city);
        //intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
         city = adapterView.getItemAtPosition(position).toString();

        // create Toast with user selected value
        Toast.makeText(Cities4.this, "Selected Item is: \t" + city, Toast.LENGTH_LONG).show();
        Log.d("citiiiiiiiiiiiiiiiii", "" + city);

        SharedPreference.saveSharedSetting(Cities4.this,"city",city);
    }
}
