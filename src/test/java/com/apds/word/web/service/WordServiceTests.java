package com.apds.word.web.service;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.apds.palindromechecker.service.WordService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WordServiceTests {
	
	@TestConfiguration
    static class EmployeeServiceTestContextConfiguration {
  
        @Bean
        public WordService wordService() {
            return new WordService();
        }
    }
	
	 @Autowired
	 private WordService wordService;
	
	@Test
	public void isPalindromePositiveTest() {
		String word = "civic";
		boolean expected = true;
		boolean actual = wordService.isPalindrome(word);
		assertTrue(expected == actual);
	}
	
	@Test
	public void isPalindromeNegativeTest() {
		String word = "towel";
		boolean expected = true;
		boolean actual = wordService.isPalindrome(word);
		assertTrue(expected == actual);
	}
}
