package com.meitu.example.showinputmethod;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class Main2Activity extends AppCompatActivity {
    private EditText mEditText;
    private InputMethodManager mInputMethodManager;


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main2);
        mEditText = findViewById(R.id.edit_text);

//        mEditText.getViewTreeObserver().addOnWindowFocusChangeListener(new ViewTreeObserver.OnWindowFocusChangeListener() {
//            @Override
//            public void onWindowFocusChanged(boolean hasFocus) {
//                if (hasFocus) {
//                    mEditText.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            showInputMethod();
//                        }
//                    });
//                }else{
//                    hideInputMethod();
//                }
//                //                    mEditText.getViewTreeObserver().removeOnWindowFocusChangeListener(this);
//            }
//        });

        mEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    mEditText.post(new Runnable() {
                        @Override
                        public void run() {
                            showInputMethod();
                        }
                    });
                }else{
                    hideInputMethod();
                }
            }
        });


    }

    public void start(View view) {
        startActivityForResult(new Intent(this, Main3Activity.class), 1);
    }

    private void analogClick(View v) {
        Rect rect = new Rect();
        v.getGlobalVisibleRect(rect);
        v.requestFocus();

        long uptimeMillis = SystemClock.uptimeMillis();
        MotionEvent obtain = MotionEvent.obtain(
                uptimeMillis, uptimeMillis, 0, (float)v.getWidth(), (float) v.getHeight(), 0);
        MotionEvent obtain1 = MotionEvent.obtain(
                uptimeMillis, uptimeMillis, 1, (float)v.getWidth(), (float) v.getHeight(), 0);

        mEditText.onTouchEvent(obtain);
        mEditText.onTouchEvent(obtain1);
    }

    private void showInputMethod() {
        if (mInputMethodManager == null) {
            mInputMethodManager = (InputMethodManager) getApplication()
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
        }
        if (mInputMethodManager != null) {
            mInputMethodManager.showSoftInput(mEditText, 0);
        }
    }

    private void hideInputMethod() {
        if (mInputMethodManager == null) {
            mInputMethodManager = (InputMethodManager) getApplication()
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
        }
        if (mInputMethodManager != null) {
            mInputMethodManager.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
