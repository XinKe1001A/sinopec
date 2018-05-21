package com.oil.controller.baseData.zhy;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oil.model.AttibuteModel;
import com.oil.model.DataClassDetailModel;
import com.oil.model.DataClassZRreeNodeModel;
import com.oil.service.baseData.zyh.BaseDataService;

/**
 * 
 * 基础数据类型   
 * @author: zyh 
 * @date:   2018年5月15日 上午10:23:40   
 *
 */
@Controller
public class DateClassController {
	@Resource
	private BaseDataService baseDataService;
	/**
	 * 
	 * 获取所有的基础数据类型
	 * 用于构建zTree,每个基础数据只包括ID，PID，name
	 * @return
	 */
	@RequestMapping("/getALLDataClass")
	@ResponseBody
	public List<DataClassZRreeNodeModel> getALLDataClass() {
		return baseDataService.getALLDataClass();
	}
	
	/**
	 * 
	 * 根据基础数据级别获取该级别所有的基础数据类
	 * 用于构建zTree,每个基础数据只包括ID，PID，name
	 * @param level 基础数据级别
	 * @return
	 */
	@RequestMapping("/getDataClassByLevel")
	@ResponseBody
	public List<DataClassZRreeNodeModel> getDataClassByLevel(Integer level) {
		return baseDataService.getDataClassByLevel(new Integer(level));
	}
	
	/**
	 * 
	 * 根据PID获取所有子基础数据类型
	 * 用于构建zTree,每个基础数据只包括ID，PID，name
	 * @param PID
	 * @return
	 */
	@RequestMapping("/getDataClassByPID")
	@ResponseBody
	public List<DataClassZRreeNodeModel> getDataClassByPID(Integer PID) {
		return baseDataService.getDataClassByPID(PID);
	}
	
	/**
	 * 
	 * 根据ID获取基础数据详细信息
	 * 
	 * @param ID
	 * @return
	 */
	@RequestMapping("/getDataClassDetailByID")
	@ResponseBody
	public DataClassDetailModel getDataClassDetailByID(Integer ID) {
		return baseDataService.getDataClassDetailByID(ID);
	}
	
	/**
	 * 
	 * 修改基础数据类型
	 * 用DataClassDetailModel接受修改后的基础数据类型
	 * 前端能够修改的字段：
	 * 	name
	 * 	responsibilityDepartment
	 * 	priority
	 * 	note
	 * 
	 * 不能修改的字段：
	 * 	ID
	 * 	PID
	 *  level
	 *  
	 * 前端不需要修改，在后端更新字段
	 * 	creationTime
	 * 	creationAccountName
	 * 
	 * @param dataClassDetail
	 * @return
	 */
	@RequestMapping("/updateDataClass")
	@ResponseBody
	public boolean updateDataClass(DataClassDetailModel dataclass) {
//		System.out.println("updateDataClass");
//		System.out.println(dataclass);
		return baseDataService.updateDataClass(dataclass);
	}
	
	/**
	 * 
	 * 新增基础数据类型
	 * 前端需要传值：
	 * 	PID
	 * 	priority 优先级，越小优先级越高
	 * 	name
	 * 	responsibilityDepartment 责任部门
	 * 	note
	 * 
	 * @param dataClassDetail
	 * @return 新增基础数据的ID
	 */
	@RequestMapping("/insertDataClass")
	@ResponseBody
	public Long insertDataClass(DataClassDetailModel dataclass) {
//		System.out.println("insertDataClass");
//		System.out.println(dataclass);
		return baseDataService.insertDataClass(dataclass);
	}
	
	/**
	 * 
	 * 根据ID删除基础数据
	 * 
	 * @param ID
	 * @return
	 */
	@RequestMapping("/deleteDataClassByID")
	@ResponseBody
	public boolean deleteDataClassByID(Long ID) {
		//System.out.println("deleteDataClassByID " + ID);
		return baseDataService.deleteDataClassByID(ID);
	}
	
	/**
	 * 
	 * 根据基础数据ID分页信息获取属性列表
	 * 
	 * @param DataClassID 基础数据ID
	 * @param pageIndex 分页信息
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/getAttributeListByDataClassIDPaging")
	@ResponseBody
	public List<AttibuteModel> getAttributeListByDataClassIDPaging(Long DataClassID, Integer pageIndex, Integer pageSize) {
		return baseDataService.getAttributeListByDataClassIDPaging(DataClassID, pageIndex, pageSize);
	}
	
	/**
	 * 
	 * 根据基础数据ID获取所有属性列表，不分页
	 * 
	 * @param DataClassID
	 * @return
	 */
	@RequestMapping("/getAttributeListByDataClassID")
	@ResponseBody
	public List<AttibuteModel> getAttributeListByDataClassID(Long DataClassID) {
		return baseDataService.getAttributeListByDataClassID(DataClassID);
	}
	
	/**
	 * 
	 * 更新一个属性
	 * 用AttibuteModel接收属性基础数据
	 * * 前端需要传值：
	 * 	ID
	 * 	DataclassId
	 * 	name
	 * 	priority
	 * 	note
	 * 
	 * @param attributes
	 * @return
	 */
	@RequestMapping("/updateAttribute")
	@ResponseBody
	public boolean updateAttribute(AttibuteModel attributes) {
//		System.out.println("updateAttribute");
//		System.out.println(attributes);
		return baseDataService.updateAttribute(attributes);
	}

	/**
	 * 
	 * 新增一条属性
	 * 
	 * 前端需要传值：
	 * 	DataclassId
	 * 	name
	 * 	priority
	 * 	note
	 * 
	 * @param attributes
	 * @return 新增基础数据的ID
	 */
	@RequestMapping("/insertAttribute")
	@ResponseBody
	public Long insertAttribute(AttibuteModel attributes) {
//		System.out.println("insertAttribute");
//		System.out.println(attributes.toString());
		return baseDataService.insertAttribute(attributes);
	}
	
	/**
	 * 
	 * 根据ID删除一条属性
	 * 
	 * @param attributesID
	 * @return
	 */
	@RequestMapping("/deleteAttributeByID")
	@ResponseBody
	public boolean deleteAttributeByID(Long attributesID) {
		//System.out.println("deleteAttributeByID " + attributesID);
		return baseDataService.deleteAttributeByID(attributesID);
	}
}
