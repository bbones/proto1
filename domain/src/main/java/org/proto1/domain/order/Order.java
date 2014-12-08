package org.proto1.domain.order;

import java.util.ArrayList;

import org.proto1.domain.Identified;

public class Order implements Identified {
	private Long id;
	private ArrayList<OrderLine> lines;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ArrayList<OrderLine> getLines() {
		return lines;
	}

	public void setLines(ArrayList<OrderLine> lines) {
		this.lines = lines;
	}
}
