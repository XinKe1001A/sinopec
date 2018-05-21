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
 * Organization entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "organization", catalog = "sinopec")
public class Organization implements java.io.Serializable {

	// Fields

	private Long id;
	private Instance instance;
	private String code3265;
	private String name;
	private String nameSimple;
	private String parent;
	private Long parentId;
	private String address;
	private String contacts;
	private String phone;
	private String property;
	private String state;
	private String context;
	private Set<Account> accounts = new HashSet<Account>(0);

	// Constructors

	/** default constructor */
	public Organization() {
	}

	/** full constructor */
	public Organization(Instance instance, String code3265, String name,
			String nameSimple, String parent, Long parentId, String address,
			String contacts, String phone, String property, String state,
			String context, Set<Account> accounts) {
		this.instance = instance;
		this.code3265 = code3265;
		this.name = name;
		this.nameSimple = nameSimple;
		this.parent = parent;
		this.parentId = parentId;
		this.address = address;
		this.contacts = contacts;
		this.phone = phone;
		this.property = property;
		this.state = state;
		this.context = context;
		this.accounts = accounts;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "insId")
	public Instance getInstance() {
		return this.instance;
	}

	public void setInstance(Instance instance) {
		this.instance = instance;
	}

	@Column(name = "code3265")
	public String getCode3265() {
		return this.code3265;
	}

	public void setCode3265(String code3265) {
		this.code3265 = code3265;
	}

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "name_simple")
	public String getNameSimple() {
		return this.nameSimple;
	}

	public void setNameSimple(String nameSimple) {
		this.nameSimple = nameSimple;
	}

	@Column(name = "parent")
	public String getParent() {
		return this.parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	@Column(name = "parentId")
	public Long getParentId() {
		return this.parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	@Column(name = "address")
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "contacts")
	public String getContacts() {
		return this.contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	@Column(name = "phone")
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "property")
	public String getProperty() {
		return this.property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	@Column(name = "state")
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "context")
	public String getContext() {
		return this.context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "organization")
	public Set<Account> getAccounts() {
		return this.accounts;
	}

	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}

}