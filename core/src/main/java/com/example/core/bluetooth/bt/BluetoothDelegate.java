package com.example.core.bluetooth.bt;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.example.core.delegate.LatteDelegate;


/**
 * Created by liuguirong on 8/1/17.
 */
public abstract class BluetoothDelegate extends LatteDelegate implements BtInterface {


    /**
     * blue tooth broadcast receiver
     */
    protected BroadcastReceiver mBtReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (null == intent) {
                return;
            }
            String action = intent.getAction();
            if (TextUtils.isEmpty(action)) {
                return;
            }
            if (BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action)) {
                btStartDiscovery(intent);
            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                btFinishDiscovery(intent);
            } else if (BluetoothAdapter.ACTION_STATE_CHANGED.equals(action)) {
                btStatusChanged(intent);
            } else if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                btFoundDevice(intent);
            } else if (BluetoothDevice.ACTION_BOND_STATE_CHANGED.equals(action)) {
                btBondStatusChange(intent);
            } else if ("android.bluetooth.device.action.PAIRING_REQUEST".equals(action)) {
                btPairingRequest(intent);
            }
        }
    };


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BtUtil.registerBluetoothReceiver(mBtReceiver, getActivity());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        BtUtil.unregisterBluetoothReceiver(mBtReceiver, getActivity());
    }



    @Override
    public void btStartDiscovery(Intent intent) {

    }

    @Override
    public void btFinishDiscovery(Intent intent) {

    }

    @Override
    public void btStatusChanged(Intent intent) {

    }

    @Override
    public void btFoundDevice(Intent intent) {

    }

    @Override
    public void btBondStatusChange(Intent intent) {

    }

    @Override
    public void btPairingRequest(Intent intent) {

    }
}
