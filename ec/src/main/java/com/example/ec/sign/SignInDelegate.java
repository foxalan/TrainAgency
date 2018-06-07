package com.example.ec.sign;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.core.delegate.LatteDelegate;
import com.example.ec.R;
import com.example.ec.main.EcBottomDelegate;

/**
 * @author alan
 *         Date  2018/6/5.
 *         Function : 登入界面
 *         Issue :
 */

public class SignInDelegate extends LatteDelegate {

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

    }
}
