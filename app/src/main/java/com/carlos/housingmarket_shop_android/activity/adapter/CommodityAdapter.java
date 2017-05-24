package com.carlos.housingmarket_shop_android.activity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.carlos.housingmarket_shop_android.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Carlos on 2017/5/24.
 */

public class CommodityAdapter extends RecyclerView.Adapter<CommodityAdapter.MyViewHolder> {


    private LayoutInflater mInflater;
    private List<String> mDatas;
    private Context context;

    public CommodityAdapter(Context context, List<String> datas) {
        mInflater = LayoutInflater.from(context);
        mDatas = datas;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.commodity_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.commodity_name)
        TextView commodityName;
        @BindView(R.id.commodity_size)
        TextView commoditySize;
        @BindView(R.id.commodity_cost)
        TextView commodityCost;
        @BindView(R.id.commodity_exist)
        TextView commodityExist;
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
