package com.meitu.myexample;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.airbnb.lottie.LottieAnimationView;
import com.wyh.slideAdapter.ItemBind;
import com.wyh.slideAdapter.ItemView;
import com.wyh.slideAdapter.SlideAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecycleView;
    private List<Boolean> mData;
//    private Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView() {
        mRecycleView = findViewById(R.id.recycleView);
        mRecycleView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initData() {
        mData = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            mData.add(false);
        }
        SlideAdapter.load(mData)
                .item(R.layout.item)
                .bind(new ItemBind<Boolean>() {
                    @Override
                    public void onBind(final ItemView itemView, Boolean s, final int i) {
                        itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mData.set(i, true);
                            }
                        });


                        final LottieAnimationView view = itemView.getView(R.id.anim_view);
                        view.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                view.playAnimation();
                            }
                        },500);
//                        view.playAnimation();

//                        if (mData.get(i)) {
//                            view.playAnimation();
//                        }else{
////                            view.pauseAnimation();
//                        }
//                                view.setAnimation("data.json",LottieAnimationView.CacheStrategy.Strong);


                    }
                })
                .into(mRecycleView);
    }


    class Adapter extends RecyclerView.Adapter {
        private List<Boolean> mData;
        private Context mContext;

        public Adapter(List<Boolean> data, Context context) {
            mData = data;
            mContext = context;
        }


        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(
                    LayoutInflater.from(mContext).inflate(R.layout.item, parent, false));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return mData.size();
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        LottieAnimationView mLottieAnimationView;

        public MyViewHolder(View itemView) {
            super(itemView);
            mLottieAnimationView = itemView.findViewById(R.id.anim_view);
        }
    }


}

