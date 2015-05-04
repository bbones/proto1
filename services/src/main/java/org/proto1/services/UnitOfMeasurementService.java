package org.proto1.services;

import java.util.List;
import java.util.Map;

import org.proto1.domain.UnitOfMeasurement;

public interface UnitOfMeasurementService {
	
	UnitOfMeasurement get(Long id);

	List<Map<String, Object>> getUOMList(Long languageId);

	UnitOfMeasurement save(UnitOfMeasurement uom);

	void delete(Long id);

}
