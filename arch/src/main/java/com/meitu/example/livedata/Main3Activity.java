package com.meitu.example.livedata;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.meitu.example.arch.R;

public class Main3Activity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        textView = findViewById(R.id.tv_name);

        /**
         *  将数据所有权从UI控制器中剥离出去
         */
        NameLiveData2.getInstance().observe(this,
                new Observer<String>() {
                    @Override
                    public void onChanged(@Nullable String name) {
                        textView.setText(name);
                    }
                });
    }

    public void click(View view) {
        NameLiveData2.getInstance().load();
    }
}
