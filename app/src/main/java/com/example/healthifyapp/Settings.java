package com.example.healthifyapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.healthifyapp.SharedPreferences.SharedPreference;

import java.util.Locale;

public class Settings extends AppCompatActivity {
    public static final String SHARED_PREFS = "shared_prefs";
    public static final String NAME_KEY = "name_key";
    public static final String EMAIL_KEY = "email_key";
    public static final String MOBILENUMBER_KEY = "mobilenumber_key";
    SharedPreferences sharedpreferences;

LinearLayout linearLayout, linearLayoutaccount,languagelinearLayout;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        linearLayout=findViewById(R.id.btn_Back);
        languagelinearLayout=findViewById(R.id.language);
        loadLocale();

        languagelinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 language();
            }
        });

       // linearLayoutaccount=findViewById(R.id.logout);

      //  linearLayoutaccount.setOnClickListener(new View.OnClickListener() {
        //            @Override
        //            public void onClick(View view) {
        //
        //                AlertDialog.Builder builder= new AlertDialog.Builder(Settings.this);
        //                builder.setTitle("Logout");
        //                 builder.setMessage("Would you like to logout?");
        //                 builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
        //                     @Override
        //                     public void onClick(DialogInterface dialogInterface, int i) {
        //
        //                         SharedPreferences.Editor editor = sharedpreferences.edit();
        //                         editor.clear();
        //                         editor.apply();
        //                         Intent intent=new Intent(Settings.this,MainActivity.class);
        //                         startActivity(intent);
        //                         finish();
        //
        //                        // Intent intent=new Intent(Settings.this,MainActivity.class);
        //                        // startActivity(intent);
        //
        //                     }
        //                 })
        //                         .setNegativeButton("No", new DialogInterface.OnClickListener() {
        //                             @Override
        //                             public void onClick(DialogInterface dialogInterface, int i) {
        //
        //                                 dialogInterface.dismiss();
        //
        //                             }
        //                         }).show();
        //
        //            }
        //        });


        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Settings.this,DrawerActivity.class);
                startActivity(intent);
            }
        });


    }

    private void language() {

        final String[] languages={"English","Hindi","Marathi"};
        AlertDialog.Builder mbBuilder=new AlertDialog.Builder(Settings.this);
        mbBuilder.setTitle("Choose Language");
        mbBuilder.setSingleChoiceItems(languages, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {

                if(which == 0){
                    setLocale("");
                    recreate();
                } else if(which == 1){
                    setLocale("hi");
                    recreate();
                } else if(which == 2){
                    setLocale("mr");
                    recreate();
                }
                dialogInterface.dismiss();

            }
        });
     AlertDialog alertDialog=mbBuilder.create();
        alertDialog.show();
    }

    private void setLocale(String language) {
        Locale locale=new Locale(language);
        Locale.setDefault(locale);

        Configuration configuration=new Configuration();
        configuration.locale=locale;
        getBaseContext().getResources().updateConfiguration(configuration,getBaseContext().getResources()
                .getDisplayMetrics());


        SharedPreferences.Editor editor=getSharedPreferences("Settings",MODE_PRIVATE).edit();
        editor.putString("app_lang",language);
        editor.apply();
       // SharedPreferences sp=getSharedPreferences("Setting",MODE_PRIVATE);
        //        SharedPreferences.Editor editor= sp.edit();
        //        editor.putString("app_lang",language);
        //        editor.apply();
    }

    private void loadLocale(){

        SharedPreferences preference=getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String lang=preference.getString("app_lang","");
        setLocale(lang);

    }
}