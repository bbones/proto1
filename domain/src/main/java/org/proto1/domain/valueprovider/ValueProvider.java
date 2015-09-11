package org.proto1.domain.valueprovider;

import java.util.Map;

public interface ValueProvider {
	Map<Long, Object> getValues();
	boolean checkValue(Object value);
}
