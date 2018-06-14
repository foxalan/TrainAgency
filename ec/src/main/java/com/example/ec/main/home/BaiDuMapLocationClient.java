package com.example.ec.main.home;

import android.content.Context;

import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.example.ec.main.home.location.LatteLocationListener;
import com.example.ec.main.home.location.ILocationListener;
import com.example.ec.main.home.locationclient.LocationClientImpl;

/**
 * @author alan
 *         Date  2018/6/9.
 *         Function : 用于定位
 *         Issue :
 */

public class BaiDuMapLocationClient implements IOpenLocationListener {

    private LocationClientImpl locationClientImpl;
    private LocationClient locationClient;
    private LatteLocationListener latteLocationListener;

    /**
     * 当前的位置信息
     */
    private BDLocation currentLocation;

    public BDLocation getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(BDLocation currentLocation) {
        this.currentLocation = currentLocation;
    }

    public BaiDuMapLocationClient(Context context, ILocationListener iLocationListener) {
        locationClient = new LocationClient(context);
        locationClientImpl = new LocationClientImpl();
        latteLocationListener = new LatteLocationListener(iLocationListener);
    }

    private static BaiDuMapLocationClient baiDuMapLocationClient;

    public static BaiDuMapLocationClient create(Context context, ILocationListener locationListener) {

        baiDuMapLocationClient = new BaiDuMapLocationClient(context, locationListener);

        return baiDuMapLocationClient;
    }

    public static BaiDuMapLocationClient getInstance() {
        if (baiDuMapLocationClient != null) {
            return baiDuMapLocationClient;
        }

        return null;
    }

    @Override
    public void startRequestLocation() {
        if (locationClient != null) {
            //初始化定位信息
            locationClient = locationClientImpl.initLocationClient(locationClient);
            //注册Listener
            locationClient.start();
            locationClient.registerLocationListener(latteLocationListener);
        }
    }

    @Override
    public void stopRequestLocation() {
        if (locationClient != null) {
            locationClient.unRegisterLocationListener(latteLocationListener);
        }
    }


//    @Override
//    public void Location(BDLocation location) {
//       //30.622694===114.359512
//
//        Log.e("location","latitude:"+location.getLatitude()+"==="+location.getLongitude());
//        Log.e("location",location.getLocationDescribe()+"==="+location.getAddrStr());
//
//        setCurrentLocation(location);
//    }
}
