package com.walke.demo.location_phone_system;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;

/**
 * Created by walke on 2018/11/16.
 * email:1032458982@qq.com
 */

public class LocationTracker {

    private static Location sLocation;
    private LocationManager mLocationManagerService;
    private Context mContext;
    private TrackerSettings trackerSettings;

    public LocationTracker(Context context, TrackerSettings trackerSettings) {
        mContext = context;
        this.trackerSettings = trackerSettings;

        // Get the location manager
        this.mLocationManagerService = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        // default
        if (sLocation == null && trackerSettings.shouldUseGPS()) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return ;
            }
            LocationTracker.sLocation = mLocationManagerService.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        }
        if (sLocation == null && trackerSettings.shouldUseNetwork()) {
            LocationTracker.sLocation = mLocationManagerService.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        }
        if (sLocation == null && trackerSettings.shouldUsePassive()) {
            LocationTracker.sLocation = mLocationManagerService.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
        }

    }

    public void startListening() {
//        if (!mIsListening) {
//            Log.i(TAG, "LocationTracked is now listening for location updates");
//            // Listen for GPS Updates
//            if (mTrackerSettings.shouldUseGPS()) {
//                if (LocationUtils.isGpsProviderEnabled(mContext)) {
//                    mLocationManagerService.requestLocationUpdates(LocationManager.GPS_PROVIDER, mTrackerSettings.getTimeBetweenUpdates(), mTrackerSettings.getMetersBetweenUpdates(), this);
//                } else {
//                    onProviderError(new ProviderError(LocationManager.GPS_PROVIDER, "Provider is not enabled"));
//                }
//            }
//            // Listen for Network Updates
//            if (mTrackerSettings.shouldUseNetwork()) {
//                if (LocationUtils.isNetworkProviderEnabled(mContext)) {
//                    mLocationManagerService.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, mTrackerSettings.getTimeBetweenUpdates(), mTrackerSettings.getMetersBetweenUpdates(), this);
//                } else {
//                    onProviderError(new ProviderError(LocationManager.NETWORK_PROVIDER, "Provider is not enabled"));
//                }
//            }
//            // Listen for Passive Updates
//            if (mTrackerSettings.shouldUsePassive()) {
//                if (LocationUtils.isPassiveProviderEnabled(mContext)) {
//                    mLocationManagerService.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, mTrackerSettings.getTimeBetweenUpdates(), this.mTrackerSettings.getMetersBetweenUpdates(), this);
//                } else {
//                    onProviderError(new ProviderError(LocationManager.PASSIVE_PROVIDER, "Provider is not enabled"));
//                }
//            }
//            mIsListening = true;
//
//            // If user has set a timeout
//            if (mTrackerSettings.getTimeout() != -1) {
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        if (!mIsLocationFound && mIsListening) {
//                            Log.i(TAG, "No location found in the meantime");
//                            stopListening();
//                            onTimeout();
//                        }
//                    }
//                }, mTrackerSettings.getTimeout());
//            }
//        } else {
//            Log.i(TAG, "Relax, LocationTracked is already listening for location updates");
//        }


    }
}
