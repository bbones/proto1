package org.proto1.generator.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema="TOOLS", name="FIELD_DESCRIPTOR")
public class FieldDescriptor {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="FIELD_DESCRIPTOR_ID")
	private Long id;
	
	private String name;
	private String type;
	private boolean key;
	
	@ManyToOne
	@JoinColumn(name="query_descriptor_id")
	private QueryDescriptor queryDescriptor;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public boolean isKey() {
		return key;
	}
	public void setKey(boolean key) {
		this.key = key;
	}
}
