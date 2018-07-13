package com.meitu.example.lifecycle;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/**
 * Created by wyh3 on 2018/7/4.
 */
public class BaseApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(mActivityLifecycleCallbacks);
    }

    // 不能监听 fragment

    private ActivityLifecycleCallbacks mActivityLifecycleCallbacks = new ActivityLifecycleCallbacks() {

        @Override
        public void onActivityResumed(Activity activity) {
            if (activity instanceof Statistics) {
                StatisticsManager.onStartPage(((Statistics) activity).getPageId());
            }
        }

        @Override
        public void onActivityPaused(Activity activity) {
            if (activity instanceof Statistics) {
                StatisticsManager.onStopPage(((Statistics) activity).getPageId());
            }
        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {

        }

        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

        }

        @Override
        public void onActivityStarted(Activity activity) {

        }
    };


}
