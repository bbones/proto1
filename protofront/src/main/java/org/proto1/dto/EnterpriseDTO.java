package org.proto1.dto;

import java.io.Serializable;

public class EnterpriseDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9062428108123912200L;
	
	private Long enterpriseId;
	private String enterpriseName;
	
	public EnterpriseDTO() {
		
	}
	
	public EnterpriseDTO(Long enterpriseId, String enterpriseName) {
		this.enterpriseId = enterpriseId;
		this.enterpriseName = enterpriseName;
	}
	public Long getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	public String getEnterpriseName() {
		return enterpriseName;
	}
	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}
	
	public String toString() {
		return "enterpriseId:" + enterpriseId + ";enterpriseName:" + enterpriseName;
	}

}
