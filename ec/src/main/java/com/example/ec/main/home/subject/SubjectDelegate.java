package com.example.ec.main.home.subject;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.core.delegate.LatteDelegate;
import com.example.core.net.RestClient;
import com.example.core.net.callback.ISuccess;
import com.example.ec.R;
import com.example.ui.recycler.MultipleItemEntity;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.List;

/**
 * @author alan
 *         Date  2018/6/22.
 *         Function : 学科
 *         Issue :
 */

public class SubjectDelegate extends LatteDelegate implements ISuccess{

    private static final String ARG_SUB_ID = "ARG_SUB_ID";
    private static final String ARG_SUB_TYPE = "ARG_SUB_TYPE";
    private int mSubId = -1;
    private String mSubType = "";

    private RecyclerView mRycSub;
    private AppCompatTextView mTvSubType;
    private IconTextView icBack;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        if (args != null) {
            mSubId = args.getInt(ARG_SUB_ID);
            mSubType = args.getString(ARG_SUB_TYPE);
        }
    }

    public static SubjectDelegate create(int  subId,String subType){

        final Bundle args = new Bundle();
        args.putInt(ARG_SUB_ID, subId);
        args.putString(ARG_SUB_TYPE,subType);
        final SubjectDelegate delegate = new SubjectDelegate();
        delegate.setArguments(args);
        return delegate;
    }



    @Override
    public Object setLayout() {
        return R.layout.delegate_home_subject;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

        mRycSub = rootView.findViewById(R.id.ryc_subject_list);
        mTvSubType = rootView.findViewById(R.id.tv_subject_type);
        icBack = rootView.findViewById(R.id.iv_back);

        icBack.setOnClickListener(v -> getSupportDelegate().pop());

        if (mSubType!=null&&!"".equals(mSubType)){
            mTvSubType.setText(mSubType);
        }

        initData();
    }

    private void initData() {
        RestClient.builder()
                .loader(getContext())
                .url("SubjectLsit?typeid="+mSubId)
                .success(this)
                .build()
                .get();
    }

    @Override
    public void onSuccess(String response) {

        Log.e("subjectalan",response);

        final GridLayoutManager manager = new GridLayoutManager(getContext(), 3);
        mRycSub.setLayoutManager(manager);
        final List<MultipleItemEntity> data =
                new SubjectDataConverter().setJsonData(response).convert();
        final SubjectAdapter addressAdapter = new SubjectAdapter(data);
        mRycSub.setAdapter(addressAdapter);
        mRycSub.addOnItemTouchListener(new SubjectItemClickListener(this));
    }
}
