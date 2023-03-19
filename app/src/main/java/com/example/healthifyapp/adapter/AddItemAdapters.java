package com.example.healthifyapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.annotation.NonNull;

import com.example.healthifyapp.R;
import com.futuremind.recyclerviewfastscroll.Utils;

import java.util.ArrayList;
import java.util.List;

public class AddItemAdapters extends ArrayAdapter<AddItemAdapters> {
    private Activity activity;
    private List<AddItemAdapters> addItemAdapters;
    private static LayoutInflater inflater = null;
    private boolean item;



    public AddItemAdapters(Activity a, int textViewResourceId, List<AddItemAdapters> lst) {
        super(a, textViewResourceId, lst);
        activity = a;
        addItemAdapters = lst;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public AddItemAdapters(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public int getCount() {
        return addItemAdapters.size();
    }

    public long getItemId(int position) {
        return position;
    }

    public AddItemAdapters getAddItemAdapter(int position) {
        return addItemAdapters.get(position);
    }

    public void removeItem(int position) {
        addItemAdapters.remove(position);
    }

    public static class ViewHolder {
        public AutoCompleteTextView autoCompleteTextView;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        ViewHolder holder;
        if (convertView == null) {


            vi=inflater.inflate(android.R.layout.simple_list_item_1,null);
            
           // vi = inflater.inflate(R.layout.people_list_item, null);
            //Utils.overrideFonts(activity, vi);

            holder = new ViewHolder();
           // holder.txtName = (TextView) vi.findViewById(R.id.txtName);
           holder.autoCompleteTextView= (AutoCompleteTextView) vi.findViewById(R.id.addbreakfast);

           vi.setTag(holder);
        } else {
            holder = (ViewHolder) vi.getTag();
        }

        AddItemAdapters o = addItemAdapters.get(position);


         if (o.addItemAdapters!= null) {
            holder.autoCompleteTextView.setText((CharSequence) o.addItemAdapters);

        } else {
            holder.autoCompleteTextView.setText("N/A");
        }

        return vi;
    }
}
