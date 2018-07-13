package com.meitu.example.lifecycle;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by wyh3 on 2018/7/4.
 */
class LiveRequest implements LifecycleObserver {

    private static OkHttpClient sClient = new OkHttpClient();

    private Call mCall;
    private Callback mCallback;
    private boolean mActive;

    private Callback mLocalCallback = new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            if (mActive) {
                mCallback.onFailure(call, e);
            }
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            if (mActive) {
                mCallback.onResponse(call, response);
            }
        }
    };

    LiveRequest(String url, Callback mCallback) {
        this.mCallback = mCallback;
        RequestBody body = RequestBody.create(null, "xxx");
        mCall = sClient.newCall(new Request.Builder().url(url).post(body).build());
        mCall.enqueue(mLocalCallback);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    void onStop() {
        mCall.cancel();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    void onStart() {
        mActive = true;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    void onPause() {
        mActive = false;
    }


}
