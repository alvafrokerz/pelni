package com.example.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import inventory.menu_inventory;
import procurement.menu_procurement;
import voyage.menu_voyage;

import static com.example.main.R.id.imageButton_main_menu_inventory;
import static com.example.main.R.id.imageButton_main_menu_people;


public class menu extends main_menu_template implements  View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout item = (LinearLayout)findViewById(R.id.tes);
        View child = getLayoutInflater().inflate(R.layout.menu_main, null);
        item.addView(child);
        ImageButton imageButton_main_menu_home = (ImageButton) findViewById(R.id.imageButton_main_menu_home);
        imageButton_main_menu_home.setOnClickListener(this);

        ImageButton imageButton_main_menu_voyage = (ImageButton) findViewById(R.id.imageButton_main_menu_voyage);
        imageButton_main_menu_voyage.setOnClickListener(this);

        ImageButton imageButton_main_menu_rekusisi = (ImageButton) findViewById(R.id.imageButton_main_menu_rekusisi);
        imageButton_main_menu_rekusisi.setOnClickListener(this);

        ImageButton imageButton_main_menu_waste = (ImageButton) findViewById(R.id.imageButton_main_menu_waste);
        imageButton_main_menu_waste.setOnClickListener(this);

        ImageButton imageButton_main_menu_inventory = (ImageButton) findViewById(R.id.imageButton_main_menu_inventory);
        imageButton_main_menu_inventory.setOnClickListener(this);

        ImageButton imageButton_main_menu_people = (ImageButton) findViewById(R.id.imageButton_main_menu_people);
        imageButton_main_menu_people.setOnClickListener(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        toolbar.setTitle("tes");



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
    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {
            case R.id.imageButton_main_menu_home:
                 i = new Intent(menu.this,menu.class);
                finish();
                startActivity(i);
                break;
            case R.id.imageButton_main_menu_voyage:
                i = new Intent(menu.this, menu_voyage.class);
                finish();
                startActivity(i);
                break;
            case R.id.imageButton_main_menu_rekusisi:
                i = new Intent(menu.this, menu_procurement.class);
                finish();
                startActivity(i);
                break;
            case R.id.imageButton_main_menu_waste:

                break;
            case imageButton_main_menu_inventory:
                i = new Intent(menu.this, menu_inventory.class);
                finish();
                startActivity(i);
                break;
            case imageButton_main_menu_people:

                break;
            case View.NO_ID:
            default:

                break;
        }
    }

}
