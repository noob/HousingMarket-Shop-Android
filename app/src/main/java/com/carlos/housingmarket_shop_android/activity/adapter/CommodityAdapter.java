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

public class CommodityAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private LayoutInflater mInflater;
    private List<String> mDatas;
    private Context context;
    //两个final int类型表示ViewType的两种类型
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(context).inflate(R.layout.commodity_item, viewGroup,
                    false);
            return new ItemViewHolder(view);
        } else if (viewType == TYPE_FOOTER) {
            View view = LayoutInflater.from(context).inflate(R.layout.commodity_foot, viewGroup,
                    false);
            return new FootViewHolder(view);
        }
        return null;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
        void onFootClick(View view, int position);

    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, int position) {

        if (viewHolder instanceof ItemViewHolder) {
            if (onItemClickListener != null) {
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = viewHolder.getLayoutPosition();
                        onItemClickListener.onItemClick(viewHolder.itemView, position);
                    }
                });
            }
        }else if (viewHolder instanceof FootViewHolder) {
            if (onItemClickListener != null) {
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = viewHolder.getLayoutPosition();
                        onItemClickListener.onFootClick(viewHolder.itemView, position);
                    }
                });
            }
        }

    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            return TYPE_FOOTER;
        }
        return TYPE_ITEM;
    }

    public CommodityAdapter(Context context, List<String> datas) {
        mInflater = LayoutInflater.from(context);
        mDatas = datas;
        this.context = context;
    }


    @Override
    public int getItemCount() {
        return mDatas.size() == 0 ? 0 : mDatas.size() + 1;
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.commodity_name)
        TextView commodityName;
        @BindView(R.id.commodity_size)
        TextView commoditySize;
        @BindView(R.id.commodity_cost)
        TextView commodityCost;
        @BindView(R.id.commodity_exist)
        TextView commodityExist;
        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class FootViewHolder extends RecyclerView.ViewHolder {

        public FootViewHolder(View itemView) {
            super(itemView);
        }
    }

}
