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

public class ListAdapter_menu_voyage_plan extends ArrayAdapter<String> {
  customButtonListener customListner;
  customButtonListener2  customListner2;
     customEditextfocusListener customListner3;
    customChangeEditextfocusListener customListner4;
String nilai;



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
        public void onCustomEditextfocus(int position);

    }
    public interface customChangeEditextfocusListener {
        public void onCustomChangeEditextfocus(int position);

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
    public ListAdapter_menu_voyage_plan(Context context, ArrayList<String> dataItem ) {
        super(context, R.layout.list_view_menu_voyage_plan, dataItem);
        this.context = context;


      this.context = context;  
  }
    ViewHolder viewHolder;
  @Override  
  public View getView(final int position, View convertView, ViewGroup parent ) {


      if (convertView == null) {  
          LayoutInflater inflater = LayoutInflater.from(context);
          convertView = inflater.inflate(R.layout.list_view_menu_voyage_plan, null);
          viewHolder = new ViewHolder();

          viewHolder.textview = (TextView) convertView .findViewById(R.id.textView54);
          viewHolder.editText_spinner = (EditText) convertView .findViewById(R.id.editText_spinner_xml_voyage);
          viewHolder.button = (Button) convertView .findViewById(R.id.button_menu_voyage_plus);
          viewHolder.button2 = (Button) convertView .findViewById(R.id.button_menu_voyage_min);
          viewHolder.mWatcher = new MutableWatcher();
          viewHolder.editText_spinner.addTextChangedListener(viewHolder.mWatcher);

            viewHolder.textview.setText(getItem(position));
          viewHolder.button.setTag(position);
             convertView.setTag(viewHolder);
          final String temp="ss";

          viewHolder.button.setTag(position);
          viewHolder.button.setOnClickListener(new  View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  if (customListner != null) {
                      int position=(Integer)v.getTag();
                      Log.i("rr",String.valueOf(position));
                      customListner.onButtonClickListner(position,temp);
                  }

              }
          });
          viewHolder.button2.setTag(position);
          viewHolder.button2.setOnClickListener(new  View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  if (customListner2 != null) {
                      int position=(Integer)v.getTag();
                      Log.i("rr",String.valueOf(position));
                      customListner2.onButtonClickListner2(position,temp);
                  }

              }
          });
      } else {

          viewHolder = (ViewHolder) convertView.getTag();  
      }










      viewHolder.editText_spinner.setOnFocusChangeListener(new View.OnFocusChangeListener() {
          @Override
          public void onFocusChange(View view, boolean b) {

              if (customListner3!= null) {
                  customListner3.onCustomEditextfocus(position);
              }

          }


      });




      viewHolder.mWatcher.setActive(false);
      viewHolder.editText_spinner.setText(variabelpublic.Data_menu_voayage_class_ammount.get(position));

      viewHolder.mWatcher.setPosition(position);
      viewHolder.mWatcher.setActive(true);

      return convertView;  
  }



  public class ViewHolder {
     TextView textview;
      Button button;
      Button button2;
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
                variabelpublic.Data_menu_voayage_class_ammount.set(mPosition, s.toString());


            }


        }
    }
}