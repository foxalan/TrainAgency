package com.example.core.app;


import com.example.core.util.storage.LattePreference;

/**
 * 检查是否登入
 * @author alan
 */

public class AccountManager {

    private enum SignTag {
        SIGN_TAG,
        USER_ID,
    }

    /**
     *保存用户登录状态，登录后调用
     */
    public static void setSignState(boolean state) {
        LattePreference.setAppFlag(SignTag.SIGN_TAG.name(), state);
    }

    /**
     * 保存用户当前ID phone
     * @return
     */
    public static void setUserId(String phone){
        LattePreference.setUserId(SignTag.USER_ID.name(),phone);
    }

    public static String getUserId(){
        return LattePreference.getUserId(SignTag.USER_ID.name());
    }


    private static boolean isSignIn() {
        return LattePreference.getAppFlag(SignTag.SIGN_TAG.name());
    }

    public static void checkAccount(IUserChecker checker) {
        if (isSignIn()) {
            checker.onSignIn();
        } else {
            checker.onNotSignIn();
        }
    }
}
