package org.proto1.domain.product;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.proto1.domain.AbstractEntity;
import org.proto1.domain.Language;

@Entity
public class ProductType extends AbstractEntity {
	@ManyToOne
	private ProductType parentType;
	
	@ElementCollection
	@CollectionTable(name="PRODUCT_TYPE_NAME")
	private Map<Language, String> productTypeNames = new HashMap<Language, String>();

	public ProductType getParentType() {
		return parentType;
	}

	public void setParentType(ProductType parentType) {
		this.parentType = parentType;
	}

	public Map<Language, String> getProductTypeNames() {
		return productTypeNames;
	}

	public void setProductTypeNames(Map<Language, String> productTypeNames) {
		this.productTypeNames = productTypeNames;
	}
	
	
}
