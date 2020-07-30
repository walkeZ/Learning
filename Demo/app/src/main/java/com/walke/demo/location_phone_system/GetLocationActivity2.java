package com.walke.demo.location_phone_system;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.walke.demo.R;

/**
 * Created by walke on 2018/11/16.
 * email:1032458982@qq.com
 * 参考；https://blog.csdn.net/cjjky/article/details/6557561
 * 失敗了
 */

public class GetLocationActivity2 extends AppCompatActivity {

    private double latitude = 0.0;
    private double longitude = 0.0;
    private TextView tvLocation;
    private LocationManager mLocationManager;
    private LocationListener mLocationListener;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_location);
        tvLocation = ((TextView) findViewById(R.id.agl_location));
        tvLocation.setText("GetLocationActivity2");
        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        getLocation();
        findViewById(R.id.agl_btGetLocation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLocation();
            }
        });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0, mLocationListener);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            return;
        }
        Location location = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        if (location != null) {
            latitude = location.getLatitude();//经度
            longitude = location.getLongitude();//纬度
            Log.i("walke: ", " GetLocationActivity:  onRequestPermissionsResult:-------> latitude=" + latitude + "   longitude=" + longitude);
        } else {
            Log.i("walke: ", " GetLocationActivity:  onRequestPermissionsResult:-------> location = null");
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public Object getLocation() {

        Location location = null;
        if (mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            location = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (location != null) {
                latitude = location.getLatitude();
                longitude = location.getLongitude();
                tvLocation.setText("当前经度：" + longitude + "\n当前纬度：" + latitude);
                Log.i("walke: ", " GetLocationActivity:  onCreate111:-------> latitude=" + latitude + "   longitude=" + longitude);
            } else {
                tvLocation.setText("location = null");
                Log.i("walke: ", " GetLocationActivity:  onCreate111:-------> location = null");
            }
        } else {
            //Provider的状态在可用、暂时不可用和无服务三个状态直接切换时触发此函数
            //Provider被enable时触发此函数，比如GPS被打开
            //Provider被disable时触发此函数，比如GPS被关闭
            //当坐标改变时触发此函数，如果Provider传进相同的坐标，它就不会被触发
            mLocationListener = new LocationListener() {

                //Provider的状态在可用、暂时不可用和无服务三个状态直接切换时触发此函数
                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {
                    Log.i("walke: ", " GetLocationActivity:  onStatusChanged:-------> ");
                }

                //Provider被enable时触发此函数，比如GPS被打开
                @Override
                public void onProviderEnabled(String provider) {
                    Log.i("walke: ", " GetLocationActivity:  onProviderEnabled:-------> provider=" + provider);
                }

                //Provider被disable时触发此函数，比如GPS被关闭
                @Override
                public void onProviderDisabled(String provider) {
                    Log.i("walke: ", " GetLocationActivity:  onProviderDisabled:-------> provider=" + provider);
                }

                //当坐标改变时触发此函数，如果Provider传进相同的坐标，它就不会被触发
                @Override
                public void onLocationChanged(Location location) {
                    if (location != null) {
                        Log.e("Map", "Locationchanged:Lat:"
                                + location.getLatitude() + "Lng:"
                                + location.getLongitude());
                    }
                }
            };
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                Log.i("walke: ", " GetLocationActivity:  onCreate:-------> noPermission");
//                toast("缺少权限，获取当前经纬度失败");
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 123);
                return null;
            }
            mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0, mLocationListener);
            location = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if (location != null) {
                latitude = location.getLatitude();//经度
                longitude = location.getLongitude();//纬度
                tvLocation.setText("当前经度：" + longitude + "\n当前纬度：" + latitude);
                Log.i("walke: ", " GetLocationActivity:  onCreate:-------> latitude=" + latitude + "   longitude=" + longitude);
            } else {
                tvLocation.setText("location = null");
                Log.i("walke: ", " GetLocationActivity:  onCreate:-------> location = null");
            }
        }
        return location;
    }
}
