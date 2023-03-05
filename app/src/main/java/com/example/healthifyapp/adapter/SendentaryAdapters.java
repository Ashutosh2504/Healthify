package com.example.healthifyapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthifyapp.R;
import com.example.healthifyapp.model.LifeStyleSubItemModel;

import java.util.List;

import static com.example.healthifyapp.SevenActivity.moderateList;
import static com.example.healthifyapp.SevenActivity.sedentaryList;

public class SendentaryAdapters extends RecyclerView.Adapter<SendentaryAdapters.MyviewHolder> {

    //
    Context context;
    List<LifeStyleSubItemModel> lifeStyleSubItemModelList;

    public SendentaryAdapters(Context context, List<LifeStyleSubItemModel> lifeStyleSubItemModelList) {
        this.context = context;
        this.lifeStyleSubItemModelList = lifeStyleSubItemModelList;
    }

    public void setLifeStyleSubItemModelList(List<LifeStyleSubItemModel> lifeStyleSubItemModelList) {
        this.lifeStyleSubItemModelList = lifeStyleSubItemModelList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SendentaryAdapters.MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_sendentary, parent, false);
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SendentaryAdapters.MyviewHolder holder, int position) {
        holder.checkBox.setText(lifeStyleSubItemModelList.get(position).getText());

        //changes made by ashutosh
        holder.checkBox.setTag(position);
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer pos = (Integer) holder.checkBox.getTag();
                Log.d("Clicked", ":" + lifeStyleSubItemModelList.get(pos).getText());

                if (lifeStyleSubItemModelList.get(pos).getSelected()) {
                    lifeStyleSubItemModelList.get(pos).setSelected(false);
                } else {
                    lifeStyleSubItemModelList.get(pos).setSelected(true);

                }
//                Log.d("MMM", "" + moderateList.get(0));
                final boolean isChecked = holder.checkBox.isChecked();
                if (isChecked) {
                    sedentaryList.add(lifeStyleSubItemModelList.get(pos).getText());

                } else {
                    sedentaryList.remove(lifeStyleSubItemModelList.get(pos).getText());

                }
                for (int i = 0; i < sedentaryList.size(); i++) {
                    Log.d("List", ":" + sedentaryList.get(i));
                }

            }
        });

        // till here
    }

    @Override
    public int getItemCount() {
        if (lifeStyleSubItemModelList != null) {

            return lifeStyleSubItemModelList.size();
        }

        return 0;
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;

        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = (CheckBox) itemView.findViewById(R.id.one);
        }
    }
}
