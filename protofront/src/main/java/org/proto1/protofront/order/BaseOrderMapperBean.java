/**
 * BaseOrderMapperBean.java
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
public class BaseOrderMapperBean implements BaseOrderMapper {

	/* (non-Javadoc)
	 * @see org.proto1.protofront.order.BaseOrderMapper#map(org.proto1.dto.order.OrderDTO)
	 */
	@Override
	public BaseOrder map(OrderDTO product) {
		return null;
	}

}
