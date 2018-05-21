package com.oil.model;

import java.io.Serializable;
/**
 * 
 * 属性model   
 * @author: zyh 
 * @date:   2018年5月16日 上午10:55:55   
 *
 */
public class AttibuteModel implements Serializable{
	private Long id;
	private Long DataclassId;
	private String name;
	private Integer priority;
	private String note;
	
	public AttibuteModel() {
	}

	public AttibuteModel(Long id, Long dataclassId, String name, Integer priority, String note) {
		this.id = id;
		DataclassId = dataclassId;
		this.name = name;
		this.priority = priority;
		this.note = note;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDataclassId() {
		return DataclassId;
	}

	public void setDataclassId(Long dataclassId) {
		DataclassId = dataclassId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "AttibuteModel [id=" + id + ", DataclassId=" + DataclassId + ", name=" + name + ", priority=" + priority
				+ ", note=" + note + "]";
	}
	
	
}
