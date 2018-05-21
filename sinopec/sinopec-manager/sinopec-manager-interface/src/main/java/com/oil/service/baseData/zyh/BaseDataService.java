package com.oil.service.baseData.zyh;

import java.util.List;

import com.oil.entity.Attributes;
import com.oil.entity.Dataclass;
import com.oil.model.AttibuteModel;
import com.oil.model.DataClassDetailModel;
import com.oil.model.DataClassZRreeNodeModel;

/**
 * 
 * 基础数据和属性 
 * @author: zyh 
 * @date:   2018年5月15日 下午3:05:49   
 *
 */
public interface BaseDataService {
	// 查询所有的基础数据类型（ID,PID,name）
	public List<DataClassZRreeNodeModel> getALLDataClass();
	// 根据级别查询基础数据类型列表（ID,PID,name）
	public List<DataClassZRreeNodeModel> getDataClassByLevel(Integer level);
	// 根据PID查询基础数据类型列表（ID,PID,name）
	public List<DataClassZRreeNodeModel> getDataClassByPID(Integer PID);
	// 根据ID查询基础数据类型详细信息
	public DataClassDetailModel getDataClassDetailByID(Integer ID);
	// 更新基础数据类型
	public boolean updateDataClass(DataClassDetailModel dataclass);
	// 新增一个基础数据类型
	public Long insertDataClass(DataClassDetailModel dataclass);
	// 根据ID删除一个基础数据类型
	public boolean deleteDataClassByID(Long ID);
	
	// 根据基础数据类型ID获取其属性列表，分页显示
	public List<AttibuteModel> getAttributeListByDataClassIDPaging(Long DataClassID, Integer pageIndex, Integer pageSize);
	// 根据基础数据ID获取所有属性列表，不分页
	public List<AttibuteModel> getAttributeListByDataClassID(Long DataClassID);
	// 更新一个属性
	public boolean updateAttribute(AttibuteModel attributes);
	// 新增一个属性
	public Long insertAttribute(AttibuteModel attributes);
	// 根据属性ID删除一条属性
	public boolean deleteAttributeByID(Long attributesID);
}
