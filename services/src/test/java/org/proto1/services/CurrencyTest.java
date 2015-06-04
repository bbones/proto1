package org.proto1.services;

import java.util.Currency;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

public class CurrencyTest {
	protected final Log logger = LogFactory.getLog(getClass());

	@Test
	public void test() {
		
		Locale locale = new Locale("ua", "ua");
		logger.debug(locale);
		logger.debug(locale.getDisplayCountry());
		logger.debug(locale.getDisplayCountry(locale));
		logger.debug(locale.getDisplayVariant());
		
		
		Currency currency = Currency.getInstance("UAH");
		logger.debug(currency.getCurrencyCode());
		logger.debug(currency.getDisplayName(locale));
		logger.debug(currency.getNumericCode());
		logger.debug(currency.getSymbol());
	}

}
