package com.example.taobao.user.webservice.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.example.taobao.user.api.UserService;
import com.example.taobao.user.common.manage.UserManage;
import com.example.taobao.user.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Service(version = "1.0.0", application = "${dubbo.application.id}", protocol = "${dubbo.protocol.id}", registry = "${dubbo.registry.id}")
@Component
public class UserServiceImpl implements UserService {

	@Autowired
	private UserManage userManage;

	@Override
	public UserDTO getUserByUsername(String username) {
		if (username == null)
			return null;
		return userManage.getUserByUsername(username);
	}

	@Override
	public UserDTO getUserById(Integer id) {
		if (id == null)
			return null;
		return userManage.getUserById(id);
	}
}
