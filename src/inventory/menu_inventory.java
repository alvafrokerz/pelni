package inventory;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.main.R;


public class menu_inventory extends com.example.main.main_menu_template {
    public LinearLayout item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

          item = (LinearLayout)findViewById(R.id.tes);
        View child = getLayoutInflater().inflate(R.layout.menu_inventory, null);
        item.addView(child);

        ImageButton button_menu_procurement_request = (ImageButton) findViewById(R.id.button_menu_inventory_plan);
        button_menu_procurement_request.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {

                Intent i = new Intent(menu_inventory.this,menu_inventory_plan.class);
                finish();
                startActivity(i);

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

}
