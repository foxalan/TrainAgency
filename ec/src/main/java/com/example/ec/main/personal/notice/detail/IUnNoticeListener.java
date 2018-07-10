package com.example.ec.main.personal.notice.detail;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * @author alan
 *         Date  2018/7/10.
 *         Function : 取消关注
 *         Issue :
 */

public interface IUnNoticeListener {
    void unNotice(int type,int position,View v);

    void toItem(int type,int position,View v);
}
