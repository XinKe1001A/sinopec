package com.oil.dao.oracle;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oil.entity.Attributes;
import com.oil.entity.Instance;
import com.oil.entity.Value;

@Transactional
@Repository
public class InstanceDAO_Plus {
	@Resource
	BaseDAO<Instance> baseDAO;
	public List<Instance>findInstancesByOrgAndClass(long oinsid,long dcid){
		String hql="from Instance i where i.organizationId=? and i.dataclass.id=?";
		return baseDAO.find(hql,new Object[]{oinsid,dcid});
	}
	public Set<Value>getValues(Long insid){
		String hql="from Instance i where i.id=?";
		return baseDAO.find(hql,new Object[]{insid}).get(0).getValues();
	}
	
}
