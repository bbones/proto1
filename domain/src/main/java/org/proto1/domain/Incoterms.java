package org.proto1.domain;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
	

@Entity
@Table(uniqueConstraints=@UniqueConstraint(columnNames={"charCode"}))

public class Incoterms extends AbstractEntity {
	@Column(name = "charCode", unique = true, nullable = false)
    private	String charCode;		
	

}
