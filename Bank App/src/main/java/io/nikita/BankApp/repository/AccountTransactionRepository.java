package io.nikita.BankApp.repository;

import io.nikita.BankApp.Models.AccountTransactions;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountTransactionRepository extends CrudRepository<AccountTransactions, String> {
List<AccountTransactions> findByCustomerIdOrderByTransactionDtDesc(long customerId);
}