/**
 * BOMService.java
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 * Created Mar 23, 2015
 */
package org.proto1.services.order;

import org.proto1.domain.order.BOM;
import org.proto1.domain.order.ProductionOrder;

/**
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 *
 */
public interface BOMService extends BaseOrderService<BOM> {

	/**
	 * @param productionOrder
	 */
	void createBOM(ProductionOrder productionOrder);


}
