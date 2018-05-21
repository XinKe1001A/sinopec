package com.oil.service.shiro;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.oil.dao.AccountDAO;
import com.oil.dao.RoleDAO;
import com.oil.entity.Account;
import com.oil.entity.AccountRole;
import com.oil.entity.Role;

@Service
public class ShiroServiceImpl implements ShiroService{
	@Resource
	AccountDAO accountDAO;
	@Resource
	RoleDAO roleDAO;

	@Override
	public String getPasswordFromAccountName(String account) {
		
		List<Account> aList =accountDAO.findByAccountName(account);
		if(aList.size()==0)return "GiGi我哩giaogiao！啵啵啵";
		else return aList.get(0).getPassword();
	}

	@Override
	public Set<String> getRolesByAccountName(String accountName) {
		Set<String> result=new HashSet<String>();
		List<Account> accounts=accountDAO.findByAccountName(accountName);
		if (accounts.size()>0) {
			Set<AccountRole>ars=accounts.get(0).getAccountRoles();
			for (AccountRole accountRole : ars) {
				result.add(accountRole.getRole().getName());
			}
			return result;
		}
		return null;
	}

	@Override
	public Set<String> getPermissionsByAccountName(String accountName) {
		Set<String> result=new HashSet<String>();
		List<Account> accounts=accountDAO.findByAccountName(accountName);
		if (accounts.size()>0) {
			Set<AccountRole>ars=accounts.get(0).getAccountRoles();
			for (AccountRole accountRole : ars) {
				String permissionsStr=accountRole.getRole().getPermissions();
				String[]permissions=permissionsStr.split(",");
				for (String permission : permissions) {
					result.add(permission);
				}
			}
			return result;
		}
		return null;
	}

}
