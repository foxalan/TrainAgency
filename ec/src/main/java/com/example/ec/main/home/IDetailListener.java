package com.example.ec.main.home;

import android.view.View;

/**
 * @author alan
 *         Date  2018/7/6.
 *         Function : 手机，关注的回调
 *         Issue :
 */

public interface IDetailListener {

    void click(int type,String phone,int state,View view);
}
