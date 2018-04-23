package com.walke.xietiaozhebuju;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Walke.Z
 * on 2017/5/25. 45.
 * email：1126648815@qq.com
 */
public class MineFragmentAdapter extends FragmentPagerAdapter {
    private String[] tabs;
    private List<LazyFragment> fgs;

    public MineFragmentAdapter(FragmentManager fm, String[] tabs, List<LazyFragment> fgs) {
        super(fm);
        this.tabs = tabs;
        this.fgs = fgs;
    }

    @Override
    public Fragment getItem(int position) {
        return fgs.get(position);
    }

    @Override
    public int getCount() {
        return fgs.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        //return super.getPageTitle(position);
        return tabs[position];
    }
}
