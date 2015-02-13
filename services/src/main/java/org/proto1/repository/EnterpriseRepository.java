package org.proto1.repository;

import org.springframework.data.repository.CrudRepository;
import org.proto1.domain.party.Enterprise;

public interface EnterpriseRepository extends CrudRepository<Enterprise, Long> {

}
