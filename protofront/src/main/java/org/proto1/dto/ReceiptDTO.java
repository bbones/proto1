/**
 * ReceiptDTO.java
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 * Created Apr 21, 2015
 */
package org.proto1.dto;

/**
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 *
 */
public class ReceiptDTO extends DTO {

	private static final long serialVersionUID = 6214696973872384845L;
	
	private Long receiptId;
	private Long productId;
	private String productName;
	private Long uomId;
	private String uomName;
	private boolean byDefault;

	public Long getReceiptId() {
		return receiptId;
	}

	public void setReceiptId(Long receiptId) {
		this.receiptId = receiptId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getUomId() {
		return uomId;
	}

	public void setUomId(Long uomId) {
		this.uomId = uomId;
	}

	public boolean isByDefault() {
		return byDefault;
	}

	public void setByDefault(boolean byDefault) {
		this.byDefault = byDefault;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getUomName() {
		return uomName;
	}

	public void setUomName(String uomName) {
		this.uomName = uomName;
	}

}
