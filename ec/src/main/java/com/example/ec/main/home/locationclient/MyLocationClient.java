package com.example.ec.main.home.locationclient;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

/**
 * @author alan
 *         Date  2018/6/9.
 *         Function :
 *         Issue :
 */

public class MyLocationClient implements ILocationClientListener {




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

}
