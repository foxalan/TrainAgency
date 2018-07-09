package com.example.ec.main.personal.help;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.core.delegate.LatteDelegate;
import com.example.ec.R;

/**
 * @author alan
 *         Date  2018/7/9.
 *         Function : 问题帮助
 *         Issue :
 */

public class HelpDelegate extends LatteDelegate {

    @Override
    public Object setLayout() {
        return R.layout.delegate_personal_help;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

    }
}
