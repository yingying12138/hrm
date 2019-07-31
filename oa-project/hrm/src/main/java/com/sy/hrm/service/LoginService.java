package com.sy.hrm.service;

import com.sy.hrm.bean.User;

public interface LoginService {

    User findByNameAndPwd(String userId, String password);
}
