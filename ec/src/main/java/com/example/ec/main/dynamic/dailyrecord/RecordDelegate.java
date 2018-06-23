package com.example.ec.main.dynamic.dailyrecord;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.core.delegate.LatteDelegate;
import com.example.ec.R;

import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;


/**
 * @author alan
 *         Date  2018/6/22.
 *         Function : 发表日志页面
 *         Issue :
 */

public class RecordDelegate extends LatteDelegate {

    @Override
    public Object setLayout() {

        return R.layout.delegate_dynamic_record;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }
}
