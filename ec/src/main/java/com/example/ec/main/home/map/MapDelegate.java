package com.example.ec.main.home.map;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ZoomControls;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapView;

import com.baidu.mapapi.map.Marker;
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


    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        mMapView = rootView.findViewById(R.id.id_mapView);
        mBaiduMap = mMapView.getMap();

        mMarkerLy = rootView.findViewById(R.id.id_maker_ly);

        //初始化BaiDu地图
        initBaiduMap();
        //获取当前设置的位置

        //获取所有的培训机构的坐标信息

        //
        mBaiduMap.setOnMarkerClickListener(marker -> {

            //

            return false;
        });
    }

    private void initBaiduMap() {
        mLocationClient = new LocationClient(getContext());
        locationListener = new MyLocationListener();
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        mapInitializeImpl = new MapInitializeImpl();
        mapInitializeImpl.initLocationClient(mLocationClient);
        mapInitializeImpl.initBaiduMap(mBaiduMap);
        // 隐藏logo
        View child = mMapView.getChildAt(1);
        if (child != null && (child instanceof ImageView || child instanceof ZoomControls)){
            child.setVisibility(View.INVISIBLE);
        }

        //地图上比例尺
        mMapView.showScaleControl(false);
        // 隐藏缩放控件
        mMapView.showZoomControls(false);
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
