package com.example.healthifyapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterViewFlipper;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;


import com.example.healthifyapp.SecondaryAnalysisDiet.SecondaryAnalysisdietActivity;
import com.example.healthifyapp.SharedPreferences.SharedPreference;

import com.example.healthifyapp.adapter.BannerAdapter;
import com.example.healthifyapp.api.BannerApi;
import com.example.healthifyapp.api.HelathCalculateApi;
import com.example.healthifyapp.api.MobileotpApi;
import com.example.healthifyapp.api.UserAccountAPI;
import com.example.healthifyapp.api.WaterTrackerApi;
import com.example.healthifyapp.model.AnalysisStatusModel;
import com.example.healthifyapp.model.AnalysisStatusResponseModel;
import com.example.healthifyapp.model.MobileDataModel;
import com.example.healthifyapp.model.apiclient.UserAccountclient;
import com.example.healthifyapp.model.BannerDataModel;
import com.example.healthifyapp.model.HealthCalculationModel;
import com.example.healthifyapp.model.MobileOtpDataModel;
import com.example.healthifyapp.model.UserAccountDataModel;
import com.example.healthifyapp.networkconnectivity.NetworkConnectivity;
import com.example.healthifyapp.tertiaryAnalysisDiet.TertiaryAnalysisDietActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class HomeFragments extends Fragment {

    TextView tokenString;
    //   private static final String TAG = ;
    private String tokenn = "";
    private String mobileno = "";
    EditText tokenedit;
    //NOTIFICATION CODE


    String mobilno = "";
    int UserId = 0, userAccountId=0;
    int userAccountIds=1;
    ViewFlipper viewFlipper;
    TextView txt_username;
    ProgressBar progressBar;
    CardView txtView_PrimaryAnalysis;
    CardView secondaryAnalysis_btn;
    CardView tertiaryAnalysis_btn;
    TextView textView_bmi, textView_bfp, textView_ibw, textView_lbw, textView_bsa;
    String secondaryAnalysis=" ";
    String tertiaryAnalysis=" ";
    String primaryAnalysis=" ";
    String bmi;
    HealthCalculationModel healthCalculationModel;
 // private ArrayList<BannerDataModel> bannerDataModel;
    List<BannerDataModel> modelList;
  //  List<UserFamilyModel> modelList;
    SharedPreference prefs;
    MobileOtpDataModel mobileOtpDataModel;
    UserAccountDataModel userAccountDataModel;

    AnalysisStatusModel analysisStatusModel;
    AnalysisStatusResponseModel analysisStatusResponseModel;
    private AdapterViewFlipper adapterViewFlipper;

    String name;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_home_fragments, container, false);
        modelList = new ArrayList<>();
        modelList=new ArrayList<>();
        progressBar=view.findViewById(R.id.pb);



        adapterViewFlipper = view.findViewById(R.id.adapterViewFlipper);

        getBanner();

         dialogs(false);
        mobilno = SharedPreference.readSharedSetting(getActivity(), "mobile_no", "");
         userAccountId= SharedPreference.readSharedSetting(getActivity(), "userAccountId", userAccountId);

               getAnalysisStatus(userAccountId);
        // creating object for healthcalculationmodel  .
        //healthCalculationModel = new HealthCalculationModel();

        //notification code

        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                if (task.isSuccessful()) {
                    tokenn = task.getResult();
                   // tokenString.setText(tokenn);
                   // tokenedit.setText(tokenn);
                    update(mobileno, tokenn);
                    //Toast.makeText(getActivity(), "Token generated:" + tokenn, Toast.LENGTH_SHORT).show();
                    Log.d("Token", "Token:" + tokenn);
                }
            }
        });



        try {
            UserId = Integer.parseInt(mobilno);
        } catch (NumberFormatException nfe) {
            // Handle the condition when str is not a number.
        }


        txt_username = view.findViewById(R.id.txt_username);
//        textView_bmi.setText(""+ SharedPreference.readSharedSetting(getActivity(),"Id",""));
      // txt_username.setText("Dear " + SharedPreference.readSharedSetting(getActivity(), "login_name", ""));
        textView_bsa = view.findViewById(R.id.bsa);

        textView_bfp = view.findViewById(R.id.bfp);
        textView_bmi = view.findViewById(R.id.bmi);
        textView_lbw = view.findViewById(R.id.lbw);
        textView_ibw = view.findViewById(R.id.ibw);
        viewFlipper = view.findViewById(R.id.filpeer);

        // bmi = SharedPreference.readSharedSetting(getContext(), "bmi", "");
        //textView_bmi.setText(Integer.parseInt(bmi));
        // getBmiData();
        txtView_PrimaryAnalysis = view.findViewById(R.id.arrowImage);
        secondaryAnalysis_btn=view.findViewById(R.id.arrowImagesecondary);
        tertiaryAnalysis_btn=view.findViewById(R.id.arrowImagetertiary);
       // ImageButton  secondaryAnalysis = view.findViewById(R.id.arrowImagesecondary);
        secondaryAnalysis_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getContext(), SecondaryAnalysisdietActivity.class);
                startActivity(intent1);
            }
        });
       // ImageButton  tertiaryAnalysis = view.findViewById(R.id.arrowImagetertiary);
        tertiaryAnalysis_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getContext(), TertiaryAnalysisDietActivity.class);
                startActivity(intent1);

            }
        });




        try {


            SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm");

        //    Date resultdate = new Date(Long.parseLong(prefs.getString("")));
            Log.v("current date ",","+sdf.format(new Date().getTime()));
          //  long diff = new Date().getTime()-resultdate;
           // long diffMinutes = (diff / 1000) / 60;
            //long diffMinutes = (TimeUnit.MILLISECONDS.toMinutes(diff));
            //Log.v("Difference in minutes is ",","+diffMinutes);

            //if(1440 < diffMinutes)
            //{
              //  txtView_PrimaryAnalysis.setVisibility(View.GONE);

            //}
        } catch (Exception e) {
            e.printStackTrace();

        }
        int minutes = new Time(System.currentTimeMillis()).getMinutes();
        if(minutes == 1){


        }else{


        }

      //  txtView_PrimaryAnalysis.setOnClickListener(new View.OnClickListener() {
        //    @Override
          //  public void onClick(View view) {




            //}
       // });



        getData(userAccountId);
        getBmiData(userAccountId);
        getBFPData(userAccountId);
        getBSAData(userAccountId);
        getIBWData(userAccountId);
        getLBWData(userAccountId);
        //ImageButton imageButton = view.findViewById(R.id.arrowImage);
        txtView_PrimaryAnalysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                customAlert();
            }
        });

        return view;

    }

    private void getAnalysisStatus(int userAccountId) {

        try {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://easywaygst.theumangsociety.org")
                    .addConverterFactory(GsonConverterFactory.create()).build();
            WaterTrackerApi retrofitAPI = retrofit.create(WaterTrackerApi.class);

            Call<AnalysisStatusModel> call = retrofitAPI.getAnalysisStatus(userAccountId);
            call.enqueue(new Callback<AnalysisStatusModel>() {
                @Override
                public void onResponse(Call<AnalysisStatusModel> call, Response<AnalysisStatusModel> response) {
                    try {
                        if (response != null && response.code() == 200) {
                            String responseString = "Response code :" + response.code();
                           // Toast.makeText(getContext(), "Analysis Status ", Toast.LENGTH_SHORT).show();
                            Log.e("TAG", "Response =" + responseString);
                            Gson gson = new Gson();



                            analysisStatusResponseModel = new AnalysisStatusResponseModel();
                            analysisStatusModel = response.body();
                            analysisStatusResponseModel = analysisStatusModel.getResult();


                            primaryAnalysis=String.valueOf(analysisStatusResponseModel.isPrimaryAnalysis());
                            secondaryAnalysis=String.valueOf(analysisStatusResponseModel.isSecondryAnalysis());
                            tertiaryAnalysis=String.valueOf(analysisStatusResponseModel.isTertiaryAnalysis());



                           if (primaryAnalysis.equalsIgnoreCase("true")) {

                               //  txtView_PrimaryAnalysis.setVisibility(View.VISIBLE);
                             } else  if (primaryAnalysis.equalsIgnoreCase("false")){

                                 //txtView_PrimaryAnalysis.setVisibility(View.GONE);

                           }



                            if (secondaryAnalysis.equalsIgnoreCase("true")) {

                                 // secondaryAnalysis_btn.setVisibility(View.VISIBLE);

                            } else if (secondaryAnalysis.equalsIgnoreCase("false")){

                                   //secondaryAnalysis_btn.setVisibility(View.GONE);

                            }

                            if (tertiaryAnalysis.equalsIgnoreCase("true")) {
                                // tertiaryAnalysis_btn.setVisibility(View.VISIBLE);

                            } else if (tertiaryAnalysis.equalsIgnoreCase("false")){
                                //tertiaryAnalysis_btn.setVisibility(View.GONE);

                            }


                            String s1 = gson.toJson(response.body());
                            Log.e("Response", s1);
                            Log.d("Analysis Status", ":" + analysisStatusModel.toString());
                            Log.d("Analysis Status", "Response =" + analysisStatusResponseModel.toString());

                        } else if (!response.isSuccessful()) {

                            Toast.makeText(getActivity(), "Analysis  Failed", Toast.LENGTH_SHORT).show();
                            String responseString = "Response code :" + response.code();
                            Log.e("TAG", "Response =" + responseString);
                        }

                    } catch (Exception e) {

                    }
                    Log.e("TAG", "Response =" + response);

                }

                @Override
                public void onFailure(Call<AnalysisStatusModel> call, Throwable t) {

                }
            });





        }
        catch (Exception e)
        {
            Log.d("Report:","::::"+e.getMessage());
        }


    }

    private void getData(int userAccountId) {
        try {

            Retrofit retrofit = new Retrofit.Builder().baseUrl("https://easywaygst.theumangsociety.org").addConverterFactory(GsonConverterFactory.create()).build();
            UserAccountAPI retrofitAPI = retrofit.create(UserAccountAPI.class);
            //  JSONObject mobileDataModel=new JSONObject();
            Call<MobileOtpDataModel> call = retrofitAPI.getUserDetails(userAccountId);
            call.enqueue(new Callback<MobileOtpDataModel>() {
                @Override
                public void onResponse(Call<MobileOtpDataModel> call, Response<MobileOtpDataModel> response) {
                    try {
                        if (response != null && response.code() == 200) {
                            String responseString = "Response code :" + response.code();
                            Toast.makeText(getContext(), "Verify Successfully", Toast.LENGTH_SHORT).show();
                            Log.e("TAG", "Response =" + responseString);
                            Gson gson = new Gson();

                            // code updated by ashutosh
                            // getting the json obect of result from api to get id

                            userAccountDataModel = new UserAccountDataModel();
                            mobileOtpDataModel = response.body();
                            userAccountDataModel = mobileOtpDataModel.getResult();
                            txt_username.setText(userAccountDataModel.getUserName());
                           // progressBar.setVisibility(View.GONE);

                            String s1 = gson.toJson(response.body());
                            Log.e("Response", s1);
                            Log.d("result of get", ":" + mobileOtpDataModel.toString());



                            Log.d("Userdetailssss profile", "Response =" + userAccountDataModel.toString());


                        } else if (!response.isSuccessful()) {

                            Toast.makeText(getActivity(), "varification Failed", Toast.LENGTH_SHORT).show();
                            String responseString = "Response code :" + response.code();
                            Log.e("TAG", "Response =" + responseString);
                        }

                    } catch (Exception e) {

                    }
                    Log.e("TAG", "Response =" + response);

                }

                @Override
                public void onFailure(Call<MobileOtpDataModel> call, Throwable t) {
                    //progressBar.setVisibility(View.GONE);
                }
            });

        }
        catch (Exception e)
        {
            Log.d("Report:","::::"+e.getMessage());
        }


    }

    private void dialogs(boolean b) {
        boolean networkConectivity =  NetworkConnectivity.isConnected(getContext());
        if (networkConectivity) {
            progressBar.setVisibility(View.VISIBLE);
            // Grid(Grid_URL,flag);
        } else {
            NetworkConnectivity.networkConnetivityShowDialog(getContext());

        }
    }

    private void getBanner() {


        try {


            BannerApi bannerApi = UserAccountclient.getClient().create(BannerApi.class);
            Call<List<BannerDataModel>> call = bannerApi.getBannerDataModel();
            call.enqueue(new Callback<List<BannerDataModel>>() {
                @Override
                public void onResponse(Call<List<BannerDataModel>> call, Response<List<BannerDataModel>> response) {

                  try {


                    modelList= (ArrayList<BannerDataModel>) response.body();
                    Log.d("Reeeeeees", "" + response.code());
                    modelList=response.body();
                    Log.d("Reeeeeees", "" + response.body());
                    BannerAdapter adapter = new BannerAdapter(getContext(), modelList);

                      progressBar.setVisibility(View.GONE);
                    adapterViewFlipper.setAdapter(adapter);
                    adapterViewFlipper.setFlipInterval(5000);
                    adapterViewFlipper.startFlipping();

                  }  catch (Exception e)
                  {
                      Log.d("Report:","::::"+e.getMessage());
                  }
                }

                @Override
                public void onFailure(Call<List<BannerDataModel>> call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                }
            });




        }
        catch (Exception e)
        {
            Log.d("Report:","::::"+e.getMessage());
        }





    }

    private void getBmiData(int userAccountId) {

try {


        HelathCalculateApi bmiapi = UserAccountclient.getClient().create(HelathCalculateApi.class);
        Call<HealthCalculationModel> call = bmiapi.getbmi(userAccountId);

        call.enqueue(new Callback<HealthCalculationModel>() {
            @Override
            public void onResponse(Call<HealthCalculationModel> call, Response<HealthCalculationModel> response) {

               try {
                   if (response != null && response.code() == 200) {
                    String responseString = "Response code :" + response.code();
                       progressBar.setVisibility(View.GONE);
                    Log.d("TAG", "Response =" + responseString);

                    healthCalculationModel = response.body();
                  //  Toast.makeText(getContext(), "" + healthCalculationModel.getResult(), Toast.LENGTH_SHORT).show();

                    SharedPreference.saveSharedSetting(getActivity(),"bmi",healthCalculationModel.getResult() );
                    Log.d("BMIIIII", ":" + healthCalculationModel.getResult());
                    textView_bmi.setText(healthCalculationModel.getResult());

                } else if (!response.isSuccessful()) {

                   // Toast.makeText(getActivity(), "Failed in BMI", Toast.LENGTH_SHORT).show();
                    String responseString = "Response code :" + response.code();
                    Log.e("Failed in BMI", "Response =" + responseString);
                }

               }  catch (Exception e)
               {
                   Log.d("Report:","::::"+e.getMessage());
               }
            }

            @Override
            public void onFailure(Call<HealthCalculationModel> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
            }
        });


}
catch (Exception e)
{
    Log.d("Report:","::::"+e.getMessage());
}

    }

    private void getLBWData(int userAccountId) {

try {


        HelathCalculateApi lbwapi = UserAccountclient.getClient().create(HelathCalculateApi.class);

        Call<HealthCalculationModel> call = lbwapi.getlbw(userAccountId);

        call.enqueue(new Callback<HealthCalculationModel>() {
            @Override
            public void onResponse(Call<HealthCalculationModel> call, Response<HealthCalculationModel> response) {

               try {

                if (response != null && response.code() == 200) {
                    String responseString = "Response code :" + response.code();
                    progressBar.setVisibility(View.GONE);
                    Log.d("TAG", "Response =" + responseString);

                    healthCalculationModel = response.body();
                   // Toast.makeText(getContext(), "" + healthCalculationModel.getResult(), Toast.LENGTH_SHORT).show();

                    Log.d("BMIIIII", ":" + healthCalculationModel.getResult());
                    textView_lbw.setText(healthCalculationModel.getResult());

                } else if (!response.isSuccessful()) {

                   // Toast.makeText(getActivity(), "Failed in LBW ", Toast.LENGTH_SHORT).show();
                    String responseString = "Response code :" + response.code();
                    Log.e("Failed in LBW", "Response =" + responseString);
                }

               } catch (Exception e) {
                   Log.d("Report:","::::"+e.getMessage());
               }
            }

            @Override
            public void onFailure(Call<HealthCalculationModel> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
            }
        });
}
catch (Exception e)
{
    Log.d("Report:","::::"+e.getMessage());
}


    }

    private void getIBWData(int userAccountId) {

try {


        HelathCalculateApi ibwapi = UserAccountclient.getClient().create(HelathCalculateApi.class);



        Call<HealthCalculationModel> call = ibwapi.getibw( userAccountId);

        call.enqueue(new Callback<HealthCalculationModel>() {
            @Override
            public void onResponse(Call<HealthCalculationModel> call, Response<HealthCalculationModel> response) {

               try {


                if (response != null && response.code() == 200) {
                    String responseString = "Response code :" + response.code();
                    progressBar.setVisibility(View.GONE);
                    Log.d("TAG", "Response =" + responseString);
//
                    healthCalculationModel = response.body();
                 //   Toast.makeText(getContext(), "" + healthCalculationModel.getResult(), Toast.LENGTH_SHORT).show();

                    Log.d("IBWWWWWW", ":" + healthCalculationModel.getResult());
                    SharedPreference.saveSharedSetting(getActivity(),"ibw",healthCalculationModel.getResult() );
                    textView_ibw.setText(healthCalculationModel.getResult());

                } else if (!response.isSuccessful()) {

                   // Toast.makeText(getActivity(), "Failed in IBW", Toast.LENGTH_SHORT).show();
                    String responseString = "Response code :" + response.code();
                    Log.e("Failed in IBW", "Response =" + responseString);
                }
               }  catch (Exception e)
               {
                   Log.d("Report:","::::"+e.getMessage());
               }
            }

            @Override
            public void onFailure(Call<HealthCalculationModel> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
            }
        });
}
catch (Exception e)
{
    Log.d("Report:","::::"+e.getMessage());
}


    }

    private void getBSAData(int userAccountId) {
try {



        HelathCalculateApi bsaapi = UserAccountclient.getClient().create(HelathCalculateApi.class);



        Call<HealthCalculationModel> call = bsaapi.getbsa(userAccountId);

        call.enqueue(new Callback<HealthCalculationModel>() {
            @Override
            public void onResponse(Call<HealthCalculationModel> call, Response<HealthCalculationModel> response) {
               try {


                if (response != null && response.code() == 200) {
                    String responseString = "Response code :" + response.code();
                    progressBar.setVisibility(View.GONE);
                    Log.d("TAG", "Response =" + responseString);
//
                    healthCalculationModel = response.body();
              //      Toast.makeText(getContext(), "" + healthCalculationModel.getResult(), Toast.LENGTH_SHORT).show();

                    Log.d("BSAAAAA", ":" + healthCalculationModel.getResult());
                    textView_bsa.setText(healthCalculationModel.getResult());

                } else if (!response.isSuccessful()) {

                   // Toast.makeText(getActivity(), "Failed in BSW", Toast.LENGTH_SHORT).show();
                    String responseString = "Response code :" + response.code();
                    Log.e("Failed in BSW", "Response =" + responseString);
                }
               }  catch (Exception e)
               {
                   Log.d("Report:","::::"+e.getMessage());
               }
            }

            @Override
            public void onFailure(Call<HealthCalculationModel> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
            }
        });
}
catch (Exception e)
{
    Log.d("Report:","::::"+e.getMessage());
}

    }

    private void getBFPData(int userAccountId) {
   try {

       HelathCalculateApi bfpapi = UserAccountclient.getClient().create(HelathCalculateApi.class);
       Call<HealthCalculationModel> call = bfpapi.getbfp(userAccountId);

        call.enqueue(new Callback<HealthCalculationModel>() {
            @Override
            public void onResponse(Call<HealthCalculationModel> call, Response<HealthCalculationModel> response) {

               try {


                   if (response != null && response.code() == 200) {
                       String responseString = "Response code :" + response.code();
                       progressBar.setVisibility(View.GONE);
                       Log.d("TAG", "Response =" + responseString);
//
                       healthCalculationModel = response.body();
                       //   Toast.makeText(getContext(), "" + healthCalculationModel.getResult(), Toast.LENGTH_SHORT).show();

                       Log.d("BFPPPPP", ":" + healthCalculationModel.getResult());
                       SharedPreference.saveSharedSetting(getActivity(), "bfp", healthCalculationModel.getResult());
                       textView_bfp.setText(healthCalculationModel.getResult());

                   } else if (!response.isSuccessful()) {

                      // Toast.makeText(getActivity(), "Failed in BFP", Toast.LENGTH_SHORT).show();
                       String responseString = "Response code :" + response.code();
                       Log.e("Failed in BFP", "Response =" + responseString);
                   }
               }  catch (Exception e)
                   {
                       Log.d("Report:","::::"+e.getMessage());
                   }
            }

            @Override
            public void onFailure(Call<HealthCalculationModel> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
            }
        });
   }
   catch (Exception e)
   {
       Log.d("Report:","::::"+e.getMessage());
   }


    }
//notification code


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
                            Toast.makeText(getContext(), "Updated Token Successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), " Token Failed", Toast.LENGTH_SHORT).show();

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


    private void customAlert() {
        final AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        View mView = getLayoutInflater().inflate(R.layout.activity_alert_dialogs, null);
        Button btn_okay = (Button) mView.findViewById(R.id.buttonOk);
        alert.setView(mView);
        final AlertDialog alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(false);

        btn_okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PrimaryAnalysisDietACtivity.class);
                startActivity(intent);
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }


    private void setFlipperImage(int res) {

        ImageView image = new ImageView(getContext());
        image.setBackgroundResource(res);
        viewFlipper.addView(image);
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);

    }
}
