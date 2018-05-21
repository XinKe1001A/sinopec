package com.oil.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Matchrelation entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "matchrelation", catalog = "sinopec")
public class Matchrelation implements java.io.Serializable {

	// Fields

	private Integer id;
	private Matchmodule matchmodule;
	private Attributes attributes;
	private String colName;

	// Constructors

	/** default constructor */
	public Matchrelation() {
	}

	/** minimal constructor */
	public Matchrelation(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Matchrelation(Integer id, Matchmodule matchmodule,
			Attributes attributes, String colName) {
		this.id = id;
		this.matchmodule = matchmodule;
		this.attributes = attributes;
		this.colName = colName;
	}

	// Property accessors
	@Id
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "moduleId")
	public Matchmodule getMatchmodule() {
		return this.matchmodule;
	}

	public void setMatchmodule(Matchmodule matchmodule) {
		this.matchmodule = matchmodule;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "attributeId")
	public Attributes getAttributes() {
		return this.attributes;
	}

	public void setAttributes(Attributes attributes) {
		this.attributes = attributes;
	}

	@Column(name = "colName")
	public String getColName() {
		return this.colName;
	}

	public void setColName(String colName) {
		this.colName = colName;
	}

}