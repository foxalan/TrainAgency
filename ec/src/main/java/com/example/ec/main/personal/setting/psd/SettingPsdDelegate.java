package com.example.ec.main.personal.setting.psd;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.Toast;

import com.example.core.app.Latte;
import com.example.core.delegate.LatteDelegate;
import com.example.ec.R;
import com.example.ui.animator.LatteAnimator;
import com.joanzapata.iconify.widget.IconTextView;

import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * @author alan
 *         Date  2018/7/11.
 *         Function : 设置密码
 *         Issue :
 */

public class SettingPsdDelegate extends LatteDelegate implements View.OnClickListener{

    private AppCompatEditText mEtPsd;
    private AppCompatEditText mEtPsdAgain;
    private AppCompatButton mBtSet;
    private IconTextView mIctBack;

    private String mPsd;
    private String mPsdAgain;

    @Override
    public Object setLayout() {
        return R.layout.delegate_personal_setting_password_set;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

        mEtPsd = rootView.findViewById(R.id.et_psd);
        mEtPsdAgain =rootView.findViewById(R.id.et_psd_again);
        mBtSet = rootView.findViewById(R.id.bt_set);
        mIctBack = rootView.findViewById(R.id.ict_back);

        mIctBack.setOnClickListener(v -> getSupportDelegate().pop());
        mBtSet.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        mPsd = mEtPsd.getText().toString();
        mPsdAgain = mEtPsdAgain.getText().toString();

        if ("".equals(mPsd)||"".equals(mPsdAgain)){
            Latte.getHandler().post(() -> Toast.makeText(getContext(),"信息不完整",Toast.LENGTH_SHORT).show());
            return;
        }

        if (!mPsd.equals(mPsdAgain)){
            Latte.getHandler().post(() -> Toast.makeText(getContext(),"两次密码不一样",Toast.LENGTH_SHORT).show());
            return;
        }
        //todo
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
     //   return new DefaultHorizontalAnimator();
        return new LatteAnimator();
    }
}
