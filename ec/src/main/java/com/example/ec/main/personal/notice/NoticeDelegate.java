package com.example.ec.main.personal.notice;

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
import com.example.ec.main.personal.notice.detail.NoticePagerAdapter;

/**
 * @author alan
 *         Date  2018/7/4.
 *         Function : 我的关注界面
 *         Issue :
 */

public class NoticeDelegate extends LatteDelegate {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Override
    public Object setLayout() {
        return R.layout.delegate_personal_notice;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        mTabLayout = rootView.findViewById(R.id.tab_layout_notice);
        mViewPager = rootView.findViewById(R.id.view_pager_notice);

        initData();
        initTabLayout();
    }

    private void initData() {

        initPager(this);
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

    private void initPager(NoticeDelegate noticeDelegate) {
        final PagerAdapter adapter = new NoticePagerAdapter(getFragmentManager(), noticeDelegate);
        mViewPager.setAdapter(adapter);
    }

}
