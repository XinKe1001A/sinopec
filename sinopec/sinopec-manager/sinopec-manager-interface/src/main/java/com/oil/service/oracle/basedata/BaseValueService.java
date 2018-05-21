package com.oil.service.oracle.basedata;

import java.util.List;

import com.oil.entity.Attributes;
import com.oil.entity.Dataclass;
import com.oil.model.AttibuteModel;



public interface BaseValueService {
	//和周昱航写重了
	public List<AttibuteModel> getAttributesByClass(Long dcid);
	//查
public String getValuesByOrgsAndClass(List<Long> oids,Long dcid);
//删
public String deleteBaseValueByInsId(Long insid);
//增，"[{"AttributesId":1,"value":"32650476","note":""},{"AttributesId":2,"value":"第一加油站","note":""}]"
public String addBaseValueByAttribute(Long oid,Long dcid,String jsonList);
//改{"AttributesId":1,"ValueId":1,"Value":"32650476","note":""}
public String EditBaseValueByValueId(Long insid,String jsonListStr);

//调取数据
public String takeBaseValueByOrgsAndClasses(List<Long>oids,List<Long>dcids);

public String getUnFilledDataClasses();

public String getUnFilledInstances();


//改{"AttributesId":1,"value":"32650476","note":""}
//public String EditBaseValueByAttributeAndInstance(Long insid,List<String> jsonList);
//测试
public Dataclass testDc(Long dcid);
}
