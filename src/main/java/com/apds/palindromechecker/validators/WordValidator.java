package com.apds.palindromechecker.validators;

import java.util.Optional;

import org.springframework.stereotype.Component;

@Component("wordValidator")
public class WordValidator {
 	private static final String nonAlphaPattern = "^.*[^a-zA-Z].*$";
 	private static final String containsWhiteSpacePattern = "^\\s*$";
	
	public Optional<String> validate(String word) {
	 	// Check for empty word
	    if (word.isEmpty()) 
	        return Optional.of(String.format("Please enter a word"));
	 	
	 	if (word.matches(nonAlphaPattern) || word.matches(containsWhiteSpacePattern)) 
	 		return Optional.of(String.format("The word must contain letters only and no blanks"));
	 	
	 	return Optional.empty();
    }
}
