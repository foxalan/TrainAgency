package com.example.ec.main.home.subject.list;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.GridLayoutManager;
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
import com.example.ec.main.home.subject.SubjectAdapter;
import com.example.ec.main.home.subject.SubjectDataConverter;
import com.example.ec.main.home.subject.SubjectDelegate;
import com.example.ec.main.home.subject.SubjectItemClickListener;
import com.example.ui.recycler.MultipleItemEntity;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.List;

/**
 *          @author alan
 *         Date  2018/6/22.
 *         Function : 具体科目的培训班信息
 *         Issue :
 */

public class SubjectListDelegate extends LatteDelegate implements ISuccess{

    private final static String TAG = "SubjectListDelegate";
    private static final String ARG_SUB_ID = "ARG_SUB_ID";
    private static final String ARG_SUB_NAME = "ARG_SUB_NAME";

    private int mSubId = -1;
    private String mSubName = "";

    private RecyclerView mRycSubName;
    private AppCompatTextView mTvSubName;
    private IconTextView icBack;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        if (args != null) {
            mSubId = args.getInt(ARG_SUB_ID);
            mSubName = args.getString(ARG_SUB_NAME);
        }
    }

    public static SubjectListDelegate create(int  subId, String subName){

        final Bundle args = new Bundle();
        args.putInt(ARG_SUB_ID, subId);
        args.putString(ARG_SUB_NAME,subName);
        final SubjectListDelegate delegate = new SubjectListDelegate();
        delegate.setArguments(args);
        return delegate;
    }


    @Override
    public Object setLayout() {
        return R.layout.delegate_home_subject_name;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

        mRycSubName = rootView.findViewById(R.id.ryc_subject_name);
        mTvSubName = rootView.findViewById(R.id.tv_subject_name);
        icBack = rootView.findViewById(R.id.iv_back);

        mTvSubName.setText(mSubName);
        icBack.setOnClickListener(v -> getSupportDelegate().pop());
        initData();
    }

    double x = 0.00;
    double y = 0.00;

    private void initData() {

        BDLocation bdLocation = CurrentLocation.getInstance().getBdLocation();
        if(bdLocation!=null){
            x = bdLocation.getLongitude();
            y = bdLocation.getLatitude();
        }

        Log.e(TAG,"mSubid"+mSubName+mSubId);
        RestClient.builder()
                .loader(getContext())
                .url("Classinfo?Subjectid="+mSubId)
                .params("Longitude",x)
                .params("Latitude",y)
                .success(this)
                .build()
                .get();
    }

    @Override
    public void onSuccess(String response) {
//        Log.e(TAG,"mSubid"+response);
//        Log.e("subjectname","response:"+response);
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRycSubName.setLayoutManager(manager);
        final List<MultipleItemEntity> data =
                new SubjectNameDataConverter().setJsonData(response).convert();
        final SubjectListAdapter addressAdapter = new SubjectListAdapter(data);
        mRycSubName.setAdapter(addressAdapter);
        mRycSubName.addOnItemTouchListener(new SubjectClickListener(this));
    }
}
