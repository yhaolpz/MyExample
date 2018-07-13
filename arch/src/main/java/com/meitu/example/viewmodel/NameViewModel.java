package com.meitu.example.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

/**
 * Created by wyh3 on 2018/7/4.
 */
public class NameViewModel extends ViewModel {


    private MutableLiveData<String> mCurrentName = new MutableLiveData<>();

    public MutableLiveData<String> getCurrentName() {
        return mCurrentName;
    }

    /**
     * 当 viewModel 无用，即将销毁时调用
     * 屏幕旋转触发的 destroy 并不会出发 onCleared
     */
    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d("wyh", "onCleared: ");
    }

    public void loadName() {

    }
}
