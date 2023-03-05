package com.example.healthifyapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ViewFlipper;


public class PlansFragment extends Fragment {

    ViewFlipper viewFlipper;
    int imgarray[]={R.drawable.health,R.drawable.images,R.drawable.images1};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_plans, container, false);
        viewFlipper = view.findViewById(R.id.filpeer);
        for (int i = 0; i < imgarray.length; i++) {
            setFlipperImage(imgarray[i]);



        }
        return view;
    }

    private void setFlipperImage(int res) {

        //  Log.i("Set Filpper Called", res+"");
        ImageView image = new ImageView(getContext());
        image.setBackgroundResource(res);
        viewFlipper.addView(image);
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);
    }
    }