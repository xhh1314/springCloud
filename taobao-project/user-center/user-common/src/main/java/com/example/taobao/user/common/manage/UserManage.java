package com.example.taobao.user.common.manage;

import com.example.taobao.user.common.dao.UserMapper;
import com.example.taobao.user.common.entity.UserDO;
import com.example.taobao.user.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserManage {

	@Autowired
	private UserMapper userMapper;

	public UserDTO getUserByUsername(String username) {
		if (!StringUtils.hasText(username))
			return null;
		UserDO userDO = userMapper.getUserByUsername(username);
		if (userDO == null)
			return null;
		return userDO.tranfertoDTO();
	}

	public UserDTO getUserById(Integer id) {
		if (id == null)
			return null;
		UserDO userDO = userMapper.getUserById(id);
		if (userDO == null)
			return null;
		return userDO.tranfertoDTO();

	}
}
