package com.oil.model;

import java.io.Serializable;

/**
 * 
 * 基础数据类型详情  
 * @author: zyh 
 * @date:   2018年5月15日 下午9:00:43   
 *
 */
public class DataClassDetailModel implements Serializable{
	// 前端可能不需要显示
	private long ID;
	private int level;
	private long PID;
	private int priority;
	
	// 前端可能需要显示
	private String name;
	private String creationTime;
	private String creationAccountName;
	private String responsibilityDepartment;
	private String note;
	
	public DataClassDetailModel() {
	}

	public DataClassDetailModel(long iD, int level, long pID, int priority, String name, String creationTime,
			String creationAccountName, String responsibilityDepartment, String note) {
		ID = iD;
		this.level = level;
		PID = pID;
		this.priority = priority;
		this.name = name;
		this.creationTime = creationTime;
		this.creationAccountName = creationAccountName;
		this.responsibilityDepartment = responsibilityDepartment;
		this.note = note;
	}

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public long getPID() {
		return PID;
	}

	public void setPID(long pID) {
		PID = pID;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}

	public String getCreationAccountName() {
		return creationAccountName;
	}

	public void setCreationAccountName(String creationAccountName) {
		this.creationAccountName = creationAccountName;
	}

	public String getResponsibilityDepartment() {
		return responsibilityDepartment;
	}

	public void setResponsibilityDepartment(String responsibilityDepartment) {
		this.responsibilityDepartment = responsibilityDepartment;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "DataClassDetailModel [ID=" + ID + ", level=" + level + ", PID=" + PID + ", priority=" + priority
				+ ", name=" + name + ", creationTime=" + creationTime + ", creationAccountName=" + creationAccountName
				+ ", responsibilityDepartment=" + responsibilityDepartment + ", note=" + note + "]";
	}
	
}
