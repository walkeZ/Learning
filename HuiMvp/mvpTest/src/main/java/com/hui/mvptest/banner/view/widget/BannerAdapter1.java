package com.hui.mvptest.banner.view.widget;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
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

public  class BannerAdapter1 extends PagerAdapter {
    private Context mContext;
    private List<Banner> mBannerList;
    private LayoutInflater mInflater;

    public BannerAdapter1(Context context, List<Banner> datas) {
        mContext = context;
        mBannerList = datas;
        mInflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mBannerList.size();//Integer.MAX_VALUE
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //return super.instantiateItem(container, position);
        View inflate = mInflater.inflate(R.layout.banner_item, null);//不能用container
        TextView tvTitle = (TextView) inflate.findViewById(R.id.bi_title);
        ImageView ivImg = (ImageView) inflate.findViewById(R.id.bi_img);
        Picasso.with(mContext).load(mBannerList.get(position).getUrl()).into(ivImg);
        tvTitle.setText(mBannerList.get(position).getTitle());
        container.addView(inflate );
        return inflate;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
        container.removeView((View) object);
    }


}
