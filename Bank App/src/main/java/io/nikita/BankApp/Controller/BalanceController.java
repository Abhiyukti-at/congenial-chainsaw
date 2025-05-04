package io.nikita.BankApp.Controller;

import io.nikita.BankApp.Models.AccountTransactions;
import io.nikita.BankApp.Models.Customer;
import io.nikita.BankApp.repository.AccountTransactionRepository;
import io.nikita.BankApp.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class BalanceController {
    private final AccountTransactionRepository transactionRepository;
    private final CustomerRepository customerRepository;

    @GetMapping("/myBalance")
    public List<AccountTransactions> getBalanceDetails(@RequestParam String email) {
        Optional<Customer> customerOptional = customerRepository.findByEmail(email);
        if (customerOptional.isPresent()) {
            List<AccountTransactions> accountTransactions = transactionRepository.findByCustomerIdOrderByTransactionDtDesc(customerOptional.get().getCustomerId());
            if (accountTransactions != null) {
                return accountTransactions;
            } else {
                return null;
            }
        } else return null;
    }

}