package com.meitu.example.ptr;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.meitu.example.ptr.indicator.PtrIndicator;

/**
 * Created by wyh3 on 2018/5/3.
 */
public class EasyHeadView extends android.support.v7.widget.AppCompatTextView implements PtrUIHandler {
    public EasyHeadView(Context context) {
        super(context);
    }

    public EasyHeadView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public EasyHeadView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onUIReset(PtrFrameLayout frame) {

    }

    @Override
    public void onUIRefreshPrepare(PtrFrameLayout frame) {

    }

    @Override
    public void onUIRefreshBegin(PtrFrameLayout frame) {

    }

    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame) {

    }

    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {

    }
}
