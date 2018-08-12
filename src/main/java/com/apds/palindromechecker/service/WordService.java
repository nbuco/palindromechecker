package com.apds.palindromechecker.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.apds.palindromechecker.entity.Words;
import com.apds.palindromechecker.entity.repositories.WordsRepository;
import com.apds.palindromechecker.exceptions.WordException;

@Service
public class WordService {

	@Autowired
	WordsRepository repo;
	
	public boolean isWordExists(String word) {
        return repo.countByWord(word) > 0;
    }
	
	public boolean isPalindrome(String word){
	    String wordMinusBlanks = word.replaceAll("\\s+", "").toLowerCase();
	    return isPalindrome(wordMinusBlanks,0,wordMinusBlanks.length()-1);
	}
	 
	private boolean isPalindrome(String word, int forward, int backward) {
	    if (forward == backward) {
	        return true;
	    }
	    if ((word.charAt(forward)) != (word.charAt(backward))) {
	        return false;
	    }
	    if (forward < backward + 1) {
	        return isPalindrome(word, forward + 1, backward - 1);
	    }
	 
	    
	    return true;
	}

	public void saveWord(String word, boolean isInEnglishDictionary ) throws WordException {
		if (Objects.isNull(word)) {
			throw new WordException("Word could not be saved, the value was either nullWord ");
		}
		
		try {
			repo.save(new Words(word, isInEnglishDictionary));
		}catch(DataAccessException ex) {
			throw new WordException(ex.getMessage(), ex);
		}
	}
	
	public List<String> findAll() throws WordException {
		try {
			 List<Words> wordsList = repo.findAll();
		   	 List<String> words = 	wordsList.stream()
			    						.map(word -> new StringBuilder()
			    								.append("word: ")
			    								.append(word.getWord())
			    								.append(" palindrome: ")
			    								.append(word.getPalindrome())
			    								.append("<p>").toString())
			    						.collect(Collectors.toList());

			return words;
		}catch(DataAccessException ex) {
			throw new WordException(ex.getMessage(), ex);
		}
	}
}