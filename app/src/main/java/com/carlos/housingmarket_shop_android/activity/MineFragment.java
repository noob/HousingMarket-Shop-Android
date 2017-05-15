package com.carlos.housingmarket_shop_android.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.carlos.housingmarket_shop_android.R;
import com.carlos.housingmarket_shop_android.activity.mine.AccountCenter;
import com.carlos.housingmarket_shop_android.activity.mine.BusinessPost;
import com.carlos.housingmarket_shop_android.activity.mine.BusinessStatus;
import com.carlos.housingmarket_shop_android.activity.mine.CommodityManage;
import com.carlos.housingmarket_shop_android.activity.mine.DistributionManage;
import com.carlos.housingmarket_shop_android.activity.mine.PreferentialManage;
import com.carlos.housingmarket_shop_android.activity.mine.ShopEvaluation;
import com.carlos.housingmarket_shop_android.activity.mine.SystemSettings;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MineFragment extends Fragment {

    Intent intent;
    @BindView(R.id.topText)
    TextView topText;
    @BindView(R.id.mine_image)
    ImageView mineImage;
    @BindView(R.id.distributionManage)
    LinearLayout distributionManage;
    @BindView(R.id.shopEvaluation)
    LinearLayout shopEvaluation;
    @BindView(R.id.accountCenter)
    LinearLayout accountCenter;
    @BindView(R.id.businessStatus)
    LinearLayout businessStatus;
    @BindView(R.id.preferentialManage)
    LinearLayout preferentialManage;
    @BindView(R.id.commodityManage)
    LinearLayout commodityManage;
    @BindView(R.id.businessPost)
    LinearLayout businessPost;
    @BindView(R.id.systemSettings)
    LinearLayout systemSettings;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        topText = (TextView) view.findViewById(R.id.topText);

        ButterKnife.bind(this, view);
        init();
        return view;

    }

    private void init() {
        topText.setText("我的店铺");
    }


    @OnClick({R.id.topText, R.id.mine_image, R.id.distributionManage, R.id.shopEvaluation, R.id.accountCenter, R.id.businessStatus, R.id.preferentialManage, R.id.commodityManage, R.id.businessPost, R.id.systemSettings})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.distributionManage:
                intent = new Intent(getActivity(), DistributionManage.class);
                startActivity(intent);
                break;
            case R.id.shopEvaluation:
                intent = new Intent(getActivity(), ShopEvaluation.class);
                startActivity(intent);
                break;
            case R.id.accountCenter:
                intent = new Intent(getActivity(), AccountCenter.class);
                startActivity(intent);
                break;
            case R.id.businessStatus:
                intent = new Intent(getActivity(), BusinessStatus.class);
                startActivity(intent);
                break;
            case R.id.preferentialManage:
                intent = new Intent(getActivity(), PreferentialManage.class);
                startActivity(intent);
                break;
            case R.id.commodityManage:
                intent = new Intent(getActivity(), CommodityManage.class);
                startActivity(intent);
                break;
            case R.id.businessPost:
                intent = new Intent(getActivity(), BusinessPost.class);
                startActivity(intent);
                break;
            case R.id.systemSettings:
                intent = new Intent(getActivity(), SystemSettings.class);
                startActivity(intent);
                break;
        }
    }
}
