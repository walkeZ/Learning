package com.walke.demo.location_phone_system;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.walke.demo.R;

/**
 * Created by walke on 2018/11/16.
 * email:1032458982@qq.com
 * 参考；https://blog.csdn.net/u012810020/article/details/52517976
 * 模拟器可以，华为不行
 */

public class GetLocationActivity3 extends AppCompatActivity {

    private double latitude = 0.0;
    private double longitude = 0.0;
    private TextView tvLocation;
    private LocationManager mLocationManager;
    private LocationListener mLocationListener;
    private Button btLocation;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_location);
        tvLocation = ((TextView) findViewById(R.id.agl_location));
        btLocation = ((Button) findViewById(R.id.agl_btGetLocation));
        tvLocation.setText("GetLocationActivity3,模拟器可以，华为不行");
        initData();

        btLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLocation();
            }
        });


    }

    private static final String TAG = GetLocationActivity3.class.getSimpleName();
    private static final String GPS_LOCATION_NAME = LocationManager.GPS_PROVIDER;
    private static final int REQUEST_PRESSMION_CODE = 10000;
    private final static String[] MULTI_PERMISSIONS = new String[]{
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION};

    private LocationManager locationManager;
    private boolean isGpsEnabled;
    private String locateType;


    /**
     * 方法描述：初始化定位相关数据
     */
    private void initData() {
        //获取定位服务
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        //判断是否开启GPS定位功能
        isGpsEnabled = locationManager.isProviderEnabled(GPS_LOCATION_NAME);
        //定位类型：GPS
        locateType = locationManager.GPS_PROVIDER;
    }



    @RequiresApi(api = Build.VERSION_CODES.M)
    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                (GetLocationActivity3.this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
//            mPermissionHelper.request(MULTI_PERMISSIONS);
            requestPermissions(MULTI_PERMISSIONS,111);
            return;
        }
        Location location = locationManager.getLastKnownLocation(locateType); // 通过GPS获取位置
        updateUI(location);
        // 设置监听*器，自动更新的最小时间为间隔N秒(1秒为1*1000，这样写主要为了方便)或最小位移变化超过N米
        locationManager.requestLocationUpdates(locateType, 100,0,
                locationListener);
    }

    private LocationListener locationListener = new LocationListener() {
        /**
         * 位置信息变化时触发:当坐标改变时触发此函数，如果Provider传进相同的坐标，它就不会被触发
         * @param location
         */
        @Override
        public void onLocationChanged(Location location) {
            Toast.makeText(GetLocationActivity3.this, "onLocationChanged函数被触发！", Toast.LENGTH_SHORT).show();
            updateUI(location);
            Log.i(TAG, "时间：" + location.getTime());
            Log.i(TAG, "经度：" + location.getLongitude());
            Log.i(TAG, "纬度：" + location.getLatitude());
            Log.i(TAG, "海拔：" + location.getAltitude());
        }

        /**
         * GPS状态变化时触发:Provider被disable时触发此函数，比如GPS被关闭
         * @param provider
         * @param status
         * @param extras
         */
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            switch (status) {
                //GPS状态为可见时
                case LocationProvider.AVAILABLE:
                    Toast.makeText(GetLocationActivity3.this, "onStatusChanged：当前GPS状态为可见状态", Toast.LENGTH_SHORT).show();
                    break;
                //GPS状态为服务区外时
                case LocationProvider.OUT_OF_SERVICE:
                    Toast.makeText(GetLocationActivity3.this, "onStatusChanged:当前GPS状态为服务区外状态", Toast.LENGTH_SHORT).show();
                    break;
                //GPS状态为暂停服务时
                case LocationProvider.TEMPORARILY_UNAVAILABLE:
                    Toast.makeText(GetLocationActivity3.this, "onStatusChanged:当前GPS状态为暂停服务状态", Toast.LENGTH_SHORT).show();
                    break;
            }
        }

        /**
         * 方法描述：GPS开启时触发
         * @param provider
         */
        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onProviderEnabled(String provider) {
            Toast.makeText(GetLocationActivity3.this, "onProviderEnabled:方法被触发", Toast.LENGTH_SHORT).show();
            getLocation();
        }

        /**
         * 方法描述： GPS禁用时触发
         * @param provider
         */
        @Override
        public void onProviderDisabled(String provider) {
            Log.i("walke: ", " GetLocationActivity:  onProviderDisabled:-------> provider="+provider);
        }
    };

    /**
     * 方法描述：在View上更新位置信息的显示
     *
     * @param location
     */
    private void updateUI(Location location) {
        if (location==null){
            tvLocation.setText("location==null" );
            return;
        }

        double longitude = location.getLongitude();
        double latitude = location.getLatitude();

        tvLocation.setText("当前经度：" + longitude + "\n当前纬度：" + latitude);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        getLocation();
    }
    
}
