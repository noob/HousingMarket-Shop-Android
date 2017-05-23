package com.carlos.housingmarket_shop_android.activity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.carlos.housingmarket_shop_android.R;
import com.carlos.housingmarket_shop_android.activity.mine.CommodityManage;
import com.carlos.sxl.use.util.T;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FoodCategoryAdapter extends RecyclerView.Adapter<FoodCategoryAdapter.fcViewHolder> {



    private LayoutInflater mInflater;
    private List<String> fcDatas;
    private Context fcContext;

    private A a;

    public void setA(A a) {
        this.a = a;
    }

    public interface A{
        void onClick(View view,int position);
    }

    public FoodCategoryAdapter(Context fcContext, List<String> datas) {
        mInflater = LayoutInflater.from(fcContext);
        fcDatas = datas;
        this.fcContext = fcContext;
    }

    @Override
    public fcViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = mInflater.inflate(R.layout.food_category_item,
                viewGroup, false);
        fcViewHolder fcViewHolder = new fcViewHolder(view);
        return fcViewHolder;
    }

    @Override
    public void onBindViewHolder(fcViewHolder holder, final int position) {
        holder.foodCategoryText.setText(fcDatas.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a.onClick(v,position);
                T.showShort(fcContext,fcDatas.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return fcDatas.size();
    }

    public class fcViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.food_category_text)
        TextView foodCategoryText;
        public fcViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
