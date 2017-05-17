package com.carlos.housingmarket_shop_android.activity.loginFirst;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.TextView;

import com.carlos.housingmarket_shop_android.R;
import com.carlos.sxl.use.manager.AppManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShopTypeActivity extends AppCompatActivity {


    @BindView(R.id.backTopText)
    TextView backTopText;
    private Context context;
    private AppManager mam = null; // Activity 管理器
    private final static String TAG = "ShopTypeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_type);
        ButterKnife.bind(this);

        mam = AppManager.getInstance();
        mam.addActivity(this);
        context = ShopTypeActivity.this;
        init();
    }

    private void init() {
        backTopText.setText("店铺类型");
    }

    @OnClick(R.id.back_button)
    public void onClick() {
        exit();
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
