package com.example.healthifyapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ViewFlipper;
import  com.example.healthifyapp.R;
import com.bumptech.glide.Glide;
import com.example.healthifyapp.model.BannerDataModel;

import java.util.List;

public class BannerAdapter extends BaseAdapter {
    Context context;
    List<BannerDataModel> userFamilyDetailModels;

    public BannerAdapter(Context context, List<BannerDataModel> userFamilyDetailModels) {
        this.context = context;
        this.userFamilyDetailModels = userFamilyDetailModels;
    }

    @Override
    public int getCount() {
        return userFamilyDetailModels.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertview, ViewGroup viewGroup) {

        BannerDataModel model=userFamilyDetailModels.get(i);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_bannerimage, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.image);
        Glide.with(context).load(model.getBannerURL()).into(imageView);
        return view;
    }
}