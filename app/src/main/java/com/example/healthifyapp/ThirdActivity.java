package com.example.healthifyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.healthifyapp.SharedPreferences.SharedPreference;
import com.example.healthifyapp.api.UserAccountAPI;
import com.example.healthifyapp.model.apiclient.UserAccountclient;

import com.example.healthifyapp.model.UserAccountDataModel;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThirdActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener{
  private RadioGroup radioGroup;
    private RadioButton radioMale;
    private RadioButton radioFemale;
    private RadioButton radioOther;
    LinearLayout textView,textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        textView=findViewById(R.id.btn_next3);
        textView1=findViewById(R.id.btn_Back3);
        radioGroup=findViewById(R.id.radiogroup);
        radioMale = findViewById(R.id.radioMale);
        radioFemale = findViewById(R.id.radioFemale);
        radioOther = findViewById(R.id.radioOther);
        //txtGender = findViewById(R.id.txtGender);

   //     radioGroup.setOnCheckedChangeListener((RadioGroup.OnCheckedChangeListener) this);
        radioMale.setOnCheckedChangeListener( this);
        radioFemale.setOnCheckedChangeListener(this);
        radioOther.setOnCheckedChangeListener(this);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if (radioGroup.getCheckedRadioButtonId()==-1){

                    radioMale.setChecked(true);
                    radioFemale.setChecked(false);
                    radioOther.setChecked(false);
                    Log.d("nmaale","ooooo");
                    DisplayMsg("male");
                    SharedPreference.saveSharedSetting(ThirdActivity.this,"gender","Male");
                }  else if(radioGroup.getCheckedRadioButtonId()==-1) {
                    radioMale.setChecked(false);
                    radioFemale.setChecked(true);
                    radioOther.setChecked(false);
                    DisplayMsg("female");
                    Log.d("fema","ooooo");
                    SharedPreference.saveSharedSetting(ThirdActivity.this,"gender","Female");

                }else{
                    radioMale.setChecked(false);
                    radioFemale.setChecked(false);
                    radioOther.setChecked(true);
                    DisplayMsg("other");
                    Log.d("other","ooooo");
                    SharedPreference.saveSharedSetting(ThirdActivity.this,"gender","Other");

                }
            }
        });



        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(ThirdActivity.this,SecondActivity.class);
                startActivity(intent);
            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if(!validateGender()){
                //
                //                    return;
                //                }

              // if(radioMale.isChecked()||radioFemale.isChecked()||radioOther.isChecked()){
                //                   Toast.makeText(ThirdActivity.this,"please select gender", Toast.LENGTH_SHORT).show();
                //                    return;
                //
                //               }else {
                //                 //   Toast.makeText(getApplicationContext(), "Please select Gender", Toast.LENGTH_SHORT).show();
                //                   // Log.d("QAOD", "Gender is Null");
                //                }


                //postData(radioGroup.toString());
                Intent intent=new Intent(ThirdActivity.this,FourthActivity.class);
                startActivity(intent);
//                }
            }
        });
    }

    private void DisplayMsg(String msg) {
        Toast.makeText(ThirdActivity.this,msg,Toast.LENGTH_SHORT).show();
    }

    private boolean validateGender() {
        if (radioGroup.getCheckedRadioButtonId()==-1){
            Toast.makeText(this,"please Select Gender",Toast.LENGTH_SHORT).show();
            return false;
        }else {


            return true;

        }


    }



    private void postData(String gender) {
//        Retrofit retrofit=new Retrofit.Builder()
//                .baseUrl("https://easywaygst.theumangsociety.org")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        UserAccountAPI retrofitAPI= retrofit.create(UserAccountAPI.class);

        UserAccountAPI retrofitAPI = UserAccountclient.getClient().create(UserAccountAPI.class);


        //DataModal responseFromAPI = response.body();
        UserAccountDataModel model=new UserAccountDataModel(gender);


        Call<UserAccountDataModel> call=retrofitAPI.createPost(model);

        call.enqueue(new Callback<UserAccountDataModel>() {
            @Override
            public void onResponse(Call<UserAccountDataModel> call, Response<UserAccountDataModel> response) {
                if(response.code()==200) {
                    //showResponse(response.body().toString());
                    String responseString="Response code :" +response.code() ;
                    Log.e("Tag", "post submitted to API." + responseString);
                }

            }

            @Override
            public void onFailure(Call<UserAccountDataModel> call, Throwable t) {
                Log.e("TAG","Response = "+t.toString());
            }
        });


    }



    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if (isChecked) {
            //  txtGender.setText(buttonView.getText().toString());
            if (R.id.radioMale == buttonView.getId()) {
                radioFemale.setChecked(false);
                radioOther.setChecked(false);
                Log.d("male","ooooo");

            } else if (R.id.radioFemale == buttonView.getId()) {
                radioMale.setChecked(false);
                radioOther.setChecked(false);
                Log.d("female","ooooo");

            } else {
                radioFemale.setChecked(false);
                radioMale.setChecked(false);
                Log.d("oth","ooooo");

            }
        }


    }
}