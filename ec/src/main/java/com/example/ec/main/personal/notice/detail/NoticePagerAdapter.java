package com.example.ec.main.personal.notice.detail;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

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

       NOTICE_TYPE.add(NoticeType.NOTICE_TEACHER);
       NOTICE_TYPE.add(NoticeType.NOTICE_CLASS);

        TAB_TITLES.add("老师");
        TAB_TITLES.add("班级");
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0){
            return new NoticeClassDelegate();
        }else {
            return new NoticeTeacherDelegate();
        }
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
