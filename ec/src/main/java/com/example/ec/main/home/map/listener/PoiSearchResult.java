package com.example.ec.main.home.map.listener;


import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchOption;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.example.ec.R;
import com.example.ec.main.home.map.bean.MapSearchAdapter;
import com.example.ec.main.home.map.option.PoiSearchOptionCreator;

import java.util.List;

/**
 * @author alan
 *         Date  2018/6/12.
 *         Function : 搜索回调
 *         Issue :
 */

public class PoiSearchResult implements OnGetPoiSearchResultListener, TextWatcher, AdapterView.OnItemClickListener {


    private ListView listView;
    private MapSearchAdapter mapSearchAdapter;
    private AppCompatEditText compatEditText;
    private BaiduMap baiduMap;
    private List<PoiInfo> poiInfoList;

    private LatLng currentLatLng;

    private PoiSearchOptionCreator poiSearchOptionCreator;
    private PoiSearch search;
    private PoiNearbySearchOption nearbySearchOption;
    private PoiDetailSearchOption detailSearchOption;


    public PoiSearchResult(ListView listView, MapSearchAdapter mapSearchAdapter, AppCompatEditText compatEditText, BaiduMap baiduMap, List<PoiInfo> poiInfoList) {
        this.listView = listView;
        this.mapSearchAdapter = mapSearchAdapter;
        this.compatEditText = compatEditText;
        this.baiduMap = baiduMap;
        this.poiInfoList = poiInfoList;
    }

    public static PoiSearchResult create(ListView listView, MapSearchAdapter mapSearchAdapter, AppCompatEditText appCompatEditText, BaiduMap baiduMap, List<PoiInfo> poiInfoList) {
        return new PoiSearchResult(listView, mapSearchAdapter, appCompatEditText, baiduMap, poiInfoList);
    }

    /**
     * 设置当前位置
     */

    public void init() {
        search = PoiSearch.newInstance();
        poiSearchOptionCreator = PoiSearchOptionCreator.create();

        nearbySearchOption = poiSearchOptionCreator.getPoiNearbySearchOption();
        detailSearchOption = poiSearchOptionCreator.getDetailSearchOption();

        compatEditText.addTextChangedListener(this);
        listView.setOnItemClickListener(this);
        search.setOnGetPoiSearchResultListener(this);
    }

    public void setCurrentLatLng(LatLng latLng) {
        this.currentLatLng = latLng;
    }

    /**
     * 大致的搜索结果
     *
     * @param poiResult
     */
    @Override
    public void onGetPoiResult(PoiResult poiResult) {

        if (poiResult != null && poiResult.error == PoiResult.ERRORNO.NO_ERROR) {
            //数据刷新
            poiInfoList.clear();
            poiInfoList.addAll(poiResult.getAllPoi());
            mapSearchAdapter.notifyDataSetChanged();

            Log.e("location", "address:success poi" + poiResult.toString());
            for (PoiInfo info : poiResult.getAllPoi()) {
                Log.e("location", info.address);
            }
        } else {

        }
    }


    /**
     * 具体的搜索
     *
     * @param poiDetailResult
     */
    @Override
    public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {
        if (poiDetailResult.error != SearchResult.ERRORNO.NO_ERROR) {
            Log.e("location", "address:error");
        } else {
            // 正常返回结果的时候，此处可以获得很多相关信息
            String address = poiDetailResult.address;
            Log.e("location", "address:" + address);
            LatLng latLng = poiDetailResult.location;
            MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(latLng);
            baiduMap.animateMapStatus(msu);

            BitmapDescriptor bitmap = BitmapDescriptorFactory
                    .fromResource(R.drawable.maker);

            OverlayOptions option = new MarkerOptions().position(latLng).icon(bitmap);
            baiduMap.addOverlay(option);
        }
    }

    @Override
    public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        String value = compatEditText.getText().toString();
        if (currentLatLng != null) {
            nearbySearchOption.keyword(value);
            nearbySearchOption.location(currentLatLng);
            search.searchNearby(nearbySearchOption);
        }
        Log.e("location", "value:" + value);

    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //进行具体定位

        PoiInfo info = poiInfoList.get(position);

        detailSearchOption.poiUid(info.uid);
        search.searchPoiDetail(detailSearchOption);
    }
}
