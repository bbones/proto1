package org.proto1.services.party;

import java.util.List;
import java.util.Map;

import org.proto1.domain.party.OrganizationUnit;
import org.proto1.domain.party.OrganizationUnitName;

public interface OrganizationUnitService {

	List<Map<String, Object>> getList(Long enterpriseId, Long languageId);

	OrganizationUnit get(Long organizatioUnitId);

	OrganizationUnit save(OrganizationUnit organizationUnit);

	void delete(Long organizatioUnitId);

	List<OrganizationUnitName> getNamesList(Long id);

	OrganizationUnitName saveName(OrganizationUnitName oun);

	void deleteName(Long id);

}
