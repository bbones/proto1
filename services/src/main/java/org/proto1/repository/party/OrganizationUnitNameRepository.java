package org.proto1.repository.party;

import java.util.List;

import org.proto1.domain.party.OrganizationUnitName;
import org.springframework.data.repository.CrudRepository;

public interface OrganizationUnitNameRepository extends CrudRepository<OrganizationUnitName, Long> {

	List<OrganizationUnitName> getByOrganizationUnitId(Long id);

}
