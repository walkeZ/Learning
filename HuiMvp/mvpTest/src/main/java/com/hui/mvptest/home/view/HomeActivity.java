package com.hui.mvptest.home.view;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.hui.mvptest.R;
import com.hui.mvptest.base.activity.AppActivity;
import com.hui.mvptest.base.fragment.BaseFragment;
import com.hui.mvptest.home.view.fragment.NewsFragment;
import com.hui.mvptest.home.view.fragment.MineFragment;
import com.hui.mvptest.home.view.fragment.FirstFragment;
import com.hui.mvptest.home.view.fragment.ViewsFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by walke.Z on 2017/8/8.
 */

public class HomeActivity extends AppActivity {
    @BindView(R.id.ah_rbFirst)
    RadioButton mRbFirst;
    @BindView(R.id.ah_rbViews)
    RadioButton mRbViews;
    @BindView(R.id.ah_rbNews)
    RadioButton mRbNews;
    @BindView(R.id.ah_rbMine)
    RadioButton mRbMine;
    @BindView(R.id.ah_radioGroup)
    RadioGroup mRadioGroup;
    @BindView(R.id.ah_viewPager)
    ViewPager mViewPager;
    @BindView(R.id.ah_launch)
    ImageView mIvLaunch;

    @Override
    protected int rootLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);

        splashPage(1700);

        mRbFirst.setChecked(true);

    }

    @Override
    protected void initData() {

        final List<BaseFragment> baseFragments = new ArrayList<>();
        NewsFragment newsFragment = new NewsFragment();

        ViewsFragment viewsFragment = new ViewsFragment();
        FirstFragment firstFragment = new FirstFragment();
        MineFragment mineFragment = new MineFragment();

        baseFragments.add(firstFragment);
        baseFragments.add(viewsFragment);
        baseFragments.add(newsFragment);
        baseFragments.add(mineFragment);

        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return baseFragments.get(position);
            }

            @Override
            public int getCount() {
                return baseFragments.size();
            }
        });

        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup arg0, int arg1) {
                switch (arg1) {
                    case R.id.ah_rbFirst:
                        mViewPager.setCurrentItem(0, false);
                        break;
                    case R.id.ah_rbViews:
                        mViewPager.setCurrentItem(1, false);
                        break;
                    case R.id.ah_rbNews:
                        mViewPager.setCurrentItem(2, false);
                        break;
                    case R.id.ah_rbMine:
                        mViewPager.setCurrentItem(3, false);
                        break;
                }
            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /** 闪屏页/启动页
     * 由于HomeActivity的启动模式是同栈单例，而且HomeActivity为启Activity,故不需要SharePreference来做记录
     * @param time 显示时间
     */
    private void splashPage(int time) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mIvLaunch.setVisibility(View.GONE);
            }
        }, time);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
