package com.example.ec.sign;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.example.core.app.AccountManager;
import com.example.core.delegate.LatteDelegate;
import com.example.core.net.RestClient;
import com.example.ec.R;
import com.example.ec.main.EcBottomDelegate;

/**
 * @author alan
 *         Date  2018/7/5.
 *         Function : 密码登录
 *         Issue :
 */

public class SignInPsdDelegate extends LatteDelegate{

    private EditText mEtPhone;
    private EditText mEtPassword;
    private Button mBtSign;

    private String phone;
    private String password;

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in_psd;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        //et_phone_number
        //et_password
        //btn_sign_in_psd

        mEtPhone = rootView.findViewById(R.id.et_phone_number);
        mEtPassword = rootView.findViewById(R.id.et_password);
        mBtSign = rootView.findViewById(R.id.btn_sign_in_psd);

        mBtSign.setOnClickListener(v -> {
            phone = mEtPhone.getText().toString();
            password = mEtPassword.getText().toString();

            if ("".equals(phone)||password.equals("")){
                Toast.makeText(getContext(),"用户名或密码为空",Toast.LENGTH_SHORT).show();
                return;
            }

            if (phone.length()!=11){
                Toast.makeText(getContext(),"手机号不对",Toast.LENGTH_SHORT).show();
                return;
            }

            signIn(phone,password);
        });

    }

    /**
     * 4.登录http://192.168.1.186/Select/login
            username:手机号
            Password:密码
     * @param phone
     * @param password
     */
    private void signIn(String phone, String password) {
        RestClient.builder()
                .url("login")
                .params("username",phone)
                .params("Password",password)
                .loader(getContext())
                .success(response -> {
                    /**
                     * 登入成功
                     */
                    final JSONObject object = JSON.parseObject(response);
                    Log.e("login","login:"+response.toString());
                    String msg = object.getString("msg");
                    if (msg.equals("OK")){
                        JSONArray array = object.getJSONArray("userinfo");
                        JSONObject user = array.getJSONObject(0);
                        int id = user.getInteger("ID");
                        Log.e("login","login:ID"+id);

                        AccountManager.setSignState(true);
                        AccountManager.setUserId(String.valueOf(id));

                        getSupportDelegate().startWithPop(new EcBottomDelegate());

                    }else {
                        Toast.makeText(getContext(),"用户名或密码错误",Toast.LENGTH_SHORT).show();
                    }

                })
                .build()
                .post();
    }
}
