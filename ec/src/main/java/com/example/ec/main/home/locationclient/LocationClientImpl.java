package com.example.ec.main.home.locationclient;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;

/**
 * @author alan
 *         Date  2018/6/9.
 *         Function : 初始化工具类
 *         Issue :
 */

public class LocationClientImpl implements ILocationClientListener {

    public LocationClientImpl(){

    }

    @Override
    public LocationClient initLocationClient(LocationClient locationClient) {

        LocationClientOption option = new LocationClientOption();
        option.setCoorType("bd09ll");
        option.setIsNeedAddress(true);
        option.setOpenGps(true);
        option.setIsNeedLocationDescribe(true);
        //请求间隔
        option.setScanSpan(1000);
        locationClient.setLocOption(option);

        return locationClient;
    }

    @Override
    public void initBaiduMap(BaiduMap baiduMap) {

        //100米
        MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(17.0f);
        baiduMap.setMapStatus(msu);
        baiduMap.setMyLocationEnabled(true);
    }



}
