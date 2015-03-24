package org.proto1.domain.order;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.proto1.domain.product.Parameter;
import org.proto1.domain.product.Receipt;
import org.proto1.domain.product.ReceiptItem;

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
		for(ReceiptItem ri : receipt.getIngredients()) {
			OrderLine bomLine = new OrderLine();
			bomLine.setOrder(this);
			bomLine.setProduct(ri.getProduct());
			bomLine.setQnty(ri.getQnty()*orderLine.getQnty());
			bomLine.setUnitOfMeasurement(ri.getUnitOfMeasurement());
			if (ri.isMaster()) {
				bomLine.setOrderLineParameterList(orderLine.getDerivativeParameters());
			};
			getLines().add(bomLine);
		}
	}
}
