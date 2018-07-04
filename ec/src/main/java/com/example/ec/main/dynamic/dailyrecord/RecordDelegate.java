package com.example.ec.main.dynamic.dailyrecord;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.core.delegate.LatteDelegate;
import com.example.core.util.callback.CallbackManager;
import com.example.core.util.callback.CallbackType;
import com.example.core.util.callback.IGlobalCallback;
import com.example.ec.R;
import com.example.ui.widget.AutoPhotoLayout;

import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;


/**
 * @author alan
 *         Date  2018/6/22.
 *         Function : 发表日志页面
 *         Issue :
 */

public class RecordDelegate extends LatteDelegate {

    private AutoPhotoLayout mAutoPhotoLayout = null;

    @Override
    public Object setLayout() {

        return R.layout.delegate_dynamic_record;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        mAutoPhotoLayout = rootView.findViewById(R.id.custom_auto_photo_layout);

        mAutoPhotoLayout.setDelegate(this);
        CallbackManager.getInstance()
                .addCallback(CallbackType.ON_CROP, new IGlobalCallback<Uri>() {
                    @Override
                    public void executeCallback(@Nullable Uri args) {
                        mAutoPhotoLayout.onCropTarget(args);
                    }
                });
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }
}
