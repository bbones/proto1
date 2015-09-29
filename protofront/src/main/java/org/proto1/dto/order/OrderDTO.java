/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/

package org.proto1.dto.order;

import java.util.List;

import org.proto1.dto.DocumentDTO;

/**
 * 
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 * See also ordermodel.js
 */
public class OrderDTO extends DocumentDTO {

	private static final long serialVersionUID = -812432410799731830L;
	
	private List<OrderLineDTO> lines;

	public List<OrderLineDTO> getLines() {
		return lines;
	}

	public void setLines(List<OrderLineDTO> lines) {
		this.lines = lines;
	}

}
