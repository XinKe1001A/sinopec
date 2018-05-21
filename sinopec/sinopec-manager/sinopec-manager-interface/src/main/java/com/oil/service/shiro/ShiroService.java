package com.oil.service.shiro;

import java.util.List;
import java.util.Set;

import com.oil.entity.Account;
import com.oil.entity.AccountRole;
import com.oil.entity.Role;

public interface ShiroService {
public String getPasswordFromAccountName(String account);
public Set<String> getRolesByAccountName(String accountName);
public Set<String> getPermissionsByAccountName(String accountName);
}
