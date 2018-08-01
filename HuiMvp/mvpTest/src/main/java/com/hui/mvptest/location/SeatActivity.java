package com.hui.mvptest.location;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.hui.mvptest.R;
import com.hui.mvptest.base.activity.AppActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by walke.Z on 2017/8/4.
 */

public class SeatActivity extends AppActivity {
    @BindView(R.id.location_tv)
    TextView mLocationTv;
    @BindView(R.id.location_button)
    Button mLocationButton;

    @Override
    protected int rootLayoutId() {
        return R.layout.location_activity_seat;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);

        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        // 返回所有已知的位置提供者的名称列表，包括未获准访问或调用活动目前已停用的。
        List<String> lp = lm.getAllProviders();
        for (String item : lp) {
            Log.i("8023", "可用位置服务：" + item);
        }
        Criteria criteria = new Criteria();
        criteria.setCostAllowed(false);//设置位置服务免费
        criteria.setAccuracy(Criteria.ACCURACY_COARSE); //设置水平位置精度
        //getBestProvider 只有允许访问调用活动的位置供应商将被返回
        String providerName = lm.getBestProvider(criteria, true);
        Log.i("8023", "------位置服务：" + providerName);

        if (providerName != null) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                toast("缺少权限，获取当前经纬度失败");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},123);
                }
                return;
            }
            Location location = lm.getLastKnownLocation(providerName);
            Log.i("8023", "-------" + location);
            //获取维度信息
            double latitude = location.getLatitude();
            //获取经度信息
            double longitude = location.getLongitude();
            mLocationTv.setText("定位方式： " + providerName + "  维度：" + latitude + "  经度：" + longitude);
        } else {
            Toast.makeText(this, "1.请检查网络连接 \n2.请打开我的位置", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.location_button)
    public void onClick() {

    }
}
