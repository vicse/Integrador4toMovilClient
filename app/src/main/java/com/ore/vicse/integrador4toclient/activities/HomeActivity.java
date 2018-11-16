package com.ore.vicse.integrador4toclient.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.ore.vicse.integrador4toclient.R;
import com.ore.vicse.integrador4toclient.fragments.HomeFragment;
import com.ore.vicse.integrador4toclient.fragments.InfoFragment;
import com.ore.vicse.integrador4toclient.fragments.MapFragment;
import com.ore.vicse.integrador4toclient.fragments.ProductFragment;
import com.ore.vicse.integrador4toclient.fragments.ProviderFragment;

public class HomeActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setToolbar();

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navview);

        setFragmentByDefault();

        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
//                Toast.makeText(HomeActivity.this,"Open",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
//                Toast.makeText(HomeActivity.this,"Close",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                boolean fragmentTransaction = false;
                Fragment fragment = null;

                switch (item.getItemId()){
                    case R.id.menu_home:
                        fragment = new HomeFragment();
                        fragmentTransaction = true;
                        break;
                    case R.id.menu_provider:
                        fragment = new ProviderFragment();
                        fragmentTransaction = true;
                        break;
                    case R.id.menu_map:
                        fragment = new MapFragment();
                        fragmentTransaction = true;
                        break;
                    case R.id.menu_info:
                        fragment = new InfoFragment();
                        fragmentTransaction = true;
                        break;
                    case R.id.menu_conf:
                        Toast.makeText(HomeActivity.this,"Configuración",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_out:
                        Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                        startActivity(intent);
                        break;
                }

                if(fragmentTransaction){
                    changeFragment(fragment, item);
                    drawerLayout.closeDrawers();
                }
                return true;
            }
        });
    }

    private void setToolbar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon( R.drawable.ic_menu );
    }

    private void setFragmentByDefault(){
        changeFragment(new HomeFragment(), navigationView.getMenu().getItem(0));
    }


    private void changeFragment(Fragment fragment, MenuItem item){
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();
        item.setChecked(true);
        getSupportActionBar().setTitle(item.getTitle());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        switch (item.getItemId()){
            case android.R.id.home:
                //abrir el menu lateral
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}