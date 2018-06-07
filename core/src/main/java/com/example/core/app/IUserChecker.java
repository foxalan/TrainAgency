package com.example.core.app;

/**
 * Created by 傅令杰 on 2017/4/22
 */

public interface IUserChecker {

    /**
     * 已经登入
     */
    void onSignIn();

    /**
     * 没有登入
     */
    void onNotSignIn();
}
