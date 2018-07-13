package com.meitu.example.leakcanarytest;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by wyh3 on 2018/4/25.
 */
public class MyApplication extends Application {
    private static final String TAG = "meitu";
    private RefWatcher refWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        refWatcher= setupLeakCanary();
    }

    private RefWatcher setupLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            Log.e(TAG, "setupLeakCanary: isInAnalyzerProcess");
            throw new IllegalStateException("setupLeakCanary: isInAnalyzerProcess");
//            return RefWatcher.DISABLED;
        }
        Log.d(TAG, "setupLeakCanary: install");
        return LeakCanary.install(this);
    }

    public static RefWatcher getRefWatcher(Context context) {
        MyApplication leakApplication = (MyApplication) context.getApplicationContext();
        return leakApplication.refWatcher;
    }
}
