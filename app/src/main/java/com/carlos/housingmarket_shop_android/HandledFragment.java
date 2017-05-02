package com.carlos.housingmarket_shop_android;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.carlos.housingmarket_shop_android.baseClass.BaseFragment;
import com.carlos.housingmarket_shop_android.Handled.StatusFragment.AllOrderFragment;
import com.carlos.housingmarket_shop_android.Handled.StatusFragment.DistributionFragment;
import com.carlos.housingmarket_shop_android.Handled.StatusFragment.FinishedFragment;
import com.carlos.housingmarket_shop_android.Handled.StatusFragment.RefundsFragment;

import java.util.ArrayList;
import java.util.List;

public class HandledFragment extends BaseFragment implements View.OnClickListener{

    private ViewPager viewPager;
    private List<Fragment> mFragmentList = new ArrayList<Fragment>();
    private FragmentAdapter mFragmentAdapter;
    private LinearLayout linearLayout;

    private AllOrderFragment allOrderFragment;
    private DistributionFragment distributionFragment;
    private FinishedFragment finishedFragment;
    private RefundsFragment refundsFragment;
    private TextView allOrder;
    private TextView distribution;
    private TextView finished;
    private TextView refunds;
    private TextView topText;

    /**
     * ViewPager的当前选中页
     */
    private int currentIndex=0;
    private int screenWidth;
    private int pageNum=4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_handled, container, false);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        viewPager.setAdapter(new FragmentAdapter(getActivity().getSupportFragmentManager()));
        linearLayout = (LinearLayout) view.findViewById(R.id.lr_layout);
        allOrder = (TextView) view.findViewById(R.id.allOrder);
        distribution = (TextView) view.findViewById(R.id.distribution);
        finished = (TextView) view.findViewById(R.id.finished);
        refunds = (TextView) view.findViewById(R.id.refunds);
        topText = (TextView) view.findViewById(R.id.topText);

        init();
        return view;
    }

    private void init() {
        topText.setText("订单管理");
        allOrder.setOnClickListener(this);
        distribution.setOnClickListener(this);
        finished.setOnClickListener(this);
        refunds.setOnClickListener(this);

        DisplayMetrics dpMetrics = new DisplayMetrics();
        getActivity().getWindow().getWindowManager().getDefaultDisplay().getMetrics(dpMetrics);
        screenWidth = dpMetrics.widthPixels;
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) linearLayout.getLayoutParams();
        lp.width = screenWidth/pageNum;
        linearLayout.setLayoutParams(lp);
        allOrderFragment = new AllOrderFragment();
        distributionFragment = new DistributionFragment();
        finishedFragment = new FinishedFragment();
        refundsFragment = new RefundsFragment();

        mFragmentList.add(allOrderFragment);
        mFragmentList.add(distributionFragment);
        mFragmentList.add(finishedFragment);
        mFragmentList.add(refundsFragment);
        mFragmentAdapter = new FragmentAdapter(getChildFragmentManager());
        viewPager.setAdapter(mFragmentAdapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentIndex = position;
                changeColor(position);
            }

            @Override
            public void onPageScrolled(int position, float offset,
                                       int offsetPixels) {
                if(currentIndex>position&&(currentIndex-position)==1)
                {
                    int xOffset = (int) (-(1 - offset)
                            * (screenWidth * 1.0 /pageNum) + currentIndex
                            * (screenWidth /pageNum));
                    linearLayout.setX(xOffset);
                }else if(currentIndex==position)
                {
                    int xOffset = (int) (offset * (screenWidth * 1.0 /pageNum) + currentIndex
                            * (screenWidth /pageNum));
                    linearLayout.setX(xOffset);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //在这里来改变被选中的item的高亮样式
            }
        });
        changeColor(0);
        viewPager.setCurrentItem(0);
    }


    class FragmentAdapter extends FragmentPagerAdapter {
        public FragmentAdapter(FragmentManager fm)
        {
            super(fm);
        }
        @Override
        public Fragment getItem(int position)
        {
            return mFragmentList.get(position);
        }
        @Override
        public int getCount()
        {
            return mFragmentList.size();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.allOrder:
                changeColor(0);
                changeView(0);
                break;
            case R.id.distribution:
                changeColor(1);
                changeView(1);
                break;
            case R.id.finished:
                changeColor(2);
                changeView(2);
                break;
            case R.id.refunds:
                changeColor(3);
                changeView(3);
                break;
        }
    }
    //手动设置ViewPager要显示的视图
    private void changeView(int desTab)
    {
        viewPager.setCurrentItem(desTab, false);
    }
    private void changeColor(int position) {
        clearColor();
        switch (position){
            case 0:
                allOrder.setTextColor(getResources().getColor(R.color.theme_color));
                break;
            case 1:
                distribution.setTextColor(getResources().getColor(R.color.theme_color));
                break;
            case 2:
                finished.setTextColor(getResources().getColor(R.color.theme_color));
                break;
            case 3:
                refunds.setTextColor(getResources().getColor(R.color.theme_color));
                break;
        }
    }
    private void clearColor(){
        allOrder.setTextColor(getResources().getColor(R.color.gray_shipping));
        distribution.setTextColor(getResources().getColor(R.color.gray_shipping));
        finished.setTextColor(getResources().getColor(R.color.gray_shipping));
        refunds.setTextColor(getResources().getColor(R.color.gray_shipping));
    }

}
