package com.lan.stock.service;

import com.lan.stock.pojo.entity.SysUser;

public interface UserService {

    /**
     * @description: 根据用户名称查询用户信息
     * @param: userName
     * @return: com.lan.stock.pojo.entity.SysUser
     * @author lan
     * @date: 2024/10/10 10:43
     */
    SysUser findByUserName(String userName);
}
