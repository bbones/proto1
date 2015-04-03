/**
 * BOMService.java
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 * Created Mar 23, 2015
 */
package org.proto1.services.order;

import java.util.List;
import java.util.Map;

import org.proto1.domain.order.ProductionOrder;

/**
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 *
 */
public interface BOMService extends BaseOrderService {

	/**
	 * @param productionOrder
	 */
	void createBOM(ProductionOrder productionOrder);


}
