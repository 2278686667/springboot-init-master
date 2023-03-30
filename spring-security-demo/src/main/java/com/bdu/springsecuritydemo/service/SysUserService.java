package com.bdu.springsecuritydemo.service;

import com.bdu.springsecuritydemo.domain.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author CAOHAOBO
* @description 针对表【sys_user(用户表)】的数据库操作Service
* @createDate 2023-02-11 18:32:44
*/
public interface SysUserService extends IService<SysUser> {
     String login(SysUser user) ;
}
