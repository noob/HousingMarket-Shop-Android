package com.carlos.housingmarket_shop_android.activity.mine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.carlos.housingmarket_shop_android.R;
import com.carlos.housingmarket_shop_android.entity.HmOrder;
import com.carlos.housingmarket_shop_android.util.GlobalUtil;
import com.carlos.housingmarket_shop_android.util.HttpUtil;
import com.carlos.sxl.use.manager.AppManager;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SystemSettings extends AppCompatActivity {

    @BindView(R.id.backTopText)
    TextView backTopText;
    @BindView(R.id.back_button)
    ImageView backButton;
    @BindView(R.id.text1)
    TextView text1;
    @BindView(R.id.button)
    Button button;

    private AppManager mam = null; // Activity 管理器
    private Map<String,Object> map = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_settings);
        ButterKnife.bind(this);

        mam = AppManager.getInstance();
        mam.addActivity(this);
        init();
    }


    private void init() {
        backTopText.setText("系统设置");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String url = GlobalUtil.DOMAIN + GlobalUtil.ENTRANCE_3;
                        System.out.println(url);
                        Gson gson = new Gson();
                        HmOrder order = new HmOrder();
                        order.setUserId((long) 1);
                        order.setUserName("默罕默德 • 凯撒 • 布兰德");
                        order.setBuyerMobile("18888888888");
                        order.setAddress("宁波大红鹰学院");
                        order.setPayway("支付宝");
                        order.setSendPrice((double) 0);
                        order.setStoreId((long) 1);
                        order.setOrderPrice((double) 117);
                        order.setOrderGoodsId((long) 1);
                        map.put("order", gson.toJson(order));
                        map.put("port", gson.toJson(3));
                        map.put("cmd", gson.toJson(GlobalUtil.CMD_1));
                        String sendData = gson.toJson(map);
                        String data = HttpUtil.doPost(url, sendData);
//		try {
//			String data = new String(HttpUtil.HttpRequestPost(url, sendData.getBytes("UTF-8")), "UTF-8");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

                        System.out.println(data);
                    }
                }).start();
            }
        });

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
