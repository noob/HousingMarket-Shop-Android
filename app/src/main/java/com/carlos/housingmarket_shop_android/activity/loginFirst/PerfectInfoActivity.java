package com.carlos.housingmarket_shop_android.activity.loginFirst;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.carlos.housingmarket_shop_android.R;
import com.carlos.housingmarket_shop_android.activity.util.NO;
import com.carlos.sxl.use.manager.AppManager;
import com.carlos.sxl.use.util.SPPrivateUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.qqtheme.framework.entity.City;
import cn.qqtheme.framework.entity.County;
import cn.qqtheme.framework.entity.Province;
import cn.qqtheme.framework.picker.AddressPicker;
import cn.qqtheme.framework.util.ConvertUtils;

public class PerfectInfoActivity extends AppCompatActivity {

    @BindView(R.id.backTopText)
    TextView backTopText;
    @BindView(R.id.shopName)
    EditText shopName;
    @BindView(R.id.address_select)
    LinearLayout addressSelect;
    @BindView(R.id.address_province)
    TextView addressProvince;
    @BindView(R.id.address_city)
    TextView addressCity;
    @BindView(R.id.address_country)
    TextView addressCountry;
    @BindView(R.id.ownerName)
    EditText ownerName;
    @BindView(R.id.ownerPhone)
    EditText ownerPhone;
    @BindView(R.id.detailAddressLy)
    LinearLayout detailAddressLy;
    @BindView(R.id.shopTypeLy)
    LinearLayout shopTypeLy;
    @BindView(R.id.detailAddress)
    TextView detailAddress;
    @BindView(R.id.shopType)
    TextView shopType;
    private Context context;
    private AppManager mam = null; // Activity 管理器
    private final static String TAG = "PerfectInfoActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfect_info);
        ButterKnife.bind(this);

        mam = AppManager.getInstance();
        mam.addActivity(this);
        context = PerfectInfoActivity.this;
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        detailAddress.setText(SPPrivateUtil.getString(context,NO.detailAdress,""));
    }
    private void init() {
        backTopText.setText("完善信息");
        detailAddress.setText(SPPrivateUtil.getString(context,NO.detailAdress,""));
        //省市县三级联动菜单
        addressSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Province> data = new ArrayList<>();
                try {
                    String json = ConvertUtils.toString(getAssets().open("city.json"));
                    data.addAll(JSON.parseArray(json, Province.class));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                final AddressPicker ap = new AddressPicker(PerfectInfoActivity.this, data);
                ap.setSelectedItem(SPPrivateUtil.getString(context, NO.province, "省"), SPPrivateUtil.getString(context, NO.city, "市"), SPPrivateUtil.getString(context, NO.country, "区（县）"));
                ap.setOnAddressPickListener(new AddressPicker.OnAddressPickListener() {
                    @Override
                    public void onAddressPicked(Province province, City city, County county) {
                        addressProvince.setText(province.getName());
                        addressCity.setText(city.getName());
                        addressCountry.setText(county.getName());
                        SPPrivateUtil.put(context, NO.province, province.getName());
                        SPPrivateUtil.put(context, NO.city, city.getName());
                        SPPrivateUtil.put(context, NO.country, county.getName());
                    }
                });
                ap.show();
            }
        });

        //详细地址设置
        detailAddressLy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailAddressActivity.class);
                startActivity(intent);
            }
        });

        //店铺类型设置
        shopTypeLy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ShopTypeActivity.class);
                startActivity(intent);
            }
        });
    }

    @OnClick(R.id.back_button)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_button:
                exit();
                break;
        }
    }

    /**
     * 退出
     */
    private void exit() {
        mam.finishActivity(this);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
        }
        return true;
    }

}
