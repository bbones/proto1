package org.proto1.domain.utility;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.proto1.domain.Language;

@Entity
public class LocalizedStringConstant implements Serializable {
	@Id
	private String key;
	@Id
	@ManyToOne
	@JoinColumn(name="language_id")
	private Language language;
	
	private String text;

	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public Language getLanguage() {
		return language;
	}
	public void setLanguage(Language language) {
		this.language = language;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

}
