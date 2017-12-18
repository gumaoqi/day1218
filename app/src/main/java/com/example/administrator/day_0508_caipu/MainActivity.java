package com.example.administrator.day_0508_caipu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import base.MyBaseActivity;
import lead.LeadActivity;

public class MainActivity extends MyBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inidata();
        setLayout();
        iniview();
        setview();
    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void iniview() {
        startActivity(LeadActivity.class, null, 0, 0);
        finish();
    }

    @Override
    public void setview() {

    }

    @Override
    public void inidata() {

    }
}
