package Accountservice.account;

import Accountservice.account.entities.BankAccount;
import Accountservice.account.enums.AccountType;
import Accountservice.account.repository.BankAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
@EnableFeignClients
public class AccountApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountApplication.class, args);
	}


	@Bean
	CommandLineRunner commandLineRunner (BankAccountRepository bankAccountRepository){
		return args -> {

			BankAccount bankAccount = BankAccount.builder()
							.accountId(UUID.randomUUID().toString())
							.currency("MAD")
							.balance(98000)
							.createAt(LocalDate.now())
							.type(AccountType.CURERNT_ACCOUNT)
							.cutomserId(1L)
							.build();

			BankAccount bankAccount2 = BankAccount.builder()
					.accountId(UUID.randomUUID().toString())
					.currency("MAD")
					.balance(12000)
					.createAt(LocalDate.now())
					.type(AccountType.SAVING_ACCOUNT)
					.cutomserId(2L)
					.build();
			bankAccountRepository.save(bankAccount);
			bankAccountRepository.save(bankAccount2);

		};
	}
}
