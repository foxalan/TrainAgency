package com.example.ec.main.home.organization;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.baidu.location.BDLocation;
import com.example.core.delegate.LatteDelegate;
import com.example.core.net.RestClient;
import com.example.core.net.callback.ISuccess;
import com.example.ec.R;
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

public class OrganizationDelegate extends LatteDelegate implements ISuccess{

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
        if(bdLocation!=null){
            x = bdLocation.getLongitude();
            y = bdLocation.getLatitude();
        }

        Log.e(TAG,"mSubid"+mSubName+mClassId);
        RestClient.builder()
                .loader(getContext())
                .url("SingleClass?classid"+mClassId)
                .params("Longitude",x)
                .params("Latitude",y)
                .success(this)
                .build()
                .get();
    }

    @Override
    public void onSuccess(String response) {
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        final List<MultipleItemEntity> data =
                new OrganizationDataConverter().setJsonData(response).convert();
        final OrganizationAdapter addressAdapter = new OrganizationAdapter(data);
        mRecyclerView.setAdapter(addressAdapter);
    }
}
