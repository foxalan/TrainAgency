package com.example.ec.main.personal.notice.detail;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.core.delegate.LatteDelegate;
import com.example.ec.main.personal.notice.NoticeDelegate;

import java.util.ArrayList;

import me.yokeyword.fragmentation.ISupportFragment;

/**
 * @author alan
 *         Date  2018/7/4.
 *         Function : 关注的分页
 *         Issue :
 */

public class NoticePagerAdapter extends FragmentStatePagerAdapter {

    private final ArrayList<String> TAB_TITLES = new ArrayList<>();
    private NoticeDelegate noticeDelegate;

    public NoticePagerAdapter(FragmentManager fm, NoticeDelegate noticeDelegate) {
        super(fm);
        this.noticeDelegate = noticeDelegate;

        TAB_TITLES.add("老师");
        TAB_TITLES.add("班级");
    }

    @Override
    public Fragment getItem(int position) {

        if (position == 0) {
            return new NoticeTeacherDelegate(noticeDelegate);
        } else {
            return new NoticeClassDelegate(noticeDelegate);
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
