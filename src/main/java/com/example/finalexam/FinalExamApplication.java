package com.example.finalexam;

import com.example.finalexam.entities.Customer;
import com.example.finalexam.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
public class FinalExamApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinalExamApplication.class, args);
	}


	@Bean
	CommandLineRunner commandLineRunner(CustomerRepository customerRepository){
		return args -> {
			customerRepository.save(new Customer(null, "Chelsy", "B1", new Date()));
			customerRepository.save(new Customer(null, "John", "A4", new Date()));
			customerRepository.save(new Customer(null, "Macci", "A2", new Date()));
			customerRepository.save(new Customer(null, "Manpreet", "C1",new Date()));
			customerRepository.findAll().forEach(p->{
				System.out.println(p.getName());
			});
		};
	}
}

