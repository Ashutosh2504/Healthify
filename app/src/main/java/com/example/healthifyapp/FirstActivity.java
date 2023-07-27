package com.example.healthifyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healthifyapp.SharedPreferences.SharedPreference;
import com.example.healthifyapp.api.UserAccountAPI;
import com.example.healthifyapp.model.UserAccountDataModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FirstActivity extends AppCompatActivity {
    TextView textView;
    EditText editTextName;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

    textView =findViewById(R.id.next);
       editTextName =findViewById(R.id.name);
        name = editTextName.getText().toString();
        Log.d("name",""+name);
       // postData(editTextName.getText().toString());
       textView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
//               if(name.isEmpty()){
//                   Toast.makeText(FirstActivity.this,"Please enter name", Toast.LENGTH_SHORT).show();
//
//
//                   return;
//               }
               SharedPreference.saveSharedSetting(FirstActivity.this,"login_name",editTextName.getText().toString());
               Intent intent=new Intent(FirstActivity.this,SecondActivity.class);
               startActivity(intent);
           }
       });


    }

    private void postData(String userName) {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://easywaygst.theumangsociety.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UserAccountAPI retrofitAPI= retrofit.create(UserAccountAPI.class);

        UserAccountDataModel model=new UserAccountDataModel(userName);
       // model.setMobileNo("+917083490012");
       // model.setUserName("Rahul");


        Call<UserAccountDataModel> call=retrofitAPI.createPost(model);



        call.enqueue(new Callback<UserAccountDataModel>() {
            @Override
            public void onResponse(Call<UserAccountDataModel> call, Response<UserAccountDataModel> response) {

                if(response.code()==200) {

                    String responseString="Response code :" +response.code() ;
                    //showResponse(response.body().toString());
                    Toast.makeText(FirstActivity.this, " Success", Toast.LENGTH_SHORT).show();
                    Log.e("Tag", "post submitted to API." + responseString);



                }else {
                    String responseString="Response code :" +response.code() ;
                    Log.e("TAG","Response ="+responseString);
                    Toast.makeText(FirstActivity.this, "", Toast.LENGTH_SHORT).show();
                }



            }

            @Override
            public void onFailure(Call<UserAccountDataModel> call, Throwable t) {
                Log.e("TAG","Response = "+t.toString());
            }
        });


    }
}