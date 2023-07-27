package com.example.healthifyapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.healthifyapp.SharedPreferences.SharedPreference;
import com.example.healthifyapp.adapter.HeavyAdapters;
import com.example.healthifyapp.adapter.ModerateAdapters;
import com.example.healthifyapp.adapter.SendentaryAdapters;
import com.example.healthifyapp.api.LifeStyleItemApi;
import com.example.healthifyapp.api.LifeStyleSubItemAPi2;
import com.example.healthifyapp.api.LifeStyleSubItemAPi3;
import com.example.healthifyapp.api.LifeStyleSubItemApi;
import com.example.healthifyapp.model.LifeStyleItemDataModel;
import com.example.healthifyapp.model.LifeStyleSubItemModel;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SevenActivity extends AppCompatActivity {
    public static List<String> moderateList = new ArrayList<>();
    public static List<String> heavyList = new ArrayList<>();
    public static List<String> sedentaryList = new ArrayList<>();
    String  lifeStylesubModelAdded = "";
    String text1, text2, text3;
    static String lifeStyleModel;
    Button btnsedentary, btnmoderate, btnheavy;
    LinearLayout linearLayout1, linearLayout2, linearLayout3;
    LinearLayout linearLayout, linearLayout6;
    List<LifeStyleSubItemModel> lifeStyleSubItemModelList;
    List<LifeStyleSubItemModel> lifeStyleSubItemModelList2;
    List<LifeStyleSubItemModel> lifeStyleSubItemModelList3;
    RecyclerView recyclerView;
    SendentaryAdapters sendentaryAdapters;
    ModerateAdapters moderateAdapters;
    HeavyAdapters heavyAdapters;
SharedPreference sharedPreference = new SharedPreference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seven);
        btnsedentary = findViewById(R.id.sedentary);
        btnmoderate = findViewById(R.id.moderate);
        btnheavy = findViewById(R.id.heavyactivity);
        linearLayout1 = findViewById(R.id.sedentarystyle);
        linearLayout2 = findViewById(R.id.moderatestyle);
        linearLayout3 = findViewById(R.id.heavystyle);
        linearLayout = findViewById(R.id.btn_next6);
        linearLayout6 = findViewById(R.id.btn_Back6);

        lifeStyleSubItemModelList = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        sendentaryAdapters = new SendentaryAdapters(getApplicationContext(), lifeStyleSubItemModelList);
        recyclerView.setAdapter(sendentaryAdapters);


        lifeStyleSubItemModelList2 = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview1);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager2);
        moderateAdapters = new ModerateAdapters(getApplicationContext(), lifeStyleSubItemModelList2);

        recyclerView.setAdapter(moderateAdapters);


        lifeStyleSubItemModelList3 = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview2);
        LinearLayoutManager layoutManager3 = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager3);
        heavyAdapters = new HeavyAdapters(getApplicationContext(), lifeStyleSubItemModelList3);

        recyclerView.setAdapter(heavyAdapters);

        getData();

        btnsedentary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lifeStyleModel = text1;
                linearLayout1.setVisibility(View.VISIBLE);
                linearLayout2.setVisibility(View.GONE);
                linearLayout3.setVisibility(view.GONE);
            }
        });
        btnmoderate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lifeStyleModel = text2;
                linearLayout1.setVisibility(View.GONE);
                linearLayout2.setVisibility(View.VISIBLE);
                linearLayout3.setVisibility(view.GONE);
            }
        });
        btnheavy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lifeStyleModel = text3;
                linearLayout3.setVisibility(view.VISIBLE);
                linearLayout1.setVisibility(View.GONE);
                linearLayout2.setVisibility(View.GONE);
            }
        });


        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openActivity(lifeStyleModel, calculateLifeStyleSubmodel());
            }
        });
        linearLayout6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Intent intent = new Intent(SevenActivity.this, SixActivity.class);
                //startActivity(intent);
            onBackPressed();

            }
        });

    }

    private String calculateLifeStyleSubmodel() {
        if (lifeStyleModel != null) {
            if (lifeStyleModel.equalsIgnoreCase(text1)) {
                for (int i = 0; i < sedentaryList.size(); i++) {
                    // Log.d("List", ":" + sedentaryList.get(i));
                    lifeStylesubModelAdded += sedentaryList.get(i)+"";
                }
            } else if (lifeStyleModel.equalsIgnoreCase(text2)) {
                for (int i = 0; i < moderateList.size(); i++) {
                    //  Log.d("List", ":" + moderateList.get(i));
                    lifeStylesubModelAdded += moderateList.get(i)+"";
                }
            } else if (lifeStyleModel.equalsIgnoreCase(text3)) {
                for (int i = 0; i < heavyList.size(); i++) {
                    //  Log.d("List", ":" + heavyList.get(i));
                    lifeStylesubModelAdded += heavyList.get(i)+"";
                }
            } else {
                return lifeStylesubModelAdded;
            }
        } else {
            return "Empty";
        }
        return lifeStylesubModelAdded;
    }

    private void getData() {

       try {


        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://easywaygst.theumangsociety.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        LifeStyleSubItemAPi2 lifeStyleSubItemAPi2 = retrofit.create(LifeStyleSubItemAPi2.class);
        LifeStyleSubItemAPi3 lifeStyleSubItemAPi3 = retrofit.create(LifeStyleSubItemAPi3.class);
        LifeStyleItemApi lifeStyleItemApi = retrofit.create(LifeStyleItemApi.class);
        LifeStyleSubItemApi lifeStyleSubItemApi = retrofit.create(LifeStyleSubItemApi.class);

        Call<List<LifeStyleSubItemModel>> call2 = lifeStyleSubItemApi.getlifestylesubitemmodel();
        call2.enqueue(new Callback<List<LifeStyleSubItemModel>>() {
            @Override
            public void onResponse(Call<List<LifeStyleSubItemModel>> call, Response<List<LifeStyleSubItemModel>> response) {

              try {


                List<LifeStyleSubItemModel> itemModels1 = response.body();

                lifeStyleSubItemModelList = response.body();

                sendentaryAdapters.setLifeStyleSubItemModelList(lifeStyleSubItemModelList);
              }  catch (Exception e)
              {
                  Log.d("Report:","::::"+e.getMessage());
              }
            }

            @Override
            public void onFailure(Call<List<LifeStyleSubItemModel>> call, Throwable t) {
            }
        });
        Call<List<LifeStyleSubItemModel>> moderatecall = lifeStyleSubItemAPi2.getlifestylesubitemmodel2();
        moderatecall.enqueue(new Callback<List<LifeStyleSubItemModel>>() {
            @Override
            public void onResponse(Call<List<LifeStyleSubItemModel>> call, Response<List<LifeStyleSubItemModel>> response) {

               try {


                lifeStyleSubItemModelList2 = response.body();
                moderateAdapters.setLifeStyleSubItemModelList2(lifeStyleSubItemModelList2);
               }  catch (Exception e)
               {
                   Log.d("Report:","::::"+e.getMessage());
               }

               }

            @Override
            public void onFailure(Call<List<LifeStyleSubItemModel>> call, Throwable t) {
                Log.e("TAG", "Response = " + t.toString());
            }
        });
        Call<List<LifeStyleSubItemModel>> heavycall = lifeStyleSubItemAPi3.getlifestylesubitemmodel3();
        heavycall.enqueue(new Callback<List<LifeStyleSubItemModel>>() {
            @Override
            public void onResponse(Call<List<LifeStyleSubItemModel>> call, Response<List<LifeStyleSubItemModel>> response) {
               try {


                lifeStyleSubItemModelList3 = response.body();
                heavyAdapters.setLifeStyleSubItemModelList3(lifeStyleSubItemModelList3);
               }  catch (Exception e)
               {
                   Log.d("Report:","::::"+e.getMessage());
               }

               }

            @Override
            public void onFailure(Call<List<LifeStyleSubItemModel>> call, Throwable t) {
                Log.e("TAG", "Response = " + t.toString());
            }
        });

        Call<List<LifeStyleItemDataModel>> call = lifeStyleItemApi.getlifestyleitemmodel();

        call.enqueue(new Callback<List<LifeStyleItemDataModel>>() {
            @Override
            public void onResponse(Call<List<LifeStyleItemDataModel>> call, Response<List<LifeStyleItemDataModel>> response) {
                try {


                List<LifeStyleItemDataModel> item = response.body();

                text1 = item.get(0).getText();
                text2 = item.get(1).getText();
                text3 = item.get(2).getText();
                lifeStyleModel = text1;
                btnsedentary.setText(text1);
                btnmoderate.setText(text2);
                btnheavy.setText(text3);
                Log.e("Tag", "sucess" + response);

                }  catch (Exception e)
                {
                    Log.d("Report:","::::"+e.getMessage());
                }

                }

            @Override
            public void onFailure(Call<List<LifeStyleItemDataModel>> call, Throwable t) {
                Log.e("TAG", "Response = " + t.toString());
            }
        });
       }
       catch (Exception e)
       {
           Log.d("Report:","::::"+e.getMessage());
       }

    }

    public void openActivity(String lifeStyleModel, String lifeStyleSubModel) {
        String name, mobileno, city, gender, dob, weightUnit, heightUnit;
        float height, weight;
        name = getIntent().getStringExtra("login_name");
        mobileno = getIntent().getStringExtra("mobile_no");
        city = getIntent().getStringExtra("city");
        gender = getIntent().getStringExtra("gender");
        dob = getIntent().getStringExtra("dob");
        Bundle bundle = getIntent().getExtras();
        height = bundle.getFloat("height");
        heightUnit = getIntent().getStringExtra("height_unit");
        weight = bundle.getFloat("weight");
        weightUnit = getIntent().getStringExtra("weight_unit");
       /*
        name = Shared("login_name");
        mobileno = getIntent().getStringExtra("mobile_no");
        city = getIntent().getStringExtra("city");
        gender = getIntent().getStringExtra("gender");
        dob = getIntent().getStringExtra("dob");
        Bundle bundle = getIntent().getExtras();
        height = bundle.getFloat("height");
        heightUnit = getIntent().getStringExtra("height_unit");
        weight = bundle.getFloat("weight");
        weightUnit = getIntent().getStringExtra("weight_unit");
*/

        Intent intent = new Intent(SevenActivity.this, EightActivity.class);
        intent.putExtra("login_name", name).toString();
        intent.putExtra("mobile_no", mobileno);
        intent.putExtra("city", city);
      //  intent.putExtra("gender", gender);
       // intent.putExtra("dob", dob);
        intent.putExtra("height", height);
        intent.putExtra("height_unit", heightUnit);
        intent.putExtra("weight", weight);
        intent.putExtra("weight_unit", weightUnit);
        intent.putExtra("life_style_model", lifeStyleModel);
        intent.putExtra("life_style_submodel", lifeStyleSubModel);



        //intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
