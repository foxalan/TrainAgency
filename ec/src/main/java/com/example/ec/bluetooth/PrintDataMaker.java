package com.example.ec.bluetooth;

import com.example.ec.test.model.Order;

import java.util.List;

/**
 * Print
 * * Created by liugruirong on 2017/8/3.
 */

public interface PrintDataMaker {
    List<byte[]> getPrintData(int type, Order order);
}
