package com.meitu.example.leakcanarytest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.squareup.leakcanary.RefWatcher;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LeakThread leakThread = new LeakThread();
        leakThread.start();
        finish();
    }

    class LeakThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 100000; i++) {
                try {
                    Thread.sleep(3000);
                    Log.d("meitu", "LeakThread running");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("meitu", "onDestroy: ");
    }
}
