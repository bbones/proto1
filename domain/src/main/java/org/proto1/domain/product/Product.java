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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.proto1.domain.AbstractEntity;
import org.proto1.domain.Language;

@Entity
@NamedEntityGraph(name = "Product.productNames", attributeNodes = @NamedAttributeNode("productNames"))
public class Product extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 5899772053294304505L;

	@ManyToOne
	@NotNull
	private ProductType productType;
	
	@OneToMany(cascade=CascadeType.PERSIST, mappedBy="product", fetch=FetchType.EAGER)
	private List<ProductName> productNames = new ArrayList<ProductName>();
	
	@OneToMany(cascade=CascadeType.PERSIST, mappedBy="product")
	private List<ProductParameter> productParameters = new ArrayList<ProductParameter>();

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
