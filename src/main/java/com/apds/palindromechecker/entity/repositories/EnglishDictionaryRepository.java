package com.apds.palindromechecker.entity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apds.palindromechecker.entity.EnglishDictionary;

@Repository
public interface EnglishDictionaryRepository  extends JpaRepository<EnglishDictionary, Long>{
	 Long countByWord(String word);
}
