package org.proto1.domain;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(
		strategy = InheritanceType.JOINED
) 
public class Document extends AbstractEntity {
	private String documentNo;

	public String getDocumentNo() {
		return documentNo;
	}

	public void setDocumentNo(String documentNo) {
		this.documentNo = documentNo;
	}
	
}
