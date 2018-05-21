package com.oil.service.test;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.oil.dao.OrganizationDAO;
import com.oil.dao.oracle.AttributesDAO_Plus;
import com.oil.dao.oracle.ValueDAO_Plus;
import com.oil.entity.Attributes;
import com.oil.entity.Dataclass;
import com.oil.entity.Value;
import com.oil.service.HelloService;
import com.oil.service.HelloServiceImpl;
import com.oil.service.oracle.basedata.OrganizationService;
import com.oil.service.oracle.basedata.OrganizationServiceImpl;
import com.oil.service.oracle.basedata.BaseValueService;
import com.oil.service.userconfig.UserConfigService;


/*
 * spring整合Junit4单元测试基类，
 * 其他类实现该类可以省略一些注解配置。
 * */
//使用junit4进行单元测试
@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
//加载配置文件，可以指定多个配置文件，locations指定的是一个数组
@ContextConfiguration(locations={"classpath:applicationContext-dao.xml","classpath:applicationContext-service.xml","classpath:applicationContext-tx.xml"})
//启动事务控制
@Transactional
//配置事务管理器，同时指定自动回滚
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)
public class TestClass {
	
//	OrganizationService organizationService;
	@Resource(name="helloServiceImpl")
	HelloService helloService;

@Resource
AttributesDAO_Plus attributesDAO_Plus;
@Resource
TestService testService;
@Resource(name="baseValueServiceImpl")
BaseValueService valueService;
@Resource
ValueDAO_Plus valueDAO_Plus;
@Resource(name="userConfigServiceImpl")
UserConfigService userConfigService;
@Test
public void testMethod(){
	System.out.println("王琨是我大哥");
//	testService.testMethod();
	String json="{\"A\":\"1\",\"B\":\"3\"}";
	JSONObject jo=JSONObject.parseObject(json);
	System.out.println(jo.getString("A"));
//	Long aid =Long.parseLong((String) jo.get("A"));
//	System.out.println(aid);
}
@Test
public void testGetOrganizationTree() {
	helloService.TestService();
}
@Test 
public void testdao(){
	List<Attributes> as=attributesDAO_Plus.findAttributesByClass((long) 1);
//	for (Attributes a : as) {
//		System.out.println(a.getAttributeName());
//	}
//	List<Value>vs=valueDAO_Plus.findValueByOrgAndClass((long)1, (long)1);
//	for (Value value : vs) {
//		System.out.print(value.getAttributes().getAttributeName()+":");
//		System.out.println(value.getValue());
//	}
//	List<Value> values=valueDAO_Plus.findValueByAttAndIns((long)1, (long)1);
//	System.out.println(values.get(0).getValue());
}
@Test
public void testMyService(){
//	Dataclass d=valueService.testDc((long) 1);
//	System.out.println(d.getClassName());
//	List<Long>list=new ArrayList();
//	list.add((long) 1);
//	valueService.getValuesByOrgsAndClass(list, (long)1);
//	valueService.getAttributesByClass((long) 1);
//	valueService.getValuesByOrgsAndClass(list, (long)12);
	
//	List<String>jaList=new ArrayList<String>();
//	JSONArray ja1=new JSONArray();
//	JSONObject json1=new JSONObject();
//	json1.put("AttributesId", 26);
//	json1.put("value","雕牌");
//	json1.put("note", "不伤手");
//	JSONObject json2=new JSONObject();
//	json2.put("AttributesId", 26);
//	json2.put("value","脑白金");
//	json2.put("note", "年轻态健康品");
//	
//	ja1.add(json1);
//	JSONArray ja2=new JSONArray();
//
//	ja2.add(json2);
//	jaList.add(ja1.toString());
//	jaList.add(ja2.toString());
//	System.out.println(ja1.toString());
//	valueService.addBaseValueByAttribute((long) 1, (long) 12, jaList.toString());
	
//	List<String>jsonList=new ArrayList<String>();
//	JSONObject json1=new JSONObject();
//	json1.put("AttributesId", 26);
//	json1.put("ValueId", 30);
//	json1.put("Value","琨哥牌");
//	json1.put("note", "新备注1");
//	JSONObject json2=new JSONObject();
//	json2.put("AttributesId", 27);
//	json2.put("ValueId", 31);
//	json2.put("Value","abc001");
//	json2.put("note", "新备注2");
//	jsonList.add(json1.toJSONString());
//	jsonList.add(json2.toJSONString());
//	valueService.EditBaseValueByValueId((long) 309,jsonList.toString());
	
//	valueService.deleteBaseValueByInsId((long) 308);
	
//	valueService.getUnFilledInstances();
	
//	List<Long>list1=new ArrayList();
//	list1.add((long) 1);
//	List<Long>list2=new ArrayList();
//	//list2.add((long) 1);
//	list2.add((long) 12);
////	list2.add((long) 309);
//	System.out.println(	valueService.takeBaseValueByOrgsAndClasses(list1, list2));
	
//	System.out.println(valueService.getUnFilledDataClasses());
	userConfigService.getAccountByAccountName("yejun");
}
}
