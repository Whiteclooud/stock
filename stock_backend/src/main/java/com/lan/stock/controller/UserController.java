package com.lan.stock.controller;

import com.lan.stock.pojo.entity.SysUser;
import com.lan.stock.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lan
 * @version 1.0
 * @description: 用户webceng接口资源
 * @date 2024/10/10 11:06
 */
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    /** 
     * @description: 根据用户名称查询用户信息
     * @param: username 
     * @return: com.lan.stock.pojo.entity.SysUser 
     * @author lan
     * @date: 2024/10/10 11:13
     */
    @GetMapping("/user/{username}")
    public SysUser getUserByUserName(@PathVariable("username") String username){
        return userService.findByUserName(username);
    }
}
