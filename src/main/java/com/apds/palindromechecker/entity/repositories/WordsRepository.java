package com.apds.palindromechecker.entity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apds.palindromechecker.entity.Words;

@Repository
public interface WordsRepository extends JpaRepository<Words, Long>  {
	 Long countByWord(String word);
}
