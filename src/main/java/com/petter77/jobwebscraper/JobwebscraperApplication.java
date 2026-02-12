package com.petter77.jobwebscraper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class JobwebscraperApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobwebscraperApplication.class, args);
	}

}
