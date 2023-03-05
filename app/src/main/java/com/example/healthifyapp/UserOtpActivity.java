package com.example.healthifyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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
import com.example.healthifyapp.model.MobileOtpDataModel;
import com.example.healthifyapp.model.UserAccountDataModel;
import com.example.healthifyapp.networkconnectivity.NetworkConnectivity;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserOtpActivity extends AppCompatActivity {
    Button btnSubmit;

    EditText edittextotp;

    String mobileno="";
    String otpStr="";
    String otp;
    int userAccountId=0;
    // ArrayList<UserAccountDataModel> userData = new ArrayList<UserAccountDataModel>();
    MobileOtpDataModel mobileOtpDataModel ;
    UserAccountDataModel userAccountDataModel;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_otp);
        btnSubmit = findViewById(R.id.submit1);
        edittextotp = findViewById(R.id.edittextotp1);

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
                   try {
                boolean networkConectivity =  NetworkConnectivity.isConnected(UserOtpActivity.this);
                if (networkConectivity) {

                //  Intent intent = new Intent(OtpActivity.this,FirstActivity.class);
                //startActivity(intent);
                otp = SharedPreference.readSharedSetting(UserOtpActivity.this, "otp", "0");
                Log.d("otp in otpActivity",":"+otp);


//
                OtpData(otpStr,mobileno);

                }
                else {
                    NetworkConnectivity.networkConnetivityShowDialog(UserOtpActivity.this);

                }
            }
                catch (Exception e)
            {
                Log.d("Report:","::::"+e.getMessage());
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
            Call<MobileOtpDataModel> call = retrofitAPI.VerifyUser(mobileno,otp);
            call.enqueue(new Callback<MobileOtpDataModel>() {
                @Override
                public void onResponse(Call <MobileOtpDataModel> call, Response<MobileOtpDataModel> response) {
                    try {


                        if (response != null && response.code() == 200) {
                            String responseString = "Response code :" + response.code();
                            Toast.makeText(UserOtpActivity.this,"Verify Successfully",Toast.LENGTH_SHORT).show();
                            Log.e("TAG","Response =" + responseString);
                            Log.d("gggggggggggggggggggggg", "Response =" + response.body());
                            Gson gson = new Gson();
                            // code updated by ashutosh
                            // getting the json obect of result from api to get id
                            mobileOtpDataModel = response.body();
                            userAccountDataModel = mobileOtpDataModel.getResult();



                            userAccountId = userAccountDataModel.getUserAccountId();
                            SharedPreference.saveSharedSetting(UserOtpActivity.this,"userAccountId",userAccountId);
                            String s1 = gson.toJson(response.body());




                            // MobileOtpDataModel item=response.body();

                            try {
                                // MobileOtpDataModel item=response.body();
                                //   String otptext=item.getOtp();
                                //   edittextotp.setText(otp);

                                Log.e("Response", s1);
                                Log.d("otp", ":" + otp);
                                Log.d("userAccountId", ":" + userAccountId);
                            }
                            catch (Exception e)
                            {
                                Log.d("Report:","::::"+e.getMessage());
                            }
                            Intent intent = new Intent(UserOtpActivity.this, DrawerActivity.class);
                            startActivity(intent);

                        } else if (!response.isSuccessful()) {

                            Toast.makeText(UserOtpActivity.this,"varification Failed",Toast.LENGTH_SHORT).show();
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