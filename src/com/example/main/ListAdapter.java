package com.example.main;


import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ListAdapter extends ArrayAdapter<String> {
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
    public ListAdapter(Context context, ArrayList<String> dataItem ) {
        super(context, R.layout.list_view_menu, dataItem);
        this.context = context;


      this.context = context;  
  }
    ViewHolder viewHolder;
  @Override  
  public View getView(final int position, View convertView, ViewGroup parent ) {


      if (convertView == null) {  
          LayoutInflater inflater = LayoutInflater.from(context);
          convertView = inflater.inflate(R.layout.list_view_menu, null);
          viewHolder = new ViewHolder();

          viewHolder.spinner_list = (Spinner) convertView.findViewById(R.id.spinner_list_view);
          viewHolder.button = (Button) convertView .findViewById(R.id.button_plus);
          viewHolder.button2 = (Button) convertView .findViewById(R.id.button_min);
          viewHolder.editText_spinner = (EditText) convertView .findViewById(R.id.editText_spinner_xml);
          viewHolder.mWatcher = new MutableWatcher();
          viewHolder.editText_spinner.addTextChangedListener(viewHolder.mWatcher);



    convertView.setTag(viewHolder);

      } else {
          Log.i("tes2",String.valueOf(position));
          viewHolder = (ViewHolder) convertView.getTag();  
      }

      List<String> categories = new ArrayList<String>();
      Log.i("tes",String.valueOf(position));
      //viewHolder.text.setText(temp);

      for (int i = 0; i <variabelpublic.Data_menu.size() ; i++) {
          final String temp2 =  variabelpublic.Data_menu.get(i);

          categories.add(temp2);

      }





      // Creating adapter for spinner_custom
      ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, android.R.layout.select_dialog_item, categories);

      // Drop down layout style - list view with radio button
      dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

      Log.i("tes3",String.valueOf(position));
      // attaching data adapter to spinner_custom
      viewHolder.spinner_list.setAdapter(dataAdapter);
      viewHolder.spinner_list.setSelection(variabelpublic.Data_menu_index[position]);
      viewHolder.spinner_list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
          @Override
          public void onItemSelected(AdapterView<?> adapterView, View view, int posisi, long l) {
              variabelpublic.Data_menu_index[position]=posisi;

              Log.i("tes4",String.valueOf(posisi));
              Log.i("tes5",String.valueOf(position));
              Log.i("tes5", String.valueOf(variabelpublic.Data_menu_index[position]));
          }

          @Override
          public void onNothingSelected(AdapterView<?> adapterView) {

          }
      });






      final String temp =  variabelpublic.Data_menu.get(position);


      viewHolder.button.setOnClickListener(new  View.OnClickListener() {

          @Override
          public void onClick(View v) {
              if (customListner != null) {
                  customListner.onButtonClickListner(position,temp);
              }

          }
      });

      viewHolder.button2.setOnClickListener(new  View.OnClickListener() {

          @Override
          public void onClick(View v) {
              if (customListner2 != null) {
                  customListner2.onButtonClickListner2(position,temp);
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

      if (viewHolder.spinner_list.getTag() != null){
          viewHolder.spinner_list.setSelection(Integer.parseInt(viewHolder.spinner_list.getTag().toString()));
      }


      viewHolder.mWatcher.setActive(false);
      viewHolder.editText_spinner.setText(variabelpublic.Data_id.get(position) );

      viewHolder.mWatcher.setPosition(position);
      viewHolder.mWatcher.setActive(true);

      return convertView;  
  }



  public class ViewHolder {  
      Spinner spinner_list;
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
                variabelpublic.Data_id.set(mPosition, s.toString());


            }


        }
    }
}  