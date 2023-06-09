package com.example.healthifyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healthifyapp.SharedPreferences.SharedPreference;
import com.example.healthifyapp.api.MedicalConditionApi;
import com.example.healthifyapp.api.MobileotpApi;
import com.example.healthifyapp.model.MedicalConditionDataModel;
import com.example.healthifyapp.model.MobileDataModel;
import com.example.healthifyapp.networkconnectivity.NetworkConnectivity;
import com.example.healthifyapp.report.LunchPrimaryAnalysisReport;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.hbb20.CountryCodePicker;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    EditText editTextmobilenumber;
    CountryCodePicker ccp;

    String MobilePattern = "[0-9]{11}";

    Button btnLogin;
    String mobilePhone;
    TextView loginaccount;
    public static  String SHARED_PREFS = "shared_prefs";
    public static final String NAME_KEY = "name_key";
    public static final String EMAIL_KEY = "email_key";
    public static final String MOBILENUMBER_KEY = "mobilenumber_key";
    SharedPreferences sharedpreferences;
    String name,email,mobilenumber;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.btnlogin);
        editTextmobilenumber = findViewById(R.id.mobilenumber);
        ccp = findViewById(R.id.ccp);
        ccp.registerCarrierNumberEditText(editTextmobilenumber);
        loginaccount=findViewById(R.id.loginAccount);


        sharedpreferences = getSharedPreferences(SHARED_PREFS, 0);

       // name=sharedpreferences.getString(NAME_KEY,null);
      //  email=sharedpreferences.getString(EMAIL_KEY,null);
        mobilenumber=sharedpreferences.getString(MOBILENUMBER_KEY,null);

         loginaccount.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent(LoginActivity.this, UserLoginActivity.class);
                 startActivity(intent);
             }
         });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    boolean networkConectivity =  NetworkConnectivity.isConnected(LoginActivity.this);
                    if (networkConectivity) {
                    SharedPreferences sharedPreferences=getSharedPreferences(LoginActivity.SHARED_PREFS,0);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                  //  editor.putString(NAME_KEY, name_edt.getText().toString());
                    //editor.putString(EMAIL_KEY, email_edt.getText().toString());
                    editor.putString(MOBILENUMBER_KEY,editTextmobilenumber.getText().toString());

                    editor.putBoolean("hasLoggedIn",true);
                    editor.commit();

                // Intent intent=new Intent(LoginActivity.this,OtpActivity.class);
                //startActivity(intent);
                mobilePhone = editTextmobilenumber.getText().toString().trim();
                Log.d("Mo.",""+mobilePhone);
                SharedPreference.saveSharedSetting(LoginActivity.this,"mobile_no",mobilePhone);

                if (mobilePhone.matches(MobilePattern)) {
                    //  if(mobilePhone.isEmpty() || mobilePhone.length() < 10){
                    // editTextmobilenumber.setError("Phone number is not valid");
                    editTextmobilenumber.requestFocus();
                    return;
                }
                LoginData(mobilePhone);

                    }
                    else {
                        NetworkConnectivity.networkConnetivityShowDialog(LoginActivity.this);

                    }
                }
                catch (Exception e)
                {
                    Log.d("Report:","::::"+e.getMessage());
                }


            //    NotificationManager notif=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

            }

        });

    }


    private void LoginData(String mobileno) {
        // String mobileno = editTextmobilenumber.getText().toString();
   try {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://easywaygst.theumangsociety.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MobileotpApi retrofitAPI = retrofit.create(MobileotpApi.class);
        //JSONObject mobileDataModel=new JSONObject();
        Call<MobileDataModel> call = retrofitAPI.createPost(mobileno);
        call.enqueue(new Callback<MobileDataModel>() {
            @Override
            public void onResponse(Call<MobileDataModel> call, Response<MobileDataModel> response) {
                try{


                if (response != null && response.code() == 200) {
                    String responseString = "Response code :" + response.code();
                    Toast.makeText(LoginActivity.this, "Send otp Success", Toast.LENGTH_SHORT).show();
                    Log.e("TAG", "Response =" + responseString);
                    Gson gson = new Gson();
                    String s1 = gson.toJson(response.body());
                    Log.e("Response", s1);

                    MobileDataModel jsonResponse = response.body();
//                    Log.e("Response OTP",jsonResponse.getResult().toString());
                    String otp = jsonResponse.getResult();
                    SharedPreference.saveSharedSetting(LoginActivity.this,"otp", otp);
                    Intent intent = new Intent(LoginActivity.this, OtpActivity.class);
                    intent.putExtra("mobile_no", mobileno);
                    intent.putExtra("otp", otp);
                    startActivity(intent);

                } else if (!response.isSuccessful()) {

                    String responseString = "Response code :" + response.code();
                    Log.e("TAG", "Response =" + responseString);
                }

//                Log.e("TAG","Response ="+response);

                }  catch (Exception e)
                {
                    Log.d("Report:","::::"+e.getMessage());
                }

                }


            @Override
            public void onFailure(Call<MobileDataModel> call, Throwable t) {

            }
        });
   }
   catch (Exception e)
   {
       Log.d("Report:","::::"+e.getMessage());
   }


    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }
}