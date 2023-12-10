package Customerservice.App.web;

import Customerservice.App.models.Customer;
import Customerservice.App.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class CustomerRestController {
    private final CustomerRepository customerRepository;


    @GetMapping("/customers")
    public List<Customer> customerList(){
        return customerRepository.findAll();
    }

    @GetMapping("/customers/{id}")
    public Customer customerById (@PathVariable Long id){
        return customerRepository.findById(id).get();
    }
}
