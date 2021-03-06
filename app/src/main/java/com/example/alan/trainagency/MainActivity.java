package com.example.alan.trainagency;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;

import com.example.ec.sign.SignInPsdDelegate;
import com.example.core.activity.ProxyActivity;
import com.example.core.app.Latte;
import com.example.core.delegate.LatteDelegate;
import com.example.ec.launcher.LauncherDelegate;
import com.example.ec.main.EcBottomDelegate;
import com.example.ui.launcher.ILauncherListener;
import com.example.ui.launcher.OnLauncherFinishTag;

import qiu.niorgai.StatusBarCompat;

/**
 * @author alan
 * 主Activity
 *
 */

public class MainActivity extends ProxyActivity implements ILauncherListener {

    @Override
    public LatteDelegate setRootDelegate() {

        return new LauncherDelegate();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去年ActionBar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},2);
        }
        Latte.getConfigurator().withActivity(this);
        //设置沉浸式状态栏
        StatusBarCompat.translucentStatusBar(this, true);

    }

    @Override
    public void post(Runnable runnable) {

    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        switch (tag){
            case SIGNED:
                getSupportDelegate().startWithPop(new EcBottomDelegate());
                break;
            case NOT_SIGNED:
                getSupportDelegate().startWithPop(new SignInPsdDelegate());
                break;
                default:

        }
    }
}
