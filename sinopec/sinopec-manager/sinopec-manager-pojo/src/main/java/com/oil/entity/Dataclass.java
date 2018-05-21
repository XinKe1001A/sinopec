package com.oil.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.oil.model.DataClassZRreeNodeModel;

/**
 * Dataclass entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "dataclass", catalog = "sinopec")
public class Dataclass implements java.io.Serializable {

	// Fields

	private Long id;
	private Dataclass dataclass;
	private String className;
	private Integer level;
	private Integer proirity;
	private Timestamp creatoinTime;
	private Long creationAccountId;
	private String responsibilityDepartment;
	private String note;
	private Set<Attributes> attributeses = new HashSet<Attributes>(0);
	private Set<Dataclass> dataclasses = new HashSet<Dataclass>(0);
	private Set<Instance> instances = new HashSet<Instance>(0);

	// Constructors

	/** default constructor */
	public Dataclass() {
	}

	/** minimal constructor */
	public Dataclass(String className, Integer level, Integer proirity,
			Long creationAccountId) {
		this.className = className;
		this.level = level;
		this.proirity = proirity;
		this.creationAccountId = creationAccountId;
	}

	/** full constructor */
	public Dataclass(Dataclass dataclass, String className, Integer level,
			Integer proirity, Timestamp creatoinTime, Long creationAccountId,
			String responsibilityDepartment, String note,
			Set<Attributes> attributeses, Set<Dataclass> dataclasses,
			Set<Instance> instances) {
		this.dataclass = dataclass;
		this.className = className;
		this.level = level;
		this.proirity = proirity;
		this.creatoinTime = creatoinTime;
		this.creationAccountId = creationAccountId;
		this.responsibilityDepartment = responsibilityDepartment;
		this.note = note;
		this.attributeses = attributeses;
		this.dataclasses = dataclasses;
		this.instances = instances;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PID")
	public Dataclass getDataclass() {
		return this.dataclass;
	}

	public void setDataclass(Dataclass dataclass) {
		this.dataclass = dataclass;
	}

	@Column(name = "className", nullable = false, length = 200)
	public String getClassName() {
		return this.className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	@Column(name = "level", nullable = false)
	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	@Column(name = "proirity", nullable = false)
	public Integer getProirity() {
		return this.proirity;
	}

	public void setProirity(Integer proirity) {
		this.proirity = proirity;
	}

	@Column(name = "creatoinTime", length = 0)
	public Timestamp getCreatoinTime() {
		return this.creatoinTime;
	}

	public void setCreatoinTime(Timestamp creatoinTime) {
		this.creatoinTime = creatoinTime;
	}

	@Column(name = "creationAccountID", nullable = false)
	public Long getCreationAccountId() {
		return this.creationAccountId;
	}

	public void setCreationAccountId(Long creationAccountId) {
		this.creationAccountId = creationAccountId;
	}

	@Column(name = "responsibilityDepartment", length = 20)
	public String getResponsibilityDepartment() {
		return this.responsibilityDepartment;
	}

	public void setResponsibilityDepartment(String responsibilityDepartment) {
		this.responsibilityDepartment = responsibilityDepartment;
	}

	@Column(name = "note")
	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "dataclass")
	public Set<Attributes> getAttributeses() {
		return this.attributeses;
	}

	public void setAttributeses(Set<Attributes> attributeses) {
		this.attributeses = attributeses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "dataclass")
	public Set<Dataclass> getDataclasses() {
		return this.dataclasses;
	}

	public void setDataclasses(Set<Dataclass> dataclasses) {
		this.dataclasses = dataclasses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "dataclass")
	public Set<Instance> getInstances() {
		return this.instances;
	}

	public void setInstances(Set<Instance> instances) {
		this.instances = instances;
	}

	@Override
	public String toString() {
		String PID = "";
		if(dataclass == null) {
			PID = 0+"";
		} else {
			PID = dataclass.getId() + "";
		}
		return "Dataclass [id=" + id + ", pid=" + PID + ", className=" + className + ", level=" + level
				+ ", proirity=" + proirity + ", creatoinTime=" + creatoinTime + ", creationAccountId="
				+ creationAccountId + ", responsibilityDepartment=" + responsibilityDepartment + ", note=" + note
				+ "]";
	}

	public DataClassZRreeNodeModel toZTreeNodeModel() {
		String PID = "";
		if(dataclass == null){
			PID = 0 + "";
		} else {
			PID = dataclass.getId() + "";
		}
		return new DataClassZRreeNodeModel(id + "", PID, className);
	}
	
}