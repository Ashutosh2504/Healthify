package com.example.healthifyapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healthifyapp.adapter.CityAdapters;
import com.example.healthifyapp.api.MedicalConditionApi;
import com.example.healthifyapp.api.UserAccountAPI;
import com.example.healthifyapp.model.CityDataModel;
import com.example.healthifyapp.model.MedicalConditionDataModel;
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

public class  SecondActivity extends AppCompatActivity {
     AutoCompleteTextView autocomplete;
     TextView textView, textView1;
     CardView banguluru;
    private static final int TRIGGER_AUTO_COMPLETE = 100;
    private static final long AUTO_COMPLETE_DELAY = 300;
    private Handler handler;
    private CityAdapters autoSuggestAdapter;
    String name, mobileno, city;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        final AutoCompleteTextView autoCompleteTextView = findViewById(R.id.city);
      //  final TextView selectedText = findViewById(R.id.selected_item);
        //Setting up the adapter for AutoSuggest
     //   autoSuggestAdapter = new CityAdapters(this, android.R.layout.simple_dropdown_item_1line);
       // autoCompleteTextView.setThreshold(2);
      //  autoCompleteTextView.setAdapter(autoSuggestAdapter);


//
        textView = findViewById(R.id.nextsecond);
        textView1 = findViewById(R.id.backsecond);
       // makeApiCall();

       // postData(autocomplete.getText().toString());
      //  autoCompleteTextView.addTextChangedListener(new TextWatcher() {
        //            @Override
        //            public void beforeTextChanged(CharSequence s, int start, int
        //                    count, int after) {
        //            }
        //
        //            @Override
        //            public void onTextChanged(CharSequence s, int start, int before,
        //                                      int count) {
        //                handler.removeMessages(TRIGGER_AUTO_COMPLETE);
        //                handler.sendEmptyMessageDelayed(TRIGGER_AUTO_COMPLETE,
        //                        AUTO_COMPLETE_DELAY);
        //            }
        //
        //            @Override
        //            public void afterTextChanged(Editable s) {
        //            }
        //        });
        //        handler = new Handler(new Handler.Callback() {
        //            @Override
        //            public boolean handleMessage(Message msg) {
        //                if (msg.what == TRIGGER_AUTO_COMPLETE) {
        //                    if (!TextUtils.isEmpty(autoCompleteTextView.getText())) {
        //
        //                    }
        //                }
        //                return false;
        //            }
        //        });


         textView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                // if(autocomplete.getText().toString().isEmpty()){
                 //                     Toast.makeText(SecondActivity.this,"please enter name", Toast.LENGTH_SHORT).show();
                 //
                 //
                 //                     return;
                 //                 }
                 Intent intent=new Intent(SecondActivity.this,ThirdActivity.class);
                 startActivity(intent);
             }
         });

         textView1.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent=new Intent(SecondActivity.this,FirstActivity.class);
                 startActivity(intent);
             }
         });

    }

//    private void makeApiCall() {
//
//        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://easywaygst.theumangsociety.org")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//          UserAccountAPI userAccountAPI=retrofit.create(UserAccountAPI.class);
//
//
//
//        Call<List<CityDataModel>> call=userAccountAPI.getcitydatamodel();
//        call.enqueue(new Callback<List<CityDataModel>>() {
//            @Override
//            public void onResponse(Call<List<CityDataModel>> call, Response<List<CityDataModel>> response) {
//                List<String> stringList = new ArrayList<>();
//                try {
//                    JSONObject responseObject = new JSONObject(String.valueOf(response));
//                    JSONArray array = responseObject.getJSONArray("results");
//                    for (int i = 0; i < array.length(); i++) {
//                        JSONObject row = array.getJSONObject(i);
//                        stringList.add(row.getString("trackName"));
//
//
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//             //   autoSuggestAdapter.setData(stringList);
//               // autoSuggestAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onFailure(Call<List<CityDataModel>> call, Throwable t) {
//
//            }
//
//        });
//
//    }



    private void postData(String city) {

            Retrofit retrofit=new Retrofit.Builder()
                    .baseUrl("https://easywaygst.theumangsociety.org")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            UserAccountAPI retrofitAPI= retrofit.create(UserAccountAPI.class);
            UserAccountDataModel model=new UserAccountDataModel(city);
            Call<UserAccountDataModel> call=retrofitAPI.createPost(model);



        call.enqueue(new Callback<UserAccountDataModel>() {
            @Override
            public void onResponse(Call<UserAccountDataModel> call, Response<UserAccountDataModel> response) {
                if(response.code()==200) {

                    String responseString="Response code :" +response.code() ;
                    //showResponse(response.body().toString());
                    Toast.makeText(SecondActivity.this, " Success", Toast.LENGTH_SHORT).show();
                    Log.e("Tag", "post submitted to API." + responseString);
                }else {
                    String responseString="Response code :" +response.code() ;
                    Log.e("TAG","Response ="+responseString);
                    Toast.makeText(SecondActivity.this, "", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<UserAccountDataModel> call, Throwable t) {
                Log.e("TAG","Response = "+t.toString());
            }
        });

    }
}