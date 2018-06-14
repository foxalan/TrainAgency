package com.example.ec.main.home.address;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.baidu.location.BDLocation;
import com.example.core.app.Latte;
import com.example.core.delegate.LatteDelegate;
import com.example.ec.R;
import com.example.ec.main.home.BaiDuMapLocationClient;
import com.example.ec.main.home.location.ILocationListener;
import com.joanzapata.iconify.widget.IconTextView;

/**
 * @author alan
 *         Date  2018/6/14.
 *         Function :
 *         Issue :
 */

public class AddressDelegate extends LatteDelegate implements ILocationListener{


    private IconTextView mIctBack;
    private RecyclerView mRvAddress;
    private BaiDuMapLocationClient baiDuMapLocationClient;
    private AppCompatTextView mTvCurrentAddress;

    @Override
    public Object setLayout() {
        return R.layout.delegate_home_address;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

        mIctBack = rootView.findViewById(R.id.ict_back);
        mRvAddress = rootView.findViewById(R.id.ryc_address);
        mTvCurrentAddress = rootView.findViewById(R.id.tv_current_address);

        mIctBack.setOnClickListener(v -> getSupportDelegate().pop());

        baiDuMapLocationClient = BaiDuMapLocationClient.create(getContext(),this);
        baiDuMapLocationClient.startRequestLocation();
    }


    @Override
    public void location(BDLocation location) {
        Log.e("address","location:"+location.getAddrStr());
        Latte.getHandler().post(() -> mTvCurrentAddress.setText(location.getAddrStr()));
        baiDuMapLocationClient.stopRequestLocation();
    }
}
