package com.lan.stock.controller;

import com.lan.stock.pojo.entity.SysUser;
import com.lan.stock.service.Impl.UserServiceImpl;
import com.lan.stock.vo.req.LoginReqVo;
import com.lan.stock.vo.resp.LoginRespVo;
import com.lan.stock.vo.resp.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author lan
 * @version 1.0
 * @description: 用户webceng接口资源
 * @date 2024/10/10 11:06
 */
@RestController
@RequestMapping("/api")
@Api(value = "用户认证相关接口定义",tags = "用户功能-用户登录功能")
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
    @ApiOperation(value = "根据用户名查询用户信息",notes = "用户信息查询",response = SysUser.class)
    @ApiImplicitParam(paramType = "path",name = "userName",value = "用户名",required = true)
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
    @ApiOperation(value = "用户登录功能",notes = "用户登录",response = R.class)
    public R<LoginRespVo> login(@RequestBody LoginReqVo vo){
        return userService.login(vo);
    }

    /** 
     * @description: 生成图片验证码功能
     * @param:  
     * @return: com.lan.stock.vo.resp.R<java.util.Map> 
     * @author lan
     * @date: 2024/10/28 13:25
     */
    @GetMapping("/captcha")
    @ApiOperation(value = "验证码生成功能",response = R.class)
    public R<Map> getCaptchaCode(){

        return userService.getCaptchaCode();
    }

}
