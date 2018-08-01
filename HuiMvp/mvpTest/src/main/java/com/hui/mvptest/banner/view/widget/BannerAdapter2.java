package com.hui.mvptest.banner.view.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hui.mvptest.R;
import com.hui.mvptest.banner.model.bean.Banner;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by walke.Z on 2017/8/11.
 */

public  class BannerAdapter2 extends BannerBaseAdapter2<Banner> {

    private List<Banner> mBannerList;

    public BannerAdapter2(Context context, List<Banner> datas) {
        super(context,datas);
        mContext = context;
        mBannerList = datas;
        mInflater=LayoutInflater.from(context);
    }

    public int getDataSize() {
        return mBannerList.size();
    }

    @Override
    protected Object itemView(ViewGroup container, int position) {
        //return super.instantiateItem(container, position);
        View inflate = mInflater.inflate(R.layout.banner_item, null);//不能用container
        TextView tvTitle = (TextView) inflate.findViewById(R.id.bi_title);
        ImageView ivImg = (ImageView) inflate.findViewById(R.id.bi_img);
        position=position%mBannerList.size();
        Picasso.with(mContext).load(mBannerList.get(position).getUrl()).into(ivImg);
        tvTitle.setText(mBannerList.get(position).getTitle());
        container.addView(inflate );
        return inflate;
    }

}
