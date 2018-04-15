package com.example.taobao.order.dto;

import java.io.Serializable;
import java.util.Date;

public class OrderDTO implements Serializable {
	private static final long serialVersionUID = 4024174610708020885L;
	private Integer orderId;
	private Integer orderItemId;
	private Integer userId;
	/**
	 * 收货地址信息id
	 */
	private Integer deliveryAddressId;
	/**
	 * 商品总金额
	 */
	private Double aggregateAmount;

	/**
	 * 返现金额
	 */
	private Double returnAmount;
	/**
	 * 优惠券金额
	 */
	private Double discountAmount;
	/**
	 * 实际支付金额
	 */
	private Double payAmount;
	/**
	 * 订单创建时间
	 */
	private Date createTime;
	/**
	 * 订单支付时间
	 */
	private Date payTime;
	/**
	 * 商品发货时间
	 */
	// private Date deliveryTime;

	/**
	 * 收货时间
	 */
	// private Date receiveTime;

	/**
	 * 配送id
	 */
	private Integer dispatchingId;

	/**
	 * 订单状态 0 提交 1 已支付 -1 取消
	 */
	private Short status;

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(Integer orderItemId) {
		this.orderItemId = orderItemId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getDeliveryAddressId() {
		return deliveryAddressId;
	}

	public void setDeliveryAddressId(Integer deliveryAddressId) {
		this.deliveryAddressId = deliveryAddressId;
	}

	public Double getAggregateAmount() {
		return aggregateAmount;
	}

	public void setAggregateAmount(Double aggregateAmount) {
		this.aggregateAmount = aggregateAmount;
	}

	public Double getReturnAmount() {
		return returnAmount;
	}

	public void setReturnAmount(Double returnAmount) {
		this.returnAmount = returnAmount;
	}

	public Double getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(Double discountAmount) {
		this.discountAmount = discountAmount;
	}

	public Double getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(Double payAmount) {
		this.payAmount = payAmount;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public Integer getDispatchingId() {
		return dispatchingId;
	}

	public void setDispatchingId(Integer dispatchingId) {
		this.dispatchingId = dispatchingId;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}


}
