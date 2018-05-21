package com.oil.controller.oracle;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oil.entity.Attributes;
import com.oil.model.AttibuteModel;
import com.oil.service.HelloService;
import com.oil.service.oracle.basedata.BaseValueService;
import com.oil.service.oracle.basedata.OrganizationService;
import com.oil.service.shiro.ShiroService;

@Controller
@RequestMapping(produces={"text/html;charset=UTF-8"})
public class BaseValueController {
	@Resource
	private OrganizationService organizationService;
	@Resource
	private BaseValueService baseValueService;
//	@Resource
//	private ShiroService shiroService;
	
//	@RequestMapping("/fuck")
//	@ResponseBody
//	public String TestController2(){
//		return shiroService.getPasswordFromAccountName("admin");
//	}

	@RequestMapping("/getOrgTree")
	@ResponseBody
	public String TestController(){
		return organizationService.GetOrganizationTree();
//		return "叶俊";
	}
	@RequestMapping("/getAttributesByClass")
	@ResponseBody
	public List<AttibuteModel>getAttributesByClass(long dcid){
		return baseValueService.getAttributesByClass(dcid);
	}
	@RequestMapping("/getValuesByOrgsAndClass")
	@ResponseBody
	public String getValuesByOrgsAndClass(@RequestParam(value = "oids[]")List<Long> oids, Long dcid){
		return baseValueService.getValuesByOrgsAndClass(oids, dcid);
	}
	@RequestMapping("/add")
	@ResponseBody
	public String add(Long oid, Long dcid, String jaList){
		
		return baseValueService.addBaseValueByAttribute(oid,dcid,jaList);
	}
	@RequestMapping("/delete")
	@ResponseBody
	public String delete(Long insid){
		return baseValueService.deleteBaseValueByInsId(insid);
	}
	@RequestMapping("/edit")
	@ResponseBody
	public String edit(Long insid,String jsonListStr){
		return baseValueService.EditBaseValueByValueId(insid,jsonListStr);
	}
	@RequestMapping("/unfill")
	@ResponseBody
	public String findUnFilledDataClasses(){
		return baseValueService.getUnFilledDataClasses();
	}
	@RequestMapping("/take")
	@ResponseBody
	public String takeBaseValue(@RequestParam(value = "oids[]")List<Long> oids,@RequestParam(value = "dcids[]")List<Long>dcids){
		return baseValueService.takeBaseValueByOrgsAndClasses(oids, dcids);
	}
}
