package io.nikita.BankApp.Controller;

import io.nikita.BankApp.Models.Customer;
import io.nikita.BankApp.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final CustomerRepository customerRepository;



    @RequestMapping("/user")
    public Customer getLoggedInUserDetails(Authentication authentication) {
        Optional<Customer> customer = customerRepository.findByEmail(authentication.getName());
        return customer.orElse(null);
    }

}