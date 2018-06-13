package com.example.ec.main.home.locationlistener;

import com.baidu.location.BDLocation;

/**
 * @author alan
 *         Date  2018/6/13.
 *         Function : 获取当前的位置工具
 *         Issue :
 */

public class CurrentLocation {

    private BDLocation bdLocation;

    private static CurrentLocation currentLocation;

    public static CurrentLocation getInstance(){
        if (currentLocation == null){
            currentLocation = new CurrentLocation();
        }

        return currentLocation;
    }

    public BDLocation getBdLocation() {
        return bdLocation;
    }

    public void setBdLocation(BDLocation bdLocation) {
        this.bdLocation = bdLocation;
    }
}
