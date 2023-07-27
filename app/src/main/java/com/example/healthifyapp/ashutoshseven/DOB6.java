package com.example.healthifyapp.ashutoshseven;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.healthifyapp.FiveActivity;
import com.example.healthifyapp.FourthActivity;
import com.example.healthifyapp.R;
import com.example.healthifyapp.SharedPreferences.SharedPreference;
import com.example.healthifyapp.ThirdActivity;
import com.example.healthifyapp.api.UserAccountAPI;
import com.example.healthifyapp.model.UserAccountDataModel;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DOB6 extends AppCompatActivity {

    DatePicker datePicker;
    LinearLayout next,back;
    String date;
    int day;
    int month;
    int year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        datePicker=findViewById(R.id.datePicker);
        next=findViewById(R.id.btn_next4);
        back=findViewById(R.id.btn_Back4);

//        int day = datePicker.getDayOfMonth();
//        int month =  (datePicker.getMonth() + 1);
//        int year =  datePicker.getYear();



        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               if(!validateAge()){

                    return;
                }
                 else
              {
                     day = datePicker.getDayOfMonth();
                     month =  (datePicker.getMonth() + 1);
                     year =  datePicker.getYear();
                    date= day+"/"+month+"/"+year;
                    SharedPreference.saveSharedSetting(DOB6.this,"dob",date);

                    openActivity(date);
                }

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DOB6.this, Gender5.class);
                startActivity(intent);
            }
        });


    }

    private boolean validateAge() {

        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int userAge=datePicker.getYear();
        int isAgeValid=currentYear-userAge;
        if(isAgeValid<1){

            return false;
        }else {
            return true;
        }
    }

    public void openActivity(String dob) {
        String name, mobileno, city, gender;
        name = getIntent().getStringExtra("login_name");
        mobileno = getIntent().getStringExtra("mobile_no");
        city = getIntent().getStringExtra("city");
       // gender = getIntent().getStringExtra("gender");
        gender = SharedPreference.readSharedSetting(DOB6.this,"gender","");


        Intent intent = new Intent(DOB6.this, FiveActivity.class);
        intent.putExtra("login_name", name).toString();
        intent.putExtra("mobile_no", mobileno);
        intent.putExtra("city", city);

        intent.putExtra("gender", gender);
        intent.putExtra("dob", dob);

        //intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }
}