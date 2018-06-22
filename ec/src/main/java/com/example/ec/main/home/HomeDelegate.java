package com.example.ec.main.home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.baidu.location.BDLocation;
import com.example.core.app.Latte;
import com.example.core.delegate.bottom.BottomItemDelegate;
import com.example.ec.R;
import com.example.ec.main.EcBottomDelegate;
import com.example.ec.main.home.address.AddressDelegate;
import com.example.ec.main.home.location.CurrentLocation;
import com.example.ec.main.home.location.ILocationListener;
import com.example.ec.main.dynamic.message.MessageDelegate;
import com.example.ec.main.home.search.SearchDelegate;
import com.example.ui.recycler.BaseDecoration;
import com.example.ui.refresh.RefreshHandler;
import com.joanzapata.iconify.widget.IconTextView;

import q.rorbin.badgeview.QBadgeView;

/**
 * @author alan
 *         Date  2018/6/5.
 *         Function :
 *         Issue :
 */

public class HomeDelegate extends BottomItemDelegate implements ILocationListener{

    private AppCompatTextView mTvLocation;
    private AppCompatEditText mEtSearch;
    private IconTextView mIconMsg;

    private BaiDuMapLocationClient baiDuMapLocationClient;
    private QBadgeView mQBadgeView;

    private RecyclerView mRecyclerView = null;
    private SwipeRefreshLayout mRefreshLayout = null;
    private RefreshHandler mRefreshHandler = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_home;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

        mRecyclerView = $(R.id.rv_index);
        mRefreshLayout = $(R.id.srl_index);

        mTvLocation = rootView.findViewById(R.id.tv_location);
        mEtSearch = rootView.findViewById(R.id.et_search_view);
        mIconMsg = rootView.findViewById(R.id.icon_index_message);

        mTvLocation.setOnClickListener(v -> getParentDelegate().getSupportDelegate().start(new AddressDelegate()));
        mIconMsg.setOnClickListener(v -> getParentDelegate().getSupportDelegate().start(new MessageDelegate()));

        mRefreshHandler = RefreshHandler.create(mRefreshLayout, mRecyclerView, new HomeDataConverter());
        //Search
        mEtSearch.setOnClickListener(v -> getParentDelegate().start(new SearchDelegate()));

        //设置消息
        mQBadgeView = new QBadgeView(getContext());
        mQBadgeView.bindTarget(mIconMsg).setBadgeNumber(12).setBadgeTextSize(8,true);

        baiDuMapLocationClient = BaiDuMapLocationClient.create(getContext(),this);
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
        final GridLayoutManager manager = new GridLayoutManager(getContext(), 4);
        final Context context = getContext();
        mRecyclerView.setLayoutManager(manager);
        if (context != null) {
            mRecyclerView.addItemDecoration
                    (BaseDecoration.create(ContextCompat.getColor(context, R.color.app_background), 5));
        }
        final EcBottomDelegate ecBottomDelegate = getParentDelegate();
        mRecyclerView.addOnItemTouchListener(IndexItemClickListener.create(ecBottomDelegate));
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initRefreshLayout();
        initRecyclerView();
        mRefreshHandler.firstPage("index.php");
    }

    @Override
    public void location(BDLocation location) {
        //得到当前的位置信息
        Latte.getHandler().post(() -> {
            mTvLocation.setText(location.getAddrStr().substring(1,4));
        });
        //将地址存入工具中
        CurrentLocation.getInstance().setBdLocation(location);
        baiDuMapLocationClient.stopRequestLocation();
    }
}
