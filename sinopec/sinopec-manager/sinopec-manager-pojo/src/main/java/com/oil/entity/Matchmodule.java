package com.oil.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Matchmodule entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "matchmodule", catalog = "sinopec")
public class Matchmodule implements java.io.Serializable {

	// Fields

	private Long id;
	private String moduleName;
	private Set<Matchrelation> matchrelations = new HashSet<Matchrelation>(0);

	// Constructors

	/** default constructor */
	public Matchmodule() {
	}

	/** minimal constructor */
	public Matchmodule(Long id) {
		this.id = id;
	}

	/** full constructor */
	public Matchmodule(Long id, String moduleName,
			Set<Matchrelation> matchrelations) {
		this.id = id;
		this.moduleName = moduleName;
		this.matchrelations = matchrelations;
	}

	// Property accessors
	@Id
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "module_name")
	public String getModuleName() {
		return this.moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "matchmodule")
	public Set<Matchrelation> getMatchrelations() {
		return this.matchrelations;
	}

	public void setMatchrelations(Set<Matchrelation> matchrelations) {
		this.matchrelations = matchrelations;
	}

}