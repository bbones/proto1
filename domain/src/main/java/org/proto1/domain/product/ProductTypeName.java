package org.proto1.domain.product;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.proto1.domain.Language;

@Entity
public class ProductTypeName implements Serializable {
	private static final long serialVersionUID = -2867768676169590444L;

	@Id
	@ManyToOne
	@JoinColumn(name="PRODUCT_TYPE_ID")
	private ProductType productType;

	@Id
	@ManyToOne
	@JoinColumn(name="LANGUAGE_ID")
	private Language language;
	private String name;

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}


}
