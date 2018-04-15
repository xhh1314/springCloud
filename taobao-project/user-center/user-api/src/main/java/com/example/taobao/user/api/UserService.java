package com.example.taobao.user.api;

import com.example.taobao.user.dto.UserDTO;

public interface UserService {

    UserDTO getUserByUsername(String username);

    UserDTO getUserById(Integer id);
}
