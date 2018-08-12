package com.apds.word.entity.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.apds.palindromechecker.entity.Words;
import com.apds.palindromechecker.entity.repositories.WordsRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class WordRepositoryTests {
	@Autowired
	private TestEntityManager entityManager;
	 
	@Autowired
	private WordsRepository wordsRepository;
	
	@Test
	public void allWords() {
		boolean isNotInDictionary = false;
		boolean isInDictionary = true;
		
		// given
	    Words firstWord = new Words("somkthing", isNotInDictionary);
	    Words secondWord = new Words("something", isInDictionary);
	    entityManager.persist(firstWord);
	    entityManager.persist(secondWord);
	    entityManager.flush();
	    
	    // when
	    List<Words> words = wordsRepository.findAll();
	    assertEquals(2, words.size());
	}
	
}
