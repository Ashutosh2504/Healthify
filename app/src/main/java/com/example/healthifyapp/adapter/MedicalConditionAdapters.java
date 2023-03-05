package com.example.healthifyapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthifyapp.R;
import com.example.healthifyapp.api.MedicalConditionApi;
import com.example.healthifyapp.model.LifeStyleSubItemModel;
import com.example.healthifyapp.model.MedicalConditionDataModel;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.healthifyapp.EightActivity.medicalCondnList;
import static com.example.healthifyapp.SevenActivity.moderateList;

public class    MedicalConditionAdapters extends RecyclerView.Adapter<MedicalConditionAdapters.MyviewHolder> {
    Context context;
    List<MedicalConditionDataModel> medicalConditionDataModels;
    public MedicalConditionAdapters(Context context, List<MedicalConditionDataModel> medicalConditionjsonObjectList) {
        this.context = context;
        this.medicalConditionDataModels= medicalConditionjsonObjectList;
    }

    public void setMedicalConditionjsonObjectList(List<MedicalConditionDataModel> medicalConditionjsonObjectList) {
        this.medicalConditionDataModels = medicalConditionjsonObjectList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MedicalConditionAdapters.MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_medicalcondition, parent, false);
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {

         holder.radioButton.setText(medicalConditionDataModels.get(position).getText());
//holder.checkBox.setText(lifeStyleSubItemModelList2.get(position).getText());
      //  holder.radioButton.setText((CharSequence) medicalConditionjsonObjectList.get(position).get(holder.toString()));
        //changes made by ashutosh
        holder.radioButton.setTag(position);
        holder.radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer pos = (Integer) holder.radioButton.getTag();
                Log.d("Clicked", ":" + medicalConditionDataModels.get(pos).getText());

                if (medicalConditionDataModels.get(pos).getSelected()) {
                    medicalConditionDataModels.get(pos).setSelected(false);
                } else {
                    medicalConditionDataModels.get(pos).setSelected(true);

                }
//                Log.d("MMM", "" + moderateList.get(0));
                final boolean isChecked = holder.radioButton.isChecked();
                if (isChecked) {
                    medicalCondnList.add(medicalConditionDataModels.get(pos).getText());

                }
                else
                {
                    medicalCondnList.remove(medicalConditionDataModels.get(pos).getText());

                }
                for (int i = 0; i < medicalCondnList.size(); i++) {
                    Log.d("ListMMMM", ":" + medicalCondnList.get(i));
                }

            }
        });


    }


    @Override
    public int getItemCount() {
        if (medicalConditionDataModels != null) {

            return medicalConditionDataModels.size();
        }

        return 0;
    }


    protected class MyviewHolder extends RecyclerView.ViewHolder {
        RadioButton radioButton;

        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            radioButton = (RadioButton) itemView.findViewById(R.id.none);
        }
    }
}
