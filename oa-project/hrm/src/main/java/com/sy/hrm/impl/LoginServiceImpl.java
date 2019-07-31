package com.sy.hrm.impl;

import com.sy.hrm.bean.User;
import com.sy.hrm.dao.UserMapper;
import com.sy.hrm.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByNameAndPwd(String userId, String password) {
        User  user = userMapper.findByNameAndPwd(userId, password);
        return user;
    }
}
