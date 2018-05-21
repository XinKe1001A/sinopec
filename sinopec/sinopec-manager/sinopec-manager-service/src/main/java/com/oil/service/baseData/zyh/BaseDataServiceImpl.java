package com.oil.service.baseData.zyh;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.oil.dao.AccountDAO;
import com.oil.dao.AttributesDAO;
import com.oil.dao.DataclassDAO;
import com.oil.entity.Attributes;
import com.oil.entity.Dataclass;
import com.oil.model.AttibuteModel;
import com.oil.model.DataClassDetailModel;
import com.oil.model.DataClassZRreeNodeModel;

/**
 * 
 * 基础数据和属性    
 * @author: zyh 
 * @date:   2018年5月15日 下午3:06:07   
 *
 */
@Service
public class BaseDataServiceImpl implements BaseDataService{
	@Resource
	DataclassDAO dataclassDAO;
	
	@Resource
	AccountDAO accountDAO;
	
	@Resource
	AttributesDAO attributesDAO;
	
	@Override
	public List<DataClassZRreeNodeModel> getALLDataClass() {
		List<Dataclass> DataclassList = dataclassDAO.findAll();
		List<DataClassZRreeNodeModel> temList = new ArrayList<DataClassZRreeNodeModel>();
		
		// 对Proirity排序
		Collections.sort(DataclassList, new Comparator<Dataclass>() {
			@Override
			public int compare(Dataclass o1, Dataclass o2) {
				return o1.getProirity() - o2.getProirity();
			}

		});
		//temList.add(new DataClassZRreeNodeModel(0+"", null, "基础数据"));
		// 转换model
		for(Dataclass dataclass : DataclassList) {
			temList.add(dataclass.toZTreeNodeModel());
		}
		return temList;
	}
	
	@Override
	public List<DataClassZRreeNodeModel> getDataClassByLevel(Integer level) {
		List<Dataclass> resultList = dataclassDAO.findByLevelOrderByPriority(level);
		List<DataClassZRreeNodeModel> temList = new ArrayList<DataClassZRreeNodeModel>();
		
		for(Dataclass dataclass : resultList) {
			temList.add(dataclass.toZTreeNodeModel());
		}
		return temList;
	}

	@Override
	public List<DataClassZRreeNodeModel> getDataClassByPID(Integer PID) {
		Dataclass pDataclass = dataclassDAO.findById(Long.valueOf(PID + ""));
		
		List<Dataclass> dataclassList = new LinkedList<Dataclass>();
		for(Dataclass dataclass: pDataclass.getDataclasses()) {
			dataclassList.add(dataclass);
		}
		
		// 对 priority 排序
		Collections.sort(dataclassList, new Comparator<Dataclass>() {
			@Override
			public int compare(Dataclass o1, Dataclass o2) {
				return o1.getProirity() - o2.getProirity();
			}
		});
		
		// 转换model
		List<DataClassZRreeNodeModel> temList = new ArrayList<DataClassZRreeNodeModel>();
		for(Dataclass dataclass : dataclassList) {
			temList.add(dataclass.toZTreeNodeModel());
		}
		return temList;
	}

	@Override
	public DataClassDetailModel getDataClassDetailByID(Integer ID) {
		Dataclass dataclass = dataclassDAO.findById(Long.valueOf(ID + ""));
		
		// 转换model
		DataClassDetailModel dataClassDetailModel = new DataClassDetailModel();
		
		dataClassDetailModel.setID(dataclass.getId());
		dataClassDetailModel.setLevel(dataclass.getLevel());
		Long PID = 0L;
		if(dataclass.getDataclass() != null){
			PID = dataclass.getDataclass().getId();
		}
		dataClassDetailModel.setPID(PID);
		dataClassDetailModel.setPriority(dataclass.getProirity());
		
		dataClassDetailModel.setName(dataclass.getClassName());
		String timeString = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(dataclass.getCreatoinTime());
		dataClassDetailModel.setCreationTime(timeString);
		String accountName = accountDAO.findById(dataclass.getCreationAccountId()).getName();
		dataClassDetailModel.setCreationAccountName(accountName);
		dataClassDetailModel.setResponsibilityDepartment(dataclass.getResponsibilityDepartment());
		dataClassDetailModel.setNote(dataclass.getNote());
		return dataClassDetailModel;
	}

	/*
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
	 * */
	@Override
	public boolean updateDataClass(DataClassDetailModel dataclassDetailModel) {
		System.out.println("service: " + dataclassDetailModel);
		Dataclass dataclass = dataclassDAO.findById(dataclassDetailModel.getID());
		dataclass.setClassName(dataclassDetailModel.getName());
		dataclass.setResponsibilityDepartment(dataclassDetailModel.getResponsibilityDepartment());
		dataclass.setProirity(dataclassDetailModel.getPriority());
		dataclass.setNote(dataclassDetailModel.getNote());
		
		dataclass.setCreatoinTime(new Timestamp(new Date().getTime()));
		// 一下字段需要访问session中的账号信息
		// creationAccountName
		dataclass.setCreationAccountId(1L);
		
		dataclassDAO.save(dataclass);
		return true;
	}

	/*
	 * 插入基础数据类型
	 * 用DataClassDetailModel接受修改后的基础数据类型
	 * 前端能够填写的字段：
	 * 	name
	 * 	responsibilityDepartment
	 * 	priority
	 * 	note
	 * 	PID
	 *  
	 * 前端填写，在后端更新字段
	 * 	creationTime
	 * 	creationAccountName
	 * 
	 * 插入时为空的字段：
	 * 	attributeses
	 * 	dataclasses
	 *  instances
	 * 
	 * */
	@Override
	public Long insertDataClass(DataClassDetailModel dataclassDetailModel) {
		Dataclass dataclass = new Dataclass();
		Dataclass PDataclass = dataclassDAO.findById(dataclassDetailModel.getPID());
		dataclass.setDataclass(PDataclass);
		dataclass.setClassName(dataclassDetailModel.getName());
		dataclass.setProirity(dataclassDetailModel.getPriority());
		dataclass.setLevel(PDataclass.getLevel() + 1);
		dataclass.setResponsibilityDepartment(dataclassDetailModel.getResponsibilityDepartment());
		dataclass.setNote(dataclassDetailModel.getNote());
		
		dataclass.setCreatoinTime(new Timestamp(new Date().getTime()));
		// 以下字段需要从session中获取Account信息
		// creationAccountName
		dataclass.setCreationAccountId(1L);
		
		dataclassDAO.save(dataclass);
		//System.out.println("新增ID： " + dataclass.getId());
		return dataclass.getId();
	}

	@Override
	public boolean deleteDataClassByID(Long ID) {
		dataclassDAO.delete(dataclassDAO.findById(ID));
		return true;
	}

	@Override
	public List<AttibuteModel> getAttributeListByDataClassIDPaging(Long DataClassID, Integer pageIndex, Integer pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<AttibuteModel> getAttributeListByDataClassID(Long DataClassID){
		// 利用双向一对多（DataClass 对 Attibute）通过 DataClass 获取 Attibute集合
		// set转list
		ArrayList<Attributes> temList = new ArrayList<Attributes>();
		for(Attributes attributes : dataclassDAO.findById(DataClassID).getAttributeses()) {
			temList.add(attributes);
		}
		// 对priority排序
		Collections.sort(temList, new Comparator<Attributes>() {

			@Override
			public int compare(Attributes o1, Attributes o2) {
				return o1.getPriority() - o2.getPriority();
			}
		});
		// 转换model
		List<AttibuteModel> resultList = new ArrayList<AttibuteModel>();
		for(Attributes attributes : temList) {
			resultList.add(attributes.toAttibuteModel());
		}
		return resultList;
	}
	
	/**
	 * 
	 * update时，不可更新字段：
	 * 	id
	 * 	dataclass
	 * 	values
	 * 
	 * @param attributesModel
	 * @return
	 * @see com.oil.service.baseData.zyh.BaseDataService#updateAttribute(com.oil.model.AttibuteModel)
	 */
	@Override
	public boolean updateAttribute(AttibuteModel attributesModel) {
		Attributes attributes = attributesDAO.findById(attributesModel.getId());
		attributes.setAttributeName(attributesModel.getName());
		attributes.setPriority(attributesModel.getPriority());
		attributes.setNote(attributesModel.getNote());
		
		attributesDAO.save(attributes);
		return true;
	}

	/**
	 * 
	 * 新增时为空的字段
	 * 	id
	 * 	values
	 * 
	 * @param attributeModel
	 * @return
	 * @see com.oil.service.baseData.zyh.BaseDataService#insertAttribute(com.oil.model.AttibuteModel)
	 */
	@Override
	public Long insertAttribute(AttibuteModel attributeModel) {
		Attributes attributes = new Attributes();
		attributes.setDataclass(dataclassDAO.findById(attributeModel.getDataclassId()));
		attributes.setAttributeName(attributeModel.getName());
		attributes.setPriority(attributeModel.getPriority());
		attributes.setNote(attributeModel.getNote());
		attributesDAO.save(attributes);
		return attributes.getId();
	}

	@Override
	public boolean deleteAttributeByID(Long attributesID) {
		attributesDAO.delete(attributesDAO.findById(attributesID));
		return true;
	}
}
