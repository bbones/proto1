package org.proto1.domain.order;

import java.util.ArrayList;

import javax.persistence.Entity;

import org.proto1.domain.AbstractEntity;

@Entity
public class Order extends AbstractEntity {
	private ArrayList<OrderLine> lines;
	
	public ArrayList<OrderLine> getLines() {
		return lines;
	}

	public void setLines(ArrayList<OrderLine> lines) {
		this.lines = lines;
	}
}
