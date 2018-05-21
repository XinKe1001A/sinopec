package com.oil.dao.oracle;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oil.entity.Attributes;
import com.oil.entity.Value;

@Transactional
@Repository
public class ValueDAO_Plus {
@Resource
BaseDAO<Value> baseDAO;



public List<Value> findValueByAttAndIns(Long aid, Long iid){
	String hql="from Value v where v.attributes.id=? and v.instance.id=?";
	return baseDAO.find(hql, new Object[]{aid,iid});
}
//public List<Value> findValueByOrgAndClass(Long oinsid, Long dcid){
//	String hql="from Value v where "
//			+ "v.instance.id in (select i.id from Instance i where i.organizationId=? and i.dataclass.id=?)";
//	return baseDAO.find(hql, new Object[]{dcid,oinsid,dcid});
//}
public List<Value> findValueByInsanceAndAttribute(Long insid,Long aid){
	String hql="from Value v where v.attributes.id = ? and v.instance.id = ?";
	return baseDAO.find(hql,new Object[]{aid,insid});
}

}
