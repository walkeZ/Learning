package com.walke.xietiaozhebuju;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ISwipeRefreshListener{

    private TabLayout mTabLayout;
    private ViewPager contentViewpager,topViewPager;
    private ViewGroup mContentLayout;
    private FloatingActionButton mFab;

    int[] imgRes = {R.mipmap.picture1, R.mipmap.picture2, R.mipmap.picture3,R.mipmap.picture4};


    private String[] tabs = new String[]{"首页", "国内特惠", "天猫折扣", "海淘精品"};
    private String[] types = new String[]{"jing", "guo", "tian", "hai"};
    private List<MineContentFragment> fgs;
    private MyFragmentAdapter fgmAdapter;
    private TitleLayout titleLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initVariable();
        findView();
        bindWidgets();
    }

    private void initVariable() {
        fgs = new ArrayList<>();
        for (int i = 0; i < tabs.length; i++) {
            MineContentFragment f = MineContentFragment.getFragment(types[i], 1);
            fgs.add(f);
        }
        fgmAdapter = new MyFragmentAdapter(getSupportFragmentManager(), tabs, fgs);

    }

    private void findView() {

        View stutasBar = findViewById(R.id.am_stutasBar);
        adaptiveNdk(this,stutasBar);
        topViewPager = (ViewPager) findViewById(R.id.am_topViewPager);
        mTabLayout = (TabLayout) findViewById(R.id.am_tabLayout);
        mContentLayout = (ViewGroup) findViewById(R.id.am_contentLayout);
        contentViewpager = (ViewPager) findViewById(R.id.am_contentViewpager);
        mFab = (FloatingActionButton) findViewById(R.id.am_fab);
        titleLayout = (TitleLayout) findViewById(R.id.am_titleLayout);
        titleLayout.setTitleLayoutClickListener(new TitleLayout.TitleLayoutClickListener() {
            @Override
            public void leftClick() {
                finish();
            }

            @Override
            public void rightClick() {

            }
        });
        findViewById(R.id.am_vpFrameLayout).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //将ViewPager父控件的触摸事件传给ViewPager,实现一屏显示3个vp页面时，触摸2边也行
                return topViewPager.dispatchTouchEvent(event);
            }
        });

        //设置Page间间距
        topViewPager.setPageMargin(ViewUtil.dpToPx(this,22));
        //设置缓存的页面数量
        topViewPager.setOffscreenPageLimit(3);
        topViewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return imgRes.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object o) {
                return view == o;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ImageView view = new ImageView(MainActivity.this);
                view.setImageResource(imgRes[position]);
                view.setScaleType(ImageView.ScaleType.FIT_XY);
                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        });
    }


    private void adaptiveNdk(Context context, View stutasBar) {
        int statusBarHeight = WindowUtil.getStatusBarHeight(context);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) stutasBar.getLayoutParams();
        params.height=statusBarHeight;
        stutasBar.setLayoutParams(params);
        stutasBar.setBackgroundResource(R.drawable.stutas_bar_blue);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT){
            //LogUtil.i("11111","SDK_INT = "+Build.VERSION.SDK_INT);
            stutasBar.setVisibility(View.GONE);
        }else {
            //LogUtil.i("1111122222","SDK_INT = "+Build.VERSION.SDK_INT);
            stutasBar.setVisibility(View.VISIBLE);
        }

    }


    private void bindWidgets() {
        contentViewpager.setAdapter(fgmAdapter);
        mTabLayout.setupWithViewPager(contentViewpager);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFabClick();
            }
        });
    }
    private void onFabClick() {
        ToastUtil.showToast(this,"MeigoActicity");
    }

    @Override
    public void loadData() {

    }

    @Override
    public void setRefresh(boolean enable) {

    }

    @Override
    public void dismissAnimation() {

    }

    @Override
    public boolean isRefreshing() {
        return false;
    }

}
