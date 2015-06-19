/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/

package org.proto1.dto.order;

import org.proto1.dto.DTO;

/**
 * 
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 * See also ordermodel.js
 */
public class OrderDTO extends DTO {

	private static final long serialVersionUID = -812432410799731830L;

	private Long id;
	private String documentNo;
	private Long issueDate;
	
	public Long getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Long issueDate) {
		this.issueDate = issueDate;
	}

	public String getDocumentNo() {
		return documentNo;
	}

	public void setDocumentNo(String documentNo) {
		this.documentNo = documentNo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
