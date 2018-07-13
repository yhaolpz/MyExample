package com.meitu.example.livedata;

import android.arch.lifecycle.LiveData;
import android.text.TextUtils;

/**
 * Created by wyh3 on 2018/7/5.
 */
public class NameLiveData extends LiveData<String> {


    public void load() {
        String name = requestFromNet();
        if (!TextUtils.isEmpty(name)) {
            //更新数据
            setValue("XXX");
            //LiveData.java  400L
        }
    }

    private String requestFromNet() {
        return "XXX";
    }


    @Override//当观察者数量从0到1
    protected void onActive() {
        super.onActive();
    }

    @Override //当观察者数量从1到0
    protected void onInactive() {
        super.onInactive();
        //取消网络请求
    }





}
