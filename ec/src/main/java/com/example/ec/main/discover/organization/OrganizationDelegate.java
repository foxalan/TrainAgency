package com.example.ec.main.discover.organization;

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
import com.example.core.ui.Lo;
import com.example.ec.R;
import com.example.ec.main.home.location.CurrentLocation;
import com.example.ec.main.home.subject.list.SubjectClickListener;
import com.example.ec.main.home.subject.list.SubjectListAdapter;
import com.example.ec.main.home.subject.list.SubjectNameDataConverter;
import com.example.ui.recycler.MultipleItemEntity;

import java.util.List;

/**
 * @author alan
 *         Date  2018/7/3.
 *         Function : 所有的机构
 *         Issue :
 */

public class OrganizationDelegate extends LatteDelegate implements ISuccess{

    private RecyclerView mRycOrganization;

    @Override
    public Object setLayout() {
        return R.layout.delegate_discover_organization;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        mRycOrganization = rootView.findViewById(R.id.ryc_organization_list);

        initData();
    }

    /**
     *http://192.168.1.186/Select/Classinfo
     * pageindex 1
     * pageSize 5;
     * Longitude：经度
        Latitude:纬度
     */

    double x = 0.00;
    double y = 0.00;

    private void initData() {
        BDLocation bdLocation = CurrentLocation.getInstance().getBdLocation();
        if(bdLocation!=null){
            x = bdLocation.getLongitude();
            y = bdLocation.getLatitude();
        }

        Log.e("organization:","longitude:"+x);
        Log.e("organization","latitude:"+y);

        RestClient.builder()
                .loader(getContext())
                .url("Classinfo")
                .params("Longitude",x)
                .params("Latitude",y)
                .success(this)
                .build()
                .get();
    }

    @Override
    public void onSuccess(String response) {
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRycOrganization.setLayoutManager(manager);
        final List<MultipleItemEntity> data =
                new SubjectNameDataConverter().setJsonData(response).convert();
        final SubjectListAdapter addressAdapter = new SubjectListAdapter(data);
        mRycOrganization.setAdapter(addressAdapter);
     //   mRycOrganization.addOnItemTouchListener(new SubjectClickListener(this));
    }
}
