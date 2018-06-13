package com.example.ec.main.home.map.option;


import com.baidu.mapapi.search.poi.PoiDetailSearchOption;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;

/**
 * @author alan
 *         Date  2018/6/13.
 *         Function :
 *         Issue :
 */

public class PoiSearchOptionCreator {

    private static PoiSearchOptionCreator poiSearchOptionCreator;
    private PoiNearbySearchOption nearbySearchOption;
    private PoiDetailSearchOption detailSearchOption;

    public PoiSearchOptionCreator(){}

    public static PoiSearchOptionCreator create(){
        if (poiSearchOptionCreator == null){
            poiSearchOptionCreator = new PoiSearchOptionCreator();
        }

        return poiSearchOptionCreator;
    }

    public PoiNearbySearchOption getPoiNearbySearchOption(){

        if (nearbySearchOption == null){
            nearbySearchOption = new PoiNearbySearchOption();
            // 检索半径，单位是米
            nearbySearchOption.pageNum(1);
            nearbySearchOption.radius(2000);

        }
        return nearbySearchOption;
    }

    public PoiDetailSearchOption getDetailSearchOption(){
        if (detailSearchOption == null){
            detailSearchOption = new PoiDetailSearchOption();
        }
        return detailSearchOption;
    }

}
