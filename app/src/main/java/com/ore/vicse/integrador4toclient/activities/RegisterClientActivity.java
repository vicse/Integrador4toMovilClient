package com.ore.vicse.integrador4toclient.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.ore.vicse.integrador4toclient.R;

public class RegisterClientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_client);
    }

    public void showLogin(View view){
        Intent intent = new Intent(RegisterClientActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
