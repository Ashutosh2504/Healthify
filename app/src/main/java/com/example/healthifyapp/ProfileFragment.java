package com.example.healthifyapp;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healthifyapp.SharedPreferences.SharedPreference;
import com.example.healthifyapp.api.UserAccountAPI;
import com.example.healthifyapp.model.apiclient.UserAccountclient;
import com.example.healthifyapp.model.MobileOtpDataModel;
import com.example.healthifyapp.model.UserAccountDataModel;
import com.example.healthifyapp.networkconnectivity.NetworkConnectivity;
import com.google.gson.Gson;


public class ProfileFragment extends Fragment /*implements CompoundButton.OnCheckedChangeListener*/ {

    static String dob, heightUnit, weightUnit, medicalCondition, lifeStyleCategory, lifeStyleSubCategory, Str_Gender;
    EditText Edit_Name, Edit_Phone, Edit_Email, Edit_Age, Edit_City ;
    String Str_Name, Str_Phone, Str_Email, Str_Age, Str_City, Str_BMI = "0", Str_BFP = "0";
    float Str_Height, Str_TargetWeight;
    int userAccountId = 0;
    MobileOtpDataModel mobileOtpDataModel;
    UserAccountDataModel userAccountDataModel;
    Button submit;
    RadioGroup gender;
    ProgressBar progressBar;
    RadioButton sexBtn;
    RadioButton male, female, other;
    String ibw, bfp, bmi;
    String name;

    TextView bfpText, bmiText,text_Height;
    EditText text_TargetWeight;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        Edit_Name = view.findViewById(R.id.name);
        Edit_Phone = view.findViewById(R.id.phone);
        Edit_Email = view.findViewById(R.id.email);
        Edit_Age = view.findViewById(R.id.age);
        Edit_City = view.findViewById(R.id.city);
        text_Height = view.findViewById(R.id.height);
        text_TargetWeight = view.findViewById(R.id.weight);
        submit = view.findViewById(R.id.submit);
        gender = view.findViewById(R.id.genderGrp);
        male = view.findViewById(R.id.male);
        female = view.findViewById(R.id.female);
        other = view.findViewById(R.id.other);
        bfpText = view.findViewById(R.id.bfp);
        bmiText = view.findViewById(R.id.bmi);
        progressBar=view.findViewById(R.id.pb);
        dialogs(false);
       // SharedPreference.saveSharedSetting(getActivity(),"login_name",Edit_Name.getText().toString());

        // Edit_Name.setText(SharedPreference.readSharedSetting(getActivity(), "first_name", ""));
        //Edit_Phone.setText(SharedPreference.readSharedSetting(getActivity(), "mobile_no", ""));
        userAccountId = SharedPreference.readSharedSetting(getActivity(), "userAccountId", 0);
        ibw = SharedPreference.readSharedSetting(getActivity(), "ibw", "0");
        bfp = SharedPreference.readSharedSetting(getActivity(), "bfp", "0");
        bmi = SharedPreference.readSharedSetting(getActivity(), "bmi", "0");
        getData(userAccountId);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    Str_Name = Edit_Name.getText().toString();
                    Str_Phone = Edit_Phone.getText().toString();
                    Str_Email = Edit_Email.getText().toString();
                    Str_Age = Edit_Age.getText().toString();
                    Str_City = Edit_City.getText().toString();
                    Log.d("city", ":" + Str_City);
                    Log.d("Gender IDDD", ":" + gender);

                    Str_Height = Float.parseFloat(text_Height.getText().toString());
                    Str_TargetWeight = Float.parseFloat(text_TargetWeight.getText().toString());

                    int selectedId = gender.getCheckedRadioButtonId();


                    RadioButton sex = view.findViewById(selectedId);
                   // Str_Gender = sex.getText().toString();
                    if (male.isChecked()) {
                        Str_Gender = "male";
                        SharedPreference.saveSharedSetting(getActivity(), "gender", Str_Gender);

                    } else if (female.isChecked()) {
                        Str_Gender = "female";
                        SharedPreference.saveSharedSetting(getActivity(), "gender", Str_Gender);

                    } else if (other.isChecked()) {
                        Str_Gender = "other";
                        SharedPreference.saveSharedSetting(getActivity(), "gender", Str_Gender);



                    } else {
                        Toast.makeText(getActivity(), "None selected", Toast.LENGTH_SHORT).show();
                    }


                    // find the radiobutton by returned id
                    sexBtn = (RadioButton) view.findViewById(selectedId);
                    Log.d("SexID", "" + selectedId);
                  /* male.setOnCheckedChangeListener( ProfileFragment.this);
                   female.setOnCheckedChangeListener(ProfileFragment.this);
                   other.setOnCheckedChangeListener(ProfileFragment.this);
                   gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                       @Override
                       public void onCheckedChanged(RadioGroup radioGroup, int i) {

                           if (radioGroup.getCheckedRadioButtonId()==-1){

                               male.setChecked(true);
                               female.setChecked(false);
                               other.setChecked(false);

                               // SharedPreference.saveSharedSetting(Gender5.this,"gender","Male");
                           }  else if(radioGroup.getCheckedRadioButtonId()==-1) {
                               male.setChecked(false);
                               female.setChecked(true);
                               other.setChecked(false);

                               //  SharedPreference.saveSharedSetting(Gender5.this,"gender","Female");

                           }else{
                               male.setChecked(false);
                               female.setChecked(false);
                               other.setChecked(true);

                               // SharedPreference.saveSharedSetting(Gender5.this,"gender","Other");

                           }
                       }
                   });*/
                  /* Log.d("Sex",""+sexBtn);
                   Str_Gender = sexBtn.getText().toString();
                   Log.d("Gender",""+sexBtn);*/

                    UpdatePofile(Str_Phone, Str_Name, Str_City, Str_Gender, dob, Str_Height, heightUnit,
                            Str_TargetWeight, weightUnit, medicalCondition, lifeStyleCategory, lifeStyleSubCategory,Str_Email,Str_Age);




                } catch (Exception e) {
                }
               /* Fragment someFragment = new SomeFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, someFragment ); // give your fragment container id in first parameter
                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                transaction.commit();*/

               /* Fragment fragment = new HomeFragments();

                FragmentManager fragmentManager = getFragmentManager();

                fragmentManager.beginTransaction().replace(R.id.profile, fragment).commit();*/
            }
        });


        return view;
    }

    private void dialogs(boolean b) {
        boolean networkConectivity =  NetworkConnectivity.isConnected(getContext());
        if (networkConectivity) {
            progressBar.setVisibility(View.VISIBLE);
            // Grid(Grid_URL,flag);
        }
        else {
            NetworkConnectivity.networkConnetivityShowDialog(getContext());

        }
    }


    private void UpdatePofile(String mobile, String name, String city, String gender, String dob,
                              float height, String heightUnit, float weight, String weightUnit,
                              String medicalCondition, String lifeStyleCategory, String lifeStyleSubCategory,String email,String age) {
try {

    //        Retrofit retrofit=new Retrofit.Builder()
//                .baseUrl("https://easywaygst.theumangsociety.org")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        UserAccountAPI api=retrofit.create(UserAccountAPI.class);

    UserAccountAPI api = UserAccountclient.getClient().create(UserAccountAPI.class);

    UserAccountDataModel userModel = new UserAccountDataModel(mobile, name, city,
            gender, dob, height, heightUnit, weight, weightUnit, medicalCondition,
            lifeStyleCategory, lifeStyleSubCategory,email,age);
    Gson g1 = new Gson();
    Log.v("Sending data:", g1.toJson(userModel));
    Call<UserAccountDataModel> call = api.createPost(userModel);

    call.enqueue(new Callback<UserAccountDataModel>() {
        @Override
        public void onResponse(Call<UserAccountDataModel> call, Response<UserAccountDataModel> response) {
            try{
            if (response != null && response.code() == 200) {
                progressBar.setVisibility(View.GONE);
                String responseString = "Response code :" + response.code();
                Toast.makeText(getActivity(), "Data Update Successfully", Toast.LENGTH_SHORT).show();
                Log.e("TAG", "Response =" + responseString);
                Gson gson = new Gson();
                String s1 = gson.toJson(response.body());
            } else if (!response.isSuccessful()) {

                String responseString = "Response code :" + response.code();
                Log.e("TAG", "Response =" + responseString);
            }
            }  catch (Exception e)
            {
                Log.d("Report:","::::"+e.getMessage());
            }
        }

        @Override
        public void onFailure(Call<UserAccountDataModel> call, Throwable t) {
            progressBar.setVisibility(View.GONE);
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

                    Log.e("TAG", "Response =" + responseString);
                    Gson gson = new Gson();

                    // code updated by ashutosh
                    // getting the json obect of result from api to get id
                  //  mobileOtpDataModel = new MobileOtpDataModel(); //comment line rahul
                    userAccountDataModel = new UserAccountDataModel();
                    mobileOtpDataModel = response.body();
                    userAccountDataModel = mobileOtpDataModel.getResult();
                    progressBar.setVisibility(View.GONE);

                    String s1 = gson.toJson(response.body());
                    Log.e("Response", s1);
                    Log.d("result of get", ":" + mobileOtpDataModel.toString());
                    Edit_Name.setText(userAccountDataModel.getUserName());
                    Edit_Phone.setText(userAccountDataModel.getMobileNo());
                    Edit_City.setText(userAccountDataModel.getCity());
                    text_Height.setText(String.valueOf(userAccountDataModel.getHeight()));
                    Edit_Email.setText(userAccountDataModel.getEmail());
                    Edit_Age.setText(userAccountDataModel.getAge());
                    text_TargetWeight.setText(String.valueOf( userAccountDataModel.getWeight()));
                    dob = userAccountDataModel.getDob();
                    weightUnit = userAccountDataModel.getWeightUnit();
                    heightUnit = userAccountDataModel.getHeightUnit();
                    medicalCondition = userAccountDataModel.getMedicalCondition();
                    lifeStyleCategory = userAccountDataModel.getLifeStyleCategory();
                    lifeStyleSubCategory = userAccountDataModel.getLifeStyleSubCategories();
                  //  text_TargetWeight.setText(ibw);
                    bfpText.setText(bfp);
                    bmiText.setText(bmi);

                    if (userAccountDataModel.getGender().equalsIgnoreCase("male")) {
                        male.setChecked(true);
                    } else if (userAccountDataModel.getGender().equalsIgnoreCase("female")) {
                        female.setChecked(true);

                    } else {
                        other.setChecked(true);

                    }


                } else if (!response.isSuccessful()) {

                    String responseString = "Response code :" + response.code();
                    Log.e("TAG", "Response =" + responseString);
                }

               } catch (Exception e) {

               }
                Log.e("TAG", "Response =" + response);

            }

            @Override
            public void onFailure(Call<MobileOtpDataModel> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
            }
        });

     }
     catch (Exception e)
     {
         Log.d("Report:","::::"+e.getMessage());
     }

    }

   /* @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

        if (isChecked) {
            //  txtGender.setText(buttonView.getText().toString());
            if (R.id.radioMale == compoundButton.getId()) {
               female.setChecked(false);
               other.setChecked(false);
                Log.d("male","ooooo");
                Str_Gender = "male";
            } else if (R.id.radioFemale == compoundButton.getId()) {
                male.setChecked(false);
                other.setChecked(false);
                Log.d("female","ooooo");
                Str_Gender = "female";

            } else {
                female.setChecked(false);
                male.setChecked(false);
                Log.d("oth","ooooo");
                Str_Gender = "other";

            }
        }

        SharedPreference.saveSharedSetting(getActivity(),"gender",Str_Gender);

    }*/
}