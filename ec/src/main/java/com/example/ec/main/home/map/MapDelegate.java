package com.example.ec.main.home.map;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;

import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ZoomControls;

import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;

import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.TextOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.example.core.delegate.LatteDelegate;
import com.example.ec.R;
import com.example.ec.main.home.locationclient.LocationClientImpl;
import com.example.ec.main.home.location.CurrentLocation;
import com.example.ec.main.home.map.bean.MapSearchAdapter;
import com.example.ec.main.home.map.bean.TrainBean;
import com.example.ec.main.home.map.bean.TrainDataConverter;
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

    private LocationClient mLocationClient;
//    private LatteLocationListener locationListener;

    private List<TrainBean> trainBeanList;

    private IconTextView mItvMapBack;
    private ListView mLvSearch;
    private AppCompatEditText mEtMapSearch;
    private MapSearchAdapter mapSearchAdapter;
    private List<PoiInfo> mPoiInfoList;

    private PoiSearchResult mPoiSearchResult;
    private LocationClientImpl locationClientImpl;


    @Override
    public Object setLayout() {
        return R.layout.fragment_baidu_map;
    }


    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        return super.onCreateAnimation(R.anim.anim_slide_in_bottom, true, R.anim.anim_slide_out_bottom);
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

        initViews(rootView);
        initSearchListView();
        //初始化BaiDu地图
        initBaiduMap();
        //获取当前设置的位置
        //标出所有的位置
        setTrainTitle();
    }

    private void initViews(View rootView) {
        mMapView = rootView.findViewById(R.id.id_mapView);

        mItvMapBack = rootView.findViewById(R.id.tv_map_back);
        mItvMapBack.setOnClickListener(v -> getSupportDelegate().pop());

        mLvSearch = rootView.findViewById(R.id.lv_map_search);
        mEtMapSearch = rootView.findViewById(R.id.et_map_search_view);

        mBaiduMap = mMapView.getMap();
    }

    private void initSearchListView() {
        //获取所有的培训机构的坐标信息
        trainBeanList = new TrainDataConverter().convert();
        mPoiInfoList = new ArrayList<>();
        mapSearchAdapter = new MapSearchAdapter(getContext(), mPoiInfoList, R.layout.item_list_search_map_location);
        mLvSearch.setAdapter(mapSearchAdapter);

        mPoiSearchResult = PoiSearchResult.create(mLvSearch, mapSearchAdapter, mEtMapSearch, mBaiduMap, mPoiInfoList);
        mPoiSearchResult.init();
    }

    /**
     * 添加标识物
     */
    private void setTrainTitle() {
        for (TrainBean trainBean : trainBeanList) {
            LatLng p = new LatLng(trainBean.getLatitude(), trainBean.getLongitude());
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

        locationClientImpl = new LocationClientImpl();
        locationClientImpl.initBaiduMap(mBaiduMap);

        mLocationClient = locationClientImpl.initLocationClient(mLocationClient);

        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);

        // 隐藏logo
        View child = mMapView.getChildAt(1);
        if (child != null) {
            if ((child instanceof ImageView || child instanceof ZoomControls)) {
                child.setVisibility(View.INVISIBLE);
            }
        }
        //地图上比例尺
        mMapView.showScaleControl(false);
        // 隐藏缩放控件
        mMapView.showZoomControls(false);

        //地图移动到当前位置
        moveToCurrentLocation(CurrentLocation.getInstance().getBdLocation());
    }


    private void moveToCurrentLocation(BDLocation location) {
        if (location != null) {
            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(latLng);
            mBaiduMap.animateMapStatus(msu);
            BitmapDescriptor bitmap = BitmapDescriptorFactory
                    .fromResource(R.drawable.maker);

            LatLng p = new LatLng(latitude, longitude);
            //补充一下p是这个
            OverlayOptions option = new MarkerOptions().position(p).icon(bitmap);
            //加图片
            OverlayOptions textOption = new TextOptions().bgColor(0xFFE33539)
                    .fontSize(32).fontColor(0xFFFFFFFF).text("我的位置").rotate(0)
                    .position(p);
            mBaiduMap.addOverlay(textOption);
            //在地图上添加Marker，并显示
            mBaiduMap.addOverlay(option);
        } else {
            //定位失败
        }


    }



    @Override
    public void onResume() {
        super.onResume();
//        mLocationClient.registerLocationListener(locationListener);
//        //发起定位请求
//        mLocationClient.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    //    mLocationClient.unRegisterLocationListener(locationListener);
    }

//    private class LatteLocationListener extends BDAbstractLocationListener {
//
//        @Override
//        public void onReceiveLocation(BDLocation location) {
//            mPoiSearchResult.setCurrentLatLng(new LatLng(location.getLatitude(), location.getLongitude()));
//        }
//    }


    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        getFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.anim_scale,R.anim.anim_rotate);

    }


}
