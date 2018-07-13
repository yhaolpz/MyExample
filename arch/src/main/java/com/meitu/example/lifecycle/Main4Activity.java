package com.meitu.example.lifecycle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.meitu.example.arch.R;

public class Main4Activity extends AppCompatActivity {

    private String mPageId = "Main4Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        StatisticsObserver.observe(getLifecycle(), mPageId);
    }

}
