package com.hui.mvptest.location.model;

import android.location.Location;

import java.util.HashMap;
import java.util.Map;

/** 3
 * Created by walke.Z on 2017/8/8.
 * 数据处理(增删改查)
 */

public class LocateModel implements ILocateModel {

    private Map<String,Location> mLocates=new HashMap<>();

    @Override
    public Location getLocation(String dateStr) {
        return mLocates.get(dateStr);
    }

    @Override
    public void svaeLocation(String dateStr,Location location) {
        mLocates.put(dateStr,location);
    }
}
