package com.oil.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.oil.service.userconfig.UserConfigService;
import com.oil.shiro.MonitorRealm;



@Controller
@RequestMapping(value ="login")
public class LoginController {
	@Resource
	UserConfigService userConfigService;
	@RequestMapping(params = "main")
	public ModelAndView login(String j_username,String j_password,HttpSession session, HttpServletRequest request) {


		
		ModelAndView modelView = new ModelAndView();
		Subject currentUser = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(
				j_username, j_password);
		token.setRememberMe(true);

		try {
			currentUser.login(token);
		} catch (AuthenticationException e) {
			modelView.addObject("message", "您的凭证有误");
			modelView.setViewName("login");
			e.printStackTrace();
			
		}
		if(currentUser.isAuthenticated()){
			JSONObject userInfo=JSONObject.parseObject(userConfigService.getAccountByAccountName(j_username));
			session.setAttribute("UserName", userInfo.get("name"));
			session.setAttribute("OrganizationId", userInfo.get("OrganizationId"));
			
			modelView.setViewName("home");
		}else{
			modelView.addObject("message", "您的凭证有误");
			modelView.setViewName("login");
		}
		return modelView;
	}
	@RequestMapping(params = "logout")
	public String logout() {
		Subject currentUser = SecurityUtils.getSubject();
		try {
			currentUser.logout();
		} catch (AuthenticationException e) {
			e.printStackTrace();
		}
		   			RealmSecurityManager rsm=(RealmSecurityManager)SecurityUtils.getSecurityManager();
		   			MonitorRealm monitorRealm=(MonitorRealm)rsm.getRealms().iterator().next();
		   			monitorRealm.clearAC();
		return "login";
	}
}
