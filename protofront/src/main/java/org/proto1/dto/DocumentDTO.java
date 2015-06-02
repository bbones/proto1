package org.proto1.dto;

import java.util.Date;

import org.proto1.dtotools.DTODecode;
import org.proto1.dtotools.DTOEncode;

@SuppressWarnings("serial")
public class DocumentDTO extends DTO {

	private Long id;
	private String documentNo;
	private Date issueDate;

	public Long getId() {
		return id;
	}

	@DTOEncode(sourceMethod="getId")
	public void setId(Long id) {
		this.id = id;
	}

	@DTODecode(destination="setDocumentNo")
	public String getDocumentNo() {
		return documentNo;
	}

	@DTOEncode(sourceMethod="getDocumentNo")
	public void setDocumentNo(String documentNo) {
		this.documentNo = documentNo;
	}

	@DTODecode(destination="setIssueDate")
	public Date getIssueDate() {
		return issueDate;
	}

	@DTOEncode(sourceMethod="getIssueDate")
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

}
