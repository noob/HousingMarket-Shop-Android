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
import com.carlos.housingmarket_shop_android.util.NO;
import com.carlos.sxl.use.manager.AppManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    @BindView(R.id.commodity_list)
    RecyclerView commodityList;
    private AppManager mam = null; // Activity 管理器
    private GalleryAdapter mAdapter;
    private FoodCategoryAdapter fcAdapter;
    private List<String> mDatas;
    private List<String> fcDatas;
    private List<String> spDatas;
    private List<String> nnDatas;
    private List<List> lists;
    private Map<String,List<String>> map;

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
        lists = new ArrayList<>();
        fcDatas = new ArrayList<>();
        spDatas = new ArrayList<>();
        mDatas = new ArrayList<>();
        nnDatas = new ArrayList<>();
        fcDatas.add("食品零食");
        fcDatas.add("饮料类");
        fcDatas.add("牛奶酸奶");
        spDatas.add("全部");
        spDatas.add("食品1");
        spDatas.add("食品2");
        mDatas.add("全部");
        mDatas.add("茶饮料");
        mDatas.add("纯净水");
        nnDatas.add("全部");
        nnDatas.add("酸奶1");
        nnDatas.add("酸奶2");
        nnDatas.add("酸奶3");
        lists.add(spDatas);
        lists.add(mDatas);
        lists.add(nnDatas);

        map = new HashMap<>();
        for(int i=0;i<fcDatas.size();i++) {
                map.put(fcDatas.get(i),lists.get(i));
        }

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

        fcAdapter.setA(new FoodCategoryAdapter.A() {
            @Override
            public void onClick(View view, int position) {

                mAdapter = new GalleryAdapter(CommodityManage.this, map.get( fcDatas.get(position)));
                categoryDetail.setAdapter(mAdapter);
            }

        });
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
