/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
// TODO query
//	select p.id, p.address, p.name, pn.lastName from Party p join p.personNames pn where pn.language.id=1
package org.proto1.domain.party;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import org.proto1.domain.AbstractEntity;
import org.proto1.domain.Language;

@Entity
@Inheritance(
		strategy = InheritanceType.JOINED
) 

public abstract class  Party extends AbstractEntity {
	public abstract String getName(Language language);
}
