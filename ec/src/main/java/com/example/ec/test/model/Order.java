package com.example.ec.test.model;

/**
 * @author alan
 *         Date  2018/6/5.
 *         Function : 订单信息
 *
 *
 *         Issue :
 */

import java.util.List;

/**
 * 订单编号：	20180605153804
    备注：

    商品明细单

    A味好美干鲜调料15827975345已付款
    海天特级味极鲜    4    约500ml    32.00
    配送费：	4.00元
    优惠券：	8.00元
    实付款金额：	24.00元
 */

public class Order {
    /**
     *订单ID
     */
    private String orderId;
    /**
     * 收货人
     */
    private String receiver;
    /**
     * 收货人电话
     */
    private String phone;
    /**
     * 收货人地址
     */
    private String address;
    /**
     * 总金额
     */
    private double totalPrice;

    /**
     * 订单时间
     */
    private String orderTime;

    /**
     * 订单商品
     */
    private List<Ware> wareList;

    /**
     * 配送费
     */
    private double sendPrice;

    /**
     * 优惠卷
     */
    private double coupon;

    /**
     * 实付款金额
     */

    private double orderPrice;

    /**
     * 订单状态
     */
    private int state;

}
