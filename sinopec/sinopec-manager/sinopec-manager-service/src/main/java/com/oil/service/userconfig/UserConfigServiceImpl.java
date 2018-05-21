package com.oil.service.userconfig;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.oil.dao.AccountDAO;
import com.oil.entity.Account;
import com.oil.entity.Organization;
@Service
public class UserConfigServiceImpl implements UserConfigService{
@Resource
AccountDAO accountDAO;
	@Override
	public String getAccountByAccountName(String accountName) {
		Account account=accountDAO.findByAccountName(accountName).get(0);
		String accountStr=JSONObject.toJSONString(account);
		JSONObject jo=JSON.parseObject(accountStr);
		Organization organization=account.getOrganization();
		if(organization==null)return accountStr;
		jo.put("OrganizationId", organization.getId());
		jo.put("code3265", organization.getCode3265());
		jo.put("OrganizationName", organization.getName());
//		System.out.println(jo.toJSONString());
		return jo.toJSONString();
	}

}
