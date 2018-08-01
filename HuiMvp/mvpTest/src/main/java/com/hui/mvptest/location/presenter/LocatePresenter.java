package com.hui.mvptest.location.presenter;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import com.hui.mvptest.location.model.ILocateModel;
import com.hui.mvptest.location.model.LocateModel;
import com.hui.mvptest.location.view.ILocateView;
import com.hui.mvptest.util.HttpConnectionUtil;
import com.hui.mvptest.util.TimeUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.List;

import static android.content.Context.LOCATION_SERVICE;

/**
 * Created by walke.Z on 2017/8/8.
 * 表示/控制器 -- 复杂的逻辑处理
 */

public class LocatePresenter {
    private Context mContext;
    private ILocateView mILocateView;
    private ILocateModel mILocateModel;
    private LocationManager locationManager;
    private Location mLocation;

    public LocatePresenter(Context context,ILocateView ILocateView) {
        mContext=context;
        mILocateView = ILocateView;
        mILocateModel=new LocateModel();
    }

    public void setLocation(){

        locationManager = (LocationManager) mContext.getSystemService(LOCATION_SERVICE);//获得位置服务
        String provider = judgeProvider(locationManager);

        if (provider != null) {//有位置提供器的情况
            //为了压制getLastKnownLocation方法的警告
            if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                return;
            }
            mLocation = locationManager.getLastKnownLocation(provider);
            if (mLocation != null) {
                String id = TimeUtil.formatDate_YYYY_MM_DD_HH_MM(new Date());
                mILocateModel.svaeLocation(id, mLocation);
                mILocateView.setLocation(mLocation,"经度 = "+mLocation.getLongitude()+"  \n纬度 = "+mLocation.getLatitude()+"\n请求具体位置...");
                getLocation(mLocation);//得到当前经纬度并开启线程去反向地理编码
            } else {
                mILocateView.setLocation(mLocation,"暂时无法获得当前位置");
            }

        }else{//不存在位置提供器的情况
            mILocateView.setLocation(null,"不存在位置提供器的情况");
        }

    }


    /**
     * 得到当前经纬度并开启线程去反向地理编码
     */
    public void getLocation(Location location) {
        String latitude = location.getLatitude()+"";
        String longitude = location.getLongitude()+"";
        String url = "http://api.map.baidu.com/geocoder/v2/?ak=pPGNKs75nVZPloDFuppTLFO3WXebPgXg&callback=renderReverse&location="+latitude+","+longitude+"&output=json&pois=0";
        new MyAsyncTask(url).execute();
    }

    /**
     * 判断是否有可用的内容提供器
     * @return 不存在返回null
     *
     * 需要手机打开获取位置按钮(设置)
     */
    private String judgeProvider(LocationManager locationManager) {
        List<String> prodiverlist = locationManager.getProviders(true);
        if(prodiverlist.contains(LocationManager.NETWORK_PROVIDER)){
            return LocationManager.NETWORK_PROVIDER;
        }else if(prodiverlist.contains(LocationManager.GPS_PROVIDER)) {
            return LocationManager.GPS_PROVIDER;
        }else{
            Toast.makeText(mContext,"没有可用的位置提供器",Toast.LENGTH_SHORT).show();
        }
        return null;
    }

    class MyAsyncTask extends AsyncTask<Void,Void,Void> {
        String url = null;//要请求的网址
        String str = null;//服务器返回的数据
        String address = null;
        public MyAsyncTask(String url){
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
                str = str.replace("renderReverse&&renderReverse","");
                str = str.replace("(","");
                str = str.replace(")","");
                JSONObject jsonObject = new JSONObject(str);
                JSONObject address = jsonObject.getJSONObject("result");
                String city = address.getString("formatted_address");
                String district = address.getString("sematic_description");
                mILocateView.setLocation(mLocation,"当前位置：" + city + district);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            super.onPostExecute(aVoid);
        }
    }



}
