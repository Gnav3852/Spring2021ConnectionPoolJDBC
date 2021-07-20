package com.example.demo.student;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {
	
	@Bean
	CommandLineRunner commandLineRunner(StudentRepository repository) {
		return args -> {
			Student das = new Student(
					1L,"das","fds",LocalDate.of(1999, 2, 5));
			Student da2s = new Student(
					"d3as","f3ds",LocalDate.of(2000, 5, 5));
			Student da4s = new Student(
					"d3as","f54ds",LocalDate.of(2000, 1, 5));
			Student da6s = new Student(
					"da52s","f4ds",LocalDate.of(2000, 5, 5));
			
			repository.saveAll(
					List.of(das,da2s)
					);

		};
		
	}
}
