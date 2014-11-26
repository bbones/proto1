package org.proto1.generator.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(schema="TOOLS", name="QUERY_DESCRIPTOR")
public class QueryDescriptor {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String query;
	private String updateEntity;
	
	@OneToMany(cascade={CascadeType.ALL})
	@JoinColumn(name="query_descriptor_id",nullable=true)
	private List<FieldDescriptor> fieldList;
	
	public String getQuery() {
		return query;
	}
	
	public void setQuery(String query) {
		this.query = query;
	}
	
	public List<FieldDescriptor> getFieldList() {
		return fieldList;
	}
	
	public void setFieldList(List<FieldDescriptor> fieldList) {
		this.fieldList = fieldList;
	}

	public String getUpdateEntity() {
		return updateEntity;
	}

	public void setUpdateEntity(String updateEntity) {
		this.updateEntity = updateEntity;
	}
}
