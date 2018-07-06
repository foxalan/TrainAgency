package com.example.ec.main.home.organization;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.test.espresso.core.internal.deps.guava.base.MoreObjects;
import android.support.test.espresso.remote.EspressoRemoteMessage;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baidu.location.BDLocation;
import com.example.core.app.AccountManager;
import com.example.core.app.Latte;
import com.example.core.delegate.LatteDelegate;
import com.example.core.net.RestClient;
import com.example.core.net.callback.ISuccess;
import com.example.ec.R;
import com.example.ec.config.Config;
import com.example.ec.main.home.IDetailListener;
import com.example.ec.main.home.location.CurrentLocation;
import com.example.ec.main.home.subject.list.SubjectNameDataConverter;
import com.example.ui.recycler.MultipleItemEntity;

import java.util.List;

/**
 * @author alan
 *         Date  2018/6/14.
 *         Function : 机构详情页
 *         Issue :
 */

public class OrganizationDelegate extends LatteDelegate implements ISuccess {

    private final static String TAG = "SubjectListDelegate";
    private static final String ARG_CLASS_ID = "ARG_CLASS_ID";
    private static final String ARG_SUB_NAME = "ARG_SUB_NAME";

    private int mClassId = -1;
    private String mSubName = "";

    private RecyclerView mRecyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        if (args != null) {
            mClassId = args.getInt(ARG_CLASS_ID);
            mSubName = args.getString(ARG_SUB_NAME);
        }
    }

    public static OrganizationDelegate create(int subId, String subName) {

        final Bundle args = new Bundle();
        args.putInt(ARG_CLASS_ID, subId);
        args.putString(ARG_SUB_NAME, subName);
        final OrganizationDelegate delegate = new OrganizationDelegate();
        delegate.setArguments(args);
        return delegate;

    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_home_organize;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

        mRecyclerView = rootView.findViewById(R.id.ryc_org);
        initData();

    }

    double x = 0.00;
    double y = 0.00;

    /**
     * http://192.168.1.186/Select/SingleClass?classid=1&longitude=&latitude=
     */
    private void initData() {

        BDLocation bdLocation = CurrentLocation.getInstance().getBdLocation();
        if (bdLocation != null) {
            x = bdLocation.getLongitude();
            y = bdLocation.getLatitude();
        }

        Log.e("organization", mClassId + "++++++++++");

        Log.e(TAG, "mSubid" + mSubName + mClassId);
        RestClient.builder()
                .loader(getContext())
                .url("SingleClass?classid=" + mClassId)
                .params("Longitude", x)
                .params("Latitude", y)
                .params("userid",AccountManager.getUserId())
                .success(this)
                .build()
                .get();
    }

    @Override
    public void onSuccess(String response) {
        Log.e("organization", response + "++++++++++");
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        final List<MultipleItemEntity> data =
                new OrganizationDataConverter().setJsonData(response).convert();
        final OrganizationAdapter organizationAdapter = new OrganizationAdapter(data);
        organizationAdapter.setDetailListener(new IDetailListener() {
            @Override
            public void click(int type, String phone, int state, View view) {
                switch (type) {
                    case Config.DETAIL_ORGANIZATION_PHONE:

                        break;
                    case Config.DETAIL_ORGANIZATION_STATE:
                        AppCompatTextView textView = (AppCompatTextView) view;
                        String text = textView.getText().toString();
                        Log.e("notice","text:"+text);
                        if ("关注".equals(text)){
                            addNotice(textView);
                        }else {
                            cancelNotice(textView);
                        }
                        break;
                    default:
                        break;
                }
            }
        });
        mRecyclerView.setAdapter(organizationAdapter);
    }

    /**
     * 取消关注
     *   http://192.168.1.186Remove/dellike?userid=4&classid=1
        userid:用户ID
        classid:培训班ID
     * @param textView
     */
    private void cancelNotice(AppCompatTextView textView) {
        RestClient.builder()
                .loader(getContext())
                .url("http://192.168.1.186/Remove/dellike")
                .params("userid", getUserId())
                .params("classid", mClassId)
                .success(response -> {
                    final JSONObject object = JSON.parseObject(response);
                    String msg = object.getString("msg");
                    Latte.getHandler().post(() -> {
                        Toast.makeText(getContext(),msg, Toast.LENGTH_SHORT).show();
                        textView.setText("关注");
                    });
                })
                .build()
                .post();
    }

    /**
     * 添加关注
     * http://192.168.1.186/Increase/Addlike?userid=4&classid=1
        userid:用户ID
        classid:培训班ID
     * @param textView
     */
    private void addNotice(AppCompatTextView textView) {

        Log.e("notice",getUserId());
        RestClient.builder()
                .loader(getContext())
                .url("http://192.168.1.186/Increase/Addlike")
                .params("userid", getUserId())
                .params("classid", mClassId)
                .success(response -> {
                    final JSONObject object = JSON.parseObject(response);
                    String msg = object.getString("msg");
                    Latte.getHandler().post(() -> {
                        Toast.makeText(getContext(),msg, Toast.LENGTH_SHORT).show();
                        textView.setText("取消关注");
                    });
                })
                .build()
                .post();
    }

    private String getUserId(){
       return AccountManager.getUserId();
    }
}
