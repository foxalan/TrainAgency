package com.example.ec.main.home.map;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.ZoomControls;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapView;

import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.TextOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchOption;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.example.core.delegate.LatteDelegate;
import com.example.core.ui.Lo;
import com.example.ec.R;
import com.example.ec.main.home.map.bean.MapSearchAdapter;
import com.example.ec.main.home.map.bean.TrainBean;
import com.example.ec.main.home.map.bean.TrainDataConverter;
import com.example.ec.main.home.map.impl.MapInitializeImpl;
import com.example.ec.main.home.map.listener.PoiSearchResult;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;
import java.util.List;




/**
 * @Author Alan
 * Date 2018/3/27 0027
 * Function
 * Issue
 */

public class MapDelegate extends LatteDelegate {

    private MapView mMapView;
    private BaiduMap mBaiduMap = null;

    private MapInitializeImpl mapInitializeImpl;

    private LocationClient mLocationClient;
    private MyLocationListener locationListener;
    private List<TrainBean> trainBeanList;

    private IconTextView mItvMapBack;
    private ListView mLvSearch;
    private AppCompatEditText mEtMapSearch;
    private MapSearchAdapter mapSearchAdapter;
    private List<PoiInfo> mPoiInfoList;

    private PoiSearchResult mPoiSearchResult;


    @Override
    public Object setLayout() {
        return R.layout.fragment_baidu_map;
    }


    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

        mMapView = rootView.findViewById(R.id.id_mapView);
        mItvMapBack = rootView.findViewById(R.id.tv_map_back);
        mLvSearch = rootView.findViewById(R.id.lv_map_search);
        mEtMapSearch = rootView.findViewById(R.id.et_map_search_view);
        mBaiduMap = mMapView.getMap();

        mPoiInfoList = new ArrayList<>();
        mapSearchAdapter = new MapSearchAdapter(getContext(),mPoiInfoList,R.layout.item_list_search_map_location);
        mLvSearch.setAdapter(mapSearchAdapter);

        mPoiSearchResult = PoiSearchResult.create(mLvSearch,mapSearchAdapter,mEtMapSearch,mBaiduMap,mPoiInfoList);
        mPoiSearchResult.init();

        mItvMapBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportDelegate().pop();
            }
        });


        //初始化BaiDu地图
        initBaiduMap();
        //获取当前设置的位置
        //获取所有的培训机构的坐标信息
        trainBeanList = new TrainDataConverter().convert();
        //标出所有的位置
        setTrainTitle();
    }

    /**
     * 初始化Map搜索
     */
    private void initMapSearch() {
        //创建探索对象
        PoiSearch search = PoiSearch.newInstance();

        //POI检索的监听对象
        OnGetPoiSearchResultListener resultListener = new OnGetPoiSearchResultListener() {
            //获得POI的检索结果，一般检索数据都是在这里获取
            @Override
            public void onGetPoiResult(PoiResult poiResult) {
                Log.e("location","address:poi");
                //如果搜索到的结果不为空，并且没有错误
                if (poiResult != null && poiResult.error == PoiResult.ERRORNO.NO_ERROR) {

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
            //获得POI的详细检索结果，如果发起的是详细检索，这个方法会得到回调(需要uid)
            //详细检索一般用于单个地点的搜索，比如搜索一大堆信息后，选择其中一个地点再使用详细检索
            @Override
            public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {
                if (poiDetailResult.error != SearchResult.ERRORNO.NO_ERROR) {
                    Log.e("location","address:error");
                } else {
                    // 正常返回结果的时候，此处可以获得很多相关信息
                    String address = poiDetailResult.address;
                    Log.e("location","address:"+address);


                }
            }
            //获得POI室内检索结果
            @Override
            public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

            }
        };

        search.setOnGetPoiSearchResultListener(resultListener);
        LatLng point = new LatLng(30.622694, 114.359512);
        //获得Key
        String key = "怡江苑";
        //周边检索
        PoiNearbySearchOption nearbySearchOption = new PoiNearbySearchOption();
        nearbySearchOption.location(point);
        nearbySearchOption.keyword(key);
        nearbySearchOption.radius(1500);
        // 检索半径，单位是米
        nearbySearchOption.pageNum(1);
        //搜索一页
        search.searchNearby(nearbySearchOption);
        // 发起附近检索请求
    }

    /**
     * 添加标识物
     */
    private void setTrainTitle() {
        for (TrainBean trainBean:trainBeanList){
            LatLng p = new LatLng(trainBean.getLatitude(),trainBean.getLongitude());
            //补充一下p是这个
            BitmapDescriptor bitmap = BitmapDescriptorFactory
                    .fromResource(R.drawable.maker);

            OverlayOptions option = new MarkerOptions().position(p).icon(bitmap);
            mBaiduMap.addOverlay(option);
            //加图片
            OverlayOptions textOption = new TextOptions().bgColor(0xFFE33539)
                    .fontSize(32).fontColor(0xFFFFFFFF).text(trainBean.getTitle()).rotate(0)
                    .position(p);
            mBaiduMap.addOverlay(textOption);
            //在地图上添加Marker，并显示
            mBaiduMap.addOverlay(option);
        }
    }

    private void initBaiduMap() {
        mLocationClient = new LocationClient(getContext());
        locationListener = new MyLocationListener();

        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        mapInitializeImpl = new MapInitializeImpl();
        mapInitializeImpl.initLocationClient(mLocationClient);
        mapInitializeImpl.initBaiduMap(mBaiduMap);
        // 隐藏logo
        View child = mMapView.getChildAt(1);
        if (child != null && (child instanceof ImageView || child instanceof ZoomControls)){
            child.setVisibility(View.INVISIBLE);
        }

        //地图上比例尺
        mMapView.showScaleControl(false);
        // 隐藏缩放控件
        mMapView.showZoomControls(false);
    }

    @Override
    public void onResume() {
        super.onResume();

        mLocationClient.registerLocationListener(locationListener);
        //发起定位请求
        mLocationClient.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mLocationClient.unRegisterLocationListener(locationListener);
    }

    private class MyLocationListener extends BDAbstractLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            mPoiSearchResult.setCurrentLatLng(new LatLng(location.getLatitude(),
                    location.getLongitude()));
            mapInitializeImpl.location(mBaiduMap, location);
        }
    }
}
