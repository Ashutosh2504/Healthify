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
import com.example.healthifyapp.WaterTracker_Activity;
import com.example.healthifyapp.model.WaterTrackerModel;
import com.example.healthifyapp.model.WaterTrakerModel;
import com.example.healthifyapp.report.Root;

import org.w3c.dom.Text;

import java.util.List;

public class WaterAdapter extends RecyclerView.Adapter<WaterAdapter.MyviewHolder> {


    Context context;

    List<WaterTrakerModel> dietAnalysisReport;

    public WaterAdapter(Context context, List<WaterTrakerModel> dietAnalysisReport) {
        this.context = context;
        this.dietAnalysisReport = dietAnalysisReport;
    }
    public void setDietAnalysisObjectList(List<WaterTrakerModel> dietAnalysisReport) {
        this.dietAnalysisReport = dietAnalysisReport;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public WaterAdapter.MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.waterrecyclesview, parent, false);
        return new WaterAdapter.MyviewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {

        try {

            holder.tv_time.setText(dietAnalysisReport.get(position).getTodayTime());
            holder.tv_date.setText(dietAnalysisReport.get(position).getTodayDate());
            holder.tv_waterml.setText(dietAnalysisReport.get(position).getQuanity());
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
        TextView tv_time,tv_date,tv_waterml;

        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            tv_date=(TextView) itemView.findViewById(R.id.date_tv);
            tv_time = (TextView) itemView.findViewById(R.id.time_tv);

            tv_waterml=(TextView) itemView.findViewById(R.id.water_tv);
        }
    }
}
