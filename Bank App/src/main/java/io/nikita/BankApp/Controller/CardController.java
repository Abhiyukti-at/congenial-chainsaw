package io.nikita.BankApp.Controller;

import io.nikita.BankApp.Models.Cards;
import io.nikita.BankApp.Models.Customer;
import io.nikita.BankApp.repository.CardsRepository;
import io.nikita.BankApp.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class CardController {
    private final CardsRepository cardsRepository;
    private final CustomerRepository customerRepository;

    @GetMapping("/myCards")
    public List<Cards> getCardsDetails(@RequestParam String email) {
        Optional<Customer> customerOptional = customerRepository.findByEmail(email);
        if(customerOptional.isPresent()) {
            List<Cards> cards = cardsRepository.findByCustomerId(customerOptional.get().getCustomerId());
            if (cards != null) {
                return cards;
            } else {
                return null;
            }
        }else return null;
    }
}