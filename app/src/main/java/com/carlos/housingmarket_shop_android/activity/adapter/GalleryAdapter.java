package com.carlos.housingmarket_shop_android.activity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.carlos.housingmarket_shop_android.R;
import com.carlos.sxl.use.util.T;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder> {



    private LayoutInflater mInflater;
    private List<String> mDatas;
    private Context context;

    public GalleryAdapter(Context context, List<String> datas) {
        mInflater = LayoutInflater.from(context);
        mDatas = datas;
        this.context = context;
    }

    @OnClick(R.id.category_detail_text)
    public void onClick() {
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    /**
     * 创建ViewHolder
     */
    @Override
    public GalleryViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.category_detil_item,
                viewGroup, false);
        GalleryViewHolder galleryViewHolder = new GalleryViewHolder(view);

        return galleryViewHolder;
    }

    /**
     * 设置值
     */
    @Override
    public void onBindViewHolder(final GalleryViewHolder galleryViewHolder, final int i) {
        // GalleryViewHolder.mImg.setImageResource(mDatas.get(i));
        galleryViewHolder.categoryDetailText.setText(mDatas.get(i));
        galleryViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                T.showShort(context,mDatas.get(i));
            }
        });
    }

    public class GalleryViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.category_detail_text)
        TextView categoryDetailText;
        public GalleryViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
