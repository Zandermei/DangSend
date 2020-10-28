package com.two.dangsend.model;

public class OrderModel {

    private String orderMoney;
    private String orderName;
    private String orderNum;
    private String orderPeopleName;
    private String orderAddress;
    private String orderPhone;
    private String orderTime;
    private String orderAccount;


    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getOrderAccount() {
        return orderAccount;
    }

    public void setOrderAccount(String orderAccount) {
        this.orderAccount = orderAccount;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderPeopleName() {
        return orderPeopleName;
    }

    public void setOrderPeopleName(String orderPeopleName) {
        this.orderPeopleName = orderPeopleName;
    }

    public String getOrderAddress() {
        return orderAddress;
    }

    public void setOrderAddress(String orderAddress) {
        this.orderAddress = orderAddress;
    }

    public String getOrderPhone() {
        return orderPhone;
    }

    public void setOrderPhone(String orderPhone) {
        this.orderPhone = orderPhone;
    }

    private int pos;
    private String orderSucess;

    public String getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(String orderMoney) {
        this.orderMoney = orderMoney;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }


    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public String getOrderSucess() {
        return orderSucess;
    }

    public void setOrderSucess(String orderSucess) {
        this.orderSucess = orderSucess;
    }
}
