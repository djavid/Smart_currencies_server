package com.djavid.br_server;

import com.djavid.br_server.tasks.ScheduledTasks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
@EnableScheduling
public class BrServerApplication {

	public static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

	public static void main(String[] args) {
		SpringApplication.run(BrServerApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

}
