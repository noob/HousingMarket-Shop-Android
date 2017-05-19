package com.carlos.housingmarket_shop_android.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.carlos.housingmarket_shop_android.R;
import com.carlos.housingmarket_shop_android.activity.loginFirst.LoginActivity;
import com.carlos.housingmarket_shop_android.activity.loginFirst.PerfectInfoActivity;
import com.carlos.housingmarket_shop_android.activity.util.NO;
import com.carlos.sxl.use.util.SPPrivateUtil;
import com.carlos.sxl.use.util.T;

public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        Integer time = 2000;    //设置等待时间，单位为毫秒
        Handler handler = new Handler();
        //当计时结束时，跳转至主界面
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(SPPrivateUtil.getBoolean(LaunchActivity.this, NO.isLogin,false)){
                    startActivity(new Intent(LaunchActivity.this, MainActivity.class));
                    LaunchActivity.this.finish();
                }else {
                    startActivity(new Intent(LaunchActivity.this, LoginActivity.class));
                    LaunchActivity.this.finish();
                }
            }
        }, time);
    }

}
