package com.example.ec.main.dynamic;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.core.delegate.bottom.BottomItemDelegate;
import com.example.ec.R;

/**
 * @author alan
 *         Date  2018/6/5.
 *         Function :
 *         Issue :
 */

public class DynamicDelegate extends BottomItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_dynamic;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

    }
}
