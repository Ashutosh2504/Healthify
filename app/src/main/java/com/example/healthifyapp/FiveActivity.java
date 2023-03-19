package com.example.healthifyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.example.healthifyapp.SharedPreferences.SharedPreference;

public class FiveActivity extends AppCompatActivity {

    LinearLayout linearLayout1,linearLayout2;
    LinearLayout linearLayout,linearLayout4;
    View textView;

    EditText editTextcm;
    NumberPicker cmNumberPicker;
    NumberPicker numberPickerFt,numberPickerInch;


    Button buttonft,buttonCm;
    String string1,string2,string3;
    float height;
    String heightUnit;
    int numberPickerValueFt = 0;
    int numberPickerValueInch = 0;
    int  numberPickerValueCm= 0;
    float ftInch =0;
    float Cm=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_five);
        cmNumberPicker = findViewById(R.id.cmnumberpicker);
        numberPickerInch=findViewById(R.id.second_number_inch);
        numberPickerFt =findViewById(R.id.first_number_ft);

        buttonCm =findViewById(R.id.cm);
        buttonft =findViewById(R.id.ft);
        linearLayout=findViewById(R.id.btn_next5);
        linearLayout4=findViewById(R.id.btn_Back5);
        linearLayout2=findViewById(R.id.linearLayout2);
        linearLayout1=findViewById(R.id.linearLayout1);

        heightUnit ="Ft";


        buttonft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linearLayout1.setVisibility(View.VISIBLE);
                linearLayout2.setVisibility(View.GONE);


            }
        });
        numberPickerFt.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                numberPickerValueFt = i1;
            }
        });

        numberPickerInch.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int neww) {
                numberPickerValueInch = neww;
            }
        });


        SharedPreference.saveSharedSetting(FiveActivity.this,"height",ftInch);
        /*if(buttonft.isSelected())
        {
            Log.d("ft btn selected","");

           *//* ftInch=Float.parseFloat(String.valueOf(numberPickerValueFt+numberPickerValueInch *0.1));
            height = ftInch;
            SharedPreference.saveSharedSetting(FiveActivity.this,"height",ftInch);*//*
        }
        else if (buttonCm.isSelected())
        {
            Log.d("cm btn selected","");
           *//* Cm=Float.parseFloat(String.valueOf(numberPickerValueCm));
            SharedPreference.saveSharedSetting(FiveActivity.this,"height",Cm);
            height = Cm;*//*

        }
        else
        {
            Toast.makeText(FiveActivity.this, "Please Select  height"+ftInch+":::"+Cm, Toast.LENGTH_SHORT).show();

        }*/

        buttonCm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                heightUnit = "cm";
                linearLayout1.setVisibility(View.GONE);
                linearLayout2.setVisibility(View.VISIBLE);



            }
        });

        cmNumberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int neww) {
                numberPickerValueCm = neww;
                Cm=Float.parseFloat(String.valueOf(numberPickerValueCm));
            }
        });
//        Cm=Float.parseFloat(String.valueOf(numberPickerValueCm));
//        SharedPreference.saveSharedSetting(FiveActivity.this,"height",Cm);
//        height = Cm;


        //Cm=Float.parseFloat(String.valueOf(numberPickerValueCm));


        if (cmNumberPicker != null) {
            cmNumberPicker.setMinValue(100);
            cmNumberPicker.setMaxValue(200);
            cmNumberPicker.setWrapSelectorWheel(true);

        }


        if (numberPickerInch != null) {
            numberPickerInch.setMinValue(0);//setValue(Integer.parseInt(txtMyText.getText().toString()));
            numberPickerInch.setMaxValue(11);

            numberPickerInch.setWrapSelectorWheel(true);
        }

        if (numberPickerFt != null) {
            numberPickerFt.setMinValue(3);
            numberPickerFt.setMaxValue(8);

            numberPickerFt.setWrapSelectorWheel(true);
        }
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(heightUnit.equalsIgnoreCase("cm"))
                {
                    Cm=Float.parseFloat(String.valueOf(numberPickerValueCm));
                    SharedPreference.saveSharedSetting(FiveActivity.this,"height",Cm);
                    SharedPreference.saveSharedSetting(FiveActivity.this,"height_unit","cm");
                    height = Cm;
                    heightUnit = "cm";
                }
                else
                {
                    ftInch=Float.parseFloat(String.valueOf(numberPickerValueFt+numberPickerValueInch *0.1));
                    height = ftInch;
                    SharedPreference.saveSharedSetting(FiveActivity.this,"height",height);
                    SharedPreference.saveSharedSetting(FiveActivity.this,"height_unit","ft");
                    height = ftInch;
                    heightUnit = "ft";
                }

                SharedPreference.saveSharedSetting(FiveActivity.this,"height",height);

                openActivity(height, heightUnit);
            }
        });
        linearLayout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Intent intent=new Intent(FiveActivity.this,FourthActivity.class);
                //                startActivity(intent);
                onBackPressed();
            }
        });
      /*  textView=findViewById(R.id.textviewdot);



         buttonft=findViewById(R.id.ft);
         button=findViewById(R.id.cm);
     //   String string1=numberPicker1;
       // String string2="textView";
        //String string3=numberPicker;
        // String Concate=string1+string2+string3;
        numberPicker2=findViewById(R.id.second_number);

        int numberPickerValue1 = numberPicker1.getValue();
        int numberPickerValue2 = numberPicker2.getValue();
      float ftInch=Float.parseFloat(String.valueOf(numberPickerValue1+numberPickerValue2));
linearLayout1=findViewById(R.id.linearLayout1);
        linearLayout2=findViewById(R.id.linearLayout2);


        int  numberPickerValueCm= cmNumberPicker.getValue();
      float Cm=Float.parseFloat(String.valueOf(numberPickerValueCm));








        cmNumberPicker=findViewById(R.id.cmnumberpicker);

        if (cmNumberPicker != null) {
            cmNumberPicker.setMinValue(100);
            cmNumberPicker.setMaxValue(200);
            cmNumberPicker.setWrapSelectorWheel(true);

        }


        if (numberPicker2 != null) {
            numberPicker2.setMinValue(0);//setValue(Integer.parseInt(txtMyText.getText().toString()));
            numberPicker2.setMaxValue(11);

            numberPicker1.setWrapSelectorWheel(true);
        }
        numberPicker1 =findViewById(R.id.first_number);
        if (numberPicker1 != null) {
            numberPicker1.setMinValue(3);
            numberPicker1.setMaxValue(8);

            numberPicker1.setWrapSelectorWheel(true);
        }


*/

   /*     buttonft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linearLayout1.setVisibility(View.VISIBLE);
                linearLayout2.setVisibility(View.GONE);


            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                linearLayout1.setVisibility(View.GONE);
                linearLayout2.setVisibility(View.VISIBLE);




            }
        });
*/

    }

    public void openActivity(float height, String heightUnit) {
        String name, mobileno, city, gender, dob;
        name = getIntent().getStringExtra("login_name");
        mobileno = getIntent().getStringExtra("mobile_no");
        city = getIntent().getStringExtra("city");
       // gender = getIntent().getStringExtra("gender");
        dob = getIntent().getStringExtra("dob");



        Intent intent = new Intent(FiveActivity.this, SixActivity.class);
        intent.putExtra("login_name", name).toString();
        intent.putExtra("mobile_no", mobileno);
        intent.putExtra("city", city);
      //  intent.putExtra("gender", gender);
       // intent.putExtra("dob", dob);
        intent.putExtra("height", height);
        intent.putExtra("height_unit", heightUnit);
        //intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }


//    private void postData(float height) {
//        Retrofit retrofit=new Retrofit.Builder()
//                .baseUrl("https://easywaygst.theumangsociety.org")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        UserAccountAPI retrofitAPI= retrofit.create(UserAccountAPI.class);
//        //DataModal responseFromAPI = response.body();
//        UserAccountDataModel model=new UserAccountDataModel(height);
//
//
//        Call<UserAccountDataModel> call=retrofitAPI.createPost(model);
//
//
//
//
//        //    UserAccountAPI apiService = UserAccountclient.getClient().create(UserAccountAPI.class);
//        //   Call<List<model>> call = apiService.getmodels();
//      //  UserAccountDataModel userAccountDataModel=new UserAccountDataModel(apiService);
//        //Call<UserAccountDataModel> call=apiService.createPost(userAccountDataModel);
//        call.enqueue(new Callback<UserAccountDataModel>() {
//            @Override
//            public void onResponse(Call<UserAccountDataModel> call, Response<UserAccountDataModel> response) {
//                if(response.code()==200) {
//                    //showResponse(response.body().toString());
//                    String responseString="Response code :" +response.code() ;
//                    Log.e("Tag", "post submitted to API." + responseString);
//                }
//            //    if (response != null && response.code() == 200){
//             //       String responseString="Response code :" +response.code() ;
//               //     Toast.makeText(FiveActivity.this, "Selected city", Toast.LENGTH_SHORT).show();
//                 //   Log.e("TAG","Response ="+responseString);
//                //}
//                //else if (!response.isSuccessful()){
//
//                  //  Toast.makeText(FiveActivity.this, "Faild", Toast.LENGTH_SHORT).show();
//                    //String responseString="Response code :" +response.code() ;
//                    //Log.e("TAG","Response ="+responseString);
//                //}
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
//    }
}