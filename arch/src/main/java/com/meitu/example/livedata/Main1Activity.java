package com.meitu.example.livedata;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.Transformations;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.meitu.example.arch.R;

public class Main1Activity extends AppCompatActivity {

    private TextView textView;

    private NameLiveData mCurrentName = new NameLiveData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        textView = findViewById(R.id.tv_name);

        mCurrentName.observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String name) {
                textView.setText(name);
            }
        });
    }

    public void click(View view) {
        mCurrentName.load();
    }
}
