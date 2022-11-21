package com.example.multiplelanguageapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.app_name));


        Button changeLang = findViewById(R.id.changelang);
        changeLang.setOnClickListener (new View.OnClickListener(){
            @Override
            public void onClick(View view){
                
                showChangeLanguageDialog();

            }
        });
    }

    private void showChangeLanguageDialog() {
        final String [] listItems = {"Afrikaans","Zulu","English"};
        Context context;
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(context: MainActivity.this);
        mBuilder.setTitle("Choose Language...");
        mBuilder.setSingleChoiceItems(listItems,  -1, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i){
                if (i == 0){
                    setLocale("af-rZA");
                    recreate();
                }
                else if (i == 1) {
                    setLocale("zu-rZA");
                    recreate();
                }
                else if (i == 2) {
                    setLocale("en");
                    recreate();
                }

                dialogInterface.dismiss();

            }
        });
        AlertDialog mDialog = mBuilder.create)();
        mDialog.show();
    }

    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());


        SharedPreferences.Editor editor = getSharedPreferences("Settings",MODE_PRIVATE).edit();
        editor.putString("My_Lang",lang);
        editor.apply();
    }
    public void loadLocale(){
        SharedPreferences prefs = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = prefs.getString("My_Lang","");
        setLocale(language);
    }
}