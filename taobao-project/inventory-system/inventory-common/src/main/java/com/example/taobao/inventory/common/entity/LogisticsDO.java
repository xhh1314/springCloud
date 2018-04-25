package com.example.taobao.inventory.common.entity;


import java.util.Date;

/**
 * 物流表
 *
 * @author lh
 * @date 2018/4/25
 * @since
 */
public class LogisticsDO {


    /**
     * 物流id号
     */
    private Integer logisticsId;

    /**
     * 商品id
     */
    private Integer productId;
    /**
     * 订单id
     */
    private Integer orderId;
    /**
     * 收货地址
     */
    private Integer deliveryAddress;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 接受订单时间
     */
    private Date acceptingOrderTime;
    /**
     * 出库时间
     */
    private Date deliveryTime;
    /**
     * 中转详情
     */
    private String transitParticular;
    /**
     * 收货时间
     */
    private Date receivingTime;

    public Integer getLogisticsId() {
        return logisticsId;
    }

    public void setLogisticsId(Integer logisticsId) {
        this.logisticsId = logisticsId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(Integer deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getAcceptingOrderTime() {
        return acceptingOrderTime;
    }

    public void setAcceptingOrderTime(Date acceptingOrderTime) {
        this.acceptingOrderTime = acceptingOrderTime;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getTransitParticular() {
        return transitParticular;
    }

    public void setTransitParticular(String transitParticular) {
        this.transitParticular = transitParticular;
    }

    public Date getReceivingTime() {
        return receivingTime;
    }

    public void setReceivingTime(Date receivingTime) {
        this.receivingTime = receivingTime;
    }
}
