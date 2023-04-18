package com.example.vismutsf;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    public String[] Signex = {"1   ", "2   ", "3   ", "4   ","5   ","6   ","7   ","8   ","9   ","10  "};
    CheckBox exMode, exOutPut;
    Spinner countSign;
    //public static SharedPreferences settings;
    Button saveb;
    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        exMode = findViewById(R.id.exmode);
        exOutPut = findViewById(R.id.specialoutput);
        countSign = findViewById(R.id.countsignex);
        saveb = findViewById(R.id.savebutton);
        ArrayAdapter<String> AllowSignex = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Signex);
        AllowSignex.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countSign.setAdapter(AllowSignex);
        //settings = getSharedPreferences("SETTINGS_SAVE",MODE_PRIVATE);
        exMode.setChecked(StartActivity.valueSettings.getBoolean("EX_MODE", false));
        exOutPut.setChecked(StartActivity.valueSettings.getBoolean("EX_OUTPUT", false));
        countSign.setSelection(StartActivity.valueSettings.getInt("EX_COUNT", 1) - 1);
        saveb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = StartActivity.valueSettings.edit();
                editor.putBoolean("EX_MODE", exMode.isChecked());
                editor.putBoolean("EX_OUTPUT", exOutPut.isChecked());
                editor.putInt("EX_COUNT", Integer.parseInt(countSign.getSelectedItem().toString().trim()));
                editor.apply();
                Toast.makeText(SettingsActivity.this, "Настройки сохранены", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public static boolean getOutPut(){
        return StartActivity.valueSettings.getBoolean("EX_OUTPUT", false);
    }
}