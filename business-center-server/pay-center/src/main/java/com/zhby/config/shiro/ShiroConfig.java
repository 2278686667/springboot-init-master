package com.zhby.config.shiro;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


@Configuration
public class ShiroConfig {
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	@Value(value = "${shiro_redis_session}")
	private int shiro_redis_session;
	@Value(value = "${shiro_redis_cache}")
	private int shiro_redis_cache;
	@Value("${redis_session_name}")
    private String redissessionname;

	@Bean
	public ShiroFilterFactoryBean shirFilter(@Qualifier("securityManager")SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);

		// 拦截器.
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
		filterChainDefinitionMap.put("/reg.json", "anon");
		filterChainDefinitionMap.put("/login.json", "anon");
		filterChainDefinitionMap.put("/login-index.json", "anon");
		filterChainDefinitionMap.put("/common/**", "anon");
		filterChainDefinitionMap.put("/**/anon/**", "anon");

		filterChainDefinitionMap.put("/sso/**", "anon");
		filterChainDefinitionMap.put("/oauth2*/**", "anon");
		filterChainDefinitionMap.put("/sso-auth.html", "anon");
		filterChainDefinitionMap.put("/login.html", "anon");
		filterChainDefinitionMap.put("/confirm.html", "anon");
		filterChainDefinitionMap.put("/doc.html", "anon");

		filterChainDefinitionMap.put("/swagger*/**", "anon");
		filterChainDefinitionMap.put("/webjars/**", "anon");
		filterChainDefinitionMap.put("/v2/**", "anon");

		filterChainDefinitionMap.put("/exec", "anon");
		filterChainDefinitionMap.put("/uedtior/**", "anon");
		filterChainDefinitionMap.put("/**", "authc");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

		Map<String, Filter> map = new HashMap<String, Filter>();
		map.put("authc",  new ShiroAuthenticationFilter());
		shiroFilterFactoryBean.setFilters(map);
		
		return shiroFilterFactoryBean;
	}

	@Bean(value = "myShiroRealm")
	public ShiroDbRealm myShiroRealm() {
		ShiroDbRealm myShiroRealm = new ShiroDbRealm();
		return myShiroRealm;
	}

	@Bean
	public RedisCacheManager redisCacheManager() {
		RedisCacheManager cacheManager = new RedisCacheManager();
		cacheManager.setRedisTemplate(redisTemplate);
		cacheManager.setExpire(shiro_redis_cache);
		return cacheManager;
	}

	@Bean
	public RedisSessionDAO redisSessionDAO() {
		RedisSessionDAO sessionDAO = new RedisSessionDAO();
		sessionDAO.setRedisTemplate(redisTemplate);
		sessionDAO.setExpire(shiro_redis_session);
		return sessionDAO;
	}

	@Bean
	public DefaultWebSessionManager sessionManager() {
		DefaultWebSessionManager defaultWebSessionManager = new DefaultWebSessionManager();
		defaultWebSessionManager.setSessionDAO(redisSessionDAO());
		defaultWebSessionManager.setSessionIdCookie(sessionIdCookie());
		defaultWebSessionManager.setGlobalSessionTimeout(shiro_redis_session);
		return defaultWebSessionManager;
	}

	@Bean
	public SimpleCookie sessionIdCookie() {
		SimpleCookie simpleCookie = new SimpleCookie(redissessionname);
		// JSESSIONID的path为/用于多个系统共享JSESSIONID
		simpleCookie.setPath("/");
		// 设置了HttpOnly=true,在脚本中就不能的到cookie，可以避免cookie被盗用
		simpleCookie.setHttpOnly(true);
		return simpleCookie;
	}

	@Bean
	public DefaultWebSecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(myShiroRealm());
		// <!-- 用户授权/认证信息Cache, 采用EhCache 缓存 -->
		securityManager.setSessionManager(sessionManager());
		securityManager.setCacheManager(redisCacheManager());
		return securityManager;
	}

	/**
	 * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),
	 * 需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
	 * 配置以下两个bean(DefaultAdvisorAutoProxyCreator
	 * (可选)和AuthorizationAttributeSourceAdvisor)即可实现此功能
	 * 
	 * @return
	 */
	@Bean
	public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
		advisorAutoProxyCreator.setProxyTargetClass(true);
		return advisorAutoProxyCreator;
	}

	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
			@Qualifier("securityManager") SecurityManager manager) {
		AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
		advisor.setSecurityManager(manager);
		return advisor;

	}
}
