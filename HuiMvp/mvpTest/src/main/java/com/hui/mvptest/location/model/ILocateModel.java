package com.hui.mvptest.location.model;

import android.location.Location;

/**
 * Created by walke.Z on 2017/8/8.
 */

public interface ILocateModel {

    Location getLocation(String id);
    void svaeLocation(String id,Location locate);

}
