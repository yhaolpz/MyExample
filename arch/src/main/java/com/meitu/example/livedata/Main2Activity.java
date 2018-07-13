package com.meitu.example.livedata;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.meitu.example.arch.R;

public class Main2Activity extends AppCompatActivity {

    /**
     *  MediatorLiveData :  可以监听其他 liveData 的 liveData
     */
    private MediatorLiveData mMediatorLiveData = new MediatorLiveData();

    private LiveData mLiveData1 = new MutableLiveData();

    private LiveData mLiveData2 = new MutableLiveData();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);


        mLiveData1.observe(this, new Observer() {
            @Override
            public void onChanged(@Nullable Object o) {

            }
        });

        mLiveData2.observe(this, new Observer() {
            @Override
            public void onChanged(@Nullable Object o) {

            }
        });


        // 监听给定的源LiveData
        mMediatorLiveData.addSource(mLiveData1, new Observer() {
            @Override
            public void onChanged(@Nullable Object o) {

            }
        });

        mMediatorLiveData.addSource(mLiveData2, new Observer() {
            @Override
            public void onChanged(@Nullable Object o) {


            }
        });

        mMediatorLiveData.observe(this, new Observer() {
            @Override
            public void onChanged(@Nullable Object o) {

            }
        });

    }


//    Transformations.map(mCurrentName, new Function<String, String>() {
//        @Override
//        public String apply(String input) {
//            return null;
//        }
//    });
//
//        Transformations.switchMap(mCurrentName, new Function<String, LiveData<String>>() {
//        @Override
//        public LiveData<String> apply(String input) {
//            return null;
//        }
//    });



}
