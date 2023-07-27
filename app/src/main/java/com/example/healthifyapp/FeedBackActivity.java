package com.example.healthifyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.healthifyapp.SharedPreferences.SharedPreference;
import com.example.healthifyapp.api.FeedBackApi;
import com.example.healthifyapp.model.FeedBackModel;
import com.example.healthifyapp.model.RatingModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FeedBackActivity extends AppCompatActivity {

    RatingBar ratingBar;
    Button submit;
    EditText editText;
    int  id=0;
    String mobilno = "";
    int UserId = 0;
    String patientID="0";
    String feedback="good";
    String rating="0";
    String createdDate="0";
    FeedBackModel feedBackModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        ratingBar=findViewById(R.id.ratingBar);
        submit=findViewById(R.id.submit);
        editText=findViewById(R.id.chatLine);
     //   float ratings=ratingBar.getRating();

        id = SharedPreference.readSharedSetting(this, "userAccountId", id);


       // String rating=String.valueOf(ratings);

       //  feedback=editText.getText().toString();



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postFeedBack(id,patientID,rating ,feedback);
            }
        });
    }

    private void postFeedBack(int id, String patientID, String rating, String feedback) {
        try {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://easywaygst.theumangsociety.org")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            FeedBackApi retrofitAPI = retrofit.create(FeedBackApi.class);

            RatingModel model = new RatingModel(id,patientID,rating,feedback);
            Call<FeedBackModel> call = retrofitAPI.createPost(model);

            call.enqueue(new Callback<FeedBackModel>() {
                @Override
                public void onResponse(Call<FeedBackModel> call, Response<FeedBackModel> response) {
                    try {

                    if (response.code() == 200) {

                        String responseString = "Response code :" + response.code();
                        Log.d("Response", "" + response.body());
                        Log.d("Success", "" + response.toString());

                        feedBackModel=response.body();
                        Toast.makeText(FeedBackActivity.this, " FeedBack Submitted Successfully", Toast.LENGTH_SHORT).show();
                        Log.d("Tag", "post submitted to API." + responseString);

                    } else {
                        String responseString = "Response code :" + response.code();
                        Log.e("TAG", "Response =" + responseString);

                           Toast.makeText(FeedBackActivity.this, "Faild",Toast.LENGTH_SHORT).show();
                    }
                }  catch (Exception e)
                {
                    Log.d("ReportRahul:","::::"+e.getMessage());
                }
                }

                @Override
                public void onFailure(Call<FeedBackModel> call, Throwable t) {
                    Log.e("TAG", "Response =" + t);
                    Log.d("ReportRahul:","::::"+t.getMessage());

                }
            });

        } catch (Exception e){
            Log.e("TAG", "Response =" + e);

            Log.d("ReportRahul:","::::"+e.getMessage());

        }
    }
}