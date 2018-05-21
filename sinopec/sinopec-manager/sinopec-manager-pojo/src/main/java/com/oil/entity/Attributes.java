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





import com.oil.model.AttibuteModel;




/**
 * Attributes entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "attributes", catalog = "sinopec")
public class Attributes implements java.io.Serializable {

	// Fields

	private Long id;
	private Dataclass dataclass;
	private String attributeName;
	private Integer priority;
	private String note;
	private Set<Value> values = new HashSet<Value>(0);
	private Set<Matchrelation> matchrelations = new HashSet<Matchrelation>(0);

	// Constructors

	/** default constructor */
	public Attributes() {
	}

	/** minimal constructor */
	public Attributes(Dataclass dataclass, Integer priority) {
		this.dataclass = dataclass;
		this.priority = priority;
	}

	/** full constructor */
	public Attributes(Dataclass dataclass, String attributeName,
			Integer priority, String note, Set<Value> values,
			Set<Matchrelation> matchrelations) {
		this.dataclass = dataclass;
		this.attributeName = attributeName;
		this.priority = priority;
		this.note = note;
		this.values = values;
		this.matchrelations = matchrelations;
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

	@Column(name = "attributeName", length = 200)
	public String getAttributeName() {
		return this.attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	@Column(name = "priority", nullable = false)
	public Integer getPriority() {
		return this.priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	@Column(name = "note", length = 2550)
	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}


	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "attributes")
	public Set<Value> getValues() {
		return this.values;
	}

	public void setValues(Set<Value> values) {
		this.values = values;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "attributes")
	public Set<Matchrelation> getMatchrelations() {
		return this.matchrelations;
	}

	public void setMatchrelations(Set<Matchrelation> matchrelations) {
		this.matchrelations = matchrelations;
	}

	public AttibuteModel toAttibuteModel() {
		AttibuteModel attibuteModel = new AttibuteModel();
		attibuteModel.setId(id);
		attibuteModel.setDataclassId(dataclass.getId());
		attibuteModel.setName(attributeName);
		attibuteModel.setPriority(priority);
		attibuteModel.setNote(note);
		
		return attibuteModel;
	}
}