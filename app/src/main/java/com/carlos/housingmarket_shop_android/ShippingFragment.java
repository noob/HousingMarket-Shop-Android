package com.carlos.housingmarket_shop_android;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.carlos.housingmarket_shop_android.BaseClass.BaseFragment;
import com.carlos.housingmarket_shop_android.R;

public class ShippingFragment extends BaseFragment {

    private Context context;
    private TextView topText;

    public ShippingFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shipping, container, false);
        topText =(TextView) view.findViewById(R.id.topText);
        init();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context = getActivity();
    }

    private void init() {
        topText.setText("宅集市");
    }

}
