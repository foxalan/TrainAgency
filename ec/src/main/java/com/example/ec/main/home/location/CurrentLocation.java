package com.example.ec.main.home.location;

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


    public  double getLongitude(){
        if (getBdLocation()!=null){

            return getBdLocation().getLongitude();
        }else {
            return 0.00;
        }
    }

    public double getLatitude(){
        if (getBdLocation() != null){
            return getBdLocation().getLatitude();
        }else {
            return 0.00;
        }
    }

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
