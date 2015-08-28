/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.domain.product;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;

import org.proto1.domain.AbstractEntity;

@Entity
@NamedEntityGraph(name = "ProductType.productTypeNames", attributeNodes = @NamedAttributeNode("productTypeNames"))
public class ProductType extends AbstractEntity {
	@ManyToOne
	private ProductType parentType;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="productType")
	private List<ProductTypeName> productTypeNames = new ArrayList<ProductTypeName>();
	
	@OneToMany(mappedBy="parentType")
	private List<ProductType> childProductTypes = new ArrayList<ProductType>();

	public ProductType getParentType() {
		return parentType;
	}

	public void setParentType(ProductType parentType) {
		this.parentType = parentType;
	}

	public List<ProductTypeName> getProductTypeNames() {
		return productTypeNames;
	}

	public void setProductTypeNames(List<ProductTypeName> productTypeNames) {
		this.productTypeNames = productTypeNames;
	}

	public List<ProductType> getChildProductTypes() {
		return childProductTypes;
	}

	public void setChildProductTypes(List<ProductType> childProductTypes) {
		this.childProductTypes = childProductTypes;
	}
	
}
