package com.hui.mvptest.location;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.hui.mvptest.R;
import com.hui.mvptest.base.activity.TitleActivity;
import com.hui.mvptest.location.view.MVPLocateActivity;
import com.hui.mvptest.util.HttpConnectionUtil;
import com.hui.mvptest.widget.TitleLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by walke.Z on 2017/8/8.
 * 有时成功，有时失败，不稳定
 */

public class SystemLocationActivity extends TitleActivity {

    @BindView(R.id.las_location_show)
    TextView mTvLocationShow;
    @BindView(R.id.las_tvJump)
    TextView mTvJump;
    @BindView(R.id.las_show_location)
    Button mBtnShowLocation;
    private LocationManager locationManager;
    private String provider;

    @Override
    protected int rootLayoutId() {
        return R.layout.location_activity_system;
    }

    @Override
    protected void initView(TitleLayout titleLayout) {
        ButterKnife.bind(this);
        titleLayout.setTitleText("系统简单定位");
    }

    @Override
    protected void initData() {

    }

    /**
     * 得到当前经纬度并开启线程去反向地理编码
     */
    public void getLocation(Location location) {
        String latitude = location.getLatitude() + "";
        String longitude = location.getLongitude() + "";
        String url = "http://api.map.baidu.com/geocoder/v2/?ak=pPGNKs75nVZPloDFuppTLFO3WXebPgXg&callback=renderReverse&location=" + latitude + "," + longitude + "&output=json&pois=0";
        new MyAsyncTask(url).execute();
    }

    /**
     * 判断是否有可用的内容提供器
     *
     * @return 不存在返回null
     * <p>
     * 需要手机打开获取位置按钮(设置)
     */
    private String judgeProvider(LocationManager locationManager) {
        List<String> prodiverlist = locationManager.getProviders(true);
        if (prodiverlist.contains(LocationManager.NETWORK_PROVIDER)) {
            return LocationManager.NETWORK_PROVIDER;
        } else if (prodiverlist.contains(LocationManager.GPS_PROVIDER)) {
            return LocationManager.GPS_PROVIDER;
        } else {
            Toast.makeText(this, "没有可用的位置提供器", Toast.LENGTH_SHORT).show();
            mTvLocationShow.setText("没有可用的位置提供器");
        }
        return null;
    }


    @OnClick({R.id.las_tvJump, R.id.las_show_location})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.las_tvJump:
                startActivity(new Intent(this, MVPLocateActivity.class));
                break;
            case R.id.las_show_location:
                locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);//获得位置服务
                provider = judgeProvider(locationManager);

                if (provider != null) {//有位置提供器的情况
                    //为了压制getLastKnownLocation方法的警告
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                        return;
                    }
                    Location location = locationManager.getLastKnownLocation(provider);
                    if (location != null) {
                        toastTime(location.toString(),4*1000);
                        mTvLocationShow.setText("经度 = "+location.getLongitude()+"  \n纬度 = "+location.getLatitude()+"\n请求具体位置...");
                        getLocation(location);//得到当前经纬度并开启线程去反向地理编码
                    } else {
                        mTvLocationShow.setText("暂时无法获得当前位置");
                    }
                } else {//不存在位置提供器的情况
                    mTvLocationShow.setText("不存在位置提供器的情况");
                }
                break;
        }
    }

    class MyAsyncTask extends AsyncTask<Void, Void, Void> {
        String url = null;//要请求的网址
        String str = null;//服务器返回的数据
        String address = null;

        public MyAsyncTask(String url) {
            this.url = url;
        }

        @Override
        protected Void doInBackground(Void... params) {
            str = HttpConnectionUtil.getData(url);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            try {
                str = str.replace("renderReverse&&renderReverse", "");
                str = str.replace("(", "");
                str = str.replace(")", "");
                JSONObject jsonObject = new JSONObject(str);
                JSONObject address = jsonObject.getJSONObject("result");
                String city = address.getString("formatted_address");
                String district = address.getString("sematic_description");
                mTvLocationShow.setText("当前位置：" + city + district);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            super.onPostExecute(aVoid);
        }
    }

}
