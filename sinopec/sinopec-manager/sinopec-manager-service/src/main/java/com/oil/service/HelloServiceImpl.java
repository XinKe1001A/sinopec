package com.oil.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.oil.dao.TestDAO;
import com.oil.entity.*;
@Service
public class HelloServiceImpl implements HelloService{
	@Resource
	TestDAO testDAO;

public String TestService(){
//	System.out.println("test");
	Test test=testDAO.findById(1);
	System.out.println(test.getName());
	return test.getName();
}
}
