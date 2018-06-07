package com.example.ec.bluetooth;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.core.bluetooth.AppInfo;
import com.example.core.bluetooth.bt.BluetoothDelegate;
import com.example.core.bluetooth.print.PrintUtil;
import com.example.core.util.log.LoggerUtil;
import com.example.core.util.toast.ToastUtil;
import com.example.ec.R;
import com.example.ec.bluetooth.model.OrderModelController;
import com.example.ec.test.model.Order;

import java.io.Serializable;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * @author alan
 *         Date  2018/6/7.
 *         Function :蓝牙打印
 *         Issue :
 */

public class BlueToothPrintDelegate extends BluetoothDelegate implements View.OnClickListener{

    public static final String TAG = "BlueToothPrintDelegate";

    BluetoothAdapter mAdapter;
    int PERMISSION_REQUEST_COARSE_LOCATION=2;
    Button mBtnPrint;

    TextView mTvOrderInfo;
    TextView mTvBlueTooth;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //6.0以上的手机要地理位置权限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, PERMISSION_REQUEST_COARSE_LOCATION);
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_blue_tooth_print;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        mBtnPrint = rootView.findViewById(R.id.bt_print);
        mTvOrderInfo = rootView.findViewById(R.id.tv_order_info);
        mTvBlueTooth = rootView.findViewById(R.id.tv_blue_tooth);

        BluetoothController.init(this);
        mBtnPrint.setOnClickListener(this);

        //rxJava使用
        setOrderInfo();
    }


    private void setOrderInfo() {

        LoggerUtil.e(TAG,"TEXT");

        Observable.create(new Observable.OnSubscribe<Order>() {
            @Override
            public void call(Subscriber<? super Order> subscriber) {
                Order order = OrderModelController.getInstance().getOrder();
                subscriber.onNext(order);
                LoggerUtil.e(TAG,"ONCREATE"+order.toString());
            //    subscriber.onCompleted();
            }
        })
                // 指定 Subscriber 的回调发生在主线程
                .subscribeOn(AndroidSchedulers.mainThread())
                // 指定 subscribe() 发生在 IO 线程
                .observeOn(Schedulers.io())
                .subscribe(new Subscriber<Order>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Order order) {

                        LoggerUtil.e(TAG,"ONNETXT"+order.getOrderId());
                       StringBuffer sb = new StringBuffer();

                       sb.append("订单编号："+order.getOrderId()+"\n");
                       sb.append("备注："+"\n");
                       sb.append("      "+"商品明细单"+"\n");
                       sb.append("      "+order.getWareList().get(0).getShopName()+"\n");
                       sb.append("      "+order.getWareList().get(0).getWareName()+"   "+"\n");
                       sb.append("配送费："+order.getSendPrice()+"\n");
                       sb.append("优惠劵："+order.getCoupon()+"\n");
                       sb.append("实付款金额："+order.getOrderPrice()+"\n");
                        LoggerUtil.e(TAG,"VALUE"+sb.toString());
                        mTvOrderInfo.setText(sb.toString());
                    }
                });
    }

    @Override
    public void onClick(View v) {

        if (TextUtils.isEmpty(AppInfo.btAddress)){
            ToastUtil.showToast(getContext(),"请连接蓝牙...");
            getSupportDelegate().start(new SearchBluetoothDelegate(),SINGLETASK);
        }else {
            if ( mAdapter.getState()==BluetoothAdapter.STATE_OFF ){
                //蓝牙被关闭时强制打开
                mAdapter.enable();
                ToastUtil.showToast(getContext(),"蓝牙被关闭请打开...");
            }else {
                ToastUtil.showToast(getContext(),"开始打印...");

                Intent intent = new Intent(getContext(), BtService.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("data",OrderModelController.getInstance().getOrder() );
                intent.putExtras(bundle);
                intent.setAction(PrintUtil.ACTION_PRINT_TEST);
                getContext().startService(intent);
            }

        }
    }

    /**
     * 蓝牙状态改变时调用
     * @param intent
     */
    @Override
    public void btStatusChanged(Intent intent) {
        super.btStatusChanged(intent);
        BluetoothController.init(this);
    }
}
