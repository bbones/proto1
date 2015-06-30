package org.proto1.services.party;

import java.util.List;
import java.util.Map;

import org.proto1.domain.party.OrganizationUnit;
import org.proto1.repository.party.OrganizationUnitNameRepository;
import org.proto1.repository.party.OrganizationUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationUnitServiceBean implements OrganizationUnitService {
	
	@Autowired
	OrganizationUnitRepository organizationUnitRepository;
	
	@Autowired
	OrganizationUnitNameRepository organizationUnitNameRepository;

	public List<Map<String, Object>> getList(Long languageId) {
		return organizationUnitRepository.getList(languageId);
	}

	public OrganizationUnit get(Long organizatioUnitId) {
		return organizationUnitRepository.findOne(organizatioUnitId);
	}

	public OrganizationUnit save(OrganizationUnit organizationUnit) {
		return organizationUnitRepository.save(organizationUnit);
	}

	public void delete(Long organizatioUnitId) {
		organizationUnitRepository.delete(organizatioUnitId);
	}

}
