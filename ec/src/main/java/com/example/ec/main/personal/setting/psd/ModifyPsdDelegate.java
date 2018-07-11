package com.example.ec.main.personal.setting.psd;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.core.app.Latte;
import com.example.core.delegate.LatteDelegate;
import com.example.ec.R;
import com.joanzapata.iconify.widget.IconTextView;

import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * @author alan
 *         Date  2018/7/9.
 *         Function :修改密码
 *         Issue :
 */

public class ModifyPsdDelegate extends LatteDelegate implements View.OnClickListener{

    private AppCompatEditText mEtCurrentPsd;
    private AppCompatEditText mEtNewPsd;
    private AppCompatEditText mEtNewPsdAgain;
    private AppCompatButton mBtModify;
    private IconTextView mIctBack;

    private String mCurrentPsd;
    private String mNewPsd;
    private String mNewPsdAgain;

    @Override
    public Object setLayout() {
        return R.layout.delegate_personal_setting_password_modify;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
            mEtCurrentPsd = rootView.findViewById(R.id.et_current_psd);
            mEtNewPsd = rootView.findViewById(R.id.et_new_psd);
            mEtNewPsdAgain = rootView.findViewById(R.id.et_new_psd_again);
            mBtModify = rootView.findViewById(R.id.bt_modify);
            mIctBack = rootView.findViewById(R.id.ict_back);

            mBtModify.setOnClickListener(this);
            mIctBack.setOnClickListener(v -> getSupportDelegate().pop());
    }

    @Override
    public void onClick(View v) {

        mCurrentPsd = mEtCurrentPsd.getText().toString();
        mNewPsd = mEtNewPsd.getText().toString();
        mNewPsdAgain = mEtNewPsdAgain.getText().toString();

        if ("".equals(mCurrentPsd)||"".equals(mNewPsd)||"".equals(mNewPsdAgain)){
            Latte.getHandler().post(() ->
                    Toast.makeText(getContext(),"信息不完整",Toast.LENGTH_SHORT).show());
            return;
        }

        if (!mNewPsd.equals(mNewPsdAgain)){
            Latte.getHandler().post(() ->
                    Toast.makeText(getContext(),"两次输入的密码不一样",Toast.LENGTH_SHORT).show());
            return;
        }


        //todo

    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }
}
