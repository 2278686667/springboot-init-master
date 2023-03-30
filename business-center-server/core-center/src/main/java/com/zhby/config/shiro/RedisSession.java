package com.zhby.config.shiro;

import com.alibaba.fastjson.JSONArray;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.*;

public class RedisSession extends SimpleSession{
	/**
	 * The key in the hash representing {@link Session#getCreationTime()}.
	 */
	static final String CREATION_TIME_KEY = "creationTime";

	/**
	 * The key in the hash representing {@link Session#getLastAccessedTime()}.
	 */
	static final String LAST_ACCESSED_TIME_KEY = "lastAccessedTime";

	/**
	 * The key in the hash representing {@link Session#getMaxInactiveInterval()}.
	 */
	//与 spring-session 存储类型应一致，设置为Integer
	static final String MAX_INACTIVE_INTERVAL_KEY = "maxInactiveInterval";

	/**
	 * The prefix of the key in the hash used for session attributes. For example, if the
	 * session contained an attribute named {@code attributeName}, then there would be an
	 * entry in the hash named {@code sessionAttr:attributeName} that mapped to its value.
	 */
	static final String ATTRIBUTE_PREFIX = "sessionAttr:";
	//备用 shiro redis权限认证通过标志
	static final String AUTHENTICATED_SESSION_KEY = "org.apache.shiro.subject.support.DefaultSubjectContext_AUTHENTICATED_SESSION_KEY";

	private final Map<String, Object> delta = new HashMap<>();

	private static final long serialVersionUID = 1L;
	public RedisSession(Serializable sessionId) {
		Assert.hasText(sessionId+"", "sessionId must not be empty");
		this.setId(sessionId);
	}

	public void DecoratorShiroRedis(Map<Object, Object> map) {
		Assert.notEmpty(map, "map must not be empty");
		Long creationTime = (Long) map.get(CREATION_TIME_KEY);
		if (creationTime == null) {
			handleMissingKey(CREATION_TIME_KEY);
		}
		this.setStartTimestamp(new Date(creationTime));
		Long lastAccessedTime = (Long) map.get(LAST_ACCESSED_TIME_KEY);
		if (lastAccessedTime == null) {
			handleMissingKey(LAST_ACCESSED_TIME_KEY);
		}
		this.setLastAccessTime(new Date(lastAccessedTime));
		Integer maxInactiveInterval = (Integer) map.get(MAX_INACTIVE_INTERVAL_KEY);
		if (maxInactiveInterval == null) {
			handleMissingKey(MAX_INACTIVE_INTERVAL_KEY);
		}
		this.setTimeout((maxInactiveInterval.longValue())*1000);
		map.forEach((name, value) -> {
			if (((String) name).startsWith(ATTRIBUTE_PREFIX)) {
				if(((String) name).endsWith("SimplePrincipalCollection")){
					SimplePrincipalCollection simplePrincipal = new SimplePrincipalCollection();
					Class simplePrincipalClass = simplePrincipal.getClass();
					try {
						Field realmPrincipalsField = simplePrincipalClass.getDeclaredField("realmPrincipals");
						realmPrincipalsField.setAccessible(true);
						Map<String,Set> newvaluemap = new LinkedHashMap<>();
						Map<String, Object> valuemap = (Map<String, Object>) value;
						for(Map.Entry<String,Object> entry:valuemap.entrySet())
						{
							Set set = new LinkedHashSet();
							if(entry.getValue() instanceof Set){
								set.addAll((Set)entry.getValue());
							}else if(entry.getValue() instanceof JSONArray){
								for(Object arrayobj:(JSONArray)entry.getValue()){
									set.add(arrayobj);
								}
							}
							newvaluemap.put(entry.getKey(),set);
						}
						realmPrincipalsField.set(simplePrincipal,newvaluemap);
						this.setAttribute(((String) name).substring(ATTRIBUTE_PREFIX.length()).replace(".SimplePrincipalCollection",""), simplePrincipal);
					} catch (NoSuchFieldException | IllegalAccessException e) {
						e.printStackTrace();
					}
				}else{
					this.setAttribute(((String) name).substring(ATTRIBUTE_PREFIX.length()), value);
				}
			}
		});
	}

	private static void handleMissingKey(String key) {
		throw new IllegalStateException(key + " key must not be null");
	}

	public void DecoratorSpringRedis() {
		RefreshSpringRedisData();
	}

	public Map<String, Object> RefreshSpringRedisData(){
		this.delta.put(CREATION_TIME_KEY, this.getStartTimestamp().getTime());
		int ITimeOut = (int)this.getTimeout();
		this.delta.put(MAX_INACTIVE_INTERVAL_KEY,ITimeOut/1000);
		this.delta.put(LAST_ACCESSED_TIME_KEY, this.getLastAccessTime().getTime());
		getAttributeKeys().forEach((attributeName) -> {
			Object obj = this.getAttribute(attributeName);
			if(obj instanceof SimplePrincipalCollection)
			{
				Class simplePrincipalClass = obj.getClass();
				try {
					Field realmPrincipalsField = simplePrincipalClass.getDeclaredField("realmPrincipals");
					realmPrincipalsField.setAccessible(true);
					Map<String, Set> realmPrincipalsMap = (Map<String, Set>) realmPrincipalsField.get(obj);
					if(realmPrincipalsMap!=null){
						this.delta.put(ATTRIBUTE_PREFIX+attributeName+".SimplePrincipalCollection",realmPrincipalsMap);
					}else{
						this.delta.put(ATTRIBUTE_PREFIX+attributeName,this.getAttribute(attributeName));
					}
				} catch (NoSuchFieldException | IllegalAccessException e) {
					e.printStackTrace();
				}

			}else{
				this.delta.put(ATTRIBUTE_PREFIX+attributeName,this.getAttribute(attributeName));
			}
		});
		return delta;
	}
}
