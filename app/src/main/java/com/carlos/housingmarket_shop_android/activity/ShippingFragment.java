package com.carlos.housingmarket_shop_android.activity;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.carlos.housingmarket_shop_android.R;
import com.carlos.housingmarket_shop_android.activity.adapter.OderInfoAdapter;
import com.carlos.housingmarket_shop_android.activity.baseClass.BaseFragment;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShippingFragment extends BaseFragment {

    @BindView(R.id.topText)
    TextView topText;
    @BindView(R.id.targetNum)
    TextView targetNum;
    @BindView(R.id.wait_shipping_recy)
    RecyclerView waitShippingRecy;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    boolean isLoading;
    private int[] data = new int[10];
    private Context context;
    private OderInfoAdapter adapter;
    private MyHandler myHandler;

    public ShippingFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shipping, container, false);
        ButterKnife.bind(this, view);
        context = getActivity();
        myHandler = new MyHandler(this);
        init();
        initData();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void init() {
        topText.setText("宅集市");
        swipeRefreshLayout.setColorSchemeResources(R.color.theme_color);
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
            }
        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //1:send network request data
                //2:refreshLayout.refreshFinish();
                myHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        data = new int[10];
                        getData();
                    }
                }, 2000);
            }

        });

        adapter = new OderInfoAdapter(context, data);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        waitShippingRecy.setLayoutManager(layoutManager);
        waitShippingRecy.setAdapter(adapter);
        waitShippingRecy.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.d("test", "StateChanged = " + newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.d("test", "onScrolled");

                int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                if (lastVisibleItemPosition + 1 == adapter.getItemCount()) {
                    Log.d("test", "loading executed");

                    boolean isRefreshing = swipeRefreshLayout.isRefreshing();
                    if (isRefreshing) {
                        adapter.notifyItemRemoved(adapter.getItemCount());
                        return;
                    }
                    if (!isLoading) {
                        isLoading = true;
                        myHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                getData();
                                Log.d("test", "load more completed");
                                isLoading = false;
                            }
                        }, 1000);
                    }
                }
            }
        });

        //添加点击事件
        adapter.setOnItemClickListener(new OderInfoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.d("test", "item position = " + position);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
    }

    private class MyHandler extends Handler {

        final private WeakReference<ShippingFragment> mFragment;

        private MyHandler(ShippingFragment fragment) {
            mFragment = new WeakReference<>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            ShippingFragment shippingFragment = mFragment.get();
            switch (msg.what) {
                case 1:
                    break;
                case 2:
                    break;
                default:
                    break;
            }

            super.handleMessage(msg);
        }
    }

    public void initData() {
        myHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getData();
            }
        }, 1500);

    }

    /**
     * 获取测试数据
     */
    private void getData() {
        for (int i = 0; i < 10; i++) {
            data[i] = i;
        }
        adapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);
        adapter.notifyItemRemoved(adapter.getItemCount());
    }


}
