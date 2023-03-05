package com.example.healthifyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healthifyapp.SecondaryReports.SecondaryReport;
import com.example.healthifyapp.SharedPreferences.SharedPreference;

import com.example.healthifyapp.TertiaryReport.TertaryReport;
import com.example.healthifyapp.api.UserAccountAPI;
import com.example.healthifyapp.model.CalculationDataModel;
import com.example.healthifyapp.model.MobileOtpDataModel;
import com.example.healthifyapp.model.UserAccountDataModel;
import com.example.healthifyapp.report.PrimaryReport;
import com.example.healthifyapp.services.Permission;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.security.Permissions;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DrawerActivity extends AppCompatActivity {
    NavigationView navigationView;
    ActionBarDrawerToggle actionBarDrawerToggle;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    String photos = "";
    String mobilno = "";
    int UserId = 0, userAccountId=0;
    MobileOtpDataModel mobileOtpDataModel;
    UserAccountDataModel userAccountDataModel;
    ActionBar actionBar;
    CalculationDataModel bmiModel;
    private Integer REQUEST_CAMERA = 1, SELECT_FILE = 0;
    String bmi;
    SharedPreference sharedPreference;
    String name, mobileno, city, gender, dob, weightUnit, heightUnit, lifeStyleModel, lifeStyleSubModel;
    float height, weight;
//    name = getIntent().getStringExtra("login_name");
public static final String SHARED_PREFS = "shared_prefs";
    public static final String NAME_KEY = "name_key";
    public static final String EMAIL_KEY = "email_key";
    public static final String MOBILENUMBER_KEY = "mobilenumber_key";
    SharedPreferences sharedpreferences;
    String name1,email,mobilenumber;
    ImageView imageView;

    TextView userNameText, mobileNoText;
    private BottomNavigationView.OnNavigationItemSelectedListener navSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {



            Fragment selectFragment = null;

            switch (item.getItemId()) {
                case R.id.page_1:
                    selectFragment = new HomeFragments();

                    break;

                case R.id.page_2:
                    selectFragment = new PlansFragment();
                    break;

                 //   case R.id.page_3:
                //                    selectFragment = new MailFragment();
                //                    break;

                case R.id.page_3:
                    selectFragment = new ProfileFragment();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    selectFragment).commit();
            return true;


        }
    };

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        toolbar = findViewById(R.id.toolsbar);
        setSupportActionBar(toolbar);
     //   imageView=findViewById(R.id.imageviewheader);

        sharedpreferences = getSharedPreferences(SHARED_PREFS, 0);
        name1=sharedpreferences.getString(NAME_KEY,null);
        mobilenumber=sharedpreferences.getString(MOBILENUMBER_KEY,null);
        
       // imageView.setOnClickListener(new View.OnClickListener() {
        //            @Override
        //            public void onClick(View view) {
        //                selectImage();
        //            }
        //        });


        mobilno = SharedPreference.readSharedSetting(getApplication(), "mobile_no", "");
        userAccountId= SharedPreference.readSharedSetting(getApplication(), "userAccountId", userAccountId);
         getData(userAccountId);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        //   navigation.setOnNavigationItemReselectedListener((BottomNavigationView.OnNavigationItemReselectedListener) navigationItemSelectedListener);
        navigation.setOnNavigationItemSelectedListener(navSelectedListener);
        navigationView = findViewById(R.id.navmenu);
        drawerLayout = findViewById(R.id.drawer);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragments()).commit();
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.Close);
        actionBarDrawerToggle.syncState();

        View headerView = navigationView.getHeaderView(0);
        userNameText = (TextView) headerView.findViewById(R.id.userNameText);
        mobileNoText = (TextView) headerView.findViewById(R.id.mobileNoText);
        userNameText.setText(SharedPreference.readSharedSetting(this, "login_name", ""));
        mobileNoText.setText(SharedPreference.readSharedSetting(this, "mobile_no", ""));
        mobileno = SharedPreference.readSharedSetting(this, "mobile_no", "");
        Log.d("in Drawer",":"+mobileno);
        //getBmiData();
       try {
           navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
               @Override
               public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                   Fragment selectFragment = null;
                   getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                   getSupportActionBar().setHomeButtonEnabled(true);
                   switch (item.getItemId()) {
                       case R.id.primaryAnalysis:
                           Intent primary = new Intent(DrawerActivity.this,
                                   PrimaryReport.class);
                           startActivity(primary);
                           drawerLayout.closeDrawer(GravityCompat.START);
                           break;
                       case R.id.secondaryAnalysis:
                           Intent secondary = new Intent(DrawerActivity.this,
                                   SecondaryReport.class);
                           startActivity(secondary);
                           drawerLayout.closeDrawer(GravityCompat.START);
                           break;
                       case R.id.tertiaryAnalysis:
                           Intent tertiary = new Intent(DrawerActivity.this,
                                   TertaryReport.class);
                           startActivity(tertiary);
                           drawerLayout.closeDrawer(GravityCompat.START);
                           break;

                       case R.id.trackers:
                           Intent trackers = new Intent(DrawerActivity.this,
                                   TrackersActivity.class);
                           startActivity(trackers);
                           drawerLayout.closeDrawer(GravityCompat.START);
                           break;
                       case R.id.chatting:
                           Intent intent = new Intent(DrawerActivity.this, Chatus.class);
                           startActivity(intent);
                           drawerLayout.closeDrawer(GravityCompat.START);
                           break;
                       case R.id.askdietitian:
                           Intent askdietintent = new Intent(DrawerActivity.this,
                                   AskDietitian.class);
                           startActivity(askdietintent);
                           drawerLayout.closeDrawer(GravityCompat.START);
                           break;

                       case R.id.sharethisapp:
                           Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
                           shareIntent.setType("text/plain");
                           shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Insert Subject here");
                           String app_url = " https://play.google.com/store/apps/saraswatiapp";
                           shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, app_url);
                           startActivity(Intent.createChooser(shareIntent, "Share via"));
                           // selectFragment=new PlansFragment();
                           drawerLayout.closeDrawer(GravityCompat.START);
                           break;
                       case R.id.feedback:
                           Intent feedback = new Intent(DrawerActivity.this,
                                   FeedBackActivity.class);
                           startActivity(feedback);
                           drawerLayout.closeDrawer(GravityCompat.START);

                           break;
                       case R.id.setting:
                           Intent settingintent = new Intent(DrawerActivity.this,
                                   Settings.class);
                           startActivity(settingintent);
                           drawerLayout.closeDrawer(GravityCompat.START);


                           break;


                       case R.id.logout:

                           AlertDialog.Builder builder= new AlertDialog.Builder(DrawerActivity.this);
                           builder.setTitle("Logout");
                           builder.setMessage("Would you like to logout?");
                           builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                               @Override
                               public void onClick(DialogInterface dialogInterface, int i) {
                                   SharedPreferences.Editor editor = sharedpreferences.edit();
                                   editor.clear();
                                   editor.apply();

                                   editor.remove("id");

                                   editor.apply();
                                   Intent intent=new Intent(DrawerActivity.this,
                                           MainActivity.class);
                                   startActivity(intent);
                                   finish();

                               }
                           })
                                   .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                       @Override
                                       public void onClick(DialogInterface dialogInterface, int i) {

                                           dialogInterface.dismiss();

                                       }
                                   }).show();
                           drawerLayout.closeDrawer(GravityCompat.START);
                           break;
                   }

                   return true;
               }
           });
       }
       catch (Exception e)
       {
           Log.d("Report:",""+e.getMessage());
       }
    }

    private void getData(int userAccountId) {
        try {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://easywaygst.theumangsociety.org")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            UserAccountAPI retrofitAPI = retrofit.create(UserAccountAPI.class);
            //  JSONObject mobileDataModel=new JSONObject();
            Call<MobileOtpDataModel> call = retrofitAPI.getUserDetails(userAccountId);
            call.enqueue(new Callback<MobileOtpDataModel>() {
                @Override
                public void onResponse(Call<MobileOtpDataModel> call, Response<MobileOtpDataModel> response) {
                    try {
                        if (response != null && response.code() == 200) {
                            String responseString = "Response code :" + response.code();
                            //Toast.makeText(getApplication(), "Verify Successfully", Toast.LENGTH_SHORT).show();
                            Log.e("TAG", "Response =" + responseString);
                            Gson gson = new Gson();

                            // code updated by ashutosh
                            // getting the json obect of result from api to get id

                            userAccountDataModel = new UserAccountDataModel();
                            mobileOtpDataModel = response.body();
                            userAccountDataModel = mobileOtpDataModel.getResult();
                            userNameText.setText(userAccountDataModel.getUserName());
                            mobileNoText.setText(userAccountDataModel.getMobileNo());
                            // progressBar.setVisibility(View.GONE);

                            String s1 = gson.toJson(response.body());
                            Log.e("Response", s1);
                            Log.d("result of get", ":" + mobileOtpDataModel.toString());



                            Log.d("Userdetailssss profile", "Response =" + userAccountDataModel.toString());


                        } else if (!response.isSuccessful()) {

                          //  Toast.makeText(getActivity(), "varification Failed", Toast.LENGTH_SHORT).show();
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

    private void selectImage() {
        boolean checkPermissions = Permission.checkPermission(DrawerActivity.this);
        if (checkPermissions) {
            final CharSequence[] items = {"Camera", "Gallery", "Cancel"};
            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(
                    DrawerActivity.this);
            builder.setTitle("Add Photo !");
            builder.setItems(items, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int i) {
                    if (items[i].equals("Camera")) {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent, REQUEST_CAMERA);

                    } else if (items[i].equals("Gallery")) {
                        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.
                                Media.EXTERNAL_CONTENT_URI);
                        intent.setType("image/*");
                        startActivityForResult(intent.createChooser(intent, "Select File"),
                                SELECT_FILE);
                    } else if (items[i].equals("Cancel")) {
                        dialog.dismiss();
                    }
                }
            });
            builder.show();
        }
    }
   // @Override
    //    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    //        super.onActivityResult(requestCode, resultCode, data);
    //        if (resultCode == Activity.RESULT_OK) {
    //            if (requestCode == REQUEST_CAMERA) {
    //                Bundle bundle = data.getExtras();
    //                final Bitmap bitmap = (Bitmap) bundle.get("data");
    //                getBase64Image(bitmap);
    //                imageView.setImageBitmap(bitmap);
    //            } else if (requestCode == SELECT_FILE) {
    //                Uri SelectedImageUri = data.getData();
    //                imageView.setImageURI(SelectedImageUri);
    //            }
    //        } else if (requestCode == RESULT_CANCELED) {
    //        }
    //    }

    public void getBase64Image(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] byteArrayImage = baos.toByteArray();
        photos = Base64.encodeToString(byteArrayImage, Base64.DEFAULT);
    }

    @Override
       public void onBackPressed() {
            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);
        }

}