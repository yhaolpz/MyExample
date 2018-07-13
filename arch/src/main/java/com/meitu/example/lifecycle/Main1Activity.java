package com.meitu.example.lifecycle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.meitu.example.arch.R;

public class Main1Activity extends AppCompatActivity {

    private String mPageId = "Main1Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
    }

    @Override
    protected void onResume() {
        super.onResume();
        StatisticsManager.onStartPage(mPageId);
    }

    @Override
    protected void onPause() {
        super.onPause();
        StatisticsManager.onStopPage(mPageId);
    }
}
