package com.meitu.example.lifecycle;

import android.os.Bundle;

import com.meitu.example.arch.R;

public class Main2Activity extends BaseStatisticsActivity {

    private String mPageId = "Main3Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    @Override
    protected String getPageId() {
        return mPageId;
    }
}
