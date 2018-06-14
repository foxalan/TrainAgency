package com.example.ec.main.home.location;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.example.ec.main.home.locationclient.ILocationClientListener;

/**
 * @author alan
 *         Date  2018/6/9.
 *         Function : 定位监听，每次定位后，保存到CurrentLocation
 *         Issue :
 */

public class LatteLocationListener extends BDAbstractLocationListener {

    private ILocationListener locationListener;

    public LatteLocationListener(ILocationListener locationListener){
        this.locationListener = locationListener;
    }

    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
        locationListener.location(bdLocation);
        CurrentLocation.getInstance().setBdLocation(bdLocation);
    }
}
