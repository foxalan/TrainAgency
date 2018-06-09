package com.example.ec.main.home.map.impl;



import com.baidu.location.BDLocation;
import com.baidu.mapapi.map.BaiduMap;

/**
 * @Author Alan
 * Date 2018/3/27 0027
 * Function 百度地图的操作接口
 * Issue
 */

public interface IMapOperate {
    /**
     * 对地图进行操作
     * @param baiduMap
     * @param location
     */
    void location(BaiduMap baiduMap, BDLocation location);

    /**
     * 定位到自己
     * @param baiduMap
     */
    void centerToMyLocation(BaiduMap baiduMap);
}
