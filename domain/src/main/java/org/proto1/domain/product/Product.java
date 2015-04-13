/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.domain.product;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;

import org.proto1.domain.AbstractEntity;
import org.proto1.domain.Language;

@Entity
@NamedEntityGraph(name = "Product.productNames", attributeNodes = @NamedAttributeNode("productNames"))
public class Product extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 5899772053294304505L;

	@ManyToOne
	private ProductType productType;
	
	@OneToMany(cascade=CascadeType.PERSIST, mappedBy="product", fetch=FetchType.EAGER)
	private List<ProductName> productNames;
	
	@OneToMany(cascade=CascadeType.PERSIST, mappedBy="product")
	private List<ProductParameter> productParameters;

	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

	public List<ProductName> getProductNames() {
		return productNames;
	}
	
	public ProductName getTranslation(Language language) {
		for (ProductName name: productNames) {
			if (name.getLanguage().equals(language)) {
				return name;
			}
		}
		return null;
	}

	public void setProductNames(List<ProductName> productNames) {
		this.productNames = productNames;
	}

	
	
	public List<ProductParameter> getProductParameters() {
		return productParameters;
	}

	public void setProductParameters(List<ProductParameter> productParameters) {
		this.productParameters = productParameters;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((productNames == null) ? 0 : productNames.hashCode());
		result = prime * result
				+ ((productType == null) ? 0 : productType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (productNames == null) {
			if (other.productNames != null)
				return false;
		} else if (!productNames.equals(other.productNames))
			return false;
		if (productType == null) {
			if (other.productType != null)
				return false;
		} else if (!productType.equals(other.productType))
			return false;
		return true;
	}
	
	
}
