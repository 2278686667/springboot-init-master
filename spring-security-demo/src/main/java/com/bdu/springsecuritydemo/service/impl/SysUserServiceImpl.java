package com.bdu.springsecuritydemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bdu.springsecuritydemo.domain.LoginUser;
import com.bdu.springsecuritydemo.domain.SysUser;
import com.bdu.springsecuritydemo.service.SysUserService;
import com.bdu.springsecuritydemo.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
* @author CAOHAOBO
* @description 针对表【sys_user(用户表)】的数据库操作Service实现
* @createDate 2023-02-11 18:32:44
*/
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser>
    implements SysUserService{
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Override
    public String login(SysUser user) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        //前面登录时用两参，对认证状态还未确认，之后调用ProviderManager对账号密码进行确认后，返回的那个Authentication是认证的。
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        if (Objects.isNull(authenticate)){
            throw new RuntimeException("登陆失败");
        }
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String s = loginUser.getUser().getId().toString();
        String jwt = "123";
        Map<String,String> map=new HashMap<>();
        map.put("token",jwt);
        redisTemplate.opsForValue().set("token:"+s,loginUser);
        return "登陆成功";
    }
}




