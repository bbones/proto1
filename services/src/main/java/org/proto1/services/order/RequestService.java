/**
 * RequestService.java
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 * Created Apr 3, 2015
 */
package org.proto1.services.order;

import org.proto1.domain.order.OrderLine;
import org.proto1.domain.order.OrderLineParameter;
import org.proto1.domain.order.Request;

/**
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 *
 */
public interface RequestService extends BaseOrderService<Request> {

	/**
	 * @param request
	 * @return
	 */
	Request save(Request request);
	
	/**
	 * @param id
	 * @return Request object with given Id
	 */
	Request get(Long id);

	OrderLine getOrderLine(Long orderLineId);

	OrderLineParameter saveOrderLineParameter(OrderLineParameter orderLineParameter);
	
}
