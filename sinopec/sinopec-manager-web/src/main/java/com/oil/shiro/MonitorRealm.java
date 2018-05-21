package com.oil.shiro;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.dubbo.config.spring.ServiceBean;
import com.oil.entity.AccountRole;
import com.oil.entity.Role;
import com.oil.service.shiro.ShiroService;





@Component
public class MonitorRealm extends AuthorizingRealm {
	/*
	 * @Autowired UserService userService;
	 * 
	 * @Autowired RoleService roleService;
	 * 
	 * @Autowired LoginLogService loginLogService;
	 */

@Resource
ShiroService shiroService;


	public void setShiroService(ShiroService shiroService) {
	this.shiroService = shiroService;
}

	public MonitorRealm() {
		super();

	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		/* 这里编写授权代码 */
//		Set<String> roleNames = new HashSet<String>();
//	    Set<String> permissions = new HashSet<String>();
//	    roleNames.add("admin");
//	    permissions.add("user.do?myjsp");
//	    permissions.add("login.do?main");
//	    permissions.add("login.do?logout");
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		String loginName = (String)principals.fromRealm(getName()).iterator().next();
		Set<String> roles=shiroService.getRolesByAccountName(loginName);
		Set<String> permissions=shiroService.getPermissionsByAccountName(loginName);
		info.setRoles(roles);
	    info.setStringPermissions(permissions);
		return info;

	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		/* 这里编写认证代码 */
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		String account =token.getUsername();
//		WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
//		ShiroService shiroService=wac.getBean("shiroService",ShiroService.class);
//		TestServiceBean tb=(TestServiceBean) wac.getBean("testServiceBean");
//		TestServiceBean tb=(TestServiceBean) ServiceBean.getSpringContext().getBean("testServiceBean");
//		tb.lalala();
		String password=shiroService.getPasswordFromAccountName(account);
//		System.out.println(password);
		return new SimpleAuthenticationInfo(account,
				password, getName());

//		return new SimpleAuthenticationInfo("admin",
//				"admin", getName());


	}

	public void clearCachedAuthorizationInfo(String principal) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(
				principal, getName());
		clearCachedAuthorizationInfo(principals);
	}
	//清除用户权限缓存
	public void clearAC() {
		this.clearCache(SecurityUtils.getSubject().getPrincipals());
	}

}
