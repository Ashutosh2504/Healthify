package com.example.healthifyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.healthifyapp.api.UserAccountAPI;
import com.example.healthifyapp.model.UserAccountDataModel;

import java.time.Month;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FourthActivity extends AppCompatActivity {

    DatePicker datePicker;
    LinearLayout next,back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        datePicker=findViewById(R.id.datePicker);
        next=findViewById(R.id.btn_next4);
        back=findViewById(R.id.btn_Back4);

        int day = datePicker.getDayOfMonth();
        int month =  (datePicker.getMonth() + 1);
        int year =  datePicker.getYear();

        String date= day+"/"+month+"/"+year;


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                      // if(!validateAge()){

                        //   return;
                      // }



                //String string1="day";
                //String string2="month";
                //String string3="year";

                //String Concate=string1+string2+string3;

                    // display the values by using a toast
                    //Toast.makeText(getApplicationContext(), day + "\n" + month + "\n" + year, Toast.LENGTH_LONG).show();

               // postData(date.toString());
                    Intent intent = new Intent(FourthActivity.this, FiveActivity.class);
                    startActivity(intent);

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FourthActivity.this,ThirdActivity.class);
                startActivity(intent);
            }
        });


    }

    private boolean validateAge() {

        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int userAge=datePicker.getYear();
        int isAgeValid=currentYear-userAge;
        //14
        if(isAgeValid<1){

            return false;
        }else {
            return true;
        }
    }

    private void postData(String dob) {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://easywaygst.theumangsociety.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UserAccountAPI retrofitAPI= retrofit.create(UserAccountAPI.class);

        UserAccountDataModel model=new UserAccountDataModel(dob);


        Call<UserAccountDataModel> call=retrofitAPI.createPost(model);




        call.enqueue(new Callback<UserAccountDataModel>() {
            @Override
            public void onResponse(Call<UserAccountDataModel> call, Response<UserAccountDataModel> response) {
                if(response.code()==200) {
                    //showResponse(response.body().toString());
                    String responseString="Response code :" +response.code() ;
                    Log.e("Tag", "post submitted to API." + responseString);
                }
                //if (response != null && response.code() == 200){
                  //  String responseString="Response code :" +response.code() ;
                    //Toast.makeText(FourthActivity.this, "Selected city", Toast.LENGTH_SHORT).show();
                   // Log.e("TAG","Response ="+responseString);
                //}
                //else if (!response.isSuccessful()){

                  //  Toast.makeText(FourthActivity.this, "Faild", Toast.LENGTH_SHORT).show();
                    //String responseString="Response code :" +response.code() ;
                    //Log.e("TAG","Response ="+responseString);
                //}

            }

            @Override
            public void onFailure(Call<UserAccountDataModel> call, Throwable t) {
                Log.e("TAG","Response = "+t.toString());
            }
        });



    }
}