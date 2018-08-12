package com.apds.palindromechecker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class WordApplication {
	
    private static final Logger logger = LogManager.getLogger(WordApplication.class);

    public static void main(String[] args) {
		SpringApplication.run(WordApplication.class, args);
	}
}
