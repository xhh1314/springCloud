package com.example.taobao.user.common.entity;

import com.example.taobao.user.dto.UserDTO;
import org.springframework.beans.BeanUtils;

import java.util.Date;

public class UserDO {

	private Integer userId;
	private String username;
	private String password;
	private String email;
	private String phone;
	private String nickname;
	private String deliveryAddress;
	private Short status;
	private Date createTime;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public UserDO transfertoDO(UserDTO userDTO) {
		BeanUtils.copyProperties(userDTO, this);
		return this;
	}

	public UserDTO tranfertoDTO() {
		UserDTO userDTO = new UserDTO();
		BeanUtils.copyProperties(this, userDTO);
		return userDTO;
	}
}
