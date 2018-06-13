package com.example.ec.main.home;

import android.location.Location;

import com.baidu.location.BDLocation;

/**
 * @author alan
 *         Date  2018/6/9.
 *         Function :
 *         Issue :
 */

public interface IHomeLocationListener {

    /**
     * 第一次进入，得到当前的位置
     * @return
     */
    void getCurrentLocation(BDLocation location);

    /**
     * 重新定位的地址
     */
    void changeCurrentLocation(String string);

    /**
     * 重新选择位置
     */
    void  reChooseLocation();
}
