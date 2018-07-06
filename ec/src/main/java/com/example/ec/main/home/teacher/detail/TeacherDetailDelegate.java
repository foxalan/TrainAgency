package com.example.ec.main.home.teacher.detail;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
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
import com.example.ec.main.home.organization.OrganizationAdapter;
import com.example.ec.main.home.organization.OrganizationDataConverter;
import com.example.ec.main.home.organization.OrganizationDelegate;
import com.example.ui.recycler.MultipleItemEntity;

import java.util.List;

/**
 * @author alan
 *         Date  2018/7/5.
 *         Function : 老师详情页
 *         Issue :
 */

public class TeacherDetailDelegate extends LatteDelegate implements ISuccess {

    private RecyclerView mRycTeacherDetail;

    @Override
    public Object setLayout() {
        return R.layout.delegate_home_teacher_detail;
    }

    private int mSubId = -1;
    private String mSubType = "";

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        mRycTeacherDetail = rootView.findViewById(R.id.ryc_teacher_detail);
        initData();

    }

    private final static String TAG = "SubjectListDelegate";
    private static final String ARG_TEACHER_ID = "ARG_CLASS_ID";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        if (args != null) {
            mSubId = args.getInt(ARG_TEACHER_ID);

        }
    }

    public static TeacherDetailDelegate create(int subId) {

        final Bundle args = new Bundle();
        args.putInt(ARG_TEACHER_ID, subId);
        final TeacherDetailDelegate delegate = new TeacherDetailDelegate();
        delegate.setArguments(args);
        return delegate;

    }

    /**
     * http://192.168.1.186/Select/SingleTeacher
     * teacherid:老师ID
     * userid:用户ID
     * 详细老师信息
     * http://192.168.1.186/Select/SingleTeacher
     * longitude:经度
     * latitude:纬度
     */

    double x = 0.00;
    double y = 0.00;

    private void initData() {

        BDLocation bdLocation = CurrentLocation.getInstance().getBdLocation();
        if (bdLocation != null) {
            x = bdLocation.getLongitude();
            y = bdLocation.getLatitude();
        }

        String userId = AccountManager.getUserId();

        RestClient.builder()
                .loader(getContext())
                .url("SingleTeacher")
                .params("teacherid", mSubId)
                .params("userid", userId)
                .params("Longitude", x)
                .params("Latitude", y)
                .success(this)
                .build()
                .get();
    }

    @Override
    public void onSuccess(String response) {
        Log.e("teacher", response + "++++++++++");
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRycTeacherDetail.setLayoutManager(manager);
        final List<MultipleItemEntity> data =
                new TeacherDataConverter().setJsonData(response).convert();
        final TeacherDetailAdapter teacherDetailAdapter = new TeacherDetailAdapter(data);
        teacherDetailAdapter.setDetailListener((type, phone, state, view) -> {

            switch (type) {
                case Config.DETAIL_TEACHER_PHONE:

                    String mobile = phone;
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    Uri uri= Uri.parse("tel:" + mobile);
                    intent.setData(uri);
                    startActivity(intent);

                    break;
                case Config.DETAIL_TEACHER_STATE:
                    AppCompatTextView textView = (AppCompatTextView) view;
                    String text = textView.getText().toString();
                    Log.e("notice", "text:" + text);
                    if ("关注".equals(text)) {
                        addNotice(textView);
                    } else {
                        cancelNotice(textView);
                    }
                    break;
                default:
                    break;

            }
        });
        mRycTeacherDetail.setAdapter(teacherDetailAdapter);
    }


    /**
     * 取消关注
     * http://192.168.1.186Remove/dellike?userid=4&classid=1
     * userid:用户ID
     * classid:培训班ID
     *
     * @param textView
     */
    private void cancelNotice(AppCompatTextView textView) {
        RestClient.builder()
                .loader(getContext())
                .url("http://192.168.1.186/Remove/dellike")
                .params("userid", getUserId())
                .params("teacherid", mSubId)
                .success(response -> {
                    Log.e("notice","remove:"+response);
                    final JSONObject object = JSON.parseObject(response);
                    String msg = object.getString("msg");
                    Latte.getHandler().post(() -> {
                        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
                        textView.setText("关注");
                    });
                })
                .build()
                .post();
    }

    /**
     * 添加关注
     * http://192.168.1.186/Increase/Addlike
     * userid:用户ID
     * teacherid:老师ID
     *
     * @param textView
     */
    private void addNotice(AppCompatTextView textView) {

        Log.e("notice", getUserId());
        RestClient.builder()
                .loader(getContext())
                .url("http://192.168.1.186/Increase/Addlike")
                .params("userid", getUserId())
                .params("teacherid", mSubId)
                .success(response -> {
                    final JSONObject object = JSON.parseObject(response);
                    String msg = object.getString("msg");
                    Latte.getHandler().post(() -> {
                        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
                        textView.setText("取消关注");
                    });
                })
                .build()
                .post();
    }

    private String getUserId() {
        return AccountManager.getUserId();
    }
}
