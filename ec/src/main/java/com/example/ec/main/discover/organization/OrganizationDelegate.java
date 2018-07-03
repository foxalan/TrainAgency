package com.example.ec.main.discover.organization;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.baidu.location.BDLocation;
import com.example.core.delegate.LatteDelegate;
import com.example.ec.R;
import com.example.ec.main.discover.DiscoverOrganizationRefreshHandler;
import com.example.ec.main.home.location.CurrentLocation;
import com.example.ec.main.home.subject.list.SubjectNameDataConverter;

/**
 * @author alan
 *         Date  2018/7/3.
 *         Function : 所有的机构
 *         Issue :
 */

public class OrganizationDelegate extends LatteDelegate{

    private RecyclerView mRycOrganization;

    private DiscoverOrganizationRefreshHandler mRefreshHandler = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_discover_organization;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        mRycOrganization = rootView.findViewById(R.id.ryc_organization_list);

        mRefreshHandler = DiscoverOrganizationRefreshHandler.create(null, mRycOrganization, new SubjectNameDataConverter());
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
    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);

        BDLocation bdLocation = CurrentLocation.getInstance().getBdLocation();
        if(bdLocation!=null){
            x = bdLocation.getLongitude();
            y = bdLocation.getLatitude();
        }

        initRecyclerView();
        mRefreshHandler.firstPage("Classinfo");
    }


    private void initRecyclerView() {
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRycOrganization.setLayoutManager(manager);
    }
}
