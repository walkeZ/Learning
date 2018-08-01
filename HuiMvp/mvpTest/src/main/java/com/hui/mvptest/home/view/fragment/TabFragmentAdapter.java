package com.hui.mvptest.home.view.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.hui.mvptest.base.fragment.BaseFragment;

import java.util.List;


/**
 * Created by walke.Z on 2017/8/2.
 */

public class TabFragmentAdapter extends FragmentPagerAdapter {
    String[] tabs;
    List<BaseFragment> pageFragments;

    public TabFragmentAdapter(FragmentManager fm, String[] tabs, List<BaseFragment> pageFragments) {
        super(fm);
        this.tabs = tabs;
        this.pageFragments = pageFragments;
    }

    @Override
    public Fragment getItem(int position) {
        return pageFragments.get(position);
    }

    @Override
    public int getCount() {
        return pageFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
//        return super.getPageTitle(position);
        return tabs[position];
    }
}
