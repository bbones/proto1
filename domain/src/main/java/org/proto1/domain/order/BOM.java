package org.proto1.domain.order;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.proto1.domain.product.Receipt;

@Entity
public class BOM extends BaseOrder {
	
	@OneToOne
	@JoinColumn(name="ORDER_LINE_ID")
	private OrderLine orderLine;
	
	@ManyToOne
	@JoinColumn(name="RECEIPT_ID")
	private Receipt receipt;

	public OrderLine getOrderLine() {
		return orderLine;
	}

	public void setOrderLine(OrderLine orderLine) {
		this.orderLine = orderLine;
	}

	public Receipt getReceipt() {
		return receipt;
	}

	public void setReceipt(Receipt receipt) {
		this.receipt = receipt;
	}
	
	public void calcBOMLines() {
		
	}
}