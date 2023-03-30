package com.bdu.springsecuritydemo;

import com.bdu.springsecuritydemo.domain.SysUser;
import com.bdu.springsecuritydemo.mapper.SysUserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SpringSecurityDemoApplicationTests {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Test
    void contextLoads() {
        List<SysUser> sysUsers = sysUserMapper.selectList(null);
        System.out.println(sysUsers);
    }

}
