package com.example.ec.main.home.search;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.core.delegate.LatteDelegate;
import com.example.ec.R;
import com.example.ui.widget.FlowLayout;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author alan
 *         Date  2018/6/9.
 *         Function :
 *         Issue :
 */

public class SearchDelegate extends LatteDelegate implements View.OnClickListener{

    private IconTextView mIconBack;
    private AppCompatEditText mEtSearch;
    private AppCompatTextView mTvSearch;

    private LinearLayout mLinerLayoutSearchHistory;
    private AppCompatTextView mTvDelHistory;

    private FlowLayout mFlHistory;
    private FlowLayout mFlHot;

    private List<String> dataList = new ArrayList<>();

    @Override
    public Object setLayout() {
        return R.layout.delegate_home_search;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

        initViews(rootView);

        //得到Data
        initData();
        //添充到FlowLayout中
        setFlowLayout();
        //添加点击事件
        initEvents();
    }

    private void initViews(View rootView) {
        mIconBack = rootView.findViewById(R.id.icon_back);
        mEtSearch = rootView.findViewById(R.id.et_search_view);
        mTvSearch = rootView.findViewById(R.id.tv_search);

        mLinerLayoutSearchHistory = rootView.findViewById(R.id.ll_search_history);
        mTvDelHistory = rootView.findViewById(R.id.tv_delete_search_history);

        mFlHistory = rootView.findViewById(R.id.fl_search_history);
        mFlHot = rootView.findViewById(R.id.fl_search_hot);
    }

    private void initData() {
        dataList = new SearchDataConverter().convert();
    }

    private void setFlowLayout() {

        for(String value:dataList){
            TextView label = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.search_label_tv,mFlHistory,false);
            label.setText(value);
            label.setOnClickListener(v -> {
                Toast.makeText(getContext(),value,Toast.LENGTH_SHORT).show();
            });
            mFlHistory.addView(label);
        }

        for(String value:dataList){
            TextView label = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.search_label_tv,mFlHot,false);
            label.setText(value);
            label.setOnClickListener(v -> {
                Toast.makeText(getContext(),value,Toast.LENGTH_SHORT).show();
            });
            mFlHot.addView(label);
        }
    }

    private void initEvents() {
        mIconBack.setOnClickListener(this);
        mTvSearch.setOnClickListener(this);
        mTvDelHistory.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.icon_back) {
            getSupportDelegate().pop();
        } else if (i == R.id.tv_delete_search_history) {
            mLinerLayoutSearchHistory.setVisibility(View.GONE);
            mFlHistory.removeAllViews();
            mFlHistory.setVisibility(View.GONE);
        } else if (i == R.id.tv_search) {

        } else {

        }
    }
}
