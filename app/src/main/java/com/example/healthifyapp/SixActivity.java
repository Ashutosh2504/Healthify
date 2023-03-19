package com.example.healthifyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.example.healthifyapp.SharedPreferences.SharedPreference;

public class  SixActivity extends AppCompatActivity {
    LinearLayout linearLayout, linearLayout6;
    NumberPicker numberPicker1, numberPicker2;
    TextView textView;
    float weight;
    String weightUnit = "kg";
    int numberPickerValue1;
    int numberPickerValue2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_six);
        linearLayout = findViewById(R.id.btn_next6);
        linearLayout6 = findViewById(R.id.btn_Back6);
        textView = findViewById(R.id.dotstext);
        numberPicker2 = findViewById(R.id.second_number);
        numberPicker1 = findViewById(R.id.first_number);



        numberPicker1.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                numberPickerValue1 = i1;
            }
        });
        numberPicker2.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                numberPickerValue2 = i1;
            }
        });
        weight=Float.parseFloat(String.valueOf(numberPickerValue1+numberPickerValue2 *0.1));


        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {


                    numberPickerValue1 = numberPicker1.getValue();
                    numberPickerValue2 = numberPicker2.getValue();
                    // weight = Float.parseFloat(String.valueOf(numberPickerValue1 + numberPickerValue2));
                    SharedPreference.saveSharedSetting(SixActivity.this, "weight", weight);
                    SharedPreference.saveSharedSetting(SixActivity.this, "weight_unit", weightUnit);
                    weight = Float.parseFloat(String.valueOf(numberPickerValue1 + numberPickerValue2 * 0.1));

                    openActivity(weight, weightUnit);

                } catch (Exception e) {
                    Log.d("Report:", "::::" + e.getMessage());
                }
            }
        });
        linearLayout6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(SixActivity.this, FiveActivity.class);
                //startActivity(intent);
                onBackPressed();
            }
        });


        if (numberPicker2 != null) {
            numberPicker2.setMinValue(0);
            numberPicker2.setMaxValue(10);
            //  numberPicker.setValue(Integer.parseInt(string2));
            numberPicker2.setWrapSelectorWheel(true);
        }

        if (numberPicker1 != null) {
            numberPicker1.setMinValue(30);
            numberPicker1.setMaxValue(200);
            //numberPicker.setValue(Integer.parseInt((string1)));
            numberPicker1.setWrapSelectorWheel(true);
        }


    }

    public void openActivity(float weight, String weightUnit) {


        String name, mobileno, city, gender, dob, heightUnit;
        float height;
        name = getIntent().getStringExtra("login_name");
        mobileno = getIntent().getStringExtra("mobile_no");
        city = getIntent().getStringExtra("city");
        gender = getIntent().getStringExtra("gender");
        dob = getIntent().getStringExtra("dob");
        //height = Float.parseFloat(getIntent().getStringExtra("height"));
        Bundle bundle = getIntent().getExtras();
        height = bundle.getFloat("height");
        heightUnit = getIntent().getStringExtra("height_unit");


        Intent intent = new Intent(SixActivity.this, SevenActivity.class);
        intent.putExtra("login_name", name).toString();
        intent.putExtra("mobile_no", mobileno);
        intent.putExtra("city", city);
      //  intent.putExtra("gender", gender);
        //intent.putExtra("dob", dob);
        intent.putExtra("height", height);
        intent.putExtra("height_unit", heightUnit);
        intent.putExtra("weight", weight);
        intent.putExtra("weight_unit", weightUnit);
        //intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();

    }

//    private void postData(float weight) {
//        Retrofit retrofit=new Retrofit.Builder()
//                .baseUrl("https://easywaygst.theumangsociety.org")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        UserAccountAPI retrofitAPI= retrofit.create(UserAccountAPI.class);
//        //DataModal responseFromAPI = response.body();
//        UserAccountDataModel model=new UserAccountDataModel(weight);
//
//
//        Call<UserAccountDataModel> call=retrofitAPI.createPost(model);
//
//
//        call.enqueue(new Callback<UserAccountDataModel>() {
//            @Override
//            public void onResponse(Call<UserAccountDataModel> call, Response<UserAccountDataModel> response) {
//
//                if (response.code() == 200){
//                    String responseString="Response code :" +response.code() ;
//                    Toast.makeText(SixActivity.this, "Selected Height", Toast.LENGTH_SHORT).show();
//                    Log.e("TAG","Response ="+responseString);
//                }
//                else if (!response.isSuccessful()){
//
//                    Toast.makeText(SixActivity.this, "", Toast.LENGTH_SHORT).show();
//                    String responseString="Response code :" +response.code() ;
//                    Log.e("TAG","Response ="+responseString);
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<UserAccountDataModel> call, Throwable t) {
//                Log.e("TAG","Response = "+t.toString());
//            }
//        });
//
//
//
//    }
}