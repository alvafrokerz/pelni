package voyage;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.main.ListAdapter_menu_voyage_plan;
import com.example.main.ListAdapter_menu_voyage_plan.customButtonListener;
import com.example.main.ListAdapter_menu_voyage_plan.customButtonListener2;
import com.example.main.ListAdapter_menu_voyage_plan.customEditextfocusListener;
import com.example.main.R;
import com.example.main.variabelpublic;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import static android.R.attr.value;
import static com.example.main.R.id.tes;
import static com.example.main.variabelpublic.Data_menu_voayage_class;
import static com.example.main.variabelpublic.Data_menu_voayage_class_ammount;
import static com.example.main.variabelpublic.Data_menu_voayage_class_voyage_index;
import static com.example.main.variabelpublic.Data_menu_voyage_addmenu_day;
import static com.example.main.variabelpublic.Data_menu_voyage_addmenu_totalday;
import static com.example.main.variabelpublic.Data_menu_voyage_jam;
import static com.example.main.variabelpublic.Data_menu_voyage_tanggal;
import static com.example.main.variabelpublic.Data_menu_voyage_tipemakan_index;
import static com.example.main.variabelpublic.Data_menu_voyage_tipemakan_total;
import static com.example.main.variabelpublic.String_database_menu_voyage_menu;


public class menu_voyage_plan extends com.example.main.main_menu_template  implements AdapterView.OnItemSelectedListener, customButtonListener2,customButtonListener, customEditextfocusListener, View.OnTouchListener {
     TimePickerDialog.OnTimeSetListener time;
     DatePickerDialog.OnDateSetListener date;

    ArrayAdapter<String> dataAdapter;
    ListView listView;
    ListAdapter_menu_voyage_plan adapter4 = null;
    public EditText editText_menu_voyage_date,editText_menu_voyage_time,editText_menu_voyage_origin,editText_menu_voyage_to,editText_menu_voyage_pilihkapal;
    public Calendar myCalendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout item = (LinearLayout)findViewById(tes);
        View child = getLayoutInflater().inflate(R.layout.menu_voyage_plan, null);
        item.addView(child);
        editText_menu_voyage_date=(EditText)findViewById(R.id.editText_menu_voyage_date);
        editText_menu_voyage_time=(EditText)findViewById(R.id.editText_menu_voyage_time);
        editText_menu_voyage_origin=(EditText)findViewById(R.id.editText_menu_voyage_origin);
        editText_menu_voyage_to=(EditText)findViewById(R.id.editText_menu_voyage_to);
        editText_menu_voyage_pilihkapal=(EditText)findViewById(R.id.editText_menu_voyage_pilihkapal);

        myCalendar = Calendar.getInstance();
        time = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hours, int minute) {

                myCalendar.set(Calendar.HOUR, hours);
                myCalendar.set(Calendar.MINUTE, minute);

                updateLabeltime();
            }
        };
        date = new
                DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        // TODO Auto-generated method stub
                        myCalendar.set(Calendar.YEAR, year);
                        myCalendar.set(Calendar.MONTH, monthOfYear);
                        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        updateLabeldate();
                    }};

        editText_menu_voyage_time.setOnTouchListener(this);
        editText_menu_voyage_date.setOnTouchListener(this);
        editText_menu_voyage_origin.setOnTouchListener(this);
        editText_menu_voyage_to.setOnTouchListener(this);
        editText_menu_voyage_pilihkapal.setOnTouchListener(this);

        editText_menu_voyage_time.setKeyListener(null);
        editText_menu_voyage_date.setKeyListener(null);
        editText_menu_voyage_origin.setKeyListener(null);
        editText_menu_voyage_to.setKeyListener(null);
        editText_menu_voyage_pilihkapal.setKeyListener(null);

        Data_menu_voyage_addmenu_totalday=3;
        Data_menu_voyage_tipemakan_total=3;
        final Spinner spinner_menu_voyage_plan_class = (Spinner) findViewById(R.id.spinner_menu_voyage_plan_class);
        List<String> classmenu = new ArrayList<String>();
        classmenu.add("kelas A");
        classmenu.add("kelas B");
        dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, classmenu);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_menu_voyage_plan_class.setAdapter(dataAdapter);

        listView = (ListView) findViewById(R.id.listviewmenu_voyage);

if(Data_menu_voayage_class.size()>1) {
    Data_menu_voayage_class_voyage_index = new int[Data_menu_voayage_class.size()];
    for (int i = 0; i < Data_menu_voayage_class_voyage_index.length; i++) {
        Data_menu_voayage_class_voyage_index[i] = i;
    }
    adapter4 = new ListAdapter_menu_voyage_plan(menu_voyage_plan.this, Data_menu_voayage_class);
    adapter4.setCustomButtonListner(menu_voyage_plan.this);
    adapter4.setCustomButtonListner2(menu_voyage_plan.this);
    adapter4.setCustomEditextfocus(menu_voyage_plan.this);
    ViewGroup.LayoutParams listViewParams = (ViewGroup.LayoutParams)listView.getLayoutParams();
    listViewParams.height = (Data_menu_voayage_class_voyage_index.length+1)*70;
    listView.requestLayout();
    listView.setAdapter(adapter4);
}

        String_database_menu_voyage_menu=new String [Data_menu_voyage_addmenu_totalday][Data_menu_voyage_tipemakan_total][Data_menu_voayage_class.size()];

        Button neext = (Button) findViewById(R.id.button_menu_voyage_next);
        neext.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                Data_menu_voyage_addmenu_day=1;
                Data_menu_voyage_tipemakan_index=0;
                for (int i = 0; i < Data_menu_voyage_addmenu_totalday; i++) {
                for (int j = 0; j <Data_menu_voayage_class.size() ; j++) {
                    for (int k = 0; k <Data_menu_voyage_tipemakan_total ; k++) {
                        String_database_menu_voyage_menu[i][k][j] ="0";
                    }

                }}

                Log.i("ss",variabelpublic.Data_menu_voyage_origin+"-"+variabelpublic.Data_menu_voyage_to);
                Intent intent = new Intent(menu_voyage_plan.this, menu_voyage_plan_addmenu.class);
                finish();
                startActivity(intent);
            }
        });

        Button start = (Button) findViewById(R.id.add);
        start.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {


int index= Data_menu_voayage_class.size() ;
 if (Data_menu_voayage_class.size()==0){
     Data_menu_voayage_class.add(0,spinner_menu_voyage_plan_class.getSelectedItem().toString());
     Data_menu_voayage_class_ammount.add(0,"0");
     ViewGroup.LayoutParams listViewParams = (ViewGroup.LayoutParams)listView.getLayoutParams();
     listViewParams.height = 70;
     listView.requestLayout();
     Log.i("ee1",String.valueOf(Data_menu_voayage_class_ammount.size()));

 }else{
     Data_menu_voayage_class.add(index,spinner_menu_voyage_plan_class.getSelectedItem().toString());
     Data_menu_voayage_class_ammount.add(index,"0");

 }
                String_database_menu_voyage_menu=new String [Data_menu_voyage_addmenu_totalday][Data_menu_voyage_tipemakan_total][Data_menu_voayage_class.size()];


                Log.i("ee",String.valueOf(Data_menu_voayage_class_ammount.size()));
                adapter4 = new ListAdapter_menu_voyage_plan(menu_voyage_plan.this, Data_menu_voayage_class   );
                Data_menu_voayage_class_voyage_index =new int[index];

                for (int i = 0; i < Data_menu_voayage_class_voyage_index.length ; i++) {
                    Data_menu_voayage_class_voyage_index[i]=i;

                }


                ViewGroup.LayoutParams listViewParams = (ViewGroup.LayoutParams)listView.getLayoutParams();
                listViewParams.height = (Data_menu_voayage_class_voyage_index.length+1)*70;

                adapter4.setCustomButtonListner(menu_voyage_plan.this);
                adapter4.setCustomButtonListner2(menu_voyage_plan.this) ;
                adapter4.setCustomEditextfocus(menu_voyage_plan.this); ;
                listView.setAdapter(adapter4);
                listView.requestLayout();
            }
        });


    }
    private void updateLabeldate() {

        String myFormat = "dd.MM.yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        editText_menu_voyage_date.setText(sdf.format(myCalendar.getTime()));
        Data_menu_voyage_tanggal=sdf.format(myCalendar.getTime());
    }
    private void updateLabeltime() {

        String myFormat = "HH:mm"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        editText_menu_voyage_time.setText(sdf.format(myCalendar.getTime()));
        Data_menu_voyage_jam=sdf.format(myCalendar.getTime());
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the main_menu_template; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l  ) {
        Spinner spinner = (Spinner) parent;
        if(spinner.getId() == R.id.spinner)
        {
            String item = parent.getItemAtPosition(position).toString();

            // Showing selected spinner_custom item
            Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
        }
        else if(spinner.getId() == R.id.spinner_kategori)
        {
            String item = parent.getItemAtPosition(position).toString();

            // Showing selected spinner_custom item
            Toast.makeText(parent.getContext(), "Selected2: " + item, Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onButtonClickListner(int position, String value) {
        int plus=Integer.parseInt(Data_menu_voayage_class_ammount.get(position))+1;
        Data_menu_voayage_class_ammount.set(position, String.valueOf(plus));
         adapter4 = new ListAdapter_menu_voyage_plan(menu_voyage_plan.this, Data_menu_voayage_class);
        adapter4.setCustomButtonListner(menu_voyage_plan.this);
        adapter4.setCustomButtonListner2(menu_voyage_plan.this);
        adapter4.setCustomEditextfocus(menu_voyage_plan.this);
        ViewGroup.LayoutParams listViewParams = (ViewGroup.LayoutParams)listView.getLayoutParams();
        listViewParams.height = (Data_menu_voayage_class_voyage_index.length+1)*70;
        listView.requestLayout();
        listView.setAdapter(adapter4);
        Log.i("ts","tr");
    }

    @Override
    public void onButtonClickListner2(int position, String value) {

        int min=Integer.parseInt(Data_menu_voayage_class_ammount.get(position))-1;
        if(min >=0){
        Data_menu_voayage_class_ammount.set(position, String.valueOf(min));
        adapter4 = new ListAdapter_menu_voyage_plan(menu_voyage_plan.this, Data_menu_voayage_class);
        adapter4.setCustomButtonListner(menu_voyage_plan.this);
        adapter4.setCustomButtonListner2(menu_voyage_plan.this);
        adapter4.setCustomEditextfocus(menu_voyage_plan.this);
        ViewGroup.LayoutParams listViewParams = (ViewGroup.LayoutParams)listView.getLayoutParams();
            listViewParams.height = (Data_menu_voayage_class_voyage_index.length+1)*70;
        listView.requestLayout();
        listView.setAdapter(adapter4);
        Log.i("ts","tr");
    }

    }

    @Override
    public void onCustomEditextfocus(int position ) {


       variabelpublic.id_induk =position ;

        Log.i("re1","posisi= "+String.valueOf(position)+" value= "+value);
    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        EditText edittext = (EditText) view;

        if(edittext.getId() == R.id.editText_menu_voyage_time){
            if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                new  TimePickerDialog(menu_voyage_plan.this,time,myCalendar.get(Calendar.HOUR),myCalendar.get(Calendar.MINUTE), DateFormat.is24HourFormat(menu_voyage_plan.this)).show();
            }
        }   else if(edittext.getId()== R.id.editText_menu_voyage_date){
            if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                new DatePickerDialog(menu_voyage_plan.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        }
            else if(edittext.getId()==R.id.editText_menu_voyage_origin){
            if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            AlertDialog.Builder b = new AlertDialog.Builder(menu_voyage_plan.this);
            b.setTitle("berangkat");
                final String[] types = {"Medan", "Dumai"};
            b.setItems(types, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {

                    dialog.dismiss();
                    Log.i("tes",String.valueOf(which));
                    editText_menu_voyage_origin.setText( types[which] );
                    variabelpublic.Data_menu_voyage_origin=types[which];
                }

            });
            b.show();}
        } else if(edittext.getId()==R.id.editText_menu_voyage_to){
            if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            AlertDialog.Builder b = new AlertDialog.Builder(menu_voyage_plan.this);
            b.setTitle("tujuan");
            final String[] types = {"Medan", "Dumai"};
            b.setItems(types, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {

                    dialog.dismiss();
                    editText_menu_voyage_to.setText( types[which] );
                    variabelpublic.Data_menu_voyage_to=types[which];
                }

            });
            b.show();}
        } else if(edittext.getId()==R.id.editText_menu_voyage_pilihkapal){
            if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                AlertDialog.Builder b = new AlertDialog.Builder(menu_voyage_plan.this);
                b.setTitle("tujuan");
                final String[] types = {"Kapal api", "kapal B"};
                b.setItems(types, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                        editText_menu_voyage_pilihkapal.setText( types[which] );
                        variabelpublic.Data_menu_voyage_kapal=types[which];
                    }

                });
                b.show();}
        }
        return false;
    }
}
