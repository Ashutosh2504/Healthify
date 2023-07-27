package com.example.healthifyapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthifyapp.R;
import com.example.healthifyapp.model.LifeStyleItemDataModel;
import com.example.healthifyapp.model.LifeStyleSubItemModel;

import java.util.List;

import static com.example.healthifyapp.SevenActivity.moderateList;


public class ModerateAdapters extends RecyclerView.Adapter<ModerateAdapters.MyviewHolder> {
    Context context;
    List<LifeStyleSubItemModel> lifeStyleSubItemModelList2;

    public ModerateAdapters(Context context, List<LifeStyleSubItemModel> lifeStyleSubItemModelList2) {
        this.context = context;
        this.lifeStyleSubItemModelList2 = lifeStyleSubItemModelList2;
    }

    public void setLifeStyleSubItemModelList2(List<LifeStyleSubItemModel> lifeStyleSubItemModelList2) {
        this.lifeStyleSubItemModelList2 = lifeStyleSubItemModelList2;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ModerateAdapters.MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.moderateactivity, parent, false);
        return new ModerateAdapters.MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {
        holder.checkBox.setText(lifeStyleSubItemModelList2.get(position).getText());

        //changes made by ashutosh
        holder.checkBox.setTag(position);
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer pos = (Integer) holder.checkBox.getTag();
                Log.d("Clicked", ":" + lifeStyleSubItemModelList2.get(pos).getText());

                if (lifeStyleSubItemModelList2.get(pos).getSelected()) {
                    lifeStyleSubItemModelList2.get(pos).setSelected(false);
                } else {
                    lifeStyleSubItemModelList2.get(pos).setSelected(true);

                }
//                Log.d("MMM", "" + moderateList.get(0));
                final boolean isChecked = holder.checkBox.isChecked();
                if (isChecked) {
                    moderateList.add(lifeStyleSubItemModelList2.get(pos).getText());

                }
                else
                {
                    moderateList.remove(lifeStyleSubItemModelList2.get(pos).getText());

                }
                for (int i = 0; i < moderateList.size(); i++) {
                    Log.d("List", ":" + moderateList.get(i));
                }

            }
        });

        // till here
    }


    @Override
    public int getItemCount() {
        if (lifeStyleSubItemModelList2 != null) {

            return lifeStyleSubItemModelList2.size();
        }

        return 0;
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;

        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = (CheckBox) itemView.findViewById(R.id.moderateone);
        }
    }
}
