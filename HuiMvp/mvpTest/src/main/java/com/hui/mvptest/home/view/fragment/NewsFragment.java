package com.hui.mvptest.home.view.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.hui.mvptest.R;
import com.hui.mvptest.base.fragment.AppFragment;
import com.hui.mvptest.base.fragment.BaseFragment;
import com.hui.mvptest.config.Datas;
import com.hui.mvptest.home.view.fragment.child.ChildFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 吾日三省吾身：看脸，看秤，看余额。
 * Created by lanso on 2016/11/24.
 * 首页碎片
 */
public class NewsFragment extends AppFragment {


    private TabLayout mTabLayout;//需要>=23的design包
    private ViewPager mViewPager;
    private List<BaseFragment> fgs;

    @Override
    protected int rootLayoutId() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initView(View rootView, Bundle savedInstanceState) {
        mTabLayout = ((TabLayout) rootView.findViewById(R.id.fn_tabLayout));
        mViewPager = ((ViewPager) rootView.findViewById(R.id.fn_viewPager));
    }

    @Override
    protected void initData() {
        fgs=new ArrayList<>();
        String[] stringArray = getResources().getStringArray(R.array.tabTitles);
        ChildFragment child1 = ChildFragment.getChildFragment(stringArray[0], Datas.home);
        ChildFragment child2 = ChildFragment.getChildFragment(stringArray[1], Datas.view);
        ChildFragment child3 = ChildFragment.getChildFragment(stringArray[2], Datas.news);
        ChildFragment child4 = ChildFragment.getChildFragment(stringArray[3], Datas.mine);
        fgs.add(child1);
        fgs.add(child2);
        fgs.add(child3);
        fgs.add(child4);
        /*for (String s : stringArray) {
            ChildFragment instance = ChildFragment.getChildFragment(s);
            fgs.add(instance);
        }*/
        TabFragmentAdapter tabAdapter = new TabFragmentAdapter(getChildFragmentManager(), stringArray, fgs);
        mViewPager.setAdapter(tabAdapter);
        mTabLayout.setupWithViewPager(mViewPager,false);

    }

}
