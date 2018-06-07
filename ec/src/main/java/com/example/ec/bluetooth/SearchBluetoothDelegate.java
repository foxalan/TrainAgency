package com.example.ec.bluetooth;

import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.core.bluetooth.bt.BluetoothDelegate;
import com.example.core.bluetooth.bt.BtUtil;
import com.example.core.bluetooth.print.PrintQueue;
import com.example.core.bluetooth.print.PrintUtil;
import com.example.core.util.toast.ToastUtil;
import com.example.ec.R;


import java.lang.reflect.Method;

/**
 * 蓝牙搜索界面
 * Created by liuguirong on 2017/8/3.
 */

public class SearchBluetoothDelegate extends BluetoothDelegate implements AdapterView.OnItemClickListener, View.OnClickListener {

    private BluetoothAdapter bluetoothAdapter;
    private ListView lv_searchblt;
    private TextView tv_title;
    private TextView tv_summary;
    private SearchBleAdapter searchBleAdapter;

    @Override
    public Object setLayout() {
        return R.layout.delegate_searchbooth;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        lv_searchblt = rootView.findViewById(R.id.lv_searchblt);
        tv_title =  rootView.findViewById(R.id.tv_title);
        tv_summary =  rootView.findViewById(R.id.tv_summary);
        //初始化蓝牙适配器
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        searchBleAdapter = new SearchBleAdapter(getContext(), null);
        lv_searchblt.setAdapter(searchBleAdapter);
        init();
        searchDeviceOrOpenBluetooth();
        lv_searchblt.setOnItemClickListener(this);
        tv_title.setOnClickListener(this);
        tv_summary.setOnClickListener(this);
    }


    private void init() {
        if (!BtUtil.isOpen(bluetoothAdapter)) {
            tv_title.setText("未连接蓝牙打印机");
            tv_summary.setText("系统蓝牙已关闭,点击开启");

        } else {
            if (!PrintUtil.isBondPrinter(getContext(), bluetoothAdapter)) {
                //未绑定蓝牙打印机器
                tv_title.setText("未连接蓝牙打印机");
                tv_summary.setText("点击后搜索蓝牙打印机");

            } else {
                //已绑定蓝牙设备
                tv_title.setText(getPrinterName() + "已连接");
                String blueAddress = PrintUtil.getDefaultBluethoothDeviceAddress(getContext());
                if (TextUtils.isEmpty(blueAddress)) {
                    blueAddress = "点击后搜索蓝牙打印机";
                }
                tv_summary.setText(blueAddress);
            }
        }
    }

    @Override
    public void btStatusChanged(Intent intent) {

        if (bluetoothAdapter.getState() == BluetoothAdapter.STATE_OFF) {
            //蓝牙被关闭时强制打开
            bluetoothAdapter.enable();
        }
        if (bluetoothAdapter.getState() == BluetoothAdapter.STATE_ON) {
            //蓝牙打开时搜索蓝牙
            searchDeviceOrOpenBluetooth();
        }
    }

    private String getPrinterName() {
        String dName = PrintUtil.getDefaultBluetoothDeviceName(getContext());
        if (TextUtils.isEmpty(dName)) {
            dName = "未知设备";
        }
        return dName;
    }

    private String getPrinterName(String dName) {
        if (TextUtils.isEmpty(dName)) {
            dName = "未知设备";
        }
        return dName;
    }

    /**
     * 开始搜索
     * search device
     */
    private void searchDeviceOrOpenBluetooth() {
        if (BtUtil.isOpen(bluetoothAdapter)) {
            BtUtil.searchDevices(bluetoothAdapter);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        BtUtil.cancelDiscovery(bluetoothAdapter);
    }

    /**
     * 关闭搜索
     * cancel search
     */

    @Override
    public void btStartDiscovery(Intent intent) {
        tv_title.setText("正在搜索蓝牙设备…");
        tv_summary.setText("");
    }

    @Override
    public void btFinishDiscovery(Intent intent) {
        tv_title.setText("搜索完成");
        tv_summary.setText("点击重新搜索");
    }

    @Override
    public void btFoundDevice(Intent intent) {
        BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
        Log.d("1", "!");
        if (null != bluetoothAdapter && device != null) {
            searchBleAdapter.addDevices(device);
            String dName = device.getName() == null ? "未知设备" : device.getName();
            Log.d("未知设备", dName);
            Log.d("1", "!");
        }
    }

    @Override
    public void btBondStatusChange(Intent intent) {
        super.btBondStatusChange(intent);
        BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
        switch (device.getBondState()) {
            case BluetoothDevice.BOND_BONDING:
                //正在配对
                Log.d("BlueToothTestActivity", "正在配对......");
                break;
            case BluetoothDevice.BOND_BONDED:
                //配对结束
                Log.d("BlueToothTestActivity", "完成配对");
                connectBlt(device);
                break;
            case BluetoothDevice.BOND_NONE:
                //取消配对/未配对
                Log.d("BlueToothTestActivity", "取消配对");
            default:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

        if (null == searchBleAdapter) {
            return;
        }
        final BluetoothDevice bluetoothDevice = searchBleAdapter.getItem(position);
        if (null == bluetoothDevice) {
            return;
        }
        new AlertDialog.Builder(getContext())
                .setTitle("绑定" + getPrinterName(bluetoothDevice.getName()) + "?")
                .setMessage("点击确认绑定蓝牙设备")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            BtUtil.cancelDiscovery(bluetoothAdapter);


                            if (bluetoothDevice.getBondState() == BluetoothDevice.BOND_BONDED) {
                                connectBlt(bluetoothDevice);
                            } else {
                                Method createBondMethod = BluetoothDevice.class.getMethod("createBond");
                                createBondMethod.invoke(bluetoothDevice);
                            }
                            PrintQueue.getQueue(getContext()).disconnect();
                            String name = bluetoothDevice.getName();
                        } catch (Exception e) {
                            e.printStackTrace();
                            PrintUtil.setDefaultBluetoothDeviceAddress(getContext(), "");
                            PrintUtil.setDefaultBluetoothDeviceName(getContext(), "");
                            ToastUtil.showToast(getContext(), "蓝牙绑定失败,请重试");
                        }
                    }
                })
                .create()
                .show();


    }

    /***
     * 配对成功连接蓝牙
     * @param bluetoothDevice
     */

    private void connectBlt(BluetoothDevice bluetoothDevice) {
        if (null != searchBleAdapter) {
            searchBleAdapter.setConnectedDeviceAddress(bluetoothDevice.getAddress());
        }
        init();
        searchBleAdapter.notifyDataSetChanged();
        PrintUtil.setDefaultBluetoothDeviceAddress(getContext(), bluetoothDevice.getAddress());
        PrintUtil.setDefaultBluetoothDeviceName(getContext(), bluetoothDevice.getName());
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.tv_title) {
        } else if (i == R.id.tv_summary) {
            searchDeviceOrOpenBluetooth();

        } else {
        }
    }
}
