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
    private final ArrayList<ArrayList<NoticeBean>> NOTICEBEANS = new ArrayList<>();


    public NoticePagerAdapter(FragmentManager fm, com.alibaba.fastjson.JSONObject data) {
        super(fm);

        //获取tabs信息，注意，这里的tabs是一条信息
//        final JSONArray tabs = data.getJSONArray("tabs");
//        final int size = tabs.size();
//        for (int i = 0; i < size; i++) {
//            final com.alibaba.fastjson.JSONObject eachTab = tabs.getJSONObject(i);
//            final String name = eachTab.getString("name");
//            final JSONArray pictureUrls = eachTab.getJSONArray("pictures");
//            final ArrayList<String> eachTabPicturesArray = new ArrayList<>();
//            //存储每个图片
//            final int pictureSize = pictureUrls.size();
//            for (int j = 0; j < pictureSize; j++) {
//                eachTabPicturesArray.add(pictureUrls.getString(j));
//            }
//            TAB_TITLES.add(name);
//            PICTURES.add(eachTabPicturesArray);
//        }

        TAB_TITLES.add("最新更新");
        TAB_TITLES.add("全部关注");


    }

    @Override
    public Fragment getItem(int position) {

        return  NoticeDetailDelegate.create(NOTICEBEANS.get(position));
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
