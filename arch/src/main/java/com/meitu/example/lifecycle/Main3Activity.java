package com.meitu.example.lifecycle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.meitu.example.arch.R;

public class Main3Activity extends AppCompatActivity implements Statistics {

    private String mPageId = "Main2Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
    }

    @Override
    public String getPageId() {
        return mPageId;
    }
}
