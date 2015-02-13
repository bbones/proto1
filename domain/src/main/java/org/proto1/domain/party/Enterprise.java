package org.proto1.domain.party;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotNull;

@Entity
@PrimaryKeyJoinColumn(name="ENTERPRISE_ID")
public class Enterprise extends Party {

}
