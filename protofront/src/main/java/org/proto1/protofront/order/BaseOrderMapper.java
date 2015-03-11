/**
 * BaseOrderMapper.java
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 * Created Mar 11, 2015
 */
package org.proto1.protofront.order;

import org.proto1.domain.order.BaseOrder;
import org.proto1.dto.order.OrderDTO;

/**
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 *
 */
public interface BaseOrderMapper {
	BaseOrder map(OrderDTO product);
}
