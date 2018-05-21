package com.oil.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oil.service.HelloService;

@Controller
@RequestMapping(produces={"text/html;charset=UTF-8"})
public class HelloController {
	@Resource
	private HelloService helloService;
	@RequestMapping("/hello")
	public String TestHelloController(){
		System.out.println("hello world1");
		return "v/world";
	}
	@RequestMapping("/test")
	@ResponseBody
	public String TestController(){
//		System.out.println(helloService.TestService());
		return helloService.TestService();
	}
	@RequestMapping("/method")
	public String Method(){
		return "view/test";
	}
	
}
