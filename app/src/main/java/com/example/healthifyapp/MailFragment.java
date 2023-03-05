package com.example.healthifyapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healthifyapp.SharedPreferences.SharedPreference;
import com.example.healthifyapp.api.MobileotpApi;
import com.example.healthifyapp.api.UserAccountAPI;
import com.example.healthifyapp.model.MobileDataModel;
import com.example.healthifyapp.model.MobileOtpDataModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.core.Tag;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MailFragment extends Fragment {
    TextView tokenString;
    //   private static final String TAG = ;
    private String tokenn = "";
    private String mobileno = "";
    EditText tokenedit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_mail, container, false);
        tokenString = (TextView) view.findViewById(R.id.token);
        mobileno = SharedPreference.readSharedSetting(getActivity(), "mobile_no", "0");
        tokenedit = (EditText)view.findViewById(R.id.token_edit);
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                if (task.isSuccessful()) {
                    tokenn = task.getResult();
                    tokenString.setText(tokenn);
                    tokenedit.setText(tokenn);
                    update(mobileno, tokenn);
                    Toast.makeText(getActivity(), "Token generated:" + tokenn, Toast.LENGTH_SHORT).show();
                    Log.d("Token", "Token:" + tokenn);
                }
            }
        });
        return view;
    }
   /* @Override
    public void onCreate(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         = inflater.inflate(R.layout.fragment_home_fragments, container, false);


        *//*FirebaseMessaging.getInstance().subscribeToTopic("health")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "Subscribed";
                        if (!task.isSuccessful()) {
                            msg = "Subscribe failed";
                        }
                       // Log.d(Tag,msg);
                         // Log.d(TAG, msg);
                          //Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
*//*

    }*/


    public void update(String mobile, String token) {
        try {
            Retrofit retrofit = new Retrofit.Builder().baseUrl("https://easywaygst.theumangsociety.org").addConverterFactory(GsonConverterFactory.create()).build();
            MobileotpApi retrofitAPI = retrofit.create(MobileotpApi.class);
            //  JSONObject mobileDataModel=new JSONObject();
            Call<MobileDataModel> call = retrofitAPI.updateToken(mobile, token);
            call.enqueue(new Callback<MobileDataModel>() {
                @Override
                public void onResponse(Call<MobileDataModel> call, Response<MobileDataModel> response) {

                   try{
                    if (response != null && response.code() == 200) {
                        String responseString = "Response code :" + response.code();
                        tokenString.setText(response.body().getResult());
                        //tokenedit.setText(response.body().getResult());
                        Log.d("Messagee",""+response.body().getResult());
                      //  Toast.makeText(getContext(), "Updated Token Successfully", Toast.LENGTH_SHORT).show();
                    } else {
                      //  Toast.makeText(getContext(), " Token Failed", Toast.LENGTH_SHORT).show();

                    }

                   } catch (Exception e) {

                   }
                }

                @Override
                public void onFailure(Call<MobileDataModel> call, Throwable t) {
                    Toast.makeText(getContext(), " No Response from Server", Toast.LENGTH_SHORT).show();

                }
            });

        } catch (Exception e) {

        }
    }
}