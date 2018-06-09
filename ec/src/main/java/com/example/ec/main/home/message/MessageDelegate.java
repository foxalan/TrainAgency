package com.example.ec.main.home.message;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.core.delegate.LatteDelegate;
import com.example.ec.R;

/**
 * @author alan
 *         Date  2018/6/9.
 *         Function :
 *         Issue :
 */

public class MessageDelegate extends LatteDelegate {

    @Override
    public Object setLayout() {
        return R.layout.delegate_home_message;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

    }
}
