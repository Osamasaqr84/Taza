package com.osamaomar.akhbarak.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.osamaomar.akhbarak.Helper.CheckLanguage;
import com.osamaomar.akhbarak.R;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new CheckLanguage(this);
        setContentView(R.layout.activity_setting);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



    }

    @Override
    protected void onStart() {
        new CheckLanguage(this);
        super.onStart();
    }
}
