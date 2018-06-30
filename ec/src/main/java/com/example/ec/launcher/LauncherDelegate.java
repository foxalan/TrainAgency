package com.example.ec.launcher;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.example.core.app.AccountManager;
import com.example.core.app.IUserChecker;
import com.example.core.app.Latte;
import com.example.core.delegate.LatteDelegate;
import com.example.core.util.log.LoggerUtil;
import com.example.core.util.storage.LattePreference;
import com.example.ec.R;
import com.example.ui.launcher.ILauncherListener;
import com.example.ui.launcher.OnLauncherFinishTag;
import com.example.ui.launcher.ScrollLauncherTag;

import java.text.MessageFormat;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author alan
 *         Date  2018/6/7.
 *         Function :每次打开APP时的界面
 *         Issue :
 */

public class LauncherDelegate extends LatteDelegate {

    private static final String TAG ="LauncherDelegate";
    private AppCompatTextView mTvLauncherTimer;
    private ScheduledExecutorService executorService;
    private long period = 1000;
    private int mCount = 4;
    private ILauncherListener mILauncherListener = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    /**
     * 绑定Activity
     * @param activity
     */
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILauncherListener) {
            mILauncherListener = (ILauncherListener) activity;
        }
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        mTvLauncherTimer = rootView.findViewById(R.id.tv_launcher_timer);

        executorService = Executors.newScheduledThreadPool(2);
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                LoggerUtil.e(TAG,"run");
                if (mTvLauncherTimer != null) {
                    Latte.getHandler().post(new Runnable() {
                        @Override
                        public void run() {
                            mTvLauncherTimer.setText(MessageFormat.format("跳过\n{0}s", mCount));
                        }
                    });

                    mCount--;
                    if (mCount <= 1) {
                        if (executorService != null) {
                            executorService.shutdown();
                            executorService = null;
                            checkIsShowScroll();
                        }
                    }
                }
            }
        },0, period, TimeUnit.MILLISECONDS);

        mTvLauncherTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (executorService!=null){
                    executorService.shutdownNow();
                }
                checkIsShowScroll();
            }
        });
    }

    /**
     *判断是否显示滑动启动页
     */
    private void checkIsShowScroll() {

        //检查用户是否是第一次登入
        AccountManager.checkAccount(new IUserChecker() {
            @Override
            public void onSignIn() {
                if (mILauncherListener != null) {
                    mILauncherListener.onLauncherFinish(OnLauncherFinishTag.SIGNED);
                }
            }

            @Override
            public void onNotSignIn() {
                if (mILauncherListener != null) {
                    mILauncherListener.onLauncherFinish(OnLauncherFinishTag.NOT_SIGNED);
                }
            }
        });



//        if (!LattePreference.getAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name())) {
//            getSupportDelegate().start(new LauncherScrollDelegate(), SINGLETASK);
//        } else {
//
//        }
    }

    /**
     * 不可以点击返回键
     * @return
     */
    @Override
    public boolean onBackPressedSupport() {

        return true;
    }

}
