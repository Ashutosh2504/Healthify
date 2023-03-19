
package com.example.healthifyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.healthifyapp.SharedPreferences.SharedPreference;
import com.example.healthifyapp.api.MobileotpApi;
import com.example.healthifyapp.ashutoshseven.Name3;
import com.example.healthifyapp.model.LifeStyleItemDataModel;
import com.example.healthifyapp.model.MobileOtpDataModel;
import com.example.healthifyapp.model.UserAccountDataModel;
import com.example.healthifyapp.networkconnectivity.NetworkConnectivity;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OtpActivity extends AppCompatActivity {
    Button btnSubmit;

    EditText edittextotp;

    String mobileno="";
    String otpStr="";
    String otp;
    int userAccountId=0;
   // ArrayList<UserAccountDataModel> userData = new ArrayList<UserAccountDataModel>();
    MobileOtpDataModel mobileOtpDataModel ;
    UserAccountDataModel userAccountDataModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        btnSubmit = findViewById(R.id.submit);
        edittextotp = findViewById(R.id.edittextotp);

        mobileno = getIntent().getStringExtra("mobile_no");
        otpStr = getIntent().getStringExtra("otp");
        edittextotp.setText(otpStr);
       // mobileOtpDataModel = new MobileOtpDataModel(); comment line rahul
        userAccountDataModel = new UserAccountDataModel();
        SharedPreference.saveSharedSetting(this,"mobile_no",mobileno);

//        Log.v("Mobile: ",mobileno);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean networkConectivity =  NetworkConnectivity.isConnected(OtpActivity.this);
                if (networkConectivity) {

                otp = SharedPreference.readSharedSetting(OtpActivity.this, "otp", "0");


//
                OtpData(otpStr,mobileno);

                }
                else {
                    NetworkConnectivity.networkConnetivityShowDialog(OtpActivity.this);

                }
            }
        });

    }

    private void OtpData(String otp,String mobileno) {
        //  String mobileno = editTextmobilenumber.getText().toString();
        try {
            Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://easywaygst.theumangsociety.org")
                .addConverterFactory(GsonConverterFactory.create()).build();

        MobileotpApi retrofitAPI = retrofit.create(MobileotpApi.class);
        //  JSONObject mobileDataModel=new JSONObject();
        Call <MobileOtpDataModel> call = retrofitAPI.VerifyUser(mobileno,otp);
        call.enqueue(new Callback <MobileOtpDataModel>() {
            @Override
            public void onResponse(Call <MobileOtpDataModel> call,Response
                    <MobileOtpDataModel> response) {
              try {


                if (response != null && response.code() == 200) {
                    String responseString = "Response code :" + response.code();
                    Toast.makeText(OtpActivity.this,"Verify Successfully",
                            Toast.LENGTH_SHORT).show();
                    Log.e("TAG","Response =" + responseString);
                    Log.d("gggggggggggggggggggggg", "Response =" + response.body());
                    Gson gson = new Gson();

                    // code updated by ashutosh
                    // getting the json obect of result from api to get id
                    mobileOtpDataModel = response.body();
                    userAccountDataModel = mobileOtpDataModel.getResult();
                    userAccountId = userAccountDataModel.getUserAccountId();
                    SharedPreference.saveSharedSetting(OtpActivity.this,"userAccountId",
                            userAccountId);
                    String s1 = gson.toJson(response.body());


                    // MobileOtpDataModel item=response.body();

                    try {
                       // MobileOtpDataModel item=response.body();
                      //   String otptext=item.getOtp();
                     //   edittextotp.setText(otp);


                    }
                    catch (Exception e)
                    {
                        Log.d("Report:","::::"+e.getMessage());
                    }
                    Intent intent = new Intent(OtpActivity.this,Name3.class);
                    startActivity(intent);

                } else if (!response.isSuccessful()) {

                    Toast.makeText(OtpActivity.this,"varification Failed",Toast.LENGTH_SHORT).show();
                    String responseString = "Response code :" + response.code();
                    Log.e("TAG","Response =" + responseString);
                }
                Log.e("TAG","Response =" + response);

              }  catch (Exception e)
              {
                  Log.d("Report:","::::"+e.getMessage());
              }

            }

            @Override
            public void onFailure(Call <MobileOtpDataModel> call,Throwable t) {

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

