package com.example.ec.main.home.experience;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.baidu.location.BDLocation;
import com.example.core.delegate.LatteDelegate;
import com.example.core.util.storage.LattePreference;
import com.example.ec.R;
import com.example.ec.main.discover.DiscoverOrganizationRefreshHandler;
import com.example.ec.main.home.location.CurrentLocation;
import com.example.ec.main.home.subject.list.SubjectNameDataConverter;

/**
 * @author alan
 *         Date  2018/7/5.
 *         Function : 体验课
 *         Issue :
 */

public class ExperienceClassDelegate extends LatteDelegate{

    private RecyclerView mRycExperience;
    private ExperienceRefreshHandler mRefreshHandler = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_home_experience_class;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
            mRycExperience = rootView.findViewById(R.id.ryc_experience_list);
            mRefreshHandler = ExperienceRefreshHandler.create(null, mRycExperience, new ExperienceDataConverter());

    }

    private double x;
    private double y;

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
        mRycExperience.setLayoutManager(manager);
    }
}
