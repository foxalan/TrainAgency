package com.example.alan.trainagency.app;

import android.app.Application;
import android.util.Log;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.example.alan.trainagency.R;
import com.example.core.app.Latte;
import com.example.core.bluetooth.AppInfo;
import com.example.core.net.interceptors.DebugInterceptor;
import com.example.ec.icon.FontEcModule;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.mob.MobSDK;




/**
 * @author alan
 *         Date  2018/6/4.
 *         Function : 初始化一些基本设置
 *         Issue : Interceptor
 */

public class TrainApplication extends Application {

    private static final String API_HOST = "http://192.168.1.186/Select/";
    private static final int DELAY_TIME = 1000;
    private static final String JAVA_INTERFACE = "train";

    @Override
    public void onCreate() {
        super.onCreate();

        AppInfo.init(getApplicationContext());

        Latte.init(this)
                .withApiHost(API_HOST)
                .withLoaderDelayed(DELAY_TIME)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withJavascriptInterface(JAVA_INTERFACE)
                .withInterceptor(new DebugInterceptor("test", R.raw.test))
                //添加Cookie同步拦截器
                .withWebHost("https://www.baidu.com/")
                .configure();

        MobSDK.init(this);

        SDKInitializer.initialize(this);
    }

}
