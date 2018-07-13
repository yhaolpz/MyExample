package com.meitu.example.viewmodel;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

/**
 * Created by wyh3 on 2018/7/5.
 */
public class Fragment1 extends Fragment {


    private NameViewModel mNameViewModel;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //viewModel存活周期依赖父activity
        mNameViewModel = ViewModelProviders.of(getActivity()).get(NameViewModel.class);

        mNameViewModel.getCurrentName().observe(this,
                new Observer<String>() {
                    @Override
                    public void onChanged(@Nullable String name) {

                    }
                });

    }
}
