package com.meitu.example.lifecycle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.meitu.example.arch.R;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class Main5Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);


        //开始一个生命周期感知的网络请求
        getLifecycle().addObserver(new LiveRequest("www.baidu.com", mCallback));


    }


    //  不再需要判断界面有无销毁
    private Callback mCallback = new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {

        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {

        }
    };
}
