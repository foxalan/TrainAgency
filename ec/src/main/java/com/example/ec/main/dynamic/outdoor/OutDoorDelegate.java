package com.example.ec.main.dynamic.outdoor;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.core.delegate.LatteDelegate;
import com.example.ec.R;

/**
 * @author alan
 *         Date  2018/6/23.
 *         Function : 户外活动
 *         Issue :
 */

public class OutDoorDelegate extends LatteDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_dynamic_outdoor;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

    }
}
