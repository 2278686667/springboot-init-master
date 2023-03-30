package com.bdu.springsecuritydemo.controller;

import com.bdu.springsecuritydemo.domain.LoginUser;
import com.bdu.springsecuritydemo.domain.SysUser;
import com.bdu.springsecuritydemo.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {
    @Autowired
    private  SysUserService sysUserService;
    @PreAuthorize("hasAuthority('system:dept:list')")
    @RequestMapping("/test1")
    public String test(){
        return "测试1";
    }
    @RequestMapping("/test2")
    public String test2(){
        return "测试2";
    }

    @RequestMapping("/user/login")
    public String login(SysUser user){
        return sysUserService.login(user);
    }
}
