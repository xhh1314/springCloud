package com.example.taobao.user.common.dao;

import com.example.taobao.user.common.entity.UserDO;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

	UserDO getUserById(Integer userId);

	UserDO getUserByUsername(String username);


}
