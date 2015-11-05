/*******************************************************************************
 * Copyright (C) 2015  Valentin Pogrebinsky
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *******************************************************************************/
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
