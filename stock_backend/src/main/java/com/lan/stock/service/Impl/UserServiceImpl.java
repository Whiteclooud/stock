package com.lan.stock.service.Impl;

import com.lan.stock.mapper.SysUserMapper;
import com.lan.stock.pojo.entity.SysUser;
import com.lan.stock.service.UserService;
import com.lan.stock.vo.req.LoginReqVo;
import com.lan.stock.vo.resp.LoginRespVo;
import com.lan.stock.vo.resp.R;
import com.lan.stock.vo.resp.ResponseCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public SysUser findByUserName(String userName) {
        return sysUserMapper.findUserInfoByUserName(userName);
    }

    /** 
     * @description: 登录
     * @param: vo 
     * @return: com.lan.stock.vo.resp.R<com.lan.stock.vo.resp.LoginRespVo> 
     * @author lan
     * @date: 2024/10/25 8:08
     */
    @Override
    public R<LoginRespVo> login(LoginReqVo vo) {
        //1. 判断传入参数是否合法
        if(vo == null || StringUtils.isBlank((vo.getUsername())) || StringUtils.isBlank((vo.getPassword())) || StringUtils.isBlank((vo.getCode())))
            return R.error(ResponseCode.DATA_ERROR);
        //2.根据用户名去数据库查询密码的密文
        SysUser dbuser = sysUserMapper.findUserInfoByUserName(vo.getUsername());
        if(dbuser == null){
            // 用户不存在
            return R.error(ResponseCode.ACCOUNT_NOT_EXISTS);
        }
        //3.调用密码的匹配器判断是否匹配
        if (passwordEncoder.matches(vo.getPassword(), dbuser.getPassword()) == true) {
            System.out.println("登录成功");
        }else {
            return R.error(ResponseCode.USERNAME_OR_PASSWORD_ERROR);
        }
        //4.响应
        LoginRespVo loginRespVo = new LoginRespVo();
//        loginRespVo.setId(dbuser.getId());
//        loginRespVo.setUsername(dbuser.getUsername());
//        loginRespVo.setNickName(dbuser.getNickName());
//        loginRespVo.setPhone(dbuser.getPhone());
        //发现LoginRespVo 与 SysUser对象属性名称和字段名一致，可以使用工具类
        // 必须保证属性名称和类型一致
        BeanUtils.copyProperties(dbuser, loginRespVo);
        return R.ok(loginRespVo);
    }
}
