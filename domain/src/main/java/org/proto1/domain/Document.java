package org.proto1.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotNull;

@Entity
@Inheritance(
		strategy = InheritanceType.JOINED
) 
public class Document extends AbstractEntity {
	@NotNull
	private String documentNo;

	private Date issueDate;
	
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
