package procurement;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.main.ListAdapter;
import com.example.main.R;

import java.util.ArrayList;

import static com.example.main.R.id.tes;


public class menu_procurement_download extends  com.example.main.main_menu_template  implements AdapterView.OnItemSelectedListener {
        String menu_makan []={"daging ayam","daging ikan","daging sapi","daging ayam","daging ikan","daging sapi"};
    String stock []={"","","","","",""};
      ArrayList<String> dataItems2,dataItems;
    ListView listView;
    ListAdapter adapter4 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout item = (LinearLayout)findViewById(tes);
        View child = getLayoutInflater().inflate(R.layout.menu_procurement_download, null);
        item.addView(child);


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




}
