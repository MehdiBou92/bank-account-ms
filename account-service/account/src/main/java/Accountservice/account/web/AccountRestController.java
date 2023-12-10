package Accountservice.account.web;

import Accountservice.account.client.CustomerRestClient;
import Accountservice.account.entities.BankAccount;
import Accountservice.account.models.Customer;
import Accountservice.account.repository.BankAccountRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class AccountRestController {

    private final BankAccountRepository bankAccountRepository;
    private final CustomerRestClient customerRestClient;
    public AccountRestController(BankAccountRepository bankAccountRepository, CustomerRestClient customerRestClient) {
        this.bankAccountRepository = bankAccountRepository;
        this.customerRestClient = customerRestClient;
    }

    @GetMapping("/accounts")
    public List<BankAccount> accountList(){
        List<BankAccount> allAccounts = bankAccountRepository.findAll();
        List<Customer> customers = customerRestClient.allCustomers();
        for(BankAccount account : allAccounts){
            customers.forEach(customer -> {
                if(customer.getId().equals(account.getCutomserId())){
                    account.setCustomer(customer);
                }
            });
        }
        return ResponseEntity.ok(allAccounts).getBody();
    }

    @GetMapping("/accounts/{id}")
    public ResponseEntity<BankAccount> bankAccountById(@PathVariable String id){
        BankAccount bankAccount = bankAccountRepository.findById(id).get();
        Customer customer = customerRestClient.findCustomerById(bankAccount.getCutomserId());
        bankAccount.setCustomer(customer);
        return ResponseEntity.ok(bankAccount);
    }
}
