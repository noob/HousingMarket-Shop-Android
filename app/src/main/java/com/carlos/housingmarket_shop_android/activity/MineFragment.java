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
import com.carlos.housingmarket_shop_android.activity.mine.DistributionManage;

import butterknife.BindView;
import butterknife.OnClick;

public class MineFragment extends Fragment {

    @BindView(R.id.topText)
    TextView topText;
    @BindView(R.id.mine_image)
    ImageView mineImage;
    @BindView(R.id.imageView)
    ImageView imageView;
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
    @BindView(R.id.businessAnnouncement)
    LinearLayout businessAnnouncement;
    @BindView(R.id.systemSettings)
    LinearLayout systemSettings;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        topText = (TextView) view.findViewById(R.id.topText);

        butterknife.ButterKnife.bind(this, view);
        init();
        return view;

    }

    private void init() {
        topText.setText("我的店铺");
    }


    @OnClick({R.id.distributionManage, R.id.shopEvaluation, R.id.accountCenter, R.id.businessStatus, R.id.preferentialManage, R.id.commodityManage, R.id.businessAnnouncement, R.id.systemSettings})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.distributionManage:
                Intent intent = new Intent(getActivity(), DistributionManage.class);
                startActivity(intent);
                break;
            case R.id.shopEvaluation:
                break;
            case R.id.accountCenter:
                break;
            case R.id.businessStatus:
                break;
            case R.id.preferentialManage:
                break;
            case R.id.commodityManage:
                break;
            case R.id.businessAnnouncement:
                break;
            case R.id.systemSettings:
                break;
        }
    }
}
