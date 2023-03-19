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

import static com.example.healthifyapp.SevenActivity.heavyList;
import static com.example.healthifyapp.SevenActivity.sedentaryList;

public class HeavyAdapters extends  RecyclerView.Adapter<HeavyAdapters.MyviewHolder> {
    Context context;
    List<LifeStyleSubItemModel> lifeStyleSubItemModelList3;

    public HeavyAdapters(Context context, List<LifeStyleSubItemModel> lifeStyleSubItemModelList2) {
        this.context = context;
        this.lifeStyleSubItemModelList3 = lifeStyleSubItemModelList2;
    }

    public void setLifeStyleSubItemModelList3(List<LifeStyleSubItemModel> lifeStyleSubItemModelList2) {
        this.lifeStyleSubItemModelList3 = lifeStyleSubItemModelList2;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HeavyAdapters.MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.heavyactivity,parent,false);
        return new HeavyAdapters.MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {
        holder.checkBox.setText(lifeStyleSubItemModelList3.get(position).getText());

        //changes made by ashutosh
        holder.checkBox.setTag(position);
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer pos = (Integer) holder.checkBox.getTag();
                Log.d("Clicked", ":" + lifeStyleSubItemModelList3.get(pos).getText());

                if (lifeStyleSubItemModelList3.get(pos).getSelected()) {
                    lifeStyleSubItemModelList3.get(pos).setSelected(false);
                } else {
                    lifeStyleSubItemModelList3.get(pos).setSelected(true);

                }
//                Log.d("MMM", "" + moderateList.get(0));
                final boolean isChecked = holder.checkBox.isChecked();
                if (isChecked) {
                    heavyList.add(lifeStyleSubItemModelList3.get(pos).getText());

                } else {
                    heavyList.remove(lifeStyleSubItemModelList3.get(pos).getText());
                }
                for (int i = 0; i < heavyList.size(); i++) {
                    Log.d("List", ":" + heavyList.get(i));
                }

            }
        });

        // till here
    }



    @Override
    public int getItemCount() {
        if(lifeStyleSubItemModelList3 !=null){

            return  lifeStyleSubItemModelList3.size();
        }

        return 0;
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;

        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox=(CheckBox)itemView.findViewById(R.id.heavychekbox);
        }
    }
}
