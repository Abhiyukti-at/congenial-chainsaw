package io.nikita.BankApp.repository;

import io.nikita.BankApp.Models.Accounts;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsRepository  extends CrudRepository<Accounts,Long> {
    Accounts findByCustomerId(Long customerId);

}