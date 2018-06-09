package com.example.alan.trainagency.sign;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.test.espresso.remote.EspressoRemoteMessage;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.alan.trainagency.R;
import com.example.alan.trainagency.sms.MsgProveHandler;
import com.example.core.app.AccountManager;
import com.example.core.app.Latte;
import com.example.core.delegate.LatteDelegate;
import com.example.core.util.log.LoggerUtil;
import com.example.ec.main.EcBottomDelegate;


import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author alan
 *         Date  2018/6/5.
 *         Function : 登入界面
 *         Issue : mBtProve有问题
 */

public class SignInDelegate extends LatteDelegate implements View.OnClickListener ,ISignInListener{

    private static final String TAG = "SignInDelegate";
    private EditText mEdPhone;
    private EditText mEdProv;
    private ImageButton mIbClearPhone;
    private ImageButton mIbClearProv;
    private Button mBtProve;
    private Button mBtLogin;
    private boolean isProve = false;
    private ScheduledExecutorService scheduledExecutorService;

    private static final int STATE_CLICK = 0;
    private static final int STATE_UNCLICK = 1;


    private SignInPresenterImpl signInPresenter;

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    private int colorBlue = 0xff33b5e5;

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        mEdPhone = rootView.findViewById(R.id.et_phone);
        mEdProv = rootView.findViewById(R.id.et_prove);
        mIbClearPhone = rootView.findViewById(R.id.delete_phone);
        mIbClearProv = rootView.findViewById(R.id.delete_prove);
        mBtProve = rootView.findViewById(R.id.bt_prove);
        mBtLogin = rootView.findViewById(R.id.btn_sign_in);

        mBtProve.setEnabled(false);
        mBtProve.setTextColor(Color.GRAY);

        setLoginClickedState(STATE_UNCLICK);

        initEvents();
        scheduledExecutorService = Executors.newScheduledThreadPool(2);

        signInPresenter = new SignInPresenterImpl(this);
    }

    /**
     * 设置登入不可以点击
     */
    private void setLoginClickedState(int state) {
        switch (state) {
            case STATE_CLICK:
                mBtLogin.setEnabled(true);
                mBtLogin.setBackgroundColor(colorBlue);
                break;
            case STATE_UNCLICK:
                mBtLogin.setBackgroundColor(Color.GRAY);
                mBtLogin.setEnabled(false);
                break;
            default:
                break;
        }

    }

    /**
     * 设置验证不可以点击
     */
    private void setProveClickedState(int state) {

        switch (state) {
            case STATE_CLICK:
                if ("".equals(mBtProve.getText().toString())){
                    return;
                }
                if ("".equals(mEdPhone.getText().toString())){
                    return;
                }
                mBtProve.setEnabled(true);
                mBtProve.setTextColor(Color.RED);
                break;
            case STATE_UNCLICK:

                mBtProve.setTextColor(Color.GRAY);
                mBtProve.setEnabled(false);
                break;
            default:
                break;
        }

    }

    private int phoneLength = 11;

    private void initEvents() {
        //当输入手机号时显示清除图标
        mEdPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    // 如果用户名有内容时候 显示删除按钮
                    // 如果是手机格式则可以点击
                    mIbClearPhone.setVisibility(View.VISIBLE);
                    if (s.length() == phoneLength) {
                        setProveClickedState(STATE_CLICK);
                        setLoginClickedState(STATE_CLICK);
                    } else {

                        setLoginClickedState(STATE_UNCLICK);
                        setProveClickedState(STATE_UNCLICK);
                    }
                } else {
                    // 如果用户名有内容时候 显示删除按钮
                    mIbClearPhone.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mEdProv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    // 如果用户名有内容时候 显示删除按钮
                    if (s.length() == 0) {
                        setLoginClickedState(STATE_UNCLICK);
                    } else {
                        setLoginClickedState(STATE_CLICK);
                    }
                    mIbClearProv.setVisibility(View.VISIBLE);
                } else {
                    // 如果用户名有内容时候 显示删除按钮
                    mIbClearProv.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mBtProve.setOnClickListener(this);
        mBtLogin.setOnClickListener(this);
        mIbClearProv.setOnClickListener(this);
        mIbClearPhone.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.bt_prove) {
            String phone = mEdPhone.getText().toString();
            sendProveMsg(phone);
        } else if (i == R.id.btn_sign_in) {
            checkMsg();
        } else if (i == R.id.delete_phone) {
            //清空文字，设置图标不可以见，设置登入不可点击,设置BUTTON颜色
            mEdPhone.setText("");
            mIbClearPhone.setVisibility(View.INVISIBLE);
            setLoginClickedState(STATE_UNCLICK);
            if (!isProve) {
                setProveClickedState(STATE_UNCLICK);
            }
        } else if (i == R.id.delete_prove) {
            //清空验证码，设置图标不可见，设置登入按钮不可点击
            mEdProv.setText("");
            mIbClearProv.setVisibility(View.INVISIBLE);
            setLoginClickedState(STATE_UNCLICK);
        }
    }

    /**
     * 检查并登录
     */
    private void checkMsg() {
        signInPresenter.submit(mEdPhone.getText().toString(),mEdProv.getText().toString());
    }

    private int totalTime;

    private void sendProveMsg(String phone) {
        LoggerUtil.e(TAG, "sendProveMsg" + "===" + isProve);
        if (!isProve) {
            totalTime = 10;
            if (scheduledExecutorService.isShutdown()) {
                scheduledExecutorService = Executors.newScheduledThreadPool(2);
            }
            //设置当前状态为
            isProve = true;
            signInPresenter.sendCode(mEdPhone.getText().toString());
            //检查是否可以发送信息
            scheduledExecutorService.scheduleAtFixedRate(() -> {
                LoggerUtil.e(TAG, "run");

                totalTime--;
                Latte.getHandler().post(() -> mBtProve.setText(String.valueOf(totalTime + "s后重新获取")));

                if (totalTime == 0) {
                    isProve = false;
                    scheduledExecutorService.shutdownNow();
                    Latte.getHandler().post(() -> mBtProve.setText(String.valueOf("获取验证码")));
                }

            }, 0, 1000, TimeUnit.MILLISECONDS);
        }

    }

    @Override
    public void phoneError() {

        Toast.makeText(getContext(),"手机格式不正确", Toast.LENGTH_LONG).show();
    }

    @Override
    public void proveError() {

        Latte.getHandler().post(() -> Toast.makeText(getContext(),"验证失败，", Toast.LENGTH_LONG).show());
    }

    @Override
    public void success() {
        AccountManager.setSignState(true);
        getSupportDelegate().startWithPop(new EcBottomDelegate());
    }

    @Override
    public void fail() {

    }
}
