package com.apds.word.web.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.apds.palindromechecker.entity.EnglishDictionary;
import com.apds.palindromechecker.entity.repositories.EnglishDictionaryRepository;
import com.apds.palindromechecker.service.DictionaryService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DictionaryServiceTests {
	
	@TestConfiguration
    static class DictionaryServiceTestContextConfiguration {
  
        @Bean
        public DictionaryService dictionaryService() {
            return new DictionaryService();
        }
    }
	 @Autowired
     private EnglishDictionaryRepository repository	;
	 
	 @Autowired
	 private DictionaryService dictionaryService;
	 
	 @Before
	 public void setUp() {
	     Mockito.when(repository.countByWord("radar"))
	       .thenReturn(1L);
	     Mockito.when(repository.countByWord("radio"))
	       .thenReturn(0L);
	 }
	
	@Test
	public void isWordInEnglishDictionaryPositiveTest() {
		String word = "radar";
		boolean expected = true;
		boolean actual = dictionaryService.isInEnglishDictionary(word);
		assertTrue(expected == actual);
	}
	
	@Test
	public void isWordInEnglishDictionaryNegativeTest() {
		String word = "radio";
		boolean expected = true;
		boolean actual = dictionaryService.isInEnglishDictionary(word);
		assertFalse(expected == actual);
	}
}
