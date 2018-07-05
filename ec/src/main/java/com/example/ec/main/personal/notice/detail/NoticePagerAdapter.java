package com.example.ec.main.personal.notice.detail;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.alibaba.fastjson.JSONArray;

import java.util.ArrayList;

/**
 * @author alan
 *         Date  2018/7/4.
 *         Function : 关注的分页
 *         Issue :
 */

public class NoticePagerAdapter extends FragmentStatePagerAdapter {

    private final ArrayList<String> TAB_TITLES = new ArrayList<>();
    private final ArrayList<String> NOTICE_TYPE = new ArrayList<>();


    public NoticePagerAdapter(FragmentManager fm, com.alibaba.fastjson.JSONObject data) {
        super(fm);

       NOTICE_TYPE.add(NoticeType.NOTICE_CURRENT);
       NOTICE_TYPE.add(NoticeType.NOTICE_ALL);

        TAB_TITLES.add("最新更新");
        TAB_TITLES.add("全部关注");


    }

    @Override
    public Fragment getItem(int position) {

        return NoticeDetailDelegate.create(NOTICE_TYPE.get(position));
    }

    @Override
    public int getCount() {
        return TAB_TITLES.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TAB_TITLES.get(position);
    }
}
