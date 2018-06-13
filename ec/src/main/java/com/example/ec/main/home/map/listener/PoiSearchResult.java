package com.example.ec.main.home.map.listener;

import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
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
    private PoiSearch search ;
    private PoiNearbySearchOption nearbySearchOption;


    public PoiSearchResult(ListView listView, MapSearchAdapter mapSearchAdapter, AppCompatEditText compatEditText, BaiduMap baiduMap,List<PoiInfo> poiInfoList) {
        this.listView = listView;
        this.mapSearchAdapter = mapSearchAdapter;
        this.compatEditText = compatEditText;
        this.baiduMap = baiduMap;
        this.poiInfoList = poiInfoList;
    }

    public static PoiSearchResult create(ListView listView,MapSearchAdapter mapSearchAdapter,AppCompatEditText appCompatEditText,BaiduMap baiduMap,List<PoiInfo> poiInfoList){
        return new PoiSearchResult(listView,mapSearchAdapter,appCompatEditText,baiduMap,poiInfoList);
    }

    /**
     * 设置当前位置
     */

    public void init(){
        search =  PoiSearch.newInstance();
        poiSearchOptionCreator = PoiSearchOptionCreator.create();

        nearbySearchOption = poiSearchOptionCreator.getPoiNearbySearchOption();

        compatEditText.addTextChangedListener(this);
        listView.setOnItemClickListener(this);
        search.setOnGetPoiSearchResultListener(this);
    }

    public void setCurrentLatLng(LatLng latLng){
        this.currentLatLng = latLng;
    }


    /**
     * 附近搜索
     */
    public void nearbySearch(){
        compatEditText.addTextChangedListener(this);
    }

    /**
     * 精确搜索
     */
    public void detailBySearch(){

    }


    /**
     * 大致的搜索结果
     * @param poiResult
     */
    @Override
    public void onGetPoiResult(PoiResult poiResult) {

        if (poiResult != null && poiResult.error == PoiResult.ERRORNO.NO_ERROR) {
            //数据刷新
            poiInfoList.clear();
            poiInfoList.addAll(poiResult.getAllPoi());
            mapSearchAdapter.notifyDataSetChanged();

            Log.e("location","address:success poi"+poiResult.toString());
            for(PoiInfo info:poiResult.getAllPoi()){
                Log.e("location",info.address);
            }

//                    MyOverLay overlay = new MyOverLay(mBaiduMap, search);
//                    //这传入search对象，因为一般搜索到后，点击时方便发出详细搜索
//                    //设置数据,这里只需要一步，
//                    overlay.setData(poiResult);
//                    //添加到地图
//                    overlay.addToMap();
//                    //将显示视图拉倒正好可以看到所有POI兴趣点的缩放等级
//                    overlay.zoomToSpan();//计算工具
//                    //设置标记物的点击监听事件
//                    mBaiduMap.setOnMarkerClickListener(overlay);
        } else {
            //   Toast.makeText(getApplication(), "搜索不到你需要的信息！", Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * 具体的搜索
     * @param poiDetailResult
     */
    @Override
    public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {

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
        if (currentLatLng !=null){
            nearbySearchOption.keyword(value);
            nearbySearchOption.location(currentLatLng);
            search.searchNearby(nearbySearchOption);
        }
        Log.e("location","value:"+value);

    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
