package com.hui.mvptest.banner.view.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

import com.hui.mvptest.util.LogUtil;

/**
 * Created by walke.Z on 2017/8/11.
 */

public class BannerViewPager1 extends ViewPager {

    private static final String TAG = "BannerViewPager";
    private Context mContext;
    private BannerAdapter1 mAdapter;
    // 2.实现自动轮播 - 发送消息的msgWhat
    private final int SCROLL_MSG = 0x0011;

    // 2.实现自动轮播 - 页面切换间隔时间
    private int mChangeTime = 2500;

    public void setChangeTime(int changeTime) {
        mChangeTime = changeTime;
    }

    // 2.实现自动轮播 - 发送消息Handler
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            // 每隔*s后切换到下一页
            setCurrentItem(getCurrentItem() + 1);
            // 不断循环执行
            startRoll();
        }
    };

    public BannerViewPager1(Context context) {
        this(context,null);
    }

    public BannerViewPager1(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext=context;
    }

   /* public void setBannerAdapter(BannerAdapter1 adapter) {
        mAdapter = adapter;
        setAdapter1(new BannerPagerAdapter());
    }*/
    /**
     * 2.实现自动轮播
     */
    public void startRoll(){
        // 清除消息
        mHandler.removeMessages(SCROLL_MSG);
        // 消息  延迟时间  让用户自定义  有一个默认  3500
        mHandler.sendEmptyMessageDelayed(SCROLL_MSG, mChangeTime);

        LogUtil.i(this,"startRoll");
    }

    /**
     * 2.停止轮播
     */
    public void stopRoll(){
        // 清除消息
        mHandler.removeMessages(SCROLL_MSG);
        LogUtil.i(this,"startRoll");
    }

    /**
     * 2.销毁Handler停止发送  解决内存泄漏
     */
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mHandler.removeMessages(SCROLL_MSG);
        mHandler = null;
    }

   /* private class BannerPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return Integer.MAX_VALUE; // 返回一个很大的值，确保可以无限轮播
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            //return super.instantiateItem(container, position);
            View bannerView = new View(mContext);//mAdapter.getView(position)  view由对应的BannerAdapter生成、在对应的instantiateItem方法里
            container.addView(bannerView );
            return bannerView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //super.destroyItem(container, position, object);
            // 销毁回调的方法  移除页面即可
            container.removeView((View) object);
        }
    }*/
}
