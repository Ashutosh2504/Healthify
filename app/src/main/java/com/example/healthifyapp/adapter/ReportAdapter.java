package com.example.healthifyapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthifyapp.R;

import com.example.healthifyapp.model.MedicalConditionDataModel;
import com.example.healthifyapp.report.Root;

import java.util.List;

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.MyviewHolder> {

    Context context;

    List<Root.Result.DietAnalysisDetails> dietAnalysisReport;

    public ReportAdapter(Context context, List<Root.Result.DietAnalysisDetails> dietAnalysisReport) {
        this.context = context;
        this.dietAnalysisReport = dietAnalysisReport;
    }

    public void setDietAnalysisObjectList(List<Root.Result.DietAnalysisDetails> dietAnalysisReport) {
        this.dietAnalysisReport = dietAnalysisReport;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ReportAdapter.MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_lunch_report, parent, false);
        return new MyviewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ReportAdapter.MyviewHolder holder, int position) {

        try {
            // Root.Result.DietAnalysisDetails dietAnalysisDetails = dietAnalysisReport.get(position);
            holder.item.setText(dietAnalysisReport.get(position).getItem());
            holder.calorie.setText(dietAnalysisReport.get(position).getkCal());
            holder.date.setText(dietAnalysisReport.get(position).getDate());
            holder.quantity.setText(dietAnalysisReport.get(position).getQuantity());
            holder.time.setText(dietAnalysisReport.get(position).getTime());
            holder.protein.setText(dietAnalysisReport.get(position).getProtein());

            holder.totalfat.setText(dietAnalysisReport.get(position).getTotalFat());
            holder.totalfiber.setText(dietAnalysisReport.get(position).getTotalfiber());
            holder.insoluble.setText(dietAnalysisReport.get(position).getInsoluble());
            holder.soluble.setText(dietAnalysisReport.get(position).getSoluble());
            holder.carbohydrate.setText(dietAnalysisReport.get(position).getCarbohydrate());
            holder.energykj.setText(dietAnalysisReport.get(position).getEnergyKJ());
            holder.energykcal.setText(dietAnalysisReport.get(position).getEnergyKcal());
            holder.sodium.setText(dietAnalysisReport.get(position).getSodium());
            holder.potasium.setText(dietAnalysisReport.get(position).getPotassium());
            holder.phosphorus.setText(dietAnalysisReport.get(position).getPhosphorus());
            holder.calcium.setText(dietAnalysisReport.get(position).getCalcium());
            holder.mangesium.setText(dietAnalysisReport.get(position).getMagnesium());
        } catch (Exception e) {
            Log.d("Reporrrtrtrtrt", "::" + e.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return dietAnalysisReport.size();


    }



    protected class MyviewHolder extends RecyclerView.ViewHolder {
        TextView item, quantity, calorie, date, time, protein,totalfat,totalfiber,insoluble,soluble,carbohydrate,energykj,energykcal,sodium,potasium,phosphorus,calcium,mangesium;

        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            item = (TextView) itemView.findViewById(R.id.item_report);
            quantity = (TextView) itemView.findViewById(R.id.quantity_report);
            calorie = (TextView) itemView.findViewById(R.id.kcal_report);
            date = (TextView) itemView.findViewById(R.id.date_report);
            time = (TextView) itemView.findViewById(R.id.time_report);
            protein=(TextView) itemView.findViewById(R.id.protein_report);
            totalfat=(TextView) itemView.findViewById(R.id.totalfat_report);
            totalfiber=(TextView) itemView.findViewById(R.id.totalfiber_report);
            insoluble=(TextView) itemView.findViewById(R.id.insoluble_report);
            soluble=(TextView) itemView.findViewById(R.id.soluble_report);
            carbohydrate=(TextView) itemView.findViewById(R.id.carbohydrate_report);
            energykj=(TextView)itemView.findViewById(R.id.energykj_report);
            energykcal=(TextView) itemView.findViewById(R.id.energykcal_report);
            sodium=(TextView) itemView.findViewById(R.id.sodium_report);
            potasium=(TextView) itemView.findViewById(R.id.potasium_report);
            phosphorus=(TextView) itemView.findViewById(R.id.phosphorus_report);
            calcium=(TextView) itemView.findViewById(R.id.calcium_report);
            mangesium=(TextView) itemView.findViewById(R.id.magnesium_report);

        }
    }
}