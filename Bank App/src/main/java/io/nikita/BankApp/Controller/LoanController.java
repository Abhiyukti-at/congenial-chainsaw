package io.nikita.BankApp.Controller;

import io.nikita.BankApp.Models.Customer;
import io.nikita.BankApp.Models.Loans;
import io.nikita.BankApp.repository.CustomerRepository;
import io.nikita.BankApp.repository.LoansRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class LoanController {
    private final LoansRepository loanRepository;
    private final CustomerRepository customerRepository;

    @GetMapping("/myLoan")
    @PostAuthorize("hasRole('USER')")
    public List<Loans> getLoanDetails(@RequestParam String email) {
        Optional<Customer> customerOptional = customerRepository.findByEmail(email);
        if(customerOptional.isPresent()) {
            List<Loans> loans = loanRepository.findByCustomerIdOrderByStartDtDesc(customerOptional.get().getCustomerId());
            if (loans != null) {
                return loans;
            } else {
                return null;
            }
        }else return null;
    }

}