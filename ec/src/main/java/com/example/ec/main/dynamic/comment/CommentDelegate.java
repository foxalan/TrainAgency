package com.example.ec.main.dynamic.comment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.core.delegate.LatteDelegate;
import com.example.ec.R;

/**
 * @author alan
 *         Date  2018/6/23.
 *         Function : 评论
 *         Issue :
 */

public class CommentDelegate extends LatteDelegate {

    @Override
    public Object setLayout() {
        return R.layout.delegate_dynamic_comment;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

    }
}
