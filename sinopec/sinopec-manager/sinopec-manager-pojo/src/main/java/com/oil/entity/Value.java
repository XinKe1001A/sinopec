package com.oil.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Value entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "value", catalog = "sinopec")
public class Value implements java.io.Serializable {

	// Fields

	private Long id;
	private Instance instance;
	private Attributes attributes;
	private String value;
	private String note;

	// Constructors

	/** default constructor */
	public Value() {
	}

	/** minimal constructor */
	public Value(Instance instance, Attributes attributes) {
		this.instance = instance;
		this.attributes = attributes;
	}

	/** full constructor */
	public Value(Instance instance, Attributes attributes, String value,
			String note) {
		this.instance = instance;
		this.attributes = attributes;
		this.value = value;
		this.note = note;
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
	@JoinColumn(name = "instandeID", nullable = false)
	public Instance getInstance() {
		return this.instance;
	}

	public void setInstance(Instance instance) {
		this.instance = instance;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "attributeID", nullable = false)
	public Attributes getAttributes() {
		return this.attributes;
	}

	public void setAttributes(Attributes attributes) {
		this.attributes = attributes;
	}

	@Column(name = "value", length = 200)
	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Column(name = "note")
	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}