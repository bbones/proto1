/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.repository.party;

import java.util.List;

import org.proto1.domain.party.EnterpriseName;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface EnterpriseNameRepository extends CrudRepository<EnterpriseName, Long>, JpaSpecificationExecutor<EnterpriseName> {
	
	List<EnterpriseName> getByEnterpriseId(Long enterpriseId);


}
