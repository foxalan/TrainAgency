package com.example.ec.main.home.experience;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baidu.location.BDLocation;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.core.app.Latte;
import com.example.core.net.RestClient;
import com.example.ec.main.home.location.CurrentLocation;
import com.example.ec.main.home.subject.list.SubjectListAdapter;
import com.example.ui.recycler.DataConverter;
import com.example.ui.refresh.PagingBean;

/**
 * @author alan
 *         Date  2018/7/3.
 *         Function : 刷新使用
 *         Issue :
 */

public class ExperienceRefreshHandler implements SwipeRefreshLayout.OnRefreshListener
        , BaseQuickAdapter.RequestLoadMoreListener{

    private final SwipeRefreshLayout REFRESH_LAYOUT;
    private final PagingBean BEAN;
    private final RecyclerView RECYCLERVIEW;
    private ExperienceAdapter mAdapter = null;
    private final DataConverter CONVERTER;
    private String baseUrl;


    private ExperienceRefreshHandler(SwipeRefreshLayout swipeRefreshLayout,
                                     RecyclerView recyclerView,
                                     DataConverter converter, PagingBean bean) {
        this.REFRESH_LAYOUT = swipeRefreshLayout;
        this.RECYCLERVIEW = recyclerView;
        this.CONVERTER = converter;
        this.BEAN = bean;
    }

    public static ExperienceRefreshHandler create(SwipeRefreshLayout swipeRefreshLayout,
                                                  RecyclerView recyclerView, DataConverter converter) {
        return new ExperienceRefreshHandler(swipeRefreshLayout, recyclerView, converter, new PagingBean());
    }

    private void refresh() {
        REFRESH_LAYOUT.setRefreshing(true);
        Latte.getHandler().postDelayed(() -> {
            //进行一些网络请求
            REFRESH_LAYOUT.setRefreshing(false);
        }, 1000);
    }



    public void firstPage(String url) {


        baseUrl  = url;
        BEAN.setDelayed(1000);
        RestClient.builder()
                .url(url)
                .params("longitude",CurrentLocation.getInstance().getLongitude())
                .params("latitude",CurrentLocation.getInstance().getLatitude())
                .loader(RECYCLERVIEW.getContext())
                .success(response -> {

                    Log.e("alan",response.toString()+"===========");
                    final JSONObject object = JSON.parseObject(response);
                    BEAN.setTotal(object.getInteger("total"))
                              .setPageSize(object.getInteger("pagesize"));
                    mAdapter = new ExperienceAdapter(null);
                    //设置Adapter
                    mAdapter.addData(CONVERTER.setJsonData(response).convert());
                    mAdapter.setOnLoadMoreListener(ExperienceRefreshHandler.this, RECYCLERVIEW);
                    RECYCLERVIEW.setAdapter(mAdapter);
                    BEAN.addIndex();
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
            Latte.getHandler().postDelayed(() -> RestClient.builder()
                    .url(url + index)
                    .params("longitude",CurrentLocation.getInstance().getLongitude())
                    .params("latitude",CurrentLocation.getInstance().getLatitude())
                    .success(response -> {
                        Log.e("refresh","response:"+response);
                        CONVERTER.clearData();
                        mAdapter.addData(CONVERTER.setJsonData(response).convert());
                        //累加数量
                        BEAN.setCurrentCount(mAdapter.getData().size());
                        mAdapter.loadMoreComplete();
                        BEAN.addIndex();
                    })
                    .build()
                    .get(), 1000);
        }
    }

    @Override
    public void onRefresh() {
        refresh();
    }


    @Override
    public void onLoadMoreRequested() {
        paging(baseUrl+"?pageindex=");
    }
}
