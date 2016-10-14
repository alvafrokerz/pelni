package com.example.main;


import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ListAdapter_menu_inventory_plan extends ArrayAdapter<String> {
  customButtonListener customListner;

     customEditextfocusListener customListner3;
    customChangeEditextfocusListener customListner4;




    public static ArrayList<String> Data_id2   = new ArrayList<String>();
    public static ArrayList<String> Data_menu   = new ArrayList<String>();

    private  List<? extends Map<String, ?>>  arrayListr;
    public String  holder_values;

    public interface customButtonListener {
      public void onButtonClickListner(int position, String value);
  }


    public interface customEditextfocusListener {
        public void onCustomEditextfocus(int position);

    }
    public interface customChangeEditextfocusListener {
        public void onCustomChangeEditextfocus(int position);

    }


    public void setCustomButtonListner(customButtonListener listener) {
      this.customListner = listener;
  }


    public void setCustomEditextfocus(customEditextfocusListener   listener3) {
        this.customListner3 = listener3;
    }

    public void setChangeCustomEditextfocus(customChangeEditextfocusListener   listener4) {
        this.customListner4 = listener4;
    }



    private Context context;
    public ListAdapter_menu_inventory_plan(Context context, ArrayList<String> dataItem ) {
        super(context, R.layout.list_view_menu_inventory_plan, dataItem);
        this.context = context;


      this.context = context;  
  }
    ViewHolder viewHolder;
  @Override  
  public View getView(final int position, View convertView, ViewGroup parent ) {


      if (convertView == null) {  
          LayoutInflater inflater = LayoutInflater.from(context);
          convertView = inflater.inflate(R.layout.list_view_menu_inventory_plan, null);
          viewHolder = new ViewHolder();

          viewHolder.textview = (TextView) convertView .findViewById(R.id.textView54);
          viewHolder.button = (Button) convertView .findViewById(R.id.button_menu_inventoy_burnnfc);
          viewHolder.editText_spinner = (EditText) convertView .findViewById(R.id.editText_spinner_xml_inventory);
          viewHolder.mWatcher = new MutableWatcher();
          viewHolder.editText_spinner.addTextChangedListener(viewHolder.mWatcher);



    convertView.setTag(viewHolder);

      } else {
          Log.i("tes2",String.valueOf(position));
          viewHolder = (ViewHolder) convertView.getTag();  
      }

        final String temp = getItem(position);
        viewHolder.textview.setText(temp);

      viewHolder.button.setOnClickListener(new  View.OnClickListener() {

          @Override
          public void onClick(View v) {
              if (customListner != null) {
                  customListner.onButtonClickListner(position,temp);
              }

          }
      });



      viewHolder.editText_spinner.setOnFocusChangeListener(new View.OnFocusChangeListener() {
          @Override
          public void onFocusChange(View view, boolean b) {

              if (customListner3!= null) {
                  customListner3.onCustomEditextfocus(position);
              }

          }


      });



      viewHolder.mWatcher.setActive(false);
      viewHolder.editText_spinner.setText(variabelpublic.Data_menu_inventory_edittextammount.get(position) );

      viewHolder.mWatcher.setPosition(position);
      viewHolder.mWatcher.setActive(true);

      return convertView;  
  }



  public class ViewHolder {  
      TextView textview;
      Button button; 

      EditText editText_spinner;
      public MutableWatcher mWatcher;
  }
    class MutableWatcher implements TextWatcher {

        private int mPosition;
        private boolean mActive;

        void setPosition(int position) {
            mPosition = position;
        }

        void setActive(boolean active) {
            mActive = active;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (mActive) {
                variabelpublic.Data_menu_inventory_edittextammount.set(mPosition, s.toString());


            }


        }
    }
}  