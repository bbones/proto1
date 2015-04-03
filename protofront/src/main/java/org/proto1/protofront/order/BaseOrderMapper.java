/**
 * BaseOrderMapper.java
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 * Created Mar 11, 2015
 */
package org.proto1.protofront.order;

import java.util.List;

import org.proto1.domain.Language;
import org.proto1.domain.order.BaseOrder;
import org.proto1.domain.order.OrderLine;
import org.proto1.domain.order.OrderLineParameter;
import org.proto1.dto.order.OrderDTO;
import org.proto1.dto.order.OrderLineDTO;
import org.proto1.dto.order.OrderLineParameterDTO;

/**
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 *
 */
public interface BaseOrderMapper {
	<T extends BaseOrder, S extends OrderDTO> T map(S source,
			Class<T> destinationClass) throws InstantiationException,
			IllegalAccessException;

	void mapOrderLines(List<OrderLineDTO> orderLines, List<OrderLine> lines,
			BaseOrder order);

	void mapOrderLine(OrderLineDTO old, OrderLine ol, BaseOrder order);

	void mapOrderLineParameters(List<OrderLineParameterDTO> parameterList,
			List<OrderLineParameter> orderLineParameterList, OrderLine orderLine);

	void mapOrderLineParameter(OrderLineParameterDTO olpd,
			OrderLineParameter olp, OrderLine orderLine);

	/**
	 * @param source
	 * @param destinationClass
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	<T extends OrderDTO, S extends BaseOrder> T mapToDTO(S source, Class<T> destinationClass, Language language)
			throws InstantiationException, IllegalAccessException;

	/**
	 * @param ol
	 * @param old
	 */
	void mapOrderLine(OrderLine ol, OrderLineDTO old, Language language);

	/**
	 * @param orderLines
	 * @param lines
	 * @param order
	 */
	void mapOrderLines(List<OrderLine> orderLines, List<OrderLineDTO> lines, Language language);

	/**
	 * @param olp
	 * @param olpd
	 */
	void mapOrderLineParameter(OrderLineParameter olp,
			OrderLineParameterDTO olpd, Language language);

	/**
	 * @param parameterList
	 * @param orderLineParameterList
	 */
	void mapOrderLineParameters(List<OrderLineParameter> parameterList,
			List<OrderLineParameterDTO> parameterListDTO, Language language);

}
