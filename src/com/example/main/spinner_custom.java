package com.example.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

/**
 * Created by ALVA on 10/8/2016.
 */

public class spinner_custom {

    Spinner spinner;


    public void spinner(Context context, ArrayList<String> a,int b){
        LayoutInflater inflater = LayoutInflater.from(context);
        View convertView = inflater.inflate(R.layout.list_view_menu, null);

        Spinner spinner = (Spinner) convertView.findViewById(b);


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item,a);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }
}
