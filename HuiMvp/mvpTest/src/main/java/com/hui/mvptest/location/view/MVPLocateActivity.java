package com.hui.mvptest.location.view;

import android.location.Location;
import android.widget.Button;
import android.widget.TextView;

import com.hui.mvptest.R;
import com.hui.mvptest.base.activity.ButterKnifeActivity;
import com.hui.mvptest.location.presenter.LocatePresenter;

import butterknife.BindView;
import butterknife.OnClick;

/** 1.
 * Created by walke.Z on 2017/8/8.
 * 简单的逻辑处理
 */

public class MVPLocateActivity extends ButterKnifeActivity implements ILocateView {
    @BindView(R.id.tv_lal_show)
    TextView mTvLalShow;
    @BindView(R.id.btn_lal_location)
    Button mBtnLalLocation;
    private LocatePresenter mLocatePresenter;

    @Override
    public int layoutId() {
        return R.layout.location_activity_locate;
    }

    @Override
    protected void initData() {
       // ButterKnife.bind(this);
        mLocatePresenter = new LocatePresenter(this,this);
    }

    @OnClick(R.id.btn_lal_location)
    public void onClick() {
        mLocatePresenter.setLocation();
    }


    @Override
    public void setLocation(Location location,String areaText) {
        mTvLalShow.setText(areaText);
    }
}
