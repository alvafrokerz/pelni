package com.example.main;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.example.main.variabelpublic.Data_menu_voyage_addmenu_day;
import static com.example.main.variabelpublic.Data_menu_voyage_tipemakan_index;
import static com.example.main.variabelpublic.String_database_menu_voyage_menu;

public class ListAdapter_menu_voyage_plan_addmenu extends ArrayAdapter<String> {
    customButtonListener customListner;
    customButtonListener2  customListner2;
    customEditextfocusListener customListner3;
    customChangeEditextfocusListener customListner4;




    public static ArrayList<String> Data_id2   = new ArrayList<String>();
    public static ArrayList<String> Data_menu   = new ArrayList<String>();

    private  List<? extends Map<String, ?>>  arrayListr;
    public String  holder_values;
    public interface customButtonListener {
        public void onButtonClickListner(int position, String value);
    }
    public interface customButtonListener2 {
        public void onButtonClickListner2(int position, String value);
    }

    public interface customEditextfocusListener {
        public void onCustomEditextfocus (int position);

    }
    public interface customChangeEditextfocusListener {
        public void onCustomChangeEditextfocus (int position );

    }


    public void setCustomButtonListner(customButtonListener listener) {
        this.customListner = listener;
    }
    public void setCustomButtonListner2(customButtonListener2   listener2) {
        this.customListner2 = listener2;
    }

    public void setCustomEditextfocus(customEditextfocusListener   listener3) {
        this.customListner3 = listener3;
    }

    public void setChangeCustomEditextfocus(customChangeEditextfocusListener   listener4) {
        this.customListner4 = listener4;
    }



    private Context context;
    public ListAdapter_menu_voyage_plan_addmenu(Context context, ArrayList<String> dataItem ) {
        super(context, R.layout.list_view_menu_voyage_plan_addmenu, dataItem);
        this.context = context;


        this.context = context;
    }
    ViewHolder viewHolder;
    @Override
    public View getView(final int position, View convertView, ViewGroup parent ) {


        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.list_view_menu_voyage_plan_addmenu, null);
            viewHolder = new ViewHolder();

            viewHolder.spinner_list = (Spinner) convertView.findViewById(R.id.spinner_list_view_menu_voyage_addmenu);
            viewHolder.button = (Button) convertView .findViewById(R.id.button_menu_voyage_addmenu);
            viewHolder.textView = (TextView) convertView .findViewById(R.id.textview_listview_menu_voyage_addmenu);

         // viewHolder.spinner_list.setTag(position);
            convertView.setTag(viewHolder);

        } else {
            Log.i("tes2",String.valueOf(position));
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText(getItem(position));
        List<String> categories = new ArrayList<String>();
        Log.i("tes",String.valueOf(position));
        //viewHolder.text.setText(temp);

        for (int i = 0; i <variabelpublic.Data_menu_voyage_addmenu.size() ; i++) {
            final String temp2 =  variabelpublic.Data_menu_voyage_addmenu.get(i);
            categories.add(temp2);

        }

         // Creating adapter for spinner_custom
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, android.R.layout.select_dialog_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Log.i("tes3",String.valueOf(position));
        // attaching data adapter to spinner_custom


        viewHolder.spinner_list.setAdapter(dataAdapter);

        viewHolder.spinner_list.setSelection(Integer.parseInt(String_database_menu_voyage_menu[Data_menu_voyage_addmenu_day-1][Data_menu_voyage_tipemakan_index][position]));
        viewHolder.spinner_list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int posisi, long l) {
                variabelpublic.Data_menu_voyage_addmenu_index[position]=posisi;
                String_database_menu_voyage_menu [Data_menu_voyage_addmenu_day-1][Data_menu_voyage_tipemakan_index][position]=String.valueOf(posisi);


                Log.i("tes5",String.valueOf(position));

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });






        final String temp="" ;


        viewHolder.button.setOnClickListener(new  View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (customListner != null) {
                    customListner.onButtonClickListner(position,temp);
                }

            }
        });





        if (viewHolder.spinner_list.getTag() != null){
            viewHolder.spinner_list.setSelection(Integer.parseInt(viewHolder.spinner_list.getTag().toString()));
        }


        return convertView;
    }



    public class ViewHolder {
        Spinner spinner_list;
        Button button;
        Button button2;
TextView textView;

    }

}  