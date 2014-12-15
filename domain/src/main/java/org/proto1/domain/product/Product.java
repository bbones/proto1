package org.proto1.domain.product;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Version;

import org.proto1.domain.Identified;
import org.proto1.domain.Language;

@Entity
public class Product implements Identified {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="PRODUCT_ID")
	private Long id;

	@ElementCollection
	@MapKey(name="language")
	@Column(name="name")
	private Map<Language, String> productNames = new HashMap<Language, String>();
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName(Language language) {
		return productNames.get(language);
	}

	public void setName(Language language, String name) {
		productNames.put(language, name);
	}

	@Version
	@Column(name="VERSION")
	private Integer version;

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Map<Language, String> getProductNames() {
		return productNames;
	}

	public void setProductNames(Map<Language, String> productNames) {
		this.productNames = productNames;
	}
	
}
