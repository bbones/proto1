/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/

package org.proto1.domain.order;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class ProductionOrder extends BaseOrder {
	
	private Date productionDate;

	public Date getProductionDate() {
		return productionDate;
	}

	public void setProductionDate(Date productionDate) {
		this.productionDate = productionDate;
	}

}
