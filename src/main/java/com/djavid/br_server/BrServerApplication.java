package com.djavid.br_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class BrServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BrServerApplication.class, args);
	}

}
