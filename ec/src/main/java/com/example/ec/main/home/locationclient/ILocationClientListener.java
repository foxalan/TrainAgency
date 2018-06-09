package com.example.ec.main.home.locationclient;

import com.baidu.location.LocationClient;

/**
 * @author alan
 *         Date  2018/6/9.
 *         Function :
 *         Issue :
 */

public interface ILocationClientListener {

    /**
     * 初始化定位操作
     * @param locationClient
     */
    LocationClient initLocationClient(LocationClient locationClient);
}
