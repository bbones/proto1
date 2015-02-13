package org.proto1.domain.order;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.proto1.domain.AbstractEntity;

@Entity
@Table(name="ORDER_BASE")
public class Order extends AbstractEntity {
	@OneToMany(cascade=CascadeType.PERSIST, mappedBy="order")
	private List<OrderLine> lines;
	
	public List<OrderLine> getLines() {
		return lines;
	}

	public void setLines(List<OrderLine> lines) {
		this.lines = lines;
	}
}
