/*******************************************************************************
 * Copyright (C) 2015   Valentin Pogrebinsky 
 *
 * mail:pva@isd.com.ua
 * https://github.com/bbones
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * GNU v2 license text in root directory of project
 *******************************************************************************/
/**
 * ReceiptItemDTO.java
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 * Created Apr 21, 2015
 */
package org.proto1.dto;

/**
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 *
 */
public class ReceiptItemDTO extends DTO {

	private static final long serialVersionUID = 5730282568429199781L;
	
	private Long receiptItemId;
	private Long receiptId;
	private Long productId;
	private String productName;
	private Long uomId;
	private String uomName;
	private Double qnty;
	private boolean master;

	public Long getReceiptItemId() {
		return receiptItemId;
	}

	public void setReceiptItemId(Long receiptItemId) {
		this.receiptItemId = receiptItemId;
	}

	public Long getReceiptId() {
		return receiptId;
	}

	public void setReceiptId(Long receiptId) {
		this.receiptId = receiptId;
	}

	public Long getUomId() {
		return uomId;
	}

	public void setUomId(Long uomId) {
		this.uomId = uomId;
	}

	public String getUomName() {
		return uomName;
	}

	public void setUomName(String uomName) {
		this.uomName = uomName;
	}

	public Double getQnty() {
		return qnty;
	}

	public void setQnty(Double qnty) {
		this.qnty = qnty;
	}

	public boolean isMaster() {
		return master;
	}

	public void setMaster(boolean master) {
		this.master = master;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

}
