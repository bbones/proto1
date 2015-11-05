/*******************************************************************************
 * Copyright (C) 2015  Valentin Pogrebinsky
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
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *******************************************************************************/
package org.proto1.domain.product;

import java.util.ArrayList;
import java.util.List;

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
