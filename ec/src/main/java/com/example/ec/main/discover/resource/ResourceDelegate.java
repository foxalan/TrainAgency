package com.example.ec.main.discover.resource;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.core.delegate.LatteDelegate;
import com.example.core.net.RestClient;
import com.example.ec.R;
import com.example.ec.detail.TabPagerAdapter;


/**
 * @author alan
 *         Date  2018/7/3.
 *         Function : 资源
 *         Issue :
 */

public class ResourceDelegate extends LatteDelegate {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Override
    public Object setLayout() {
        return R.layout.delegate_discover_resource;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

        mTabLayout = rootView.findViewById(R.id.tab_layout);
        mViewPager = rootView.findViewById(R.id.view_pager);

        initData();
        initTabLayout();
    }

    private void initData() {
        RestClient.builder()
                .url("goods_detail.php")
                .loader(getContext())
                .success(response -> {
                    final JSONObject data =
                            JSON.parseObject(response).getJSONObject("data");
                    initPager(data);
                //    setShopCartCount(data);
                })
                .build()
                .get();
    }

    private void initTabLayout() {
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        final Context context = getContext();
        if (context != null) {
            mTabLayout.setSelectedTabIndicatorColor
                    (ContextCompat.getColor(context, R.color.text_red));
        }
        mTabLayout.setTabTextColors(ColorStateList.valueOf(Color.BLACK));
        mTabLayout.setBackgroundColor(Color.WHITE);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void initPager(JSONObject data) {
        final PagerAdapter adapter = new TabPagerAdapter(getFragmentManager(), data);
        mViewPager.setAdapter(adapter);
    }

}
