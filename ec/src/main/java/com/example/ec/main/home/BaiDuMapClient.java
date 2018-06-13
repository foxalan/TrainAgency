package com.example.ec.main.home;

import android.content.Context;
import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.example.ec.main.home.locationclient.MyLocationClient;
import com.example.ec.main.home.locationlistener.MyLocationListener;

/**
 * @author alan
 *         Date  2018/6/9.
 *         Function :
 *         Issue :
 */

public class BaiDuMapClient implements IOpenLocationListener ,IDealLocationListener{

    private MyLocationClient myLocationClient;
    private LocationClient locationClient;
    private MyLocationListener locationListener;
    private IHomeLocationListener homeLocationListener;
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

    public BaiDuMapClient(Context context, IHomeLocationListener homeLocationListener){
        locationClient = new LocationClient(context);
        this.homeLocationListener = homeLocationListener;
        myLocationClient = new MyLocationClient();
        locationListener = new MyLocationListener(this);
    }

    private static BaiDuMapClient baiDuMapClient;

    public static BaiDuMapClient create(Context context,IHomeLocationListener homeLocationListener){
        if (baiDuMapClient == null){
            baiDuMapClient = new BaiDuMapClient(context,homeLocationListener);
        }
        return baiDuMapClient;
    }

    @Override
    public void startRequestLocation() {
        if (locationClient!=null){
            //初始化定位信息
            locationClient = myLocationClient.initLocationClient(locationClient);
            //注册Listener
            locationClient.start();
            locationClient.registerLocationListener(locationListener);
        }
    }

    @Override
    public void stopRequestLocation() {
            if (locationClient!=null){
                locationClient.unRegisterLocationListener(locationListener);
            }
    }



    @Override
    public void Location(BDLocation location) {
       //30.622694===114.359512

        Log.e("location","latitude:"+location.getLatitude()+"==="+location.getLongitude());
        Log.e("location",location.getLocationDescribe()+"==="+location.getAddrStr());
        homeLocationListener.getCurrentLocation(location);
        setCurrentLocation(location);
    }
}
