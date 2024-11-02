package com.lan.stock.service;

import com.lan.stock.pojo.entity.SysUser;
import com.lan.stock.vo.req.LoginReqVo;
import com.lan.stock.vo.resp.LoginRespVo;
import com.lan.stock.vo.resp.R;

import java.util.Map;

public interface UserService {

    /**
     * @description: 根据用户名称查询用户信息
     * @param: userName
     * @return: com.lan.stock.pojo.entity.SysUser
     * @author lan
     * @date: 2024/10/10 10:43
     */
    SysUser findByUserName(String userName);
    
    /** 
     * @description: 登录 
     * @param: vo 
     * @return: com.lan.stock.vo.resp.R<com.lan.stock.vo.resp.LoginRespVo> 
     * @author lan
     * @date: 2024/10/25 8:07
     */
    R<LoginRespVo> login(LoginReqVo vo);

    R<Map> getCaptchaCode();
}
