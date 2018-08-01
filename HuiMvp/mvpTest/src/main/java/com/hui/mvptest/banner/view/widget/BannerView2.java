package com.hui.mvptest.banner.view.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.hui.mvptest.R;
import com.hui.mvptest.banner.model.bean.Banner;
import com.hui.mvptest.util.ViewUtil;

import java.util.List;

import static com.hui.mvptest.util.ViewUtil.dipTopx;

/**
 * Created by walke.Z on 2017/8/8.
 */

public class BannerView2 extends RelativeLayout {

    private BannerViewPager2 mBannerViewPager;
    private LinearLayout pointLinearLayout;
    private BannerBaseAdapter2 mAdapter;

    public BannerView2(Context context) {
        this(context,null);
    }

    public BannerView2(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BannerView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        initChild(context);
    }

    private void initChild(Context context) {
        mBannerViewPager = new BannerViewPager2(context);
        LayoutParams pagerParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        mBannerViewPager.setLayoutParams(pagerParams);
        addView(mBannerViewPager);

        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        int margins = dipTopx(context, 8);
        params.setMargins(margins,margins,margins,margins);

        pointLinearLayout = new LinearLayout(context);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        addView(pointLinearLayout,params);

        initPoint(3);
        mBannerViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                //***********选定后点的颜色***************
                if (pointLinearLayout.getChildCount() > 1) {//轮播图大于1
                    int len = position % pointLinearLayout.getChildCount();
                    for (int i = 0; i < pointLinearLayout.getChildCount(); i++) {
                        View dot = pointLinearLayout.getChildAt(i);
                        if (len == i) {
                            dot.setBackgroundResource(R.drawable.point_selected);
                        } else {
                            dot.setBackgroundResource(R.drawable.point_unselect);
                        }
                    }
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    public void setAdapter(BannerBaseAdapter2 adapter){
        mAdapter = adapter;
        mBannerViewPager.setAdapter(mAdapter);
        int count = mAdapter.getDataSize();
        initPoint(count);
        adapter.setOnDataChangeListener(new BannerBaseAdapter2.OnDataChangeListener() {
            @Override
            public void dataChange(List list) {
                initPoint(list.size());
            }
        });

    }



    private void initPoint(int num) {
        if (pointLinearLayout==null||num<1)
            return;
        pointLinearLayout.removeAllViews();

        for (int i = 0; i < num; i++) {
            View point = new View(getContext());
            //大小
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewUtil.dipTopx(getContext(), 6), ViewUtil.dipTopx(getContext(), 6));
            //距离
            params.leftMargin = ViewUtil.dipTopx(getContext(), 3);
            params.rightMargin = ViewUtil.dipTopx(getContext(), 3);
            point.setLayoutParams(params);
            if (i == 0) {
                point.setBackgroundResource(R.drawable.point_selected);
            } else {
                point.setBackgroundResource(R.drawable.point_unselect);
            }
            pointLinearLayout.addView(point);

        }
    }


    public void startRoll(int startPosition, int cTime) {
        mBannerViewPager.setCurrentItem(startPosition);
        mBannerViewPager.setChangeTime(cTime);
        mBannerViewPager.startRoll();
    }

    public void changeDatas(List<Banner> list) {
        //mBannerViewPager.stopRoll();
        mAdapter.changeDatas(list);
        //startRoll(list.size()*20,1500);
    }
}
