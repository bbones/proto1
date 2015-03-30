/**
 * MasteredParameterValueProvider.java
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 * Created Mar 30, 2015
 */
package org.proto1.services.parameter;

import java.util.List;
import java.util.Map;

/**
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 *
 */
public interface MasteredParameterValueProvider {
	List<String> getValueList(Map<String, Object> master);
}
