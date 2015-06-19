package org.proto1.dto;

import java.util.Date;

@SuppressWarnings("serial")
public class DocumentDTO extends DTO {

	private Long id;
	private String documentNo;
	private Date issueDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDocumentNo() {
		return documentNo;
	}

	public void setDocumentNo(String documentNo) {
		this.documentNo = documentNo;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

}
