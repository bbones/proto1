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

}
