package org.proto1.domain.product;

import javax.persistence.Embeddable;

import org.proto1.domain.Language;

@Embeddable
public class ProductNameKey {
	private Product product;
	private Language language;
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Language getLanguage() {
		return language;
	}
	public void setLanguage(Language language) {
		this.language = language;
	}
}
