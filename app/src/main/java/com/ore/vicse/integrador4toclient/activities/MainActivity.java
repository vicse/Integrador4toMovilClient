package com.ore.vicse.integrador4toclient.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ore.vicse.integrador4toclient.R;

public class MainActivity extends AppCompatActivity {

    private TextView btnClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnClient = (TextView) findViewById(R.id.txtRegistrarClient);
    }

    public void showRegisterClient(View view){
        Intent intent = new Intent(MainActivity.this, RegisterClientActivity.class);
        startActivity(intent);
    }

    public void Login(View view){
        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
        startActivity(intent);
    }
}
