package com.example.alan.trainagency;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.example.core.app.ConfigKeys;
import com.example.core.app.Latte;
import com.example.core.delegate.LatteDelegate;
import com.example.core.ui.loader.LatteLoader;
import com.example.core.ui.loader.LoaderStyle;
import com.example.core.util.log.LatteLogger;
import com.example.core.util.log.LoggerUtil;

/**
 * @author alan
 *         Date  2018/6/4.
 *         Function : 测试Delegatea
 *         Issue :
 */

public class ExpDelegate extends LatteDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_exp;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        String url = Latte.getConfiguration(ConfigKeys.API_HOST);
        LatteLogger.e("api_host",url);
        Log.e("api_host",url);
        Log.e("api_host","exp");
        LoggerUtil.e("ExpDelegate",url);
        LatteLoader.showLoading(getContext(), LoaderStyle.BallClipRotateIndicator);
    }
}
