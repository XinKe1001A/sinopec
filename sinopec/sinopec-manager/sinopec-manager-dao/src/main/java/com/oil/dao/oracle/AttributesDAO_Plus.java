package com.oil.dao.oracle;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oil.entity.Attributes;
import com.oil.entity.Value;

@Transactional
@Repository
public class AttributesDAO_Plus {
	@Resource
	BaseDAO<Attributes> baseDAO;
	public List<Attributes> findAttributesByClass(Long dcid){
		String hql="from Attributes a where a.dataclass.id=? order by priority";
		return baseDAO.find(hql,new Object[]{dcid});
	}

}
