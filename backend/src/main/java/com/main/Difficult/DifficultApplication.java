package com.main.Difficult;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@SpringBootApplication(scanBasePackages = "com.main.Difficult")
@ComponentScan(basePackages = {"com.main.Difficult"})
@EnableJpaRepositories(basePackages = {"com.main.Difficult.Repositories.mysql"})
@EnableNeo4jRepositories(basePackages = {"com.main.Difficult.Repositories.neo4j"})
@EnableMongoRepositories(basePackages = {"com.main.Difficult.repositories.mongo"})
public class DifficultApplication {
	public static void main(String[] args) {
		SpringApplication.run(DifficultApplication.class, args);
	}
}
