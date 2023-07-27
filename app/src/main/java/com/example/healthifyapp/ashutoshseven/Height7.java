package com.example.healthifyapp.ashutoshseven;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.healthifyapp.FourthActivity;
import com.example.healthifyapp.R;
import com.example.healthifyapp.SixActivity;
import com.example.healthifyapp.api.UserAccountAPI;
import com.example.healthifyapp.model.UserAccountDataModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Height7 extends AppCompatActivity {

    LinearLayout linearLayout1,linearLayout2;
    LinearLayout nextBtn,backBtn;
    TextView textView;

    EditText editTextcm;
    NumberPicker cmNumberPicker;
    NumberPicker heightCM;
    NumberPicker  numberPicker1,numberPicker;
    NumberPicker  InchNumberPicker,FtnumberPicker;
    Button buttonFT,buttonCM;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_five);
        textView=findViewById(R.id.textviewdot);
        linearLayout1=findViewById(R.id.linearLayout1);
        linearLayout2=findViewById(R.id.linearLayout2);

        numberPicker =findViewById(R.id.first_number_ft);
        numberPicker1 =findViewById(R.id.second_number_inch);
         buttonFT=findViewById(R.id.ft);
         buttonCM=findViewById(R.id.cm);
        String string1=numberPicker1.toString();
        String string2=textView.toString();
       // String string3=numberPicker2.toString();


        String Concate=string1+string2;

        nextBtn=findViewById(R.id.btn_next5);
        backBtn=findViewById(R.id.btn_Back5);



        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("heightt",":"+Concate);
                Intent intent=new Intent(Height7.this, SixActivity.class);
                startActivity(intent);
                //postData(Concate.toString());
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Height7.this, DOB6.class);
                startActivity(intent);
            }
        });

        heightCM=findViewById(R.id.cmnumberpicker);

        if (cmNumberPicker != null) {
            cmNumberPicker.setMinValue(100);
            cmNumberPicker.setMaxValue(200);
            cmNumberPicker.setWrapSelectorWheel(true);
        }

        InchNumberPicker=findViewById(R.id.second_number);

        if (InchNumberPicker != null) {
            InchNumberPicker.setMinValue(0);
            InchNumberPicker.setMaxValue(11);
            InchNumberPicker.setWrapSelectorWheel(true);
        }
        FtnumberPicker=findViewById(R.id.first_number);

        if (FtnumberPicker != null) {
            FtnumberPicker.setMinValue(3);
            FtnumberPicker.setMaxValue(8);
            FtnumberPicker.setWrapSelectorWheel(true);
        }



        buttonFT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linearLayout1.setVisibility(View.VISIBLE);
                linearLayout2.setVisibility(View.GONE);


            }
        });
        buttonCM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                linearLayout1.setVisibility(View.GONE);
                linearLayout2.setVisibility(View.VISIBLE);




            }
        });


    }





}