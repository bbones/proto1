package org.proto1.services;

import org.proto1.domain.UnitOfMeasurement;
import org.proto1.repository.UnitOfMeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UnitOfMeasurementServiceBean implements UnitOfMeasurementService {

	@Autowired
	UnitOfMeasurementRepository uomRepository;
	
	public UnitOfMeasurement get(Long id) {
		return uomRepository.findOne(id);
	}

}
