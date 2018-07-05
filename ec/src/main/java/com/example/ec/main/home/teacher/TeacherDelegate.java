package com.example.ec.main.home.teacher;

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
import com.example.ec.main.discover.teacher.TeacherDataConverter;
import com.example.ec.main.home.location.CurrentLocation;
import com.example.ec.main.home.subject.list.SubjectClickListener;
import com.example.ec.main.home.subject.list.SubjectListAdapter;
import com.example.ec.main.home.subject.list.SubjectNameDataConverter;
import com.example.ui.recycler.MultipleItemEntity;

import java.util.List;

/**
 * @author alan
 *         Date  2018/6/22.
 *         Function : 优秀老师
 *         Issue :
 */

public class TeacherDelegate extends LatteDelegate implements ISuccess{

    private RecyclerView mRecyclerView;


    @Override
    public Object setLayout() {
        return R.layout.delegate_home_teacher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        mRecyclerView = rootView.findViewById(R.id.ryc_teacher_list);
        initData();
    }


    double x = 0.00;
    double y = 0.00;

    /**
     * http://192.168.1.186/Select/TeacherList
     longitude:经度
     latitude:纬度
     */

    private void initData() {

        BDLocation bdLocation = CurrentLocation.getInstance().getBdLocation();
        if(bdLocation!=null){
            x = bdLocation.getLongitude();
            y = bdLocation.getLatitude();
        }

        RestClient.builder()
                .loader(getContext())
                .url("TeacherList")
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
                new TeacherDataConverter().setJsonData(response).convert();
        final TeacherAdapter addressAdapter = new TeacherAdapter(data);
        mRecyclerView.setAdapter(addressAdapter);
        mRecyclerView.addOnItemTouchListener(new TeacherItemClickListener(this));
    }
}
