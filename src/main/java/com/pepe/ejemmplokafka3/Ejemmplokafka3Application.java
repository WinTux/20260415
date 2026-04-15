package com.pepe.ejemmplokafka3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafkaStreams;

@SpringBootApplication
@EnableKafkaStreams
public class Ejemmplokafka3Application {

	public static void main(String[] args) {
		SpringApplication.run(Ejemmplokafka3Application.class, args);
	}

}
