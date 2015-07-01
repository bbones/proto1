package org.proto1.repository.party;

import java.util.List;
import java.util.Map;

import org.proto1.domain.party.OrganizationUnit;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface OrganizationUnitRepository extends CrudRepository<OrganizationUnit, Long> {
	@Query("select new Map(ou.id as id, oun.unitName as name) "
			+ "from OrganizationUnit ou "
			+ "join ou.names oun where oun.language.id = :language_id")
	List<Map<String, Object>> getList(@Param("language_id") Long languageId);
}
