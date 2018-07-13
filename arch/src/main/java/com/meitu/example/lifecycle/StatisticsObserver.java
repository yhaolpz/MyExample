package com.meitu.example.lifecycle;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.support.annotation.NonNull;

/**
 * Created by wyh3 on 2018/7/4.
 * 1.彻底解耦
 * 2.lifecycle-aware
 */
public class StatisticsObserver implements LifecycleObserver {

    private String mPageId;

    private StatisticsObserver(String pageId) {
        mPageId = pageId;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    void onResume() {
        StatisticsManager.onStartPage(mPageId);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    void onPause() {
        StatisticsManager.onStopPage(mPageId);
    }

    public static void observe(@NonNull Lifecycle lifecycle, @NonNull String pageId) {
        lifecycle.addObserver(new StatisticsObserver(pageId));
    }
}
