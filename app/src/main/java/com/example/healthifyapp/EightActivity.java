package com.example.healthifyapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.healthifyapp.SharedPreferences.SharedPreference;
import com.example.healthifyapp.adapter.MedicalConditionAdapters;
import com.example.healthifyapp.adapter.SendentaryAdapters;
import com.example.healthifyapp.api.MedicalConditionApi;
import com.example.healthifyapp.api.UserAccountAPI;
import com.example.healthifyapp.model.LifeStyleSubItemModel;
import com.example.healthifyapp.model.MedicalConditionDataModel;
import com.example.healthifyapp.model.UserAccountDataModel;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EightActivity extends AppCompatActivity {
    public static List<String> medicalCondnList = new ArrayList<>();
    RadioButton radioButton, radioButton1, radioButton2, radioButton3, radioButton4, radioButton5, radioButton6;
    EditText edittext;
    LinearLayout linearLayout, linearLayout8;
    List<MedicalConditionDataModel> medicalConditionDataModels;
    RecyclerView recyclerView;
    MedicalConditionAdapters medicalConditionAdapters;
    String medicalCondd;
    int userAccountId;
    String email="0";
    String age="0";


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eight);

         userAccountId = SharedPreference.readSharedSetting(EightActivity.this,"userAccountId",userAccountId);

        radioButton1 = findViewById(R.id.none);
        //  radioButton=findViewById(R.id.radio_other);
        // edittext =findViewById(R.id.edittext12);
        linearLayout = findViewById(R.id.nextSeven7);
        linearLayout8 = findViewById(R.id.btn_Back7);

// lifeStyleSubItemModelList = new ArrayList<>();
//        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
//        sendentaryAdapters = new SendentaryAdapters(getApplicationContext(),lifeStyleSubItemModelList);
//        recyclerView.setAdapter(sendentaryAdapters);
        medicalConditionDataModels = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview123);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        medicalConditionAdapters = new MedicalConditionAdapters(getApplicationContext(), medicalConditionDataModels);

        // medicalConditionDataModel=(List<MedicalConditionDataModel>new MedicalConditionAdapters(getApplicationContext(),medicalConditionDataModel)) ;
        //medicalConditionApis = (List<ArrayList<String>>) new MedicalConditionAdapters(getApplicationContext(),medicalConditionApis);
        recyclerView.setAdapter(medicalConditionAdapters);


        getMedicalCondition();


        linearLayout8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EightActivity.this, SevenActivity.class);
                startActivity(intent);

            }
        });

        // radioButton.setOnClickListener(new View.OnClickListener() {
        //            @Override
        //            public void onClick(View view) {
        //
        //                if (!radioButton.isSelected()) {
        //                    radioButton.setChecked(true);
        //                    radioButton.setSelected(true);
        //                    edittext.setEnabled(true);
        //                } else {
        //                    radioButton.setChecked(false);
        //
        //                    edittext.setEnabled(false);
        //                    radioButton.setSelected(false);
        //                }
        //
        //            }
        //        });

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for (int i = 0; i < medicalCondnList.size(); i++) {
                    // Log.d("List", ":" + sedentaryList.get(i));
                    medicalCondd += medicalCondnList.get(i);
                }
                openActivity(medicalCondd);
            }
        });


    }

    private void getMedicalCondition() {

        try {



        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://easywaygst.theumangsociety.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        MedicalConditionApi medicalConditionApi = retrofit.create(MedicalConditionApi.class);
        Call<List<MedicalConditionDataModel>> call = medicalConditionApi.getmedicalconditionmodel();
        //Response response=call.execute();
        //ArrayList<String> arrayList= (ArrayList<String>) response.body();


        call.enqueue(new Callback<List<MedicalConditionDataModel>>() {
            @Override
            public void onResponse(Call<List<MedicalConditionDataModel>> call, Response<List<MedicalConditionDataModel>> response) {

               try {


                medicalConditionDataModels = response.body();
                Log.e("TAG", "Response = " + response);
                //medicalConditionAdapters.setMedicalConditionjsonObjectList(medicalConditionjsonObjectList);
                medicalConditionAdapters.setMedicalConditionjsonObjectList(medicalConditionDataModels);
                //sendentaryAdapters.setLifeStyleSubItemModelList(lifeStyleSubItemModelList);

               }  catch (Exception e)
               {
                   Log.d("Report:","::::"+e.getMessage());
               }

               }

            @Override
            public void onFailure(Call<List<MedicalConditionDataModel>> call, Throwable t) {

            }
        });

        }
        catch (Exception e)
        {
            Log.d("Report:","::::"+e.getMessage());
        }

    }

    public void openActivity(String medicalCond) {
        String name, mobileno, city, gender, dob, weightUnit, heightUnit, lifeStyleModel, lifeStyleSubModel;
        float height, weight;
        name = getIntent().getStringExtra("login_name");
        mobileno = getIntent().getStringExtra("mobile_no");
        city = getIntent().getStringExtra("city");
        gender = SharedPreference.readSharedSetting(EightActivity.this,"gender","");
        dob = SharedPreference.readSharedSetting(EightActivity.this,"dob","");
      //  dob = getIntent().getStringExtra("dob");
        Bundle bundle = getIntent().getExtras();
        height = bundle.getFloat("height");
        heightUnit = getIntent().getStringExtra("height_unit");
        weight = bundle.getFloat("weight");
        weightUnit = getIntent().getStringExtra("weight_unit");
        lifeStyleModel = getIntent().getStringExtra("life_style_model");
        lifeStyleSubModel = getIntent().getStringExtra("life_style_submodel");
        Log.d("weightunit","");

        postData( mobileno,name, city, gender, dob,height, heightUnit,weight, weightUnit, lifeStyleModel, lifeStyleSubModel, medicalCondd,email,age);

        Intent intent = new Intent(EightActivity.this, DrawerActivity.class);
        intent.putExtra("login_name", name).toString();
        intent.putExtra("mobile_no", mobileno);
        intent.putExtra("city", city);
        intent.putExtra("gender", gender);
        intent.putExtra("dob", dob);
        intent.putExtra("height", height);
        intent.putExtra("height_unit", heightUnit);
        intent.putExtra("weight", weight);
        intent.putExtra("weight_unit", weightUnit);
        intent.putExtra("life_style_model", lifeStyleModel);
        intent.putExtra("life_style_submodel", lifeStyleSubModel);
        intent.putExtra("medical_condition", medicalCond);

        Log.d("lllllllll came", "" + lifeStyleModel);
        Log.d("lbst came", "" + lifeStyleSubModel);
        //intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    private void postData( String mobileno,String name,String city,String gender,String dob,float height,String heightUnit,float weight,String weightUnit,String lifeStyleModel,String lifeStyleSubModel, String medicalCondid,String email,String age)
 {
     try {



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://easywaygst.theumangsociety.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UserAccountAPI retrofitAPI = retrofit.create(UserAccountAPI.class);

        UserAccountDataModel model = new UserAccountDataModel( mobileno, name, city,
                gender, dob, height, heightUnit, weight, weightUnit,medicalCondid, lifeStyleModel,
                lifeStyleSubModel,email,age);
        // model.setMobileNo("+917083490012");
        // model.setUserName("Rahul");


        Call<UserAccountDataModel> call = retrofitAPI.createPost(model);


        call.enqueue(new Callback<UserAccountDataModel>() {
            @Override
            public void onResponse(Call<UserAccountDataModel> call, Response<UserAccountDataModel> response) {
               try{
                if (response.code() == 200) {

                    String responseString = "Response code :" + response.code();
                    //showResponse(response.body().toString());
                    Toast.makeText(EightActivity.this, " Registration Successfully Completed", Toast.LENGTH_SHORT).show();
                    Log.d("Tag", "post submitted to API." + responseString);



                } else {
                    String responseString = "Response code :" + response.code();
                    Log.e("TAG", "Response =" + responseString);
                    Toast.makeText(EightActivity.this, "", Toast.LENGTH_SHORT).show();
                }
            }
             catch (Exception e)
            {
                Log.d("Report:","::::"+e.getMessage());
            }


            }

            @Override
            public void onFailure(Call<UserAccountDataModel> call, Throwable t) {
                Log.e("TAG", "Response = " + t.toString());
            }
        });
     }
     catch (Exception e)
     {
         Log.d("Report:","::::"+e.getMessage());
     }


    }
}