package com.meitu.example.lifecycle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.meitu.example.arch.R;

public abstract class BaseStatisticsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2_base);
    }

    @Override
    protected void onResume() {
        super.onResume();
        StatisticsManager.onStartPage(getPageId());
    }

    @Override
    protected void onPause() {
        super.onPause();
        StatisticsManager.onStopPage(getPageId());
    }

    protected abstract String getPageId();

}
