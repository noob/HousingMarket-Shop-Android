package com.carlos.housingmarket_shop_android.activity.mine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.carlos.housingmarket_shop_android.R;
import com.carlos.housingmarket_shop_android.activity.adapter.FoodCategoryAdapter;
import com.carlos.housingmarket_shop_android.activity.adapter.GalleryAdapter;
import com.carlos.sxl.use.manager.AppManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CommodityManage extends AppCompatActivity {


    @BindView(R.id.back_button)
    ImageView backButton;
    @BindView(R.id.manageTopText)
    TextView manageTopText;
    @BindView(R.id.category_detail)
    RecyclerView categoryDetail;
    @BindView(R.id.food_category)
    RecyclerView foodCategory;
    private AppManager mam = null; // Activity 管理器
    private GalleryAdapter mAdapter;
    private FoodCategoryAdapter fcAdapter;
    private List<String> mDatas;
    private List<String> fcDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commodity_manage);
        ButterKnife.bind(this);

        mam = AppManager.getInstance();
        mam.addActivity(this);
        init();
        initDatas();
        initDetailAp();
        initFoodAp();
    }


    private void init() {
        manageTopText.setText("商品管理");
    }

    private void initDatas() {
        mDatas = new ArrayList<>();
        fcDatas = new ArrayList<>();
        mDatas.add("全部");
        mDatas.add("茶饮料");
        mDatas.add("纯净水");
        fcDatas.add("食品零食");
        fcDatas.add("饮料类");
        fcDatas.add("牛奶酸奶");
        fcDatas.add("酒品类");
        fcDatas.add("家庭清洁");
    }

    //食品二级详细分类
    private void initDetailAp() {
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        categoryDetail.setLayoutManager(linearLayoutManager);
        //设置适配器
        mAdapter = new GalleryAdapter(this, mDatas);
        categoryDetail.setAdapter(mAdapter);
    }

    //食品一级分类
    private void initFoodAp() {
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        foodCategory.setLayoutManager(linearLayoutManager);
        //设置适配器
        fcAdapter = new FoodCategoryAdapter(this, fcDatas);
        foodCategory.setAdapter(fcAdapter);
    }


    @OnClick({R.id.back_button, R.id.edit_manage})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_button:
                exit();
                break;
            case R.id.edit_manage:
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
