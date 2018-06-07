package com.example.ec.test.model;

/**
 * @author alan
 *         Date  2018/6/5.
 *         Function : 订单信息
 *
 *
 *         Issue :
 */

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
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

public class Order implements Serializable{
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

    public Order(String orderId, String receiver, String phone, String address, double totalPrice, String orderTime, List<Ware> wareList, double sendPrice, double coupon, double orderPrice, int state) {
        this.orderId = orderId;
        this.receiver = receiver;
        this.phone = phone;
        this.address = address;
        this.totalPrice = totalPrice;
        this.orderTime = orderTime;
        this.wareList = wareList;
        this.sendPrice = sendPrice;
        this.coupon = coupon;
        this.orderPrice = orderPrice;
        this.state = state;
    }

    public Order(String orderId, String receiver, List<Ware> wareList, double sendPrice, double coupon, double orderPrice) {
        this.orderId = orderId;
        this.receiver =receiver;
        this.wareList = wareList;
        this.sendPrice = sendPrice;
        this.coupon = coupon;
        this.orderPrice = orderPrice;
    }

    @Override
    public String toString() {
        return "Builder{" +
                "orderId='" + orderId + '\'' +
                ", sendPrice=" + sendPrice +
                ", coupon=" + coupon +
                '}';
    }

    public static class Builder{
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
        public Builder withOrderId(String orderId){
            this.orderId = orderId;
            return this;
        }

        public Builder withReceiver(String receiver){
            this.receiver = receiver;
            return this;
        }
        public Builder withWareList(List<Ware> wareList){
            this.wareList = wareList;
            return this;
        }

        public Builder withSendPrice(double sendPrice){
            this.sendPrice = sendPrice;
            return this;
        }

        public Builder withCoupon(int  coupon){
            this.coupon = coupon;
            return this;
        }



        public Builder withOrderPrice(double orderPrice){
            this.orderPrice = orderPrice;
            return this;
        }

        public Order build(){
            return new Order(orderId,receiver,wareList,sendPrice,coupon,orderPrice);
        }

    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public List<Ware> getWareList() {
        return wareList;
    }

    public void setWareList(List<Ware> wareList) {
        this.wareList = wareList;
    }

    public double getSendPrice() {
        return sendPrice;
    }

    public void setSendPrice(double sendPrice) {
        this.sendPrice = sendPrice;
    }

    public double getCoupon() {
        return coupon;
    }

    public void setCoupon(double coupon) {
        this.coupon = coupon;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
