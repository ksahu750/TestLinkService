package com.rpa.TestLinkService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.rpa.TestLinkService", "com.engati.rpa.messaging"})

@SpringBootApplication
public class TestLinkServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestLinkServiceApplication.class, args);
	}

}
