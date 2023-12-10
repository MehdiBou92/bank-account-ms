package Customerservice.App;

import Customerservice.App.models.Customer;
import Customerservice.App.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CustomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner (CustomerRepository customerRepository) {
		return  args ->{

			List<Customer> customers = List.of(
			Customer.builder()
					.firstName("Mehdi")
					.lastName("Bouafifssa")
					.email("mehdi.bouafifssa@gmail.com")
					.build(),

			Customer.builder()
					.firstName("Ino")
					.lastName("Bobocha")
					.email("Ino.Bobocha@gmail.com")
					.build(),

			Customer.builder()
							.firstName("TestList")
							.lastName("Boubouchia")
							.email("Ino.Bobocha@gmail.com")
							.build()
			);
			customerRepository.saveAll(customers);
	};
	}
}
