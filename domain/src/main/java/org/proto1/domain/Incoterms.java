package org.proto1.domain;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
	

@Entity
@Table(uniqueConstraints=@UniqueConstraint(columnNames={"charCode"}))

public class Incoterms extends AbstractEntity {
	@Column(name = "charCode", unique = true, nullable = false)
    private	String charCode;		
	

}
