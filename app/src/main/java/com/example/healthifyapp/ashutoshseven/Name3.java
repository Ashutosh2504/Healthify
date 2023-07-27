package com.example.healthifyapp.ashutoshseven;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healthifyapp.R;
import com.example.healthifyapp.SecondActivity;
import com.example.healthifyapp.SharedPreferences.SharedPreference;

public class Name3 extends AppCompatActivity {
    TextView nextBtn;
    EditText editTextName;
    String name, mobileno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name1);


        nextBtn = (TextView) findViewById(R.id.next);
        editTextName = (EditText) findViewById(R.id.name);



        name = editTextName.getText().toString();
      //  Log.d("name1", "" + name);
        //Toast.makeText(Name3.this, "name1", Toast.LENGTH_SHORT).show();
// postData(editTextName.getText().toString());
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = editTextName.getText().toString();
                mobileno = SharedPreference.readSharedSetting(Name3.this,"mobile_no","0");
                //editTextName.setText("Ashu");
             //   Toast.makeText(Name3.this, "Name" + name, Toast.LENGTH_SHORT).show();
               // Log.d("mo", "" + mobileno);
                if (name.isEmpty()) {
                    Toast.makeText(Name3.this, "Please enter name", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    SharedPreference.saveSharedSetting(Name3.this, "login_name", name);
                    Log.d("name2", "" + name);
                    //calling the intents
                    openActivity(name, mobileno);
                }
            }
        });


    }


    // going to city 2ndactivity
    public void openActivity(String name, String mobileno) {
        Intent intent = new Intent(Name3.this, Cities4.class);
        intent.putExtra("login_name", name);
        intent.putExtra("mobile_no", mobileno);
        //intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }
    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

}