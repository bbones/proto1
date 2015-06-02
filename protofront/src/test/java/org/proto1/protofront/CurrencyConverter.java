
package org.proto1.protofront;

import org.dozer.DozerConverter;
import org.proto1.domain.Currency;
import org.springframework.stereotype.Service;

@Service
public class CurrencyConverter extends DozerConverter<Long, Currency> {

	public CurrencyConverter(Class<Long> prototypeA, Class<Currency> prototypeB) {
		super(prototypeA, prototypeB);
	}

	@Override
	public Long convertFrom(Currency source, Long destination) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Currency convertTo(Long source, Currency destination) {
		// TODO Auto-generated method stub
		return null;
	}


}
