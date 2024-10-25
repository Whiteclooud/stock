package com.lan.stock;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author lan
 * @version 1.0
 * @description: TODO
 * @date 2024/10/25 7:46
 */

@SpringBootTest
public class TestPasswordEncoder {

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * @description: 测试密码加密
     * @param: null
     * @return:
     * @author lan
     * @date: 2024/10/25 7:49
     */
    @Test
    public void test01(){
        String pwd = "123456";
        String encodePwd = passwordEncoder.encode(pwd);
        System.out.println(encodePwd);
    }

    @Test
    public void test02(){
        String pwd = "123456";
        String enPwd = "$2a$10$YCP0SqIPSjBXHzlh7hrYK.xwHm4urfkoPAey.eo1IEf1Nd6.Ahjqi";
        boolean matches = passwordEncoder.matches(pwd, enPwd);
        System.out.println(matches);

    }
}
