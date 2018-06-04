package com.example.alan.trainagency;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.core.delegate.LatteDelegate;

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

    }
}
