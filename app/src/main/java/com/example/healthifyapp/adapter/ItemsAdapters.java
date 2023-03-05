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
import com.example.healthifyapp.model.WaterTrakerModel;
import com.example.healthifyapp.report.Root;

import java.util.List;

public class ItemsAdapters extends RecyclerView.Adapter<ItemsAdapters.MyviewHolder> {

    Context context;

    List<Root.Result.DietAnalysisDetails> dietAnalysisReport;

    public ItemsAdapters(Context context, List<Root.Result.DietAnalysisDetails> dietAnalysisReport) {
        this.context = context;
        this.dietAnalysisReport = dietAnalysisReport;
    }
    public void setDietAnalysisObjectList( List<Root.Result.DietAnalysisDetails>dietAnalysisReport) {
        this.dietAnalysisReport = dietAnalysisReport;
       // notifyDataSetChanged();
       // this.dietAnalysisReport.clear();


        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ItemsAdapters.MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.itemsrecyclesview, parent, false);
        return new ItemsAdapters.MyviewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {


        try {

            holder.tv_time.setText(dietAnalysisReport.get(position).getQuantity());
            holder.tv_date.setText(dietAnalysisReport.get(position).getDate());
            holder.tv_items.setText(dietAnalysisReport.get(position).getItem());


        }
        catch (Exception e)
        {
            Log.d("Reporrrtrtrtrt","::"+e.getMessage());
        }

    }
    @Override
    public int getItemCount() {
        return dietAnalysisReport.size();
    }



    protected class MyviewHolder extends RecyclerView.ViewHolder {
        TextView tv_time,tv_date,tv_items;

        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            tv_date=(TextView) itemView.findViewById(R.id.date_tv);
            tv_time = (TextView) itemView.findViewById(R.id.time_tv);

            tv_items=(TextView) itemView.findViewById(R.id.items_tv);
        }
    }
}
