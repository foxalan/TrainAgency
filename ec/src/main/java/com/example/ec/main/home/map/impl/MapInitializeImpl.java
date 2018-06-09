package com.example.ec.main.home.map.impl;

import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.example.ec.R;


/**
 * @Author Alan
 * Date 2018/3/27 0027
 * Function 百度地图的实现类
 * Issue
 */

public class MapInitializeImpl implements IMapInitialize, IMapOperate {

    private boolean isFirst = true;
    private double latitude;
    private double longitude;

    public MapInitializeImpl() {
    }

    @Override
    public void initBaiduMap(BaiduMap baiduMap) {

        MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(15.0f);
        baiduMap.setMapStatus(msu);
        baiduMap.setMyLocationEnabled(true);
    }

    @Override
    public void initLocationClient(LocationClient locationClient) {

        LocationClientOption option = new LocationClientOption();
        option.setCoorType("bd09ll");
        option.setIsNeedAddress(true);
        option.setOpenGps(true);
        option.setIsNeedLocationDescribe(true);
        //请求间隔
        option.setScanSpan(1000);
        locationClient.setLocOption(option);

    }

    @Override
    public void location(BaiduMap baiduMap, BDLocation location) {

        if (isFirst) {
            LatLng latLng = new LatLng(location.getLatitude(),
                    location.getLongitude());
            latitude = location.getLatitude();
            longitude = location.getLongitude();
            MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(latLng);
            baiduMap.animateMapStatus(msu);
            isFirst = false;
        }
        Log.e("alan", location.getLatitude() + "------" + location.getLongitude() + "====");
        MyLocationData data = new MyLocationData.Builder()
                .accuracy(location.getRadius())
                .latitude(location.getLatitude())
                .longitude(location.getLongitude())
                .direction(100)
                .build();
        baiduMap.setMyLocationData(data);

        String locationDescribe = location.getLocationDescribe();
        Log.e("alan", locationDescribe);
        MyLocationConfiguration.LocationMode mCurrentMode = MyLocationConfiguration.LocationMode.NORMAL;
        // 设置定位图层的配置（定位模式，是否允许方向信息，用户自定义定位图标）
        BitmapDescriptor mCurrentMarker = BitmapDescriptorFactory
                .fromResource(R.drawable.maker);
        MyLocationConfiguration config = new MyLocationConfiguration(mCurrentMode, true, mCurrentMarker);
        baiduMap.setMyLocationConfiguration(config);


    }


    @Override
    public void centerToMyLocation(BaiduMap baiduMap) {
        LatLng latLng = new LatLng(latitude, longitude);
        MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(latLng);
        baiduMap.animateMapStatus(msu);
    }
}
