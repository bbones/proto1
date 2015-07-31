package org.proto1.services;

import java.util.List;
import java.util.Map;

import org.proto1.domain.UnitOfMeasurement;
import org.proto1.domain.UnitOfMeasurementName;
import org.proto1.repository.UnitOfMeasurementNameRepository;
import org.proto1.repository.UnitOfMeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;


@Service
public class UnitOfMeasurementServiceBean implements UnitOfMeasurementService {

	@Autowired
	UnitOfMeasurementRepository uomRepository;
	
	@Autowired
	UnitOfMeasurementNameRepository uomNameRepository;

	@Autowired
	RuntimeService runtimeService;
	
	public UnitOfMeasurement get(Long id) {
		return uomRepository.findOne(id);
	}

	public List<Map<String, Object>> getUOMList(Long languageId) {
		return uomRepository.getList(languageId);
	}

	public UnitOfMeasurement save(UnitOfMeasurement uom) {
		return uomRepository.save(uom);
	}

	public void delete(Long id) {
		uomRepository.delete(id);
	}

	/* (non-Javadoc)
	 * @see org.proto1.services.UnitOfMeasurementService#deleteName(java.lang.Long)
	 */
	public void deleteName(Long nameId) {
		uomNameRepository.delete(nameId);
	}

	/* (non-Javadoc)
	 * @see org.proto1.services.UnitOfMeasurementService#saveName(org.proto1.domain.UnitOfMeasurementName)
	 */
	public UnitOfMeasurementName saveName(UnitOfMeasurementName uomName) {
		ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("helloworldProcess");
		return uomNameRepository.save(uomName);
	}

	public List<UnitOfMeasurementName> getUOMNamesList(Long uomId) {
		return uomNameRepository.findByUnitOfMeasurementId(uomId);
	}

}
