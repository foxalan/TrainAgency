package com.example.ec.main.home.subject.good;

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
import com.example.ec.main.home.subject.list.SubjectClickListener;
import com.example.ec.main.home.subject.list.SubjectListAdapter;
import com.example.ec.main.home.subject.list.SubjectNameDataConverter;
import com.example.ui.recycler.MultipleItemEntity;

import java.util.List;

/**
 * @author alan
 *         Date  2018/7/6.
 *         Function : 精品课程
 *         Issue :
 */

public class GreatSubjectDelegate extends LatteDelegate implements ISuccess{

    private RecyclerView mRycGreatSub;

    @Override
    public Object setLayout() {
        return R.layout.delegate_home_subject_great;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        mRycGreatSub = rootView.findViewById(R.id.ryc_great_subject_list);

        initData();
    }

    /**
     * http://192.168.1.186/Select/RecommendClassList
     */

    double x = 0.00;
    double y = 0.00;
    private void initData(){
        BDLocation bdLocation = CurrentLocation.getInstance().getBdLocation();
        if(bdLocation!=null){
            x = bdLocation.getLongitude();
            y = bdLocation.getLatitude();
        }

        RestClient.builder()
                .loader(getContext())
                .url("RecommendClassList")
                .params("Longitude",x)
                .params("Latitude",y)
                .success(this)
                .build()
                .get();
    }

    @Override
    public void onSuccess(String response) {

        Log.e("subjectname","response:"+response);
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRycGreatSub.setLayoutManager(manager);
        final List<MultipleItemEntity> data =
                new GreatSujectDataConverter().setJsonData(response).convert();
        final SubjectListAdapter addressAdapter = new SubjectListAdapter(data);
        mRycGreatSub.setAdapter(addressAdapter);
    //    mRycGreatSub.addOnItemTouchListener(new SubjectClickListener(this));
    }
}
