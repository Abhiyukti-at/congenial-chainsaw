package io.nikita.BankApp.repository;

import io.nikita.BankApp.Models.ContactMessages;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactMessagesRepository extends CrudRepository<ContactMessages,String> {
}