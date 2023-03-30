package com.zhby.config.shiro;

import com.zhby.database.dal.uc_zhuti_accountlinkDal;
import com.zhby.database.dal.uc_zhuti_zyryDal;
import com.zhby.database.model.uc_zhuti_accountlink;
import com.zhby.entity.api.SaTokenUser;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

public class ShiroDbRealm extends AuthorizingRealm {

	@Autowired
	@Lazy
	private uc_zhuti_accountlinkDal dUcZhutiAccountlink;
	@Autowired
	@Lazy
	private uc_zhuti_zyryDal dUcZhutiZyry;

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		uc_zhuti_accountlink link = dUcZhutiAccountlink.getModelByPassword(token.getUsername(), String.valueOf(token.getPassword()), "bc");
		SaTokenUser user = new SaTokenUser(link.getAccountid(), token.getUsername(), String.valueOf(token.getPassword()));
		return new SimpleAuthenticationInfo(user, user.getPassword().toCharArray(), getName());
	}

	/**
	 * 
	 * Shiro权限认证
	 * 
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		return info;
	}

}
