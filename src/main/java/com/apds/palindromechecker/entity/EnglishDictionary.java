package com.apds.palindromechecker.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class EnglishDictionary {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="word", unique=true)
	private String word;
	

	public EnglishDictionary() {
	}

	public EnglishDictionary(String word) {
		this.word = word;
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
	public void setWord(String word) {
		this.word = word;
	}
}
