package com.apds.palindromechecker.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.apds.palindromechecker.entity.EnglishDictionary;
import com.apds.palindromechecker.entity.repositories.EnglishDictionaryRepository;
import com.apds.palindromechecker.exceptions.DictionaryException;

@Service
public class DictionaryService {
	
	@Autowired
	EnglishDictionaryRepository repo;
	
	public boolean isInEnglishDictionary(String word) {
        return repo.countByWord(word) > 0;
    }
	
	public void addToDictionary(String word) throws DictionaryException{
		try {
			repo.save(new EnglishDictionary(word));
		}catch(DataAccessException ex) {
			throw new DictionaryException(ex.getMessage(), ex);
		}
	}
	
	public List<String> findAll() throws DictionaryException {
		try {
			 List<EnglishDictionary> wordsList = repo.findAll();
		   	 List<String> words = 	wordsList.stream()
			    						.map(word -> new StringBuilder()
			    								.append("word: ")
			    								.append(word.getWord())
			    								.append("<p>").toString())
			    						.collect(Collectors.toList());

			return words;
		}catch(DataAccessException ex) {
			throw new DictionaryException(ex.getMessage(), ex);
		}
	}
}
