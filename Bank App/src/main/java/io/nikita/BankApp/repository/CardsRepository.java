package io.nikita.BankApp.repository;

import io.nikita.BankApp.Models.Cards;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardsRepository extends CrudRepository<Cards,Long> {
   List<Cards> findByCustomerId(long customerId);
}