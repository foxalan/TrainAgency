package com.example.ec.main.home.locationlistener;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.example.ec.main.home.IDealLocationListener;

/**
 * @author alan
 *         Date  2018/6/9.
 *         Function :
 *         Issue :
 */

public class MyLocationListener extends BDAbstractLocationListener {


    private IDealLocationListener dealLocationListener;

    public MyLocationListener(IDealLocationListener dealLocationListener){
        this.dealLocationListener = dealLocationListener;
    }


    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
        dealLocationListener.Location(bdLocation);
    }
}
