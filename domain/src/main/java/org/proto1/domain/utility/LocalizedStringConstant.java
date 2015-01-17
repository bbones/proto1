package org.proto1.domain.utility;

import javax.persistence.Id;

import org.proto1.domain.Language;

public class LocalizedStringConstant {
	@Id
	private String key;
	@Id
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
