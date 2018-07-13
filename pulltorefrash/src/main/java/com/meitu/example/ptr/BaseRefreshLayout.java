package com.meitu.example.ptr;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import com.meitu.example.pulltorefrash.R;

import java.lang.reflect.Field;

/**
 * Created by Duke on 2017/3/9.
 */

public class BaseRefreshLayout extends PtrFrameLayout {

    protected final String TAG = this.getClass().getSimpleName();

    private EasyHeadView mHeader;

    private OnRefreshListener mOnRefreshListener;

    private RecyclerView mRecyclerView;

    private PtrHandler mPtrHandler;

    private boolean noCallback = false;

    private Runnable mRefreshRunnable = new Runnable() {
        @Override
        public void run() {
            autoRefresh();
        }
    };

    private Runnable mCompleteRunnable = new Runnable() {
        @Override
        public void run() {
            refreshComplete();
        }
    };

    private PtrHandler mInnerHandler = new PtrDefaultHandler() {

        @Override
        public void onRefreshBegin(final PtrFrameLayout frame) {
            if (noCallback) {
                noCallback = false;
                return;
            }
            if (mOnRefreshListener != null) {
                mOnRefreshListener.onRefresh();
            }
            if (mPtrHandler != null) {
                mPtrHandler.onRefreshBegin(frame);
            }
        }

        @Override
        public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
            // return BaseRefreshLayout.this.checkCanDoRefresh();
            if (mRecyclerView == null) {
                mRecyclerView = findRecyclerView(frame);
            }

            if (mRecyclerView == null) {
                return checkOutsidePtrHandler(frame, content, header) && !BaseRefreshLayout.canChildScrollUp(content);
            }
            return checkOutsidePtrHandler(frame, content, header) && !BaseRefreshLayout.canChildScrollUp(mRecyclerView);
        }
    };

    public BaseRefreshLayout(Context context) {
        this(context, null);
    }

    public BaseRefreshLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseRefreshLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private RecyclerView findRecyclerView(View view) {
        if (!(view instanceof ViewGroup)) {
            return null;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        RecyclerView recyclerView = null;
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View v = viewGroup.getChildAt(i);
            if (v != null && v instanceof RecyclerView) {
                recyclerView = (RecyclerView) v;
            } else {
                recyclerView = findRecyclerView(v);
            }
            if (recyclerView != null) {
                return recyclerView;
            }
        }
        return recyclerView;
    }

    /**
     * 适配recyclerView滑动冲突
     *
     * @return
     */
    public static boolean canChildScrollUp(View mTarget) {
        if (android.os.Build.VERSION.SDK_INT < 14) {
            if (mTarget instanceof AbsListView) {
                final AbsListView absListView = (AbsListView) mTarget;
                return absListView.getChildCount() > 0 && (absListView.getFirstVisiblePosition() > 0
                        || absListView.getChildAt(0).getTop() < absListView.getPaddingTop());
            } else {
                return ViewCompat.canScrollVertically(mTarget, -1) || mTarget.getScrollY() > 0;
            }
        } else {
            return ViewCompat.canScrollVertically(mTarget, -1);
        }
    }

    private void init(Context context) {
        mHeader = new EasyHeadView(context);
        mHeader.setText("头部");
        mHeader.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
        mHeader.setWidth(100);
        setHeaderView(mHeader);
        addPtrUIHandler(mHeader);
        setResistance(1.5f);
        setRatioOfHeaderHeightToRefresh(1.0f);
        setDurationToCloseHeader(500);
        setKeepHeaderWhenRefresh(true);
        super.setPtrHandler(mInnerHandler);
        initData();
    }

    private void initData() {
        try {
            Field field = PtrFrameLayout.class.getDeclaredField("mLoadingStartTime");
            field.setAccessible(true);
            field.set(this, System.currentTimeMillis());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void setControlRecyclerView(RecyclerView recyclerView) {
        mRecyclerView = recyclerView;
    }

    @Override
    public void setPtrHandler(PtrHandler ptrHandler) {
        mPtrHandler = ptrHandler;
    }

    private boolean checkOutsidePtrHandler(PtrFrameLayout frame, View content, View header) {
        if (mPtrHandler == null) {
            return true;
        }
        return mPtrHandler.checkCanDoRefresh(frame, content, header);
    }

    // private boolean isDown = false;

    /*@Override
    public boolean dispatchTouchEvent(MotionEvent e) {
        PtrIndicator ptrIndicator = mHeader.getPtrIndicator();
        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            isDown = true;
        }
        if (ptrIndicator == null) {
            return super.dispatchTouchEvent(e);
        }
        if (mHeader.isLoadComplete() && e.getAction() == MotionEvent.ACTION_MOVE) {
            if (isDown) {
                MotionEvent event = MotionEvent.obtain(e);
                event.setAction(MotionEvent.ACTION_CANCEL);
                isDown = false;
                super.dispatchTouchEvent(event);
                return super.dispatchTouchEventSupper(event);
            }
            return false;
        } else {
            if(ptrIndicator.isInStartPosition()){
    
            }
            return super.dispatchTouchEvent(e);
        }
    
    }*/

    public void setOnRefreshListener(OnRefreshListener onRefreshListener) {
        mOnRefreshListener = onRefreshListener;
    }

    public void setRefreshing(boolean refreshing) {
        Log.d(TAG, "setRefreshing => " + refreshing);
        if (refreshing) {
            removeCallbacks(mRefreshRunnable);
            postDelayed(mRefreshRunnable, 50);
        } else {
            postDelayed(mCompleteRunnable, 50);
            super.setPtrHandler(mInnerHandler);
        }
    }

    public void onlyDoRefreshingAnim() {
        noCallback = true;
        setRefreshing(true);
    }

    @Override
    protected void onDetachedFromWindow() {// 避免Thread starting during runtime shutdown
        removeCallbacks(mCompleteRunnable);
        removeCallbacks(mRefreshRunnable);
        super.onDetachedFromWindow();
    }


    public int getHeaderHeight() {
        return mHeader.getHeight();
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
    }

    public interface OnRefreshListener {
        void onRefresh();
    }
}
