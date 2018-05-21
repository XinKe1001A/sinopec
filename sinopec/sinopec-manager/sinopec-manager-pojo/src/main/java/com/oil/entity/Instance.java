package com.oil.entity;

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

/**
 * Instance entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "instance", catalog = "sinopec")
public class Instance implements java.io.Serializable {

	// Fields

	private Long id;
	private Dataclass dataclass;
	private Long organizationId;
	private Set<Organization> organizations = new HashSet<Organization>(0);
	private Set<Value> values = new HashSet<Value>(0);

	// Constructors

	/** default constructor */
	public Instance() {
	}

	/** minimal constructor */
	public Instance(Dataclass dataclass, Long organizationId) {
		this.dataclass = dataclass;
		this.organizationId = organizationId;
	}

	/** full constructor */
	public Instance(Dataclass dataclass, Long organizationId,
			Set<Organization> organizations, Set<Value> values) {
		this.dataclass = dataclass;
		this.organizationId = organizationId;
		this.organizations = organizations;
		this.values = values;
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
	@JoinColumn(name = "classID", nullable = false)
	public Dataclass getDataclass() {
		return this.dataclass;
	}

	public void setDataclass(Dataclass dataclass) {
		this.dataclass = dataclass;
	}

	@Column(name = "organizationID", nullable = false)
	public Long getOrganizationId() {
		return this.organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "instance")
	public Set<Organization> getOrganizations() {
		return this.organizations;
	}

	public void setOrganizations(Set<Organization> organizations) {
		this.organizations = organizations;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "instance")
	public Set<Value> getValues() {
		return this.values;
	}

	public void setValues(Set<Value> values) {
		this.values = values;
	}

}