package com.meitu.example.xxmainpage;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String TAG = "wyh";

    private TextView mTv;
    private TextView mTitle;
    private AppBarLayout mAppbarlayout;


    //收起
    private Button mExpand;
    private HorizontalScrollView mTitle1;


    //展开
    private TextView mExpandview;

    private RelativeLayout mRelative;





    private NestedScrollView mScrollView;

    private boolean expand;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRelative = (RelativeLayout) findViewById(R.id.relative);
//        mAppbarlayout = (AppBarLayout) findViewById(R.id.appbarlayout);
        mTv = (TextView) findViewById(R.id.tv);
        mTitle = (TextView) findViewById(R.id.title);
        mExpand = (Button) findViewById(R.id.expand);
        mTitle1 = (HorizontalScrollView) findViewById(R.id.title1);
        mExpandview = (TextView) findViewById(R.id.expandview);
        mScrollView = (NestedScrollView) findViewById(R.id.scrollView);
        mTitle.setText("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        mTv.setText("1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n");

//        setFlag2();




        mExpand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expand();
            }
        });




        mScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                Log.d(TAG, "onScrollChange: scrollX=" + scrollX + " scrollY=" + scrollY + " " +
                        "oldScrollX=" + oldScrollX + " oldScrollY=" + oldScrollY);





            }
        });





    }

    private void expand() {
        expand = true;
        int oh = mAppbarlayout.getHeight();
        int h = 500;
        mExpandview.setVisibility(View.VISIBLE);

        ValueAnimator animator = ValueAnimator.ofInt(oh, h);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int h = (int) animation.getAnimatedValue();
                setExpandViewHeight(h);
                if (h == 500) {
//                    setFlag2();
                }
            }
        });
        animator.setDuration(200);
        animator.start();

    }


    private void setExpandViewHeight(int height) {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mExpandview.getLayoutParams();
        params.height = height;
        mExpandview.setLayoutParams(params);
    }


    private void setFlag1() {
        AppBarLayout.LayoutParams params =
                (AppBarLayout.LayoutParams) mRelative.getLayoutParams();
        params.setScrollFlags(
                AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL
                | AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS);
        mRelative.setLayoutParams(params);
    }


    private void setFlag2() {
        AppBarLayout.LayoutParams params =
                (AppBarLayout.LayoutParams) mRelative.getLayoutParams();
        params.setScrollFlags(
                AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED);
        mRelative.setLayoutParams(params);


        mScrollView.setNestedScrollingEnabled(false);
        mScrollView.setSmoothScrollingEnabled(false);
        mScrollView.setEnabled(false);



    }





}
