package com.example.ec.main.personal.userinfo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.core.app.AccountManager;
import com.example.core.app.Latte;
import com.example.core.delegate.LatteDelegate;
import com.example.core.net.RestClient;
import com.example.core.net.callback.ISuccess;
import com.example.ec.R;
import com.example.ec.main.personal.IRrefreshView;

/**
 * @author alan
 *         Date  2018/7/16.
 *         Function : 用户设置界面
 *         Issue :
 */

@SuppressLint("ValidFragment")
public class UserInfoAccountDelegate extends LatteDelegate {

    AppCompatEditText mEdAccount;
    AppCompatButton mBtSubmit;

    private IRrefreshView refreshView;

    public UserInfoAccountDelegate(){}

    @Override
    public Object setLayout() {
        return R.layout.delegate_name;
    }

    /**
     * http://192.168.1.186/Update/updateuserinfo?ID=1&改什么就传什么 改图片传文件和ID
     * @param savedInstanceState
     * @param rootView
     */
    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        mEdAccount = rootView.findViewById(R.id.et_account);
        mBtSubmit = rootView.findViewById(R.id.btn_name_submit);

        mBtSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = mEdAccount.getText().toString();
                if ("".equals(account)){
                    Toast.makeText(getContext(),"用户名不能为空",Toast.LENGTH_LONG).show();
                    return;
                }


                RestClient.builder()
                        .url("http://192.168.1.186/Update/updateuserinfo")
                        .params("ID", AccountManager.getUserId())
                        .params("account",account)
                        .success(new ISuccess() {
                            @Override
                            public void onSuccess(String response) {
                                final JSONObject object = JSON.parseObject(response);
                                String msg = object.getString("msg");
                                Latte.getHandler().post(() -> Toast.makeText(getContext(),msg,Toast.LENGTH_LONG).show()) ;
                                Log.e("alan","msg"+msg);
                                if ("更改名称成功！".equals(msg)){
                                    AccountManager.setUserType(account);
                                    UserInfoDelegate userInfoDelegate = new UserInfoDelegate();
                                    Bundle bundle = new Bundle();
                                    bundle.putString("update","account");
                                    userInfoDelegate.setArguments(bundle);
                                    getSupportDelegate().start(userInfoDelegate,SINGLETASK);
                                }

                            }
                        })
                        .build()
                        .post();
            }
        });
    }
}
