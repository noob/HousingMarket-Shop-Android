package com.carlos.housingmarket_shop_android;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.carlos.housingmarket_shop_android.Handled.HandledFragment;
import com.carlos.housingmarket_shop_android.Mine.MineFragment;
import com.carlos.housingmarket_shop_android.Shipping.ShippingFragment;
import com.carlos.housingmarket_shop_android.Use.manager.AppManager;
import com.carlos.housingmarket_shop_android.Use.util.T;
import com.carlos.housingmarket_shop_android.Use.util.UIUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private LinearLayout tabShipping;
    private LinearLayout tabHandled;
    private LinearLayout tabMine;
    private ImageView imageShipping;
    private ImageView imageHandled;
    private ImageView imageMine;
    private TextView textShipping;
    private TextView textHandled;
    private TextView textMine;
    private List<Fragment> list;

    //屏幕宽度
    private int screenWidth;
    private int currentPage = 0;

    private final static String TAG = "MainActivity";
    private Context context;
    private AppManager appManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        appManager = AppManager.getInstance();
        appManager.addActivity(this);
        context = this;
        UIUtil.steepToolBar(this);
      // 初始化界面
        initView();
        
    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.id_viewpager);
        tabShipping = (LinearLayout) findViewById(R.id.tabShipping);
        tabHandled = (LinearLayout) findViewById(R.id.tabHandled);
        tabMine = (LinearLayout) findViewById(R.id.tabMine);
        imageShipping = (ImageView) findViewById(R.id.imageShipping);
        imageHandled = (ImageView) findViewById(R.id.imageHandled);
        imageMine = (ImageView) findViewById(R.id.imageMine);
        textShipping = (TextView) findViewById(R.id.textShipping);
        textHandled = (TextView) findViewById(R.id.textHandled);
        textMine = (TextView) findViewById(R.id.textMine);
        list = new ArrayList<>();

        // 设置数据源
        HandledFragment handledFragment = new HandledFragment();
        ShippingFragment shippingFragment = new ShippingFragment();
        MineFragment mineFragment = new MineFragment();

        list.add(shippingFragment);
        list.add(handledFragment);
        list.add(mineFragment);
        screenWidth=getResources().getDisplayMetrics().widthPixels;

        FragmentPagerAdapter adapter = new FragmentPagerAdapter(
                getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public Fragment getItem(int arg0) {
                return list.get(arg0);
            }
        };
        viewPager.setAdapter(adapter);

        // 设置滑动监听
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                // 当页面被选择时，将图片字体颜色初始化成黑
                clearAll();

                // 再改变当前选择页（position）对应的textview颜色
                switch (position) {
                    case 0:
                        textShipping.setTextColor(Color.BLACK);
                        imageShipping.setImageResource(R.mipmap.tab_shipping_checked);
                        break;
                    case 1:
                        textHandled.setTextColor(Color.BLACK);
                        imageHandled.setImageResource(R.mipmap.tab_handled_checked);
                        break;
                    case 2:
                        textMine.setTextColor(Color.BLACK);
                        imageMine.setImageResource(R.mipmap.tab_mine_checked);
                        break;
                }

                currentPage = position;

            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                Log.i("tuzi", arg0 + "," + arg1 + "," + arg2);

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub

            }
        });

        tabShipping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAll();
                currentPage = 0;
                textShipping.setTextColor(Color.BLACK);
                imageShipping.setImageResource(R.mipmap.tab_shipping_checked);
                changeView(0);
            }
        });
        tabHandled.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAll();
                currentPage = 1;
                textHandled.setTextColor(Color.BLACK);
                imageHandled.setImageResource(R.mipmap.tab_handled_checked);
                changeView(1);
            }
        });
        tabMine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAll();
                currentPage = 2;
                textMine.setTextColor(Color.BLACK);
                imageMine.setImageResource(R.mipmap.tab_mine_checked);
                changeView(2);
            }
        });

    }

    //手动设置ViewPager要显示的视图
    private void changeView(int desTab)
    {
        viewPager.setCurrentItem(desTab, true);
    }

    private void clearAll() {
        textShipping.setTextColor(Color.GRAY);
        textHandled.setTextColor(Color.GRAY);
        textMine.setTextColor(Color.GRAY);
        imageShipping.setImageResource(R.mipmap.tab_shipping_unchecked);
        imageHandled.setImageResource(R.mipmap.tab_handled_unchecked);
        imageMine.setImageResource(R.mipmap.tab_mine_unchecked);
    }

    // 上一次点击推退出的时间
    private long exitTime = 0;
    /**
     * 双击退出事件
     */
    public void exitApp() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            T.showShort(context, getResources().getString(R.string.exit_app));
            exitTime = System.currentTimeMillis();
        } else {
            appManager.appExit(context);
        }
    }

    /**
     * 回退键监听
     */
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitApp();
        }
        return true;
    }

}
