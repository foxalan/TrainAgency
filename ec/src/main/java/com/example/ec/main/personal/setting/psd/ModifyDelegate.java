package com.example.ec.main.personal.setting.psd;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.core.delegate.LatteDelegate;
import com.example.ec.R;

/**
 * @author alan
 *         Date  2018/7/9.
 *         Function :修改密码
 *         Issue :
 */

public class ModifyDelegate extends LatteDelegate{

    @Override
    public Object setLayout() {
        return R.layout.delegate_personal_setting_password_modify;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

    }
}
