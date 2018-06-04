package com.example.alan.trainagency;

import android.os.Bundle;

import com.example.core.activity.ProxyActivity;
import com.example.core.delegate.LatteDelegate;

/**
 * @author alan
 * 主Activity
 *
 */

public class MainActivity extends ProxyActivity {

    @Override
    public LatteDelegate setRootDelegate() {
        return new ExpDelegate();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void post(Runnable runnable) {

    }
}
