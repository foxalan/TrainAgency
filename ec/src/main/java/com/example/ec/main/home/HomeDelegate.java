package com.example.ec.main.home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baidu.location.BDLocation;
import com.example.core.app.Latte;
import com.example.core.delegate.bottom.BottomItemDelegate;
import com.example.core.net.RestClient;
import com.example.core.net.callback.ISuccess;
import com.example.ec.R;
import com.example.ec.main.EcBottomDelegate;
import com.example.ec.main.home.address.AddressDelegate;
import com.example.ec.main.home.experience.ExperienceClassDelegate;
import com.example.ec.main.home.location.CurrentLocation;
import com.example.ec.main.home.location.ILocationListener;
import com.example.ec.main.dynamic.message.MessageDelegate;
import com.example.ec.main.home.search.SearchDelegate;
import com.example.ec.main.home.subject.good.GreatSubjectDelegate;
import com.example.ec.main.home.teacher.TeacherDelegate;
import com.example.ui.recycler.DataConverter;
import com.example.ui.recycler.IChoicenessClickListener;
import com.example.ui.recycler.MultipleRecyclerAdapter;
import com.example.ui.refresh.RefreshHandler;
import com.joanzapata.iconify.widget.IconTextView;

import q.rorbin.badgeview.QBadgeView;

/**
 * @author alan
 *         Date  2018/6/5.
 *         Function :
 *         Issue :
 */

public class HomeDelegate extends BottomItemDelegate implements ILocationListener ,SwipeRefreshLayout.OnRefreshListener{

    private AppCompatTextView mTvLocation;
    private AppCompatEditText mEtSearch;
    private IconTextView mIconMsg;

    private BaiDuMapLocationClient baiDuMapLocationClient;
    private QBadgeView mQBadgeView;

    private RecyclerView mRecyclerView = null;
    private SwipeRefreshLayout mRefreshLayout = null;

    private MultipleRecyclerAdapter mAdapter = null;
    private DataConverter converter;

    @Override
    public Object setLayout() {
        return R.layout.delegate_home;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

        mRecyclerView = $(R.id.rv_index);
        mRefreshLayout = $(R.id.srl_index);

        mRefreshLayout.setOnRefreshListener(this);

        mTvLocation = rootView.findViewById(R.id.tv_location);
        mEtSearch = rootView.findViewById(R.id.et_search_view);
        mIconMsg = rootView.findViewById(R.id.icon_index_message);

        mTvLocation.setOnClickListener(v -> getParentDelegate().getSupportDelegate().start(new AddressDelegate()));
        mIconMsg.setOnClickListener(v -> getParentDelegate().getSupportDelegate().start(new MessageDelegate()));

        converter = new HomeDataConverter();


        mEtSearch.setOnClickListener(v -> getParentDelegate().start(new SearchDelegate()));

        //设置消息
        mQBadgeView = new QBadgeView(getContext());
        mQBadgeView.bindTarget(mIconMsg).setBadgeNumber(12).setBadgeTextSize(8, true);

        baiDuMapLocationClient = BaiDuMapLocationClient.create(getContext(), this);
        baiDuMapLocationClient.startRequestLocation();
    }


    private void initRefreshLayout() {
        mRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );
        mRefreshLayout.setProgressViewOffset(true, 120, 300);
    }

    private void initRecyclerView() {
        final GridLayoutManager manager = new GridLayoutManager(getContext(), 5);
        mRecyclerView.setLayoutManager(manager);
        final EcBottomDelegate ecBottomDelegate = getParentDelegate();
        mRecyclerView.addOnItemTouchListener(IndexItemClickListener.create(ecBottomDelegate));
    }


    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);

        initData();

        initRefreshLayout();
        initRecyclerView();
    }

    private void initData() {

        RestClient.builder()
                .url("SubjectTypeLsit?name=武昌区&longitude=114.344131&latitude=30.61037")
                .loader(getContext())
                .success(response -> {
                    mAdapter = MultipleRecyclerAdapter.create(converter.setJsonData(response));
                    mAdapter.setChoicenessClickListener((type, view) -> {
                        switch (type) {
                            case 2:
                                getParentDelegate().getSupportDelegate().start(new ExperienceClassDelegate());
                                break;
                            case 1:
                                getParentDelegate().getSupportDelegate().start(new TeacherDelegate());
                                break;
                            case 0:
                                getParentDelegate().getSupportDelegate().start(new GreatSubjectDelegate());
                                break;
                            default:
                                break;
                        }
                    });
                    mRecyclerView.setAdapter(mAdapter);
                })
                .build()
                .get();
    }

    @Override
    public void location(BDLocation location) {
        //得到当前的位置信息
        Latte.getHandler().post(() -> {
            String district = location.getAddress().district;
            mTvLocation.setText(district);
        });
        //将地址存入工具中
        CurrentLocation.getInstance().setBdLocation(location);
        baiDuMapLocationClient.stopRequestLocation();
    }

    @Override
    public void onRefresh() {
            refresh();
    }

    private void refresh() {
        mRefreshLayout.setRefreshing(true);
        Latte.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //进行一些网络请求
                mRefreshLayout.setRefreshing(false);
            }
        }, 1000);
    }

}
