package voyage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.main.ListAdapter_menu_voyage_plan_addmenu;
import com.example.main.ListAdapter_menu_voyage_plan_addmenu.customButtonListener;
import com.example.main.ListAdapter_menu_voyage_plan_addmenu.customButtonListener2;
import com.example.main.ListAdapter_menu_voyage_plan_addmenu.customEditextfocusListener;
import com.example.main.R;
import com.example.main.variabelpublic;

import static android.R.attr.value;
import static com.example.main.R.id.tes;
import static com.example.main.variabelpublic.Data_menu_voayage_class;
import static com.example.main.variabelpublic.Data_menu_voyage_addmenu;
import static com.example.main.variabelpublic.Data_menu_voyage_addmenu_day;
import static com.example.main.variabelpublic.Data_menu_voyage_addmenu_index;
import static com.example.main.variabelpublic.Data_menu_voyage_addmenu_totalday;
import static com.example.main.variabelpublic.Data_menu_voyage_tipemakan_index;
import static com.example.main.variabelpublic.Data_menu_voyage_tipemakan_total;
import static com.example.main.variabelpublic.Data_menu_voyage_tipemakans;


public class menu_voyage_plan_addmenu extends  com.example.main.main_menu_template  implements View.OnClickListener,customButtonListener2,customButtonListener, customEditextfocusListener  {


    ListView listView;
    ListAdapter_menu_voyage_plan_addmenu adapter4 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout item = (LinearLayout)findViewById(tes);
        View child = getLayoutInflater().inflate(R.layout.menu_voyage_plan_addmenu, null);
        item.addView(child);


        Data_menu_voyage_addmenu.add("nasi goreng");
        Data_menu_voyage_addmenu.add("nasi uduk");
        Data_menu_voyage_addmenu.add("nasi kuning");
        Data_menu_voyage_addmenu.add("nasi merah");
        Data_menu_voyage_addmenu.add("nasi Hitam");

        Data_menu_voyage_addmenu_index =new int[Data_menu_voyage_addmenu.size()];
        for (int i = 0; i < Data_menu_voyage_addmenu_index.length ; i++) {
            Data_menu_voyage_addmenu_index[i]=0;
        }

        listView = (ListView) findViewById(R.id.listviewmenu_voyage);


        adapter4 = new ListAdapter_menu_voyage_plan_addmenu(menu_voyage_plan_addmenu.this, Data_menu_voayage_class);
        adapter4.setCustomButtonListner(menu_voyage_plan_addmenu.this);
        adapter4.setCustomButtonListner2(menu_voyage_plan_addmenu.this) ;
        adapter4.setCustomEditextfocus(menu_voyage_plan_addmenu.this); ;
        listView.setAdapter(adapter4);

        TextView textview_menu_voyage_plan_addmenu_day=(TextView)findViewById(R.id.textview_menu_voyage_plan_addmenu_day) ;

        Button neext = (Button) findViewById(R.id.button_menu_voyage_addmenu_next);
        neext.setOnClickListener(this);

        textview_menu_voyage_plan_addmenu_day.setText("Day "+String.valueOf(Data_menu_voyage_addmenu_day)+" "+Data_menu_voyage_tipemakans[Data_menu_voyage_tipemakan_index]);

        Button previous = (Button) findViewById(R.id.button_menu_voyage_addmenu_previous);
        previous.setOnClickListener(this);


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
    public void onButtonClickListner(int position, String value) {
        Toast.makeText(menu_voyage_plan_addmenu.this, "y"+position , Toast.LENGTH_LONG).show();
        Log.i("ts","tr");
    }

    @Override
    public void onButtonClickListner2(int position, String value) {
        Toast.makeText(menu_voyage_plan_addmenu.this, "x"+position , Toast.LENGTH_LONG).show();
        Log.i("ts","tr");
    }

    @Override
    public void onCustomEditextfocus(int position ) {


       variabelpublic.id_induk =position ;

        Log.i("re1","posisi= "+String.valueOf(position)+" value= "+value);
    }


    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.button_menu_voyage_addmenu_next:
                if(  Data_menu_voyage_addmenu_day==Data_menu_voyage_addmenu_totalday & Data_menu_voyage_tipemakan_index==Data_menu_voyage_tipemakan_total-1 ){
/*
                    for (int i = 0; i <Data_menu_voyage_addmenu_totalday ; i++) {
                        for (int j = 0; j <Data_menu_voayage_class.size() ; j++) {
                              System.out.print(String_database_menu_voyage_menu[i][j]+",");

                        }
                        System.out.println();
                    }*/
                    intent = new Intent(menu_voyage_plan_addmenu.this, menu_voyage_plan_finish.class);
                    finish();
                    startActivity(intent);
                }else{
if(Data_menu_voyage_tipemakan_index==Data_menu_voyage_tipemakan_total-1){
    Data_menu_voyage_addmenu_day=Data_menu_voyage_addmenu_day+1;
    Data_menu_voyage_addmenu.clear();
    Data_menu_voyage_tipemakan_index=0;
}else if(Data_menu_voyage_tipemakan_index==Data_menu_voyage_tipemakan_total-1 & Data_menu_voyage_addmenu_day==Data_menu_voyage_addmenu_totalday){

}else{
    Data_menu_voyage_tipemakan_index=Data_menu_voyage_tipemakan_index+1;

}

                    intent = new Intent(menu_voyage_plan_addmenu.this, menu_voyage_plan_addmenu.class);
                    finish();
                    startActivity(intent);}




                break;
            case R.id.button_menu_voyage_addmenu_previous:

                if(  Data_menu_voyage_addmenu_day==1){
                intent = new Intent(menu_voyage_plan_addmenu.this, menu_voyage_plan.class);
                finish();
                startActivity(intent);}else{
                   Data_menu_voyage_addmenu_day=Data_menu_voyage_addmenu_day-1;
                    Data_menu_voyage_tipemakan_index=Data_menu_voyage_tipemakan_index-1;
                    intent = new Intent(menu_voyage_plan_addmenu.this, menu_voyage_plan_addmenu.class);
                    finish();
                    startActivity(intent);
                }

                break;
            case View.NO_ID:
            default:
                // TODO Auto-generated method stub
                break;
        }
    }

}
