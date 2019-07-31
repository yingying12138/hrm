package com.sy.hrm.dao;

import com.sy.hrm.base.BaseMapper;
import com.sy.hrm.bean.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * UserMapper 数据访问类
 * @author cisy
 * @email 1766181826@qq.com
 * @date 2019-07-29 19:43:43
 * @version 1.0
 */
@Mapper
public interface UserMapper extends BaseMapper {

    User findByNameAndPwd(String name, String password);
}