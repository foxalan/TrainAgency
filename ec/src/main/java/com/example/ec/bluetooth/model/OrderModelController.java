package com.example.ec.bluetooth.model;

import com.example.core.net.callback.IFailure;
import com.example.ec.test.model.Order;
import com.example.ec.test.model.Ware;

import java.util.ArrayList;
import java.util.List;

/**
 * @author alan
 *         Date  2018/6/7.
 *         Function :
 *         Issue :
 */

public class OrderModelController {

    /**
     * 订单编号：	20180607102801
        备注：
        商品明细单
        A温州卤菜15902755226备货中
        卤鸭锁骨    1    约500g    25.00
        配送费：	4.00元
        优惠券：	7.00元
        实付款金额：	18.00元
     *
     */
    private Order order = null;

    public static class Holder{
        public static OrderModelController orderModelController = new OrderModelController();
    }

    public static OrderModelController getInstance(){
        return Holder.orderModelController;
    }

    /**
     * 测试使用
     * @return
     */
    public Order getOrder(){

        Ware ware = new Ware("A温州卤菜","卤鸭锁骨 ","约500g",1,25.00,0);
        List<Ware> wareList = new ArrayList<>();
        wareList.add(ware);

        if (order == null){
            order = new Order.Builder()
                    .withOrderId("201806072801")
                    .withWareList(wareList)
                    .withSendPrice(4)
                    .withCoupon(4)
                    .withOrderPrice(18)
                    .build();
        }

        return order;
    }


}

