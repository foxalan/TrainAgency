package com.example.alan.trainagency.app;

import android.app.Application;

import com.example.core.app.Latte;
import com.example.ec.icon.FontEcModule;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

/**
 * @author alan
 *         Date  2018/6/4.
 *         Function : 初始化一些基本设置
 *         Issue :
 */

public class TrainApplication extends Application {

    private static final String API_HOST = "http://www.baidu.com/";
    private static final int DELAY_TIME = 1000;
    private static final String JAVA_INTERFACE = "train";

    @Override
    public void onCreate() {
        super.onCreate();

        Latte.init(this)
                .withApiHost(API_HOST)
                .withLoaderDelayed(DELAY_TIME)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withJavascriptInterface(JAVA_INTERFACE)
                .configure();
    }
}