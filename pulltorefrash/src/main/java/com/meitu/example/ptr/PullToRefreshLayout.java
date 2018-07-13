package com.meitu.example.ptr;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by Weidongjian on 2017/2/15.
 * 下拉刷新的控件，替代原来CommunityRefreshLayout
 */

public class PullToRefreshLayout extends BaseRefreshLayout {
    private OnPullToRefresh onPullToRefresh;

    public PullToRefreshLayout(Context context) {
        super(context);
        // init();
    }

    public PullToRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        // init();
    }

    // private void init() {
    // setColorSchemeColors(Color.parseColor("#FD4965"));
    // }

    public void setOnPullToRefresh(final OnPullToRefresh onPullToRefresh) {
        this.onPullToRefresh = onPullToRefresh;
        setOnRefreshListener(new BaseRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (onPullToRefresh != null) {
                    onPullToRefresh.onRefresh();
                }
            }
        });
    }

    /**
     * 不要使用这个方法，use {@link #setOnPullToRefresh(OnPullToRefresh)}
     *
     * @param listener
     */
    @Override
    @Deprecated
    public void setOnRefreshListener(OnRefreshListener listener) {
        super.setOnRefreshListener(listener);
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
    }

    @Override
    public void setRefreshing(boolean refreshing) {
        super.setRefreshing(refreshing);
    }

    public interface OnPullToRefresh {
        void onRefresh();
    }
}