
package org.proto1.mapper;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dozer.DozerConverter;
import org.proto1.domain.Currency;
import org.proto1.services.MasterDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class CurrencyConverter extends DozerConverter<Integer, Currency> {
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	MasterDataService masterDataService;

	public CurrencyConverter() {
		super(Integer.class, Currency.class);
		logger.debug("Instance of CC");
		logger.debug(masterDataService);
	}

	@Override
	public Integer convertFrom(Currency source, Integer destination) {
		return source.getNumCode();
	}

	@Override
	public Currency convertTo(Integer source, Currency destination) {
		return masterDataService.getCurrency(source);
	}


}
