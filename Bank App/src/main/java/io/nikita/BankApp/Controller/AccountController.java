package io.nikita.BankApp.Controller;

import io.nikita.BankApp.Models.Accounts;
import io.nikita.BankApp.Models.Customer;
import io.nikita.BankApp.repository.AccountsRepository;
import io.nikita.BankApp.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class AccountController {
    private final AccountsRepository accountsRepository;
    final private CustomerRepository customerRepository;

    @GetMapping("/myAccount")
    public Accounts getAccountDetails(@RequestParam String email) {
        Optional<Customer> customerOptional = customerRepository.findByEmail(email);
        if(customerOptional.isPresent()) {
            Accounts accounts = accountsRepository.findByCustomerId(customerOptional.get().getCustomerId());
            if (accounts != null) {
                return accounts;
            } else {
                return null;
            }
        }else  return null;

    }

}