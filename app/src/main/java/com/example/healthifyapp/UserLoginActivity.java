package com.example.healthifyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.healthifyapp.SharedPreferences.SharedPreference;
import com.example.healthifyapp.api.MobileotpApi;
import com.example.healthifyapp.model.MobileDataModel;
import com.example.healthifyapp.networkconnectivity.NetworkConnectivity;
import com.google.gson.Gson;
import com.hbb20.CountryCodePicker;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserLoginActivity extends AppCompatActivity {
    EditText editTextmobilenumber;
    CountryCodePicker ccp;

    String MobilePattern = "[0-9]{11}";

    Button btnLogin;
    String mobilePhone;

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
        setContentView(R.layout.activity_user_login);

        btnLogin = findViewById(R.id.btnlogin1);
        editTextmobilenumber = findViewById(R.id.mobilenumber);
        ccp = findViewById(R.id.ccp);
        ccp.registerCarrierNumberEditText(editTextmobilenumber);


        sharedpreferences = getSharedPreferences(SHARED_PREFS, 0);

        // name=sharedpreferences.getString(NAME_KEY,null);
        //  email=sharedpreferences.getString(EMAIL_KEY,null);
        mobilenumber=sharedpreferences.getString(MOBILENUMBER_KEY,null);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    boolean networkConectivity =  NetworkConnectivity.isConnected(UserLoginActivity.this);
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
                    SharedPreference.saveSharedSetting(UserLoginActivity.this,"mobile_no",mobilePhone);

                    if (mobilePhone.matches(MobilePattern)) {
                        //  if(mobilePhone.isEmpty() || mobilePhone.length() < 10){
                        // editTextmobilenumber.setError("Phone number is not valid");
                        editTextmobilenumber.requestFocus();
                        return;
                    }
                    LoginData(mobilePhone);

                    }
                    else {
                        NetworkConnectivity.networkConnetivityShowDialog(UserLoginActivity.this);

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
                            Toast.makeText(UserLoginActivity.this, "Send otp Success", Toast.LENGTH_SHORT).show();
                            Log.e("TAG", "Response =" + responseString);
                            Gson gson = new Gson();
                            String s1 = gson.toJson(response.body());
                            Log.e("Response", s1);

                            MobileDataModel jsonResponse = response.body();
//                    Log.e("Response OTP",jsonResponse.getResult().toString());
                            String otp = jsonResponse.getResult();
                            SharedPreference.saveSharedSetting(UserLoginActivity.this,"otp", otp);
                            Intent intent = new Intent(UserLoginActivity.this, UserOtpActivity.class);
                            intent.putExtra("mobile_no", mobileno);
                            intent.putExtra("otp", otp);
                            startActivity(intent);

                        } else if (!response.isSuccessful()) {

                            Toast.makeText(UserLoginActivity.this, "Failed", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(UserLoginActivity.this, "", Toast.LENGTH_SHORT).show();

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