package com.apds.palindromechecker.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Words {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="word", unique=true)
	private String word;
	
	@Column(name="palindrome", unique=true)
	private String palindrome;
	
	@Column(name="in_dictionary")
	private boolean isInDictionary;
	
	
	public Words() {
	}
	
	public Words(String word, boolean isInDictionary) {
		this.word = word;
		this.palindrome = new StringBuilder(word).reverse().toString();
		this.isInDictionary = isInDictionary;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getWord() {
		return word;
	}
	public boolean isInDictionary() {
		return isInDictionary;
	}

	public String getPalindrome() {
		return palindrome;
	}
}
