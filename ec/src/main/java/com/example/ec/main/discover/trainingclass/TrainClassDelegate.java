package com.example.ec.main.discover.trainingclass;

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
 *         Date  2018/6/23.
 *         Function : 培训班
 *         Issue :
 */

public class TrainClassDelegate extends LatteDelegate{
    @Override
    public Object setLayout() {
        return R.layout.delegate_discover_train_class;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }
}
