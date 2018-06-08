package com.example.alan.trainagency.sign;

import android.view.View;

/**
 * @author alan
 *         Date  2018/6/8.
 *         Function :
 *         Issue :
 */

public interface ISignInPresenter {

    /**
     * 手机号错误
     */
    void phoneError();

    /**
     * 验证失败
     */
    void proveFail();

    /**
     * 验证成功
     */
    void proveSuccess();


}
