package com.lan.stock.service.Impl;

import com.lan.stock.mapper.SysUserMapper;
import com.lan.stock.pojo.entity.SysUser;
import com.lan.stock.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lan
 * @version 1.0
 * @description: 用户服务实现
 * @date 2024/10/10 10:44
 */
@Service("UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser findByUserName(String userName) {
        return sysUserMapper.findUserInfoByUserName(userName);
    }
}
