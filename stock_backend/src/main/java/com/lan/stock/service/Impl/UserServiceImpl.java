package com.lan.stock.service.Impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.lan.stock.mapper.SysUserMapper;
import com.lan.stock.pojo.entity.SysUser;
import com.lan.stock.service.UserService;
import com.lan.stock.utils.IdWorker;
import com.lan.stock.vo.req.LoginReqVo;
import com.lan.stock.vo.resp.LoginRespVo;
import com.lan.stock.vo.resp.R;
import com.lan.stock.vo.resp.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author lan
 * @version 1.0
 * @description: 用户服务实现
 * @date 2024/10/10 10:44
 */
@Service("UserService")
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private RedisTemplate redisTemplate;

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

    /** 
     * @description: 生成图片验证码功能
     * @param:  
     * @return: com.lan.stock.vo.resp.R<java.util.Map> 
     * @author lan
     * @date: 2024/10/30 8:07
     */
    @Override
    public R<Map> getCaptchaCode() {
        /**
         * @description:  1、生成图片验证码
         * @param:  参数1：图片宽度
         *          参数2：图片高度
         *          参数3：验证码长度
         *          参数4：干扰线条数
         * @return: com.lan.stock.vo.resp.R<java.util.Map>
         * @author lan
         * @date: 2024/10/30 8:13
         */
        LineCaptcha captcha = CaptchaUtil.createLineCaptcha(250, 40, 4, 5);
        //设置背景颜色
        captcha.setBackground(Color.LIGHT_GRAY);
        //自定义生成校验码的规则
//        captcha.setGenerator();
        //获取校验码
        String checkCode = captcha.getCode();
        //获取经过base64编码处理的图片数据
        String imageData = captcha.getImageBase64();
        //2、生成 sessionID，转化成String，避免前端精度丢失
        String sessionId = String.valueOf(idWorker.nextId());
        log.info("当前生成的校验码：{}，会话id：{}", checkCode, sessionId);
        //3、后台将sessionId作为key，校验码作为value，保存在redis中（调试成功后改为1分钟）
        redisTemplate.opsForValue().set("CK:" + sessionId, checkCode, 5, TimeUnit.MINUTES);
        //4、组装数据
        HashMap<String, String> data = new HashMap();
        data.put("imageData", imageData);
        data.put("sessionId", sessionId);
        //5、响应数据
        return R.ok(data);
    }
}
