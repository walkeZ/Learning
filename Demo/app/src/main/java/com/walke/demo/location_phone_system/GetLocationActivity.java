package com.walke.demo.location_phone_system;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.walke.demo.R;

/**
 * Created by walke on 2018/11/16.
 * email:1032458982@qq.com
 * https://blog.csdn.net/qq_29078329/article/details/56668205
 * 佳：https://blog.csdn.net/lvshaorong/article/details/51635441
 */

public class GetLocationActivity extends AppCompatActivity {

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
        tvLocation.setText("GetLocationActivity");
        btLocation = ((Button) findViewById(R.id.agl_btGetLocation));
        //允许GPS、WiFi、基站定位，设置超时时间5秒
        TrackerSettings trackerSettings = new TrackerSettings();
        trackerSettings.setUseGPS(true).setUseNetwork(true).setUsePassive(true).setTimeout(5000);
        LocationTracker locationTracker = new LocationTracker(this, trackerSettings) {
            public void onLocationFound(Location location) {
                //定位成功时回调
                if (location != null) {
                    Log.i("walke", "经纬度：" + location.getLongitude() + "," + location.getLatitude());
                }

            }

            public void onTimeout() {
                //定位超时回调
                Log.i("walke", "定位超时");
            }
        };
        locationTracker.startListening();



    }


    
}
