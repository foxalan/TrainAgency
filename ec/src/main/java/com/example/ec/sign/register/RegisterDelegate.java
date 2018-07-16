package com.example.ec.sign.register;

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
import com.example.ec.sign.SignInPsdDelegate;

/**
 * @author alan
 *         Date  2018/7/16.
 *         Function : 注册页面
 *         Issue :
 */

public class RegisterDelegate extends LatteDelegate {

    private EditText mEdPhone;
    private EditText mEdPsd;
    private EditText mEdPsdAgain;
    private Button mBtRegister;


    @Override
    public Object setLayout() {
        return R.layout.delegate_register;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        mEdPhone = rootView.findViewById(R.id.et_phone_register);
        mEdPsd = rootView.findViewById(R.id.et_password_register);
        mEdPsdAgain = rootView.findViewById(R.id.et_password_register_again);

        mBtRegister = rootView.findViewById(R.id.btn_register);

        mBtRegister.setOnClickListener(v -> {
            String phone = mEdPhone.getText().toString();
            String password = mEdPsd.getText().toString();
            String passwordAgain = mEdPsdAgain.getText().toString();

            if (check(phone,password,passwordAgain)){
                register(phone,password);
            }
        });
    }

    /**
     *
     * http://192.168.1.186/Increase/AddUser?username=18666666666&password=0.0
     * 注册
     * @param phone
     * @param password
     */
    private void register(String phone, String password) {

        RestClient.builder()
                .url("http://192.168.1.186/Increase/AddUser")
                .params("username",phone)
                .params("password",password)
                .loader(getContext())
                .success(response -> {
                    /**
                     * 登入成功
                     */
                    final JSONObject object = JSON.parseObject(response);
                    Log.e("login","login:"+response.toString());
                    String msg = object.getString("msg");
                    Toast.makeText(getContext(),msg,Toast.LENGTH_SHORT).show();

                    if ("注册成功！".equals(msg)){
                        getSupportDelegate().start(new SignInPsdDelegate(),SINGLETASK);
                    }

                })
                .build()
                .post();

    }

    /**
     * 检查信息
     * @param phone
     * @param password
     * @param passwordAgain
     * @return
     */
    private boolean check(String phone, String password, String passwordAgain) {
        if ("".equals(phone)){
            return false;
        }

        if ("".equals(password)||"".equals(passwordAgain)){
            return false;
        }

        if (!password.equals(passwordAgain)){
            return false;
        }

        return true;
    }
}
