package com.example.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import inventory.menu_inventory;
import procurement.menu_procurement;
import voyage.menu_voyage;

/**
 * Created by ALVA on 9/19/2016.
 */

public class main_menu_template extends  AppCompatActivity{

        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);


        setContentView(R.layout.main_menu_template);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);


        drawer.setDrawerListener(toggle);
        toggle.syncState();
        final main_menu_template a= new main_menu_template();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

            navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.Procurement) {
                    Intent i = new Intent(main_menu_template.this, menu_procurement.class);
                    finish();
                    startActivity(i);
                }
                else if (id == R.id.Voyage) {
                    Intent i = new Intent(main_menu_template.this,menu_voyage.class);
                    finish();
                    startActivity(i);
                }
                else if (id == R.id.Inventory) {
                    Intent i = new Intent(main_menu_template.this,menu_inventory.class);
                    finish();
                    startActivity(i);
                }
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }}

