package com.example.healthifyapp.ashutoshseven;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.healthifyapp.R;
import com.example.healthifyapp.SharedPreferences.SharedPreference;

public class Gender5 extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    LinearLayout backBtn, nextBtn;
    private String gender;
    private RadioGroup radioGroup;
    private RadioButton radioMale;
    private RadioButton radioFemale;
    private RadioButton radioOther;
    private RadioButton radioGBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        nextBtn = findViewById(R.id.btn_next3);
        backBtn = findViewById(R.id.btn_Back3);
        radioGroup = findViewById(R.id.radiogroup);
        radioMale = findViewById(R.id.radioMale);
        radioFemale = findViewById(R.id.radioFemale);
        radioOther = findViewById(R.id.radioOther);

        radioMale.setOnCheckedChangeListener( this);
        radioFemale.setOnCheckedChangeListener(this);
        radioOther.setOnCheckedChangeListener(this);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if (radioGroup.getCheckedRadioButtonId()==-1){

                    radioMale.setChecked(true);
                    radioFemale.setChecked(false);
                    radioOther.setChecked(false);

                   // SharedPreference.saveSharedSetting(Gender5.this,"gender","Male");
                }  else if(radioGroup.getCheckedRadioButtonId()==-1) {
                    radioMale.setChecked(false);
                    radioFemale.setChecked(true);
                    radioOther.setChecked(false);

                  //  SharedPreference.saveSharedSetting(Gender5.this,"gender","Female");

                }else{
                    radioMale.setChecked(false);
                    radioFemale.setChecked(false);
                    radioOther.setChecked(true);

                   // SharedPreference.saveSharedSetting(Gender5.this,"gender","Other");

                }
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Gender5.this, Cities4.class);
                startActivity(intent);
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 openActivity(gender);

            }
        });
    }

    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if (isChecked) {
            //  txtGender.setText(buttonView.getText().toString());
            if (R.id.radioMale == buttonView.getId()) {
                radioFemale.setChecked(false);
                radioOther.setChecked(false);

                gender = "male";
            } else if (R.id.radioFemale == buttonView.getId()) {
                radioMale.setChecked(false);
                radioOther.setChecked(false);

                gender = "female";

            } else {
                radioFemale.setChecked(false);
                radioMale.setChecked(false);

                gender = "other";

            }
        }

        SharedPreference.saveSharedSetting(Gender5.this,"gender",gender);

    }






    public void openActivity(String gender) {
        String name, mobileno, city;
        name = getIntent().getStringExtra("login_name");
        mobileno = getIntent().getStringExtra("mobile_no");
        city = getIntent().getStringExtra("city");



            Intent intent = new Intent(Gender5.this, DOB6.class);
            intent.putExtra("login_name", name).toString();
            intent.putExtra("mobile_no", mobileno);
            intent.putExtra("city", city);
            intent.putExtra("gender", gender);

            //intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

    }
}