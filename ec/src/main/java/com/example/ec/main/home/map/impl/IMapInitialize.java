package com.example.ec.main.home.map.impl;

import com.baidu.location.LocationClient;
import com.baidu.mapapi.map.BaiduMap;

/**
 * @Author Alan
 * Date 2018/3/27 0027
 * Function 百度地图中初始化操作
 * Issue
 */

public interface IMapInitialize {

    /**
     * 初始化地图操作
     * @param baiduMap
     */
    void initBaiduMap(BaiduMap baiduMap);

    /**
     * 初始化定位操作
     * @param locationClient
     */
    void initLocationClient(LocationClient locationClient);


}
