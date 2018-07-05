package com.example.ui.refresh;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.core.app.Latte;
import com.example.core.net.RestClient;
import com.example.core.net.callback.ISuccess;
import com.example.core.util.log.LatteLogger;
import com.example.ui.recycler.DataConverter;
import com.example.ui.recycler.MultipleRecyclerAdapter;


/**
 * @author alan
 * RecyclerView 刷新使用
 */

public class RefreshHandler implements
        SwipeRefreshLayout.OnRefreshListener
        , BaseQuickAdapter.RequestLoadMoreListener {

    private final SwipeRefreshLayout REFRESH_LAYOUT;
    private final PagingBean BEAN;
    private final RecyclerView RECYCLERVIEW;
    private MultipleRecyclerAdapter mAdapter = null;
    private final DataConverter CONVERTER;

    public MultipleRecyclerAdapter getmAdapter() {
        return mAdapter;
    }

    public void setmAdapter(MultipleRecyclerAdapter mAdapter) {
        this.mAdapter = mAdapter;
    }

    private RefreshHandler(SwipeRefreshLayout swipeRefreshLayout,
                           RecyclerView recyclerView,
                           DataConverter converter, PagingBean bean) {
        this.REFRESH_LAYOUT = swipeRefreshLayout;
        this.RECYCLERVIEW = recyclerView;
        this.CONVERTER = converter;
        this.BEAN = bean;
        REFRESH_LAYOUT.setOnRefreshListener(this);
    }

    public static RefreshHandler create(SwipeRefreshLayout swipeRefreshLayout,
                                        RecyclerView recyclerView, DataConverter converter) {
        return new RefreshHandler(swipeRefreshLayout, recyclerView, converter, new PagingBean());
    }

    private void refresh() {
        REFRESH_LAYOUT.setRefreshing(true);
        Latte.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //进行一些网络请求
                REFRESH_LAYOUT.setRefreshing(false);
            }
        }, 1000);
    }

    public void firstPage(String url) {



        LatteLogger.d("IUDHAS", url);
        BEAN.setDelayed(1000);
        RestClient.builder()
                .url(url)
                .loader(RECYCLERVIEW.getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {

                        Log.e("alan",response.toString()+"===========");
                        final JSONObject object = JSON.parseObject(response);

                        //todo
//                        BEAN.setTotal(object.getInteger("total"))
//                                .setPageSize(object.getInteger("pagesize"));
                        //设置Adapter
                        mAdapter = MultipleRecyclerAdapter.create(CONVERTER.setJsonData(response));
                        mAdapter.setOnLoadMoreListener(RefreshHandler.this, RECYCLERVIEW);
                        RECYCLERVIEW.setAdapter(mAdapter);
                        BEAN.addIndex();
                    }
                })
                .build()
                .get();
    }

    private void paging(final String url) {
        final int pageSize = BEAN.getPageSize();
        final int currentCount = BEAN.getCurrentCount();
        final int total = BEAN.getTotal();
        final int index = BEAN.getPageIndex();

        if (mAdapter.getData().size() < pageSize || currentCount >= total) {
            mAdapter.loadMoreEnd(true);
        } else {
            Latte.getHandler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    RestClient.builder()
                            .url(url + index)
                            .success(new ISuccess() {
                                @Override
                                public void onSuccess(String response) {
                                    LatteLogger.json("paging", response);
                                    CONVERTER.clearData();
                                    mAdapter.addData(CONVERTER.setJsonData(response).convert());
                                    //累加数量
                                    BEAN.setCurrentCount(mAdapter.getData().size());
                                    mAdapter.loadMoreComplete();
                                    BEAN.addIndex();
                                }
                            })
                            .build()
                            .get();
                }
            }, 1000);
        }
    }

    @Override
    public void onRefresh() {
        refresh();
    }


    @Override
    public void onLoadMoreRequested() {
        paging("refresh.php?pageindex=");
    }
}
