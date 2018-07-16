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
        USER_ICON,
        USER_SIGN,
        USER_TYPE,
        USER_PSD,
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



    public static void setUserPsd(String psd){
        LattePreference.setUserPsd(SignTag.USER_PSD.name(),psd);
    }

    public static String getUserPsd(){
        return LattePreference.getUserPsd(SignTag.USER_PSD.name());
    }


    public static void setUserIcon(String url){
        LattePreference.setUserIcon(SignTag.USER_ICON.name(),url);
    }

    public static String getUserIcon(){
        return LattePreference.getUserIcon(SignTag.USER_ICON.name());
    }

    public static void setUserSign(String signature){
        LattePreference.setUserSignature(SignTag.USER_SIGN.name(),signature);
    }

    public static String getUserSign(){
        return LattePreference.getUserSignature(SignTag.USER_SIGN.name());
    }

    public static void setUserType(String type){
        LattePreference.setUserType(SignTag.USER_TYPE.name(),type);
    }

    public static String getUserType(){
        return LattePreference.getUserType(SignTag.USER_TYPE.name());
    }


    public static boolean isSignIn() {
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
