package com.oil.service.oracle.basedata;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javassist.expr.NewArray;

import javax.annotation.Resource;

import net.sf.ehcache.pool.Size;

import org.springframework.stereotype.Service;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.oil.dao.AttributesDAO;
import com.oil.dao.DataclassDAO;
import com.oil.dao.InstanceDAO;
import com.oil.dao.OrganizationDAO;
import com.oil.dao.ValueDAO;
import com.oil.dao.oracle.AttributesDAO_Plus;
import com.oil.dao.oracle.InstanceDAO_Plus;
import com.oil.dao.oracle.ValueDAO_Plus;
import com.oil.entity.Attributes;
import com.oil.entity.Dataclass;
import com.oil.entity.Instance;
import com.oil.entity.Organization;
import com.oil.entity.Value;
import com.oil.model.AttibuteModel;
import com.sun.tools.corba.se.idl.constExpr.Not;

@SuppressWarnings("deprecation")
@Transactional
@Service
public class BaseValueServiceImpl implements BaseValueService{
	@Resource 
	private DataclassDAO dataclassDAO; 
	@Resource
	private AttributesDAO attributeDAO;
	@Resource
	private ValueDAO valueDAO;
	@Resource
	private InstanceDAO instanceDAO;
	@Resource
	private AttributesDAO_Plus attributesDAO_Plus;
	@Resource
	private OrganizationDAO organizationDAO;
	@Resource
	private InstanceDAO_Plus instanceDAO_Plus;
	@Resource
	private ValueDAO_Plus valueDAO_Plus;

	@Override
	public Dataclass testDc(Long dcid){
		return dataclassDAO.findById(dcid);
	}
	//根据类id和组织机构id查询值
	@Override
	public String getValuesByOrgsAndClass(List<Long> oids, Long dcid) {
		 JSONArray ja=new JSONArray();
		for (Long oid : oids) {
			Organization organization=organizationDAO.findById(oid);
			List<Instance>instances=instanceDAO_Plus.findInstancesByOrgAndClass(organization.getInstance().getId(), dcid);
			for (Instance instance : instances) {
				Set<Value>values=instance.getValues();
				JSONObject jo=new JSONObject();
				for (Value v : values) {
					JSONObject vjo=new JSONObject();
					vjo.put("ValueId", v.getId());
					vjo.put("Value", v.getValue());
					jo.put(v.getAttributes().getAttributeName(), vjo);
				}
				jo.put("instanceId", instance.getId());
				jo.put("organizationId", organization.getId());
				jo.put("organizationName", organization.getName());
				jo.put("code3265", organization.getCode3265());
				ja.add(jo);
			}					
		}
//		System.out.println(ja.toJSONString());
		return ja.toString();
		
	}

	//根据类id查询该类的属性
	@Override
	public List<AttibuteModel> getAttributesByClass(Long dcid) {
		List<Attributes>temList= attributesDAO_Plus.findAttributesByClass(dcid);
		// 转换model
		List<AttibuteModel> resultList = new ArrayList<AttibuteModel>();
		for(Attributes attributes : temList) {
			resultList.add(attributes.toAttibuteModel());
		}
		return resultList;

//		if(aList==null){
//			System.out.println("null");
//		}else{
//			System.out.println("not null");
//			System.out.println(aList.size());
//			for (Attributes attributes : aList) {
//				System.out.println("enter for");
//			}
//		}
	}
	//基础数据值--删
	@Override
	public String deleteBaseValueByInsId(Long insid) {
		Instance instance=instanceDAO.findById(insid);
		//置空了还是会被删掉
//		Set<Value>values=instance.getValues();
//		for (Value value : values) {
//			value.setInstance(null);
//			valueDAO.getCurrentSession().flush();
//		}
//		删除操作
		if(instance.getOrganizations().size()>0){
			return "这条数据不能被删除";
		};
		instanceDAO.delete(instance);
//		instanceDAO.getCurrentSession().flush();
		return "success";
	}
	//基础数据值--增
	//增，"[{"AttributesId":1,"value":"32650476","note":""},{"AttributesId":2,"value":"abc","note":""}]"
	@Override
	public String addBaseValueByAttribute(Long oid,Long dcid,String jaList) {
		 List<String> jastrs = JSONArray.parseArray(jaList,String.class); 
		for (String jaStr : jastrs) {
			//先插入一个instance
			Instance instance=new Instance();
			instance.setOrganizationId(organizationDAO.findById(oid).getInstance().getId());
			instance.setDataclass(dataclassDAO.findById(dcid));
			instanceDAO.save(instance);
			//然后添加instance包含的value
			JSONArray ja=JSONArray.parseArray(jaStr);
			for (Iterator iterator = ja.iterator(); iterator.hasNext();) {
				JSONObject jo=(JSONObject) iterator.next();
				Long aid =Long.parseLong(jo.getString("AttributesId"));
				String valueStr= jo.getString("value");
				String noteStr=jo.getString("note");
				Value value=new Value();
				value.setAttributes(attributeDAO.findById(aid));
				value.setInstance(instance);
				value.setValue(valueStr);
				value.setNote(noteStr);
				valueDAO.save(value);
			}

		}
		return "success";
	}
	//基础数据值--改
	//改{"AttributesId":1,"ValueId":1,"Value":"32650476","note":""}
	@Override
	public String EditBaseValueByValueId(Long insid,String jsonListStr) {
		List<String> jsonList = JSONArray.parseArray(jsonListStr,String.class); 
		for (String json : jsonList) {
			JSONObject jo=JSONObject.parseObject(json);
			Long vid=Long.parseLong(jo.getString("ValueId"));

			String valueStr=jo.getString("Value");
			String noteStr=jo.getString("note");
			if(vid==-1){
				Value value=new Value();
				value.setValue(valueStr);
				value.setNote(noteStr);
				Instance instance=instanceDAO.findById(insid);
				value.setInstance(instance);
				Long attributesId=Long.parseLong(jo.getString("AttributesId"));
				value.setAttributes(attributeDAO.findById(attributesId));
				valueDAO.save(value);
			}else{
				Value value=valueDAO.findById(vid);
				value.setValue(valueStr);
				value.setNote(noteStr);
//				valueDAO.update(value);
				valueDAO.flush();
			}

		}
		return "success";
	}
	//返回所有没有填写的instance信息
	@Override
	public String getUnFilledInstances() {
		JSONArray ja=new JSONArray();
		List<Instance>insList=instanceDAO.findAll();
		for (Instance instance : insList) {
			if(instance.getValues().size()==0){
				JSONObject jo=new JSONObject();
				jo.put("InstanceId", instance.getId());
				jo.put("DataclassId", instance.getDataclass().getId());
				jo.put("DataclassName", instance.getDataclass().getClassName());
				Set<Organization> oList=instanceDAO.findById(instance.getOrganizationId()).getOrganizations();
				if(oList.size()!=1){
					return "数据存储逻辑错误";
				}
				Organization o=new Organization();
				for (Organization organization : oList) {
					o=organization;
				}
				jo.put("OrganizationId",o.getId());
				jo.put("OrganizationName", o.getName());
				ja.add(jo);
			}
		}
//		System.out.println(ja.toJSONString());
		return ja.toJSONString();
	}
	@Override
	public String takeBaseValueByOrgsAndClasses(List<Long> oids, List<Long> dcids) {
		JSONArray jsonArray=new JSONArray();
		for (Long oid : oids) {
			Organization organization=organizationDAO.findById(oid);
			Long oinsid=organization.getInstance().getId();
			JSONObject oneOrgJson=new JSONObject();
			oneOrgJson.put("OrganizationId", oid);
			oneOrgJson.put("Code3265", organization.getCode3265());
			oneOrgJson.put("name", organization.getName());
			JSONArray oneClassJArray=new JSONArray();
			for (Long dcid : dcids) {
				JSONObject oneClassJson=new JSONObject();
				oneClassJson.put("DataclassId", dcid);
				oneClassJson.put("DataclassName", dataclassDAO.findById(dcid).getClassName());
				JSONArray oneInstanceJArray=new JSONArray();
				List<Instance>instances=instanceDAO_Plus.findInstancesByOrgAndClass(oinsid, dcid);
				for (Instance instance : instances) {
					Set<Value>values=instance.getValues();
//					Set<Value>values=instanceDAO_Plus.getValues(instance.getId());
//					System.out.println(valueSet.size());
//					List<Value>values=new ArrayList<Value>();
//					for (Value value : valueSet) {
//						Value valueInList=valueDAO.findById(value.getId());
//						values.add(valueInList);
//					}
					JSONObject oneInstancejson=new JSONObject();
					Set<Attributes>aList=instance.getDataclass().getAttributeses();
					for (Attributes a : aList) {
						oneInstancejson.put(a.getAttributeName(), new JSONObject());
					}
					for (Value v : values) {
						JSONObject vjo=new JSONObject();
						vjo.put("ValueId", v.getId());
						vjo.put("Value", v.getValue());
						oneInstancejson.put(v.getAttributes().getAttributeName(), vjo);
					}
					oneInstancejson.put("instanceId", instance.getId());
					oneInstanceJArray.add(oneInstancejson);
				}//for (Instance instance : instances)
				oneClassJson.put("thisTypeDataAllInstanceValue", oneInstanceJArray);
				oneClassJArray.add(oneClassJson);
			}//for (Long dcid : dcids)
			oneOrgJson.put("thisDepartmentAllData", oneClassJArray);
			jsonArray.add(oneOrgJson);
		}
//		System.out.println(jsonArray);
		return jsonArray.toJSONString();
	}
	@Override
	public String getUnFilledDataClasses() {
		JSONArray resultArray = new JSONArray();
		Set<Dataclass> dcSet = new HashSet<Dataclass>();
		List<Attributes>aList=attributeDAO.findAll();
		for (Attributes a : aList) {
			Dataclass dc=a.getDataclass();
			dcSet.add(dc);
		}
		
		for (Dataclass dataclass : dcSet) {
			JSONObject result = new JSONObject();
			if (dataclass.getInstances().size() == 0) {
				result.put("DataclassId", dataclass.getId());
				result.put("DataclassName", dataclass.getClassName());
				resultArray.add(result);
			}
		}
		return resultArray.toJSONString();
	}
	
//	@Override
//	public String EditBaseValueByAttributeAndInstance(Long insid,List<String> jsonList) {		
//		return null;
//	}


}
