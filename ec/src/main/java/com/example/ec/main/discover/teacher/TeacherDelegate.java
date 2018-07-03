package com.example.ec.main.discover.teacher;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.core.delegate.LatteDelegate;
import com.example.core.net.RestClient;
import com.example.core.net.callback.ISuccess;
import com.example.ec.R;
import com.example.ui.recycler.MultipleItemEntity;

import java.util.List;

/**
 * @author alan
 *         Date  2018/7/2.
 *         Function : 找老师界面
 *         Issue :
 */

public class TeacherDelegate extends LatteDelegate implements ISuccess{

    private RecyclerView mRycTeacher;

    @Override
    public Object setLayout() {
        return R.layout.delegate_discover_teacher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        mRycTeacher = rootView.findViewById(R.id.ryc_teacher_list);
        initData();

    }
    /**
     * http://192.168.1.186/Select/TeacherList?name=武昌区&pageindex=1&pagesize=1
     */
    private void initData() {

        RestClient.builder()
                .loader(getContext())
                .url("TeacherList")
                .params("name","武昌区")
                .params("pageindex",1)
                .params("pagesize",1)
                .success(this)
                .build()
                .get();
    }

    @Override
    public void onSuccess(String response) {
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRycTeacher.setLayoutManager(manager);
        final List<MultipleItemEntity> data =
                new TeacherDataConverter().setJsonData(response).convert();
        final TeacherAdapter teacherAdapter = new TeacherAdapter(data);
        mRycTeacher.setAdapter(teacherAdapter);

//        teacherAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
//            @Override
//            public void onLoadMoreRequested() {
//
//            }
//        },mRycTeacher);

    }
}
