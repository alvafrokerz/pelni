package inventory;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.StrictMode;
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

import com.example.main.ListAdapter_menu_inventory_plan;
import com.example.main.ListAdapter_menu_inventory_plan.customButtonListener;
import com.example.main.ListAdapter_menu_inventory_plan.customEditextfocusListener;
import com.example.main.R;
import com.example.main.variabelpublic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import voyage.menu_voyage_plan_addmenu;

import static android.R.attr.value;
import static com.example.main.R.id.tes;
import static com.example.main.variabelpublic.Data_menu_inventory_edittextammount;
import static com.example.main.variabelpublic.Data_menu_inventory_nfccode;


public class menu_inventory_plan extends  com.example.main.main_menu_template  implements AdapterView.OnItemSelectedListener,  customButtonListener, customEditextfocusListener  {
    NfcAdapter mAdapter;
    Tag mTag;
    PendingIntent mPI;
    IntentFilter[] mFilter;
    EditText mData, mRespond;
    Button read, write;
    String userData;
    ArrayAdapter<String> dataAdapter;
    ListView listView;
    ListAdapter_menu_inventory_plan adapter4 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout item = (LinearLayout)findViewById(tes);
        View child = getLayoutInflater().inflate(R.layout.menu_inventory_plan, null);
        item.addView(child);
        StrictMode.ThreadPolicy policy = new	StrictMode.ThreadPolicy.Builder() .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        mAdapter = NfcAdapter.getDefaultAdapter(this);
        mPI = PendingIntent.getActivity(getApplicationContext(), 0,
                new Intent(this,getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP),0	);
        IntentFilter tagDetected = new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED);
        IntentFilter filter2 = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);
        mFilter = new IntentFilter[]{tagDetected,filter2};
        EditText textsearch=(EditText)findViewById(R.id.editText_menu_inventory_search) ;
        final Spinner spinnerlist = (Spinner) findViewById(R.id.spinner_inventory_voyage);
        spinnerlist.setOnItemSelectedListener(this);

        List<String> categories = new ArrayList<String>();
        categories.add("Medan-Dumai");
        categories.add("Jakarta-Bandung");
        dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerlist.setAdapter(dataAdapter);


        listView = (ListView) findViewById(R.id.listviewmenu_voyage);

        ArrayList<String>  dataItems = new ArrayList<String>();
        dataItems.add("1");
        dataItems.add("3");
        dataItems.add("2");
        dataItems.add("4");

        Data_menu_inventory_nfccode=dataItems;
        for (int i = 0; i <Data_menu_inventory_nfccode.size() ; i++) {
          Data_menu_inventory_edittextammount.add(String.valueOf(i));
        }
        adapter4 = new ListAdapter_menu_inventory_plan(menu_inventory_plan.this, Data_menu_inventory_nfccode   );
        adapter4.setCustomButtonListner(menu_inventory_plan.this) ;
        adapter4.setCustomEditextfocus(menu_inventory_plan.this); ;
        listView.setAdapter(adapter4);


        Button neext = (Button) findViewById(R.id.button_menu_voyage_next);
        neext.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {

                Intent intent = new Intent(menu_inventory_plan.this, menu_voyage_plan_addmenu.class);
                finish();
                startActivity(intent);
            }
        });

        textsearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                menu_inventory_plan.this.adapter4.getFilter().filter(cs);
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
        Spinner spinnerlist = (Spinner) parent;
        if(spinnerlist.getId() == R.id.spinner_inventory_voyage)
        {
            String item = parent.getItemAtPosition(position).toString();

            // Showing selected spinner_custom item
            Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
        }
        else if(spinnerlist.getId() == R.id.spinner_kategori)
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
        if(mTag == null)
        {
            //Jika tidak ada smartcard disekitar HP, maka line ini akan dieksekusi
            Toast.makeText(getApplicationContext(), "g' ada smartcard...", Toast.LENGTH_SHORT).show();

        }else
        {
            String userData =value;
            NdefRecord[] records = {createRecord(userData.getBytes())};
            NdefMessage message = new NdefMessage(records);

            try {
                NdefFormatable mNdef = NdefFormatable.get(mTag);
                if(mNdef != null)
                {

                    mNdef.connect();
                    //mNdef.writeNdefMessage(message);
                    mNdef.format(message);
                    mNdef.close();

                }else
                {
                    Ndef mNdef2 = Ndef.get(mTag);
                    if(mNdef2 != null)
                    {
                        mNdef2.connect();
                        mNdef2.writeNdefMessage(message);
                        mNdef2.close();
                    }else
                    {
                        Toast.makeText(getApplicationContext(),"Penulisan data gagal",Toast.LENGTH_SHORT).show();
                    }
                }

            } catch (IOException e) {
                // TODO Auto-generated catch block
                //e.printStackTrace();
            } catch (FormatException e) {
                // TODO Auto-generated catch block
                //e.printStackTrace();
            }

            Toast.makeText(getApplicationContext(),"Data berhasil ditulis",Toast.LENGTH_SHORT).show();
        }
    }



    @Override
    public void onCustomEditextfocus(int position ) {


       variabelpublic.id_induk =position ;

        Log.i("re1","posisi= "+String.valueOf(position)+" value= "+value);
    }



    private NdefRecord createRecord(byte[] payload)
    {
        NdefRecord record = new NdefRecord(NdefRecord.TNF_WELL_KNOWN,
                NdefRecord.RTD_TEXT,
                new byte[0],
                payload
        );
        return record;
    }



    NdefMessage[] getNdefMessage(Intent intent)
    {
        NdefMessage[] msgs = null;

        Parcelable[] rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
        if(rawMsgs != null)
        {
            msgs = new NdefMessage[rawMsgs.length];
            for(int i=0; i<rawMsgs.length; i++)
            {
                msgs[i] = (NdefMessage)rawMsgs[i];
            }
        }
        //Log.i("Length Raw Message", Integer.toString(rawMsgs.length));
        return msgs;
    }







    @Override
    protected void onNewIntent(Intent intent) {
        // TODO Auto-generated method stub
        super.onNewIntent(intent);

        if(intent.getAction().equals(NfcAdapter.ACTION_NDEF_DISCOVERED))
        {
            Toast.makeText(getApplicationContext(),"Ndefdiscovered",Toast.LENGTH_SHORT).show();

        }else if(intent.getAction().equals(NfcAdapter.ACTION_TAG_DISCOVERED))
        {
            mTag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            Toast.makeText(getApplicationContext(),"Smartcard detected",Toast.LENGTH_SHORT).show();
            //Toast.makeText(getApplicationContext(),intent.getExtras().toString(),Toast.LENGTH_SHORT).show();
            NdefMessage[] messages = getNdefMessage(intent);
            if(messages == null)
            {
                Toast.makeText(getApplicationContext(),"Data di dalam kartu kosong",Toast.LENGTH_SHORT).show();
                return;
            }
            byte[] payload = messages[0].getRecords()[0].getPayload();
            userData = new String(payload);
            String u[]=userData.split("/");




            try {


            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }else
        {
            //Toast.makeText(getApplicationContext(),intent.getExtras().toString(),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        mAdapter.disableForegroundDispatch(this);
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        mAdapter.enableForegroundDispatch(this, mPI, mFilter, null);
    }

}
