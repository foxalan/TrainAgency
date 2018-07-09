package com.example.alan.trainagency.sms;

import android.util.Log;

import com.example.ec.sign.ISignInPresenter;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * @author alan
 *         Date  2018/6/8.
 *         Function : 短信验证工具
 *         Issue :
 */

public class MsgProveHandler {

    private static final String TAG = "MSG_PROVE";

    private ISignInPresenter signInPresenter;

    public MsgProveHandler(ISignInPresenter signInPresenter) {
        this.signInPresenter = signInPresenter;
    }


    public static MsgProveHandler handler;

    public static MsgProveHandler create(ISignInPresenter signInPresenter) {

        if (handler == null){
            handler = new MsgProveHandler(signInPresenter);
        }

        return handler;
    }

    public void sendCode(final String country, final String phone) {

        // 注册一个事件回调，用于处理发送验证码操作的结果
        SMSSDK.registerEventHandler(new EventHandler() {
            @Override
            public void afterEvent(int event, int result, Object data) {

                if (result == SMSSDK.RESULT_COMPLETE) {
                    Log.e(TAG, "result:" + result);
                    // TODO 处理成功得到验证码的结果
                 //   signInPresenter.phoneError();
                } else {
                    // TODO 处理错误的结果

                }

            }
        });

        // 触发操作
        SMSSDK.getVerificationCode(country, phone);
    }

    public void submitCode(String country, String phone, String code) {


        // 注册一个事件回调，用于处理提交验证码操作的结果
        SMSSDK.registerEventHandler(new EventHandler() {

            @Override
            public void afterEvent(int event, int result, Object data) {

                if (result == SMSSDK.RESULT_COMPLETE) {
                    // TODO 处理验证成功的结果
                    signInPresenter.proveSuccess();

                } else {
                    // TODO 处理错误的结果
                    signInPresenter.proveFail();
                }

            }
        });
        // 触发操作
        SMSSDK.submitVerificationCode(country, phone, code);
    }
}
