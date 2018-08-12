package com.apds.palindromechecker.controller;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.apds.palindromechecker.exceptions.DictionaryException;
import com.apds.palindromechecker.exceptions.WordException;
import com.apds.palindromechecker.service.DictionaryService;
import com.apds.palindromechecker.service.WordService;
import com.apds.palindromechecker.validators.WordValidator;

@Controller
@SessionAttributes("word")
public class WordController {
	private static final Logger logger = LogManager.getLogger(WordController.class);
	
	@Autowired
    WordService wordService;
	
	@Autowired
    WordValidator validator;
	
	@Autowired
    DictionaryService dictionaryService;
	
	@Autowired
    WordService wordsService;
	
    @RequestMapping("/")
    public String index() {
        return "index";
    }

	 
	 @RequestMapping(value="/palindrome", method = RequestMethod.POST)
	 public String index(ModelMap model, @RequestParam("word") String word ) {
		 logger.debug(String.format("Validating word: %s ", word));
		 
		 Optional<String> isValid = validator.validate(word);
		 	
	     if (isValid.isPresent()) {
	            model.put("word",word);
	        	model.put("errorMessage", isValid.get());
	            return "index";
	      }

		 logger.debug(String.format("Word: %s was valid", word));
	     
	      boolean inEnglishDictionary = dictionaryService.isInEnglishDictionary(word);
		  boolean isPalindrome = wordService.isPalindrome(word);
		  
		  String dictionaryMessage = String.format("%s is %s the English dictionary", word, inEnglishDictionary ? "in" : "not in");
		  String palindromeMessage = String.format("%s is %s Palindrome", word, isPalindrome ? "a" : "not a");
		  
		  model.put("inDictionaryMessage", dictionaryMessage);
		  model.put("isPalindromeMessage", palindromeMessage);
		  
		  logger.debug(dictionaryMessage);
		  logger.debug(palindromeMessage);
		  
		  model.put("palindrome", new StringBuffer(word).reverse().toString());

	        try {
	  	      if (!wordService.isWordExists(word)) 
	  	    	  wordService.saveWord(word, true);
	  	    	  
	            model.put("errorMessage", String.format("%s was processed successfully  ", word));
	        }catch(WordException ex) {
	            model.put("errorMessage", String.format("%s could not be saved, %s ", word, ex.getMessage()));
	        }
	        
 	        return "index";
	    }
	 
		 @RequestMapping(value="/words", method = RequestMethod.GET)
		 public String findAllWords(ModelMap model ) {
			 logger.debug("Retrieving all words entered");
			 
			 try {
				 List<String> words = wordService.findAll();
				 model.put("words", words);
	        }catch(WordException ex) {
	            model.put("errorMessage", String.format("Unable to retrieve all words. %s ", ex.getMessage()));
	        }
			 
		   return "words";
		 }
		 
		 @RequestMapping(value="/dictionary", method = RequestMethod.POST)
		 public String addtoDictionary(ModelMap model, @RequestParam("dictWord") String dictWord ) {
	     		
			 	Optional<String> isValid = validator.validate(dictWord);
			 	
		        if (isValid.isPresent()) {
		            model.put("dictWord",dictWord);
		        	model.put("dictErrorMessage", isValid.get());
		            return "index";
		        }

		      boolean inEnglishDictionary = dictionaryService.isInEnglishDictionary(dictWord);
		      
		      if (!inEnglishDictionary) {
			        try {
			        	dictionaryService.addToDictionary(dictWord);
			            model.put("dictErrorMessage", String.format("%s was successfully to dictionary  ", dictWord));
			        }catch(DictionaryException ex) {
			            model.put("dictErrorMessage", String.format("%s could not be saved, %s to the dictionary ", dictWord, ex.getMessage()));
			        }
		      }	
		      else
		            model.put("dictErrorMessage", String.format("%s already exists in the dictionary  ", dictWord));
		    	  
	 	        return "index";
		    }
		 
			 @RequestMapping(value="/dictionary/words", method = RequestMethod.GET)
			 public String findAllDictionaryWords(ModelMap model ) {
				 try {
					 List<String> words = dictionaryService.findAll();
					 model.put("dictWords", words);
		        }catch(DictionaryException ex) {
		            model.put("errorMessage", String.format("Unable to retrieve all words from dictionary. %s ", ex.getMessage()));
		        }
				 
			   return "dictionarywords";
			 }
}
