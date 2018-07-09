package com.example.ec.sign;

/**
 * @author alan
 *         Date  2018/6/7.
 *         Function :
 *         Issue :
 */

public interface ISignInListener {

    /**
     * 手机号错误
     */
    void phoneError();

    /**
     * 验证码错误
     */
    void proveError();

    /**
     * 登入成功
     */
    void success();

    /**
     * 登入失败
     *
     */

    void fail();
}
