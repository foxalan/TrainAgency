package com.example.ec.main.home.map;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RelativeLayout;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapView;

import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.example.core.delegate.LatteDelegate;
import com.example.ec.R;
import com.example.ec.main.home.map.impl.MapInitializeImpl;


/**
 * @Author Alan
 * Date 2018/3/27 0027
 * Function
 * Issue
 */

public class MapDelegate extends LatteDelegate {

    private MapView mMapView;
    private BaiduMap mBaiduMap = null;

    private RelativeLayout mMarkerLy;
    private MapInitializeImpl mapInitializeImpl;

    private LocationClient mLocationClient;
    private MyLocationListener locationListener;


    @Override
    public Object setLayout() {
        return R.layout.fragment_baidu_map;
    }

    private BitmapDescriptor mCurrentMarker;


    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        mMapView = rootView.findViewById(R.id.id_mapView);
        mBaiduMap = mMapView.getMap();

        mMarkerLy = rootView.findViewById(R.id.id_maker_ly);

        mLocationClient = new LocationClient(getContext());
        locationListener = new MyLocationListener();

        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        mapInitializeImpl = new MapInitializeImpl();
        mapInitializeImpl.initLocationClient(mLocationClient);
        mapInitializeImpl.initBaiduMap(mBaiduMap);

        //定义Maker坐标点
        LatLng point = new LatLng(39.963175, 116.400244);
        //构建Marker图标

        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.maker);

        //构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions()
                .position(point)
                .icon(bitmap);

        //在地图上添加Marker，并显示
        mBaiduMap.addOverlay(option);
    }

    @Override
    public void onResume() {
        super.onResume();

        mLocationClient.registerLocationListener(locationListener);
        //发起定位请求
        mLocationClient.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mLocationClient.unRegisterLocationListener(locationListener);
    }

    private class MyLocationListener extends BDAbstractLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            mapInitializeImpl.location(mBaiduMap, location);
        }
    }
}
