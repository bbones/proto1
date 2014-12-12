package org.proto1.domain.product;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class ProductName {
	@EmbeddedId
	private ProductNameKey productNameKey;

	private String name;

	public ProductNameKey getProductNameKey() {
		return productNameKey;
	}

	public void setProductNameKey(ProductNameKey productNameKey) {
		this.productNameKey = productNameKey;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
