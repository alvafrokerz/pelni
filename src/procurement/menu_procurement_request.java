package procurement;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.main.ListAdapter;
import com.example.main.ListAdapter.customButtonListener;
import com.example.main.ListAdapter.customButtonListener2;
import com.example.main.ListAdapter.customEditextfocusListener;
import com.example.main.R;
import com.example.main.variabelpublic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static android.R.attr.value;
import static com.example.main.R.id.tes;


public class menu_procurement_request extends  com.example.main.main_menu_template  implements AdapterView.OnItemSelectedListener, customButtonListener2,customButtonListener, customEditextfocusListener  {
        String menu_makan []={"daging ayam","daging ikan","daging sapi","daging kucin","daging burung","daging ular"};
    String stock []={"","","","","",""};
      ArrayList<String> dataItems2,dataItems;
    ListView listView;
    ListAdapter adapter4 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout item = (LinearLayout)findViewById(tes);
        View child = getLayoutInflater().inflate(R.layout.menu_procurement_request, null);
        item.addView(child);

         Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        EditText texttes =(EditText)findViewById(R.id.editTextsearch);
        List<String> categories = new ArrayList<String>();
        categories.add("Medan-Dumai");
        categories.add("Jakarta-Bandung");


        // Creating adapter for spinner_custom
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner_custom
        spinner.setAdapter(dataAdapter);

        Spinner spinner2 = (Spinner) findViewById(R.id.spinner_kategori);
        spinner2.setOnItemSelectedListener(this);

        List<String> categories2 = new ArrayList<String>();
        categories2.add("Buah");
        categories2.add("minuman");
        // Creating adapter for spinner_custom
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);

        // Drop down layout style - list view with radio button
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner_custom
        spinner2.setAdapter(dataAdapter2);

        String[] from = {  "textview", "textview2"  };
        int[] to = {  R.id.spinner_list_view, R.id.editText_spinner_xml  };

        List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> hm = new HashMap<String, String>();
        for (int i=0;i<menu_makan.length;i++){
            hm = new HashMap<String, String>();
            hm.put("textview",menu_makan[i]);
            hm.put("textview2", String.valueOf(stock[i]));

            aList.add(hm);

        }

         dataItems = new ArrayList<String>();
        String[] dataArray = menu_makan;
        List<String> dataTemp = Arrays.asList(dataArray);
        dataItems.addAll(dataTemp);

          dataItems2 = new ArrayList<String>();
        String[] dataArray2 = stock;
        List<String> dataTemp2 = Arrays.asList(dataArray2);
        dataItems2.addAll(dataTemp2);


        /*SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), aList,
                R.layout.list_view_menu, from, to);
*/
         listView = (ListView) findViewById(R.id.listviewmenu);
      //  listView.setAdapter(adapter);

        adapter4 = new ListAdapter(menu_procurement_request.this,dataItems   );
        variabelpublic.Data_id=dataItems2;
        variabelpublic.Data_menu=dataItems;
        variabelpublic.Data_menu_index =new int[variabelpublic.Data_menu.size()];
        for (int i = 0; i <variabelpublic.Data_menu_index.length ; i++) {
            variabelpublic.Data_menu_index[i]=i;
        }

        adapter4.setCustomButtonListner(menu_procurement_request.this);
        adapter4.setCustomButtonListner2(menu_procurement_request.this) ;
        adapter4.setCustomEditextfocus(menu_procurement_request.this); ;


        listView.setAdapter(adapter4);
       Button start=(Button)findViewById(R.id.request);
        start.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {

                for (int i = 0; i < variabelpublic.Data_menu_index.length ; i++) {
                    Log.i("tessss",variabelpublic.Data_menu.get(variabelpublic.Data_menu_index[i]));
                }

            }
        });
texttes.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                menu_procurement_request.this.adapter4.getFilter().filter(cs);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
            }
        });

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

    }

    @Override
    public void onButtonClickListner2(int position, String value) {
        Toast.makeText(menu_procurement_request.this, "rrrrrrrrr "  , Toast.LENGTH_LONG).show();
        Log.i("ts","tr");
    }

    @Override
    public void onCustomEditextfocus(int position ) {


       variabelpublic.id_induk =position ;

        Log.i("re1","posisi= "+String.valueOf(position)+" value= "+value);
    }





}
