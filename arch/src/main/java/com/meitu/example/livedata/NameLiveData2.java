package com.meitu.example.livedata;

import android.arch.lifecycle.LiveData;
import android.text.TextUtils;

/**
 * Created by wyh3 on 2018/7/5.
 */
public class NameLiveData2 extends LiveData<String> {

    private static class LazyHolder {
        private static final NameLiveData2 INSTANCE = new NameLiveData2();
    }

    private NameLiveData2() {
    }

    public static NameLiveData2 getInstance() {
        return LazyHolder.INSTANCE;
    }


    public void load() {

    }


}
