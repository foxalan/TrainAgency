package com.example.ec.test.model;

import java.io.Serializable;

/**
 * @author alan
 *         Date  2018/6/5.
 *         Function :
 *
 *
        测试一
        商品名称:鸭翅
        规格:500g
        数量:1 价格:50.0000
 *
 *         Issue :商品
 */

public class Ware implements Serializable {
    /**
     * 商店名称
     */
    private String shopName;
    /**
     * 商品名称
     */
    private String wareName;
    /**
     * 商品规格
     */
    private String wareRule;
    /**
     * 商品规格
     */
    private int wareCount;
    /**
     * 商品价格
     */
    private double warePrice;

    /**
     * 状态
     */
    private int wareState;

    public Ware(String shopName, String wareName, String wareRule, int wareCount, double warePrice, int wareState) {
        this.shopName = shopName;
        this.wareName = wareName;
        this.wareRule = wareRule;
        this.wareCount = wareCount;
        this.warePrice = warePrice;
        this.wareState = wareState;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getWareName() {
        return wareName;
    }

    public void setWareName(String wareName) {
        this.wareName = wareName;
    }

    public String getWareRule() {
        return wareRule;
    }

    public void setWareRule(String wareRule) {
        this.wareRule = wareRule;
    }

    public int getWareCount() {
        return wareCount;
    }

    public void setWareCount(int wareCount) {
        this.wareCount = wareCount;
    }

    public double getWarePrice() {
        return warePrice;
    }

    public void setWarePrice(double warePrice) {
        this.warePrice = warePrice;
    }

    public int getWareState() {
        return wareState;
    }

    public void setWareState(int wareState) {
        this.wareState = wareState;
    }
}
