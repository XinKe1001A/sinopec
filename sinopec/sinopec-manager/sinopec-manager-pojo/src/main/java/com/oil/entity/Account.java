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
import javax.persistence.UniqueConstraint;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Account entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "account", catalog = "sinopec", uniqueConstraints = {
		@UniqueConstraint(columnNames = "card"),
		@UniqueConstraint(columnNames = "accountName") })
public class Account implements java.io.Serializable {

	// Fields

	private Long id;
	private Organization organization;
	private String accountName;
	private Integer type;
	private String name;
	private String tele;
	private String sex;
	private String card;
	private String password;
	private Set<AccountRole> accountRoles = new HashSet<AccountRole>(0);

	// Constructors

	/** default constructor */
	public Account() {
	}

	/** minimal constructor */
	public Account(String accountName, Integer type, String name,
			String password) {
		this.accountName = accountName;
		this.type = type;
		this.name = name;
		this.password = password;
	}

	/** full constructor */
	public Account(Organization organization, String accountName, Integer type,
			String name, String tele, String sex, String card, String password,
			Set<AccountRole> accountRoles) {
		this.organization = organization;
		this.accountName = accountName;
		this.type = type;
		this.name = name;
		this.tele = tele;
		this.sex = sex;
		this.card = card;
		this.password = password;
		this.accountRoles = accountRoles;
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

	@JSONField(serialize=false)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organizationId")
	public Organization getOrganization() {
		return this.organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	@Column(name = "accountName", unique = true, nullable = false, length = 200)
	public String getAccountName() {
		return this.accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	@Column(name = "type", nullable = false)
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "name", nullable = false, length = 200)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "tele", length = 30)
	public String getTele() {
		return this.tele;
	}

	public void setTele(String tele) {
		this.tele = tele;
	}

	@Column(name = "sex", length = 20)
	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "card", unique = true, length = 25)
	public String getCard() {
		return this.card;
	}

	public void setCard(String card) {
		this.card = card;
	}

	@Column(name = "password", nullable = false)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@JSONField(serialize=false)
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "account")
	public Set<AccountRole> getAccountRoles() {
		return this.accountRoles;
	}

	public void setAccountRoles(Set<AccountRole> accountRoles) {
		this.accountRoles = accountRoles;
	}

}