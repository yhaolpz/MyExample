package com.meitu.example.viewmodel;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.meitu.example.arch.R;

public class Main1Activity extends AppCompatActivity {

    private TextView textView;

    private NameViewModel mNameViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        textView = findViewById(R.id.tv_name);

        /**
         * 当依赖的 lifecycle scope 完全销毁掉后(activity finished 或 fragment detach)，才会销毁 ViewModel
         * 完全销毁：屏幕旋转尽管调用了onDestroy，但并不属于完全销毁
         */
        mNameViewModel = ViewModelProviders.of(this).get(NameViewModel.class);

        mNameViewModel.getCurrentName().observe(this,
                new Observer<String>() {
                    @Override
                    public void onChanged(@Nullable String name) {
                        textView.setText(name);
                    }
                });
    }

    public void click(View view) {
        mNameViewModel.loadName();
    }

}
