package io.nikita.BankApp.Controller;

import io.nikita.BankApp.Models.Loans;
import io.nikita.BankApp.repository.LoansRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LoanController {
    private final LoansRepository loanRepository;

    @GetMapping("/myLoan")
    @PostAuthorize("hasRole('USER')")
    public List<Loans> getLoanDetails(@RequestParam long id) {
        List<Loans> loans = loanRepository.findByCustomerIdOrderByStartDtDesc(id);
        if (loans != null) {
            return loans;
        } else {
            return null;
        }
    }

}