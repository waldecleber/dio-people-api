package br.com.waldecleber.people;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableAutoConfiguration
@SpringBootApplication(scanBasePackages = { " br.com.waldecleber.people", " br.com.waldecleber.people.controller" })
@EntityScan(basePackages = "br.com.waldecleber.people.entity")
@EnableJpaRepositories(basePackages={"br.com.waldecleber.people.repository"})
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}
