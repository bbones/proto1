package org.proto1.dto;

import java.util.List;

public class ProductionOrderDTO extends DTO {
	private Long orderId;
	private List<OrderLineDTO> orderLines;
}
