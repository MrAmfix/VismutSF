package com.example.vismutsf;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


import controlPackage.DevDialog;

public class StartActivity extends AppCompatActivity {
    Button transfer, math, developer, settings;
    public static SharedPreferences valueSettings;
    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        transfer = findViewById(R.id.button_transfer);
        math = findViewById(R.id.button_math);
        settings = findViewById(R.id.button_settings);
        developer = findViewById(R.id.button_info_developer);
        valueSettings = getSharedPreferences("SETTINGS_SAVE",MODE_PRIVATE);

        transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, TransferActivity.class);
                startActivity(intent);
            }
        });
        math.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, MathActivity.class);
                startActivity(intent);
            }
        });
        developer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDialog();
            }
        });
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });
    }
    protected void createDialog(){
        FragmentManager manager = getSupportFragmentManager();
        DevDialog dialogDev = new DevDialog();
        dialogDev.show(manager, "DialogDev");
    }
}