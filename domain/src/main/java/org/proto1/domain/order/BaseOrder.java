package org.proto1.domain.order;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.proto1.domain.Document;

@Entity
@Inheritance(
		strategy = InheritanceType.JOINED
) 
@Table(name="BASE_ORDER")
public class BaseOrder extends Document {
	@OneToMany(cascade=CascadeType.PERSIST, mappedBy="order")
	private List<OrderLine> lines;
	
	public List<OrderLine> getLines() {
		return lines;
	}

	public void setLines(List<OrderLine> lines) {
		this.lines = lines;
	}
}
