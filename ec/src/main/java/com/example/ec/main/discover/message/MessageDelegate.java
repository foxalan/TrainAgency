package com.example.ec.main.discover.message;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.baidu.location.BDLocation;
import com.example.core.delegate.LatteDelegate;
import com.example.ec.R;
import com.example.ec.main.home.location.CurrentLocation;

/**
 * @author alan
 *         Date  2018/7/3.
 *         Function :
 *         Issue :
 */

public class MessageDelegate extends LatteDelegate {

    private RecyclerView mRycMessage;

    @Override
    public Object setLayout() {
        return R.layout.delegate_discover_message;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        mRycMessage = rootView.findViewById(R.id.ryc_message_list);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);

        initRecyclerView();
        //mRefreshHandler.firstPage("Classinfo");
    }

    private void initRecyclerView() {
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRycMessage.setLayoutManager(manager);
    }
}
