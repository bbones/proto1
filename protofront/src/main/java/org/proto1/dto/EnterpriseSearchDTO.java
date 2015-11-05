package org.proto1.dto;

import java.io.Serializable;

public class EnterpriseSearchDTO implements Serializable {

	private static final long serialVersionUID = 6012123500944653455L;

	private Long nameId;
	private Long enterpriseId;
	private String name;
	private Long eskId;
	public Long getNameId() {
		return nameId;
	}
	public void setNameId(Long nameId) {
		this.nameId = nameId;
	}
	public Long getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getEskId() {
		return eskId;
	}
	public void setEskId(Long eskId) {
		this.eskId = eskId;
	}
	
}
