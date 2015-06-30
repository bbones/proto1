package org.proto1.repository.party;

import java.util.List;
import java.util.Map;

import org.proto1.domain.party.OrganizationUnit;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface OrganizationUnitRepository extends CrudRepository<OrganizationUnit, Long> {
	@Query("")
	List<Map<String, Object>> getList(Long languageId);
}
