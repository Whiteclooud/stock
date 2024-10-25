package com.lan.stock.controller;

import com.lan.stock.pojo.entity.SysUser;
import com.lan.stock.service.Impl.UserServiceImpl;
import com.lan.stock.vo.req.LoginReqVo;
import com.lan.stock.vo.resp.LoginRespVo;
import com.lan.stock.vo.resp.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    /**
     * @description: 登录
     * @param:
     * @return: R<LoginRespVo>
     * @author lan
     * @date: 2024/10/25 8:03
     */
    @PostMapping("/login")
    public R<LoginRespVo> login(@RequestBody LoginReqVo vo){
        return userService.login(vo);
    }
}
