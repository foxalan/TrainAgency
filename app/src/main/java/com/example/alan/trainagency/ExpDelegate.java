package com.example.alan.trainagency;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.core.delegate.LatteDelegate;
import com.example.core.net.RestClient;
import com.example.core.net.callback.IError;
import com.example.core.net.callback.IFailure;
import com.example.core.net.callback.ISuccess;
import com.example.core.util.log.LoggerUtil;

/**
 * @author alan
 *         Date  2018/6/4.
 *         Function : 测试Delegatea
 *         Issue :
 */

public class ExpDelegate extends LatteDelegate {


    private static final String TAG = "ExpDelegate";

    @Override
    public Object setLayout() {
        return R.layout.delegate_exp;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {


//        String url = Latte.getConfiguration(ConfigKeys.API_HOST);
//        LatteLogger.e("api_host",url);
//        Log.e("api_host",url);
//        Log.e("api_host","exp");
//        LoggerUtil.e(TAG,url);
//        LatteLoader.showLoading(getContext(), LoaderStyle.BallClipRotateIndicator);

        RestClient.builder()
                .url("/tieba")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        LoggerUtil.e(TAG,"success"+"==="+response.toString());
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        LoggerUtil.e(TAG,"error");
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        LoggerUtil.e(TAG,"fail");

                    }
                }).build()
                .get();
    }
}
