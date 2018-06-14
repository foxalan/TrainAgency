package com.example.ec.main.home.location;

import com.baidu.location.BDLocation;

/**
 * @author alan
 *         Date  2018/6/14.
 *         Function : 定位功能接口，给需要定位的delegate
 *         Issue :
 */

public interface ILocationListener {
    /**
     * 定位
     * @param location
     */
    void location(BDLocation location);
}
