package com.example.core;

import android.support.v7.app.AppCompatActivity;

import me.yokeyword.fragmentation.ExtraTransaction;
import me.yokeyword.fragmentation.ISupportActivity;
import me.yokeyword.fragmentation.SupportActivityDelegate;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * @author alan
 *         Date  2018/6/4.
 *         Function :
 *         Issue :
 */

public abstract class BaseActivity extends AppCompatActivity implements ISupportActivity {


    @Override
    public SupportActivityDelegate getSupportDelegate() {
        return null;
    }

    @Override
    public ExtraTransaction extraTransaction() {
        return null;
    }

    @Override
    public FragmentAnimator getFragmentAnimator() {
        return null;
    }

    @Override
    public void setFragmentAnimator(FragmentAnimator fragmentAnimator) {

    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return null;
    }

    @Override
    public void post(Runnable runnable) {

    }

    @Override
    public void onBackPressedSupport() {

    }
}
