package com.example.ec.main.personal.setting.psd;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.core.app.AccountManager;
import com.example.core.app.Latte;
import com.example.core.delegate.LatteDelegate;
import com.example.core.net.RestClient;
import com.example.core.net.callback.ISuccess;
import com.example.ec.R;
import com.example.ec.sign.SignInPsdDelegate;
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



        updatePassWord(mNewPsd);

        //todo

    }

    /**
     * http://192.168.1.186/Update/updateuserinfo?ID=1&改什么就传什么 改图片传文件和ID
        我:
     account { get; set; }//用户名   password { get; set; }//密码  signature { get; set; }//个性签名
     * @param mNewPsd
     */
    private void updatePassWord(String mNewPsd) {
        RestClient.builder()
                .url("http://192.168.1.186/Update/updateuserinfo")
                .params("ID", AccountManager.getUserId())
                .params("password",mNewPsd)
                .success(response -> {
                    final JSONObject object = JSON.parseObject(response);
                    Log.e("modify","modify:"+response.toString());
                    String msg = object.getString("msg");
                    Toast.makeText(getContext(),msg,Toast.LENGTH_SHORT).show();
                    if ("更改密码成功".equals(msg)){
                        getSupportDelegate().start(new SignInPsdDelegate(),SINGLETASK);
                    }
                })
                .build()
                .post();
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }
}
