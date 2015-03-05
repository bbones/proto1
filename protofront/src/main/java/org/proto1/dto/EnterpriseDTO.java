/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.dto;

public class EnterpriseDTO extends DTO  {

	private static final long serialVersionUID = -9062428108123912200L;
	
	private Long id;
	private String name;
	private String address;
	
	public EnterpriseDTO() {
		
	}
	
	public EnterpriseDTO(Long id, String name, String address) {
		this.id = id;
		this.name = name;
		this.address = address;
	}
	public Long getid() {
		return id;
	}
	public void setid(Long id) {
		this.id = id;
	}
	public String getname() {
		return name;
	}
	public void setname(String name) {
		this.name = name;
	}
	
	public String toString() {
		return "id:" + id + ";name:" + name+";address:" +address +";VERSION:" + version;
	}

	public String getaddress() {
		return address;
	}

	public void setaddress(String address) {
		this.address = address;
	}

}
